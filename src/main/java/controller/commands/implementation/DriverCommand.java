package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Assignment;
import model.entity.User;
import model.service.DriverMainPageService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class DriverCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        try {
            DriverMainPageService service = new DriverMainPageService();
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getId();

            if (request.getParameter("id") != null) {
                int assignmentId = Integer.valueOf(request.getParameter("id"));
                handleDriverAccept(assignmentId, service, userId);
            }
            List<Assignment> assignments = service.getAssignmentsForDriverByStatus(userId, Assignment.Status.assigned);
            request.setAttribute("assignmentsAssignedList", assignments);

            List<Assignment> applied = service.getAssignmentsForDriverByStatus(userId, Assignment.Status.applyied);
            request.setAttribute("assignmentsAppliedList", applied);



        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/WEB-INF/driver/driver.jsp";
    }

    private void handleDriverAccept(int assignmentId, DriverMainPageService service, int driverId) throws SQLException, ClassNotFoundException {
        System.out.println("Driver: " + driverId + ", applied assignment: " + assignmentId);
        List<Assignment> assignmentsForDriverByStatus = service.getAssignmentsForDriverByStatus(driverId, Assignment.Status.assigned);
        Assignment assignmentToUpdate = searchInListById(assignmentsForDriverByStatus, assignmentId);
        assignmentToUpdate.setStatus(Assignment.Status.applyied);
        service.updateAssignment(assignmentToUpdate, driverId);
    }

    private Assignment searchInListById(List<Assignment> assignments, int id) {
        for (Assignment a : assignments) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }
}
package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Assignment;
import model.entity.User;
import model.service.DriverMainPageService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class DriverCommand implements Command {
    /**
     * @param request request from client
     * @return - driver page without redirect if form was`n used
     *         - driver page with redirect in order to reset request
     *         to prevent form resending(Some implementation of 'Post/Redirect/Get' pattern )
     */
    @Override
    public String execute(HttpServletRequest request) {
        try {
            DriverMainPageService service = new DriverMainPageService();
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getId();

            if (request.getParameter("id") != null) {
                int assignmentId = Integer.valueOf(request.getParameter("id"));
                handleDriverAccept(assignmentId, service, userId);
                return "redirect: /park/driver";
            }
            List<Assignment> assignments = service.getAssignmentsForDriverByStatus(userId, Assignment.Status.assigned);
            request.setAttribute("assignmentsAssignedList", assignments);
//TODO: Попробовать возвращать Optional
            List<Assignment> applied = service.getAssignmentsForDriverByStatus(userId, Assignment.Status.applyied);
            request.setAttribute("assignmentsAppliedList", applied);

        } catch (NullPointerException e) {
            System.out.println("bad");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/WEB-INF/driver/driver.jsp";
    }

    private void handleDriverAccept(int assignmentId, DriverMainPageService service, int driverId) throws SQLException, ClassNotFoundException {
        System.out.println("Driver: " + driverId + ", applied assignment: " + assignmentId);
        List<Assignment> assignmentsForDriverByStatus = service.getAssignmentsForDriverByStatus(driverId, Assignment.Status.assigned);
        try {

            Assignment assignmentToUpdate = searchInListById(assignmentsForDriverByStatus, assignmentId);
            assignmentToUpdate.setStatus(Assignment.Status.applyied);
            service.updateAssignment(assignmentToUpdate);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private Assignment searchInListById(List<Assignment> assignments, int id) {
        return assignments.stream().filter(a -> a.getId() == id).findFirst().get();
    }
}
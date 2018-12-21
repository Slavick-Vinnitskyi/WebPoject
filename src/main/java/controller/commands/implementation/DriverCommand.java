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
     * - driver page with redirect in order to reset request
     * to prevent form resending(Some implementation of 'Post/Redirect/Get' pattern )
     */
    @Override
    public String execute(HttpServletRequest request) {
        try {
            DriverMainPageService service = new DriverMainPageService();
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getId();

            List<Assignment> assignedList = service.getAssignmentsForDriverByStatus(userId, Assignment.Status.assigned);
            request.setAttribute("assignmentsAssignedList", assignedList);
//TODO: Попробовать возвращать Optional
            List<Assignment> applied = service.getAssignmentsForDriverByStatus(userId, Assignment.Status.applied);
            request.setAttribute("assignmentsAppliedList", applied);

            if (request.getParameter("id") != null) {
                int assignmentId = Integer.valueOf(request.getParameter("id"));
                processDriverAccept(assignmentId, assignedList, service, userId);
                return "redirect: /park/driver";
            }
            else if (request.getParameter("refused_id") != null) {
                int assignmentId = Integer.valueOf(request.getParameter("refused_id"));
                processDriverRefuse(assignmentId, service, userId);
                return "redirect: /park/driver";
            }

        } catch (NullPointerException e) {
            System.out.println("bad");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/WEB-INF/driver/driver.jsp";
    }

    private void processDriverAccept(int assignmentId, List<Assignment> assignedList, DriverMainPageService service, int driverId) throws SQLException, ClassNotFoundException {
        System.out.println("Driver: " + driverId + ", applied assignment: " + assignmentId);
//        List<Assignment> assignedList = service.getAssignmentsForDriverByStatus(driverId, Assignment.Status.assigned);
        try {
            Assignment assignmentToApply = searchInListById(assignedList, assignmentId);
            assignmentToApply.setStatus(Assignment.Status.applied);
            service.updateAssignment(assignmentToApply);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void processDriverRefuse(int assignmentId, DriverMainPageService service, int driverId) throws SQLException, ClassNotFoundException {
        List<Assignment> appliedList = service.getAssignmentsForDriverByStatus(driverId, Assignment.Status.applied);
        try {
            Assignment assignmentToRefuse = searchInListById(appliedList, assignmentId);
            assignmentToRefuse.setStatus(Assignment.Status.assigned);
            service.updateAssignment(assignmentToRefuse);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private Assignment searchInListById(List<Assignment> assignments, int id) {
        return assignments.stream().filter(a -> a.getId() == id).findAny().get();
    }
}
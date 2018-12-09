package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Assignment;
import model.entity.User;
import model.service.DriverMainPageService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DriverCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        try {
            DriverMainPageService service = new DriverMainPageService();
            User user = (User) request.getSession().getAttribute("user");
            int id = user.getId();

            List<Assignment> assignments = service.getAssignmentsForDriverByStatus(id, Assignment.Status.assigned);
            request.setAttribute("assignmentsAssignedList", assignments);

//            if(!request.getParameter("id").equals("") && request.getParameter("id") != null ) {
//                int assignmentId = Integer.valueOf(request.getParameter("id"));
//                System.out.println("asssssssssss  : " + assignmentId);
//                Assignment assignmentToUpdate =
//                        searchInListById(service.getAssignmentsForDriverByStatus(assignmentId,
//                        Assignment.Status.assigned),assignmentId);
//                assignmentToUpdate.setStatus(Assignment.Status.applyied);
//                service.updateAssignment(assignmentToUpdate);
//            }

            List<Assignment> applied = service.getAssignmentsForDriverByStatus(id, Assignment.Status.applyied);
            request.setAttribute("assignmentsAppliedList", applied);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/WEB-INF/driver/driver.jsp";
    }

    private Assignment searchInListById(List<Assignment> assignments, int id) {
        return (Assignment) assignments.stream().filter(x ->x.getId() == id).toArray()[0];
    }
}
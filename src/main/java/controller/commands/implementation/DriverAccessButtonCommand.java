package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Assignment;
import model.service.DriverMainPageService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DriverAccessButtonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        DriverMainPageService service = new DriverMainPageService();
        int assignmentId = Integer.valueOf(request.getParameter("id"));
        Assignment assignment = service.getAssignmentById(assignmentId);
        processDriverAccept(assignment, service);
        return "redirect: /park/driver";

    }


    private void processDriverAccept(Assignment assignmentToApply, DriverMainPageService service) throws SQLException, ClassNotFoundException {
        try {
            assignmentToApply.setStatus(Assignment.Status.applied);
            service.updateAssignment(assignmentToApply);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}

package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Assignment;
import model.service.DriverMainPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DriverRefuseButtonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DriverMainPageService service = new DriverMainPageService();
        int assignmentId = Integer.valueOf(request.getParameter("refused_id"));
        Assignment assignment = service.getAssignmentById(assignmentId);
        processDriverRefuse(assignment, service);
        return "redirect: /park/driver";
    }

    private void processDriverRefuse(Assignment assignmentToRefuse, DriverMainPageService service) throws SQLException, ClassNotFoundException {
        try {
            assignmentToRefuse.setStatus(Assignment.Status.assigned);
            service.updateAssignment(assignmentToRefuse);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}

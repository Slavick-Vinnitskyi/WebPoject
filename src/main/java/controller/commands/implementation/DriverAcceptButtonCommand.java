package controller.commands.implementation;

import controller.commands.Command;
import model.service.DriverMainPageService;
import org.apache.log4j.Logger;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

public class DriverAcceptButtonCommand implements Command {
    DriverMainPageService service = new DriverMainPageService();
    private static final Logger log = Logger.getLogger(DriverAcceptButtonCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int assignmentId = Integer.valueOf(request.getParameter("id"));
        processDriverAccept(request, assignmentId, service);
        return "redirect: /park/driver";
    }


    private void processDriverAccept(HttpServletRequest request, int assignmentId, DriverMainPageService service) {
        try {
            service.updateAssignment(assignmentId);
            log.info("Driver accept assignment");
        } catch (RuntimeException e) {
            request.setAttribute("error", ResourceBundle.getBundle("errors", ThreadLocalWrapper.getLocale()).getString(e.getMessage()));
            request.getSession().setAttribute("error", ResourceBundle.getBundle("errors", ThreadLocalWrapper.getLocale()).getString(e.getMessage()));
            log.error(e);
        }
    }
}

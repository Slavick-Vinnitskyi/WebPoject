package controller.commands.implementation;

import controller.commands.Command;
import model.exception.IllegalDeleteException;
import model.service.AdminMainPageService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

public class AdminRefuseButtonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdminMainPageService service = new AdminMainPageService();

        int assignmentId = Integer.parseInt(request.getParameter("refused_id"));
        try{
        if (!service.deleteAssignment(assignmentId)) {
            request.getSession().setAttribute("error", "Something went wrong");
        }
        }catch (IllegalDeleteException e){
            request.setAttribute("error", ResourceBundle.getBundle("errors",ThreadLocalWrapper.getLocale()).getString(e.getMessage()));
            request.getSession()
                    .setAttribute("error"
                            , ResourceBundle.getBundle("errors",ThreadLocalWrapper.getLocale()).getString(e.getMessage()));
        }
        return "redirect: /park/admin";
    }
}

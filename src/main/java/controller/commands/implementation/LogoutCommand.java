package controller.commands.implementation;

import controller.commands.Command;
import controller.commands.utils.LogoutUtil;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
/*        String name = ServletUtility.getUserName(request.getSession());
        ServletUtility.logOut(request, name);
        ServletUtility.setUserRole(request, User.ROLE.UNDEFINED, "Guest");*/
        LogoutUtil.makeLogout(request);

        return "redirect: /index";
    }
}

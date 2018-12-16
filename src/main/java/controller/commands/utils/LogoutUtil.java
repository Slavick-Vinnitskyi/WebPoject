package controller.commands.utils;

import model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LogoutUtil {

    public static void makeLogout(HttpServletRequest request) {
        String name = ServletUtility.getUserName(request.getSession());
        ServletUtility.logOut(request, name);
        ServletUtility.saveUserDataToSession(request, User.ROLE.guest, "guest",0);
    }
}

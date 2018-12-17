package controller.commands.utils;


import javax.servlet.http.HttpServletRequest;

public class LogoutUtil {

    public static void makeLogout(HttpServletRequest request) {
        ServletUtility.logOut(request.getSession());
    }
}

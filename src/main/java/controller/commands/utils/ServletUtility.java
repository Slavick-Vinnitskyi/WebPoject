package controller.commands.utils;

import controller.Servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


public class ServletUtility {
    private static ServletContext context = Servlet.getContext();

    /** Overloaded method
     * @param session needs to get the user from
     * @return user id if those exist of 0 if not
     */
    private static Integer getUserId(HttpSession session) {
        return (int) Optional.ofNullable(session.getAttribute("userId")).orElse(0);
    }

    public static void logOut(HttpSession session) {
        Map<Integer, HttpSession> loggedUsers = getLoggedUsers();
        loggedUsers.remove(getUserId(session));
        setLoggedUsers(loggedUsers);
        session.removeAttribute("userId");
    }

    public static void setLoggedUsers(Map<Integer, HttpSession> loggedUsers) {
        context.setAttribute("loggedUsers", loggedUsers);
    }

    public static Map<Integer, HttpSession> getLoggedUsers() {

        return (ConcurrentHashMap<Integer, HttpSession>) context.getAttribute("loggedUsers");
    }
}
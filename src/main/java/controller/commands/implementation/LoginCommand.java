package controller.commands.implementation;

import controller.commands.utils.SecurityUtil;
import controller.commands.utils.ServletUtility;
import controller.commands.Command;
import model.entity.User;
import model.entity.dao.implementation.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
//TODO: remove unnecessary methods
//TODO: move login/logout methods to Security command
//TODO: make userDTO instead of user object
public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        forceLogout(request);
//        makeLogout(request);
//
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
//
//        try {
//            User authorized = checkLoginAndPassword(name, pass, request);
//            User.ROLE role = authorized.getRole();
//            ServletUtility.logIn(request, name);
//            ServletUtility.saveUserDataToSession(request, role, name, authorized.getId());
//            ServletUtility.setUser(request, name);
//
//            return getRedirectPath(role);
//        } catch (UserNotFoundException e) {
//            return informAboutWrongInput(request);
//        }
        try {
            User user = checkLoginAndPassword(name, pass, request);
            logIn(request, user);
            return getRedirectPath(user.getRole());
        }catch (UserNotFoundException | NullPointerException e) {
            return informAboutWrongInput(request);
        }
    }

    private void logIn(HttpServletRequest request, User user) {
        Map<Integer, HttpSession> loggedUsers = ServletUtility.getLoggedUsers();
        int userId = user.getId();
        if(loggedUsers.containsKey(userId)) {
            loggedUsers.get(userId).invalidate();
        }
        loggedUsers.put(userId, request.getSession());
        ServletUtility.setLoggedUsers(loggedUsers);
        sessionSetup(request, user);
    }

    private void sessionSetup(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole());
        session.setAttribute("firstName", user.getFirstName());
        session.setAttribute("secondName", user.getSecondName());
        session.setAttribute("user", user);
    }

    private void forceLogout(HttpServletRequest request) {
        if(ServletUtility.getUserId(request) != 0 ) {
            ServletUtility.logOut(request.getSession());
        }
    }

/*    private void makeLogout(HttpServletRequest request) {
        String name = ServletUtility.getUserName(request.getSession());
        ServletUtility.logOut(request, name);
        ServletUtility.saveUserDataToSession(request, User.ROLE.guest, "guest",0);

    }*/

    private User authorization(String name, String pass, HttpServletRequest request) throws UserNotFoundException {

        try {
            return new SecurityUtil().validateUser(name, pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
/*    private void logOutIfUserExist(HttpServletRequest request, User user) {
        Map<Integer, HttpSession> loggedUsers = ServletUtility.getLoggedUsers();
        int userId = user.getId();
        if(loggedUsers.containsKey(userId)) {
            loggedUsers.get(userId).invalidate();
        }
        loggedUsers.put(userId,request.getSession());
        ServletUtility.setLoggedUsers(loggedUsers);
    }*/

    private User checkLoginAndPassword(String login, String password, HttpServletRequest request)  throws UserNotFoundException{
        if (login == null || login.equals("") || password == null || password.equals("")) {
            return null;
        }
        return authorization(login, password, request);
    }

    private String informAboutWrongInput(HttpServletRequest request) {
        request.setAttribute("info", "Invalid name or password");
        return "/login.jsp";
    }

    private String getRedirectPath(User.ROLE role) {
        if (role == User.ROLE.admin) {
            return "redirect: /park/admin";
        } else if (role == User.ROLE.driver) {
            return "redirect: /park/driver";
        } else return "/login.jsp";
    }
}
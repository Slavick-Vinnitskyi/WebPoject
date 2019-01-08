package controller.commands.implementation;

import controller.commands.utils.SecurityUtility;
import controller.commands.Command;
import model.entity.User;
import model.exception.UserNotFoundException;
import model.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

//TODO: make userDTO instead of user object
public class LoginCommand implements Command {
    private SecurityUtility security = new SecurityUtility();

    /**
     * @param request request from client
     * @return user page if input is right
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logout(request);
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        try {
            User user = checkLoginAndPassword(name, pass);
            logIn(request, user);
            return getRedirectPath(user.getRole());
        } catch (UserNotFoundException | NullPointerException e) {
            return informAboutWrongInput(request);
        }
    }

    private void logIn(HttpServletRequest request, User user) {
        Map<Integer, HttpSession> loggedUsers = security.getLoggedUsers();
        int userId = user.getId();
        destroyPreviousSession(loggedUsers, userId);
        loggedUsers.put(userId, request.getSession());
        security.setLoggedUsers(loggedUsers);
        sessionSetup(request, user);
    }

    private void destroyPreviousSession(Map<Integer, HttpSession> loggedUsers, int userId) {
        if (loggedUsers.containsKey(userId)) {
            loggedUsers.get(userId).invalidate();
        }
    }

    private void sessionSetup(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole());
        session.setAttribute("firstName", user.getFirstName());
        session.setAttribute("secondName", user.getSecondName());
        session.setAttribute("user", user);
    }

    /**
     * Making logout to invalidate last user session
     *
     * @param request with session that will be putted instead of previous
     */
    private void logout(HttpServletRequest request) {
        if (getUserId(request) != -1) {
            security.logOut(request.getSession());
        }
    }

    /**
     * @param request needs to get the session
     * @return user id if those exist of 0 if not
     */
    private int getUserId(HttpServletRequest request) {
        return (Integer) Optional.ofNullable(request.getSession().getAttribute("userId")).orElse(-1);
    }


    private User authorization(String name, String pass) throws UserNotFoundException {

            return new LoginService().validateUser(name, pass);

    }


    private User checkLoginAndPassword(String login, String password) throws UserNotFoundException {
        if (login == null || login.equals("") || password == null || password.equals("")) {
            return null;
        }
        return authorization(login, password);
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
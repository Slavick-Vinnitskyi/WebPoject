package controller.commands.implementation;

import controller.commands.utils.SecurityUtility;
import controller.commands.Command;
import model.entity.User;
import model.exception.InvalidInputException;
import model.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

public class LoginCommand implements Command {
    private SecurityUtility security = new SecurityUtility();
    private LoginService service = new LoginService();

    /**
     * @param request request from client
     * @return user page if input is right
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logout(request);

        String name = request.getParameter("login");
        String pass = request.getParameter("password");

        try {
            User user = checkLoginAndPassword(request, name, pass);
            return getRedirectPath(user.getRole());
        } catch (InvalidInputException ex) {
            return informAboutWrongInput(request, ex.getMessage());
        }
    }

    private User checkLoginAndPassword(HttpServletRequest request, String login, String password) {
        if (login == null || login.equals("") || password == null || password.equals("")) {
            throw new InvalidInputException("Invalid name or password");
        }
        Optional<User> user = service.validateUser(login, password);
        if (user.isPresent()) {
            logIn(request, user.get());
            return user.get();
        }
        return null;
    }

    private void logIn(HttpServletRequest request, User user) {
        Map<Integer, HttpSession> loggedUsers = security.getLoggedUsers();
        int userId = user.getId();
        destroyPreviousSession(loggedUsers, userId);
        loggedUsers.put(userId, request.getSession());
        security.setLoggedUsers(loggedUsers);
        sessionSetup(request, user);
    }

    private void sessionSetup(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        user.setPassword(null);
        session.setAttribute("user", user);
    }

    private void destroyPreviousSession(Map<Integer, HttpSession> loggedUsers, int userId) {
        if (loggedUsers.containsKey(userId)) {
            loggedUsers.get(userId).invalidate();
        }
    }

    /**
     * Making logout to invalidate last user session
     *
     * @param request with session that will be putted instead of previous
     */
    private void logout(HttpServletRequest request) {
        Optional.ofNullable(request.getSession().getAttribute("user")).ifPresent(x ->
                security.logOut(request.getSession())
        );
    }

    private String informAboutWrongInput(HttpServletRequest request, String message) {
        request.setAttribute("info", message);
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
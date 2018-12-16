package controller.commands.implementation;

import controller.commands.utils.SecurityUtil;
import controller.commands.utils.ServletUtility;
import controller.commands.Command;
import controller.commands.utils.LogoutUtil;
import model.entity.User;
import model.entity.dao.implementation.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        LogoutUtil.makeLogout(request);
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        try {

            User authorized = checkLoginAndPassword(name, pass, request);
            User.ROLE role = authorized.getRole();
            ServletUtility.logIn(request, name);
            ServletUtility.setUserRole(request, role, name);
            ServletUtility.setUser(request, name);

            return getRedirectPath(role);
        } catch (UserNotFoundException e) {
            return informAboutWrongInput(request);
        }
    }

    private User authorization(HttpServletRequest request) throws UserNotFoundException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        try {
            return new SecurityUtil().validateUser(name, pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private User checkLoginAndPassword(String login, String password, HttpServletRequest request)  throws UserNotFoundException{
        if (login == null || login.equals("") || password == null || password.equals("")) {
            return null;
        }
        return authorization(request);
    }

    private String informAboutWrongInput(HttpServletRequest request) {
        request.setAttribute("info", "Invalid name or password");
        return "/login.jsp";
    }

/*    private User.ROLE getUserRole(String name) {
        try {
            Properties properties = new Properties();
            ClassLoader cl = this.getClass().getClassLoader();
            InputStream is = cl.getResourceAsStream("users.properties");
            properties.load(is);

            String roleStr = properties.getProperty(name, User.ROLE.driver.toString());
            return User.ROLE.valueOf(roleStr);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return User.ROLE.guest;
    }*/

    private String getRedirectPath(User.ROLE role) {
        if (role == User.ROLE.admin) {
            return "redirect: /park/admin";
        } else if (role == User.ROLE.driver) {
            return "redirect: /park/driver";
        } else return "/login.jsp";
    }
}
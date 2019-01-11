package controller.commands.implementation;

import controller.commands.Command;
import model.entity.User;
import model.entity.enums.LicenseType;
import model.service.RegisterService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegisterCommand implements Command {
    RegisterService service = new RegisterService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = setUpUser(request);
        if (!validateFields(request, user)) {
            return "/register.jsp";
        }

        if (service.isLoginExist(user.getLogin())) {
            return informAboutWrongInput(request);
        } else {
            user = service.registerNewUser(user);
            setUserToSession(request, user);
            return "redirect: /park/driver";
        }
    }

    private User setUpUser(HttpServletRequest request) {
        User user = new User();
        user.setRole(User.ROLE.driver);
        user.setFirstName(request.getParameter("first_name"));
        user.setSecondName(request.getParameter("second_name"));
        user.setLogin(request.getParameter("username"));
        user.setPassword(request.getParameter("confirm_password"));
        user.setLicenseType(LicenseType.valueOf(request.getParameter("license")));
        return user;
    }

    private boolean validateFields(HttpServletRequest request, User driver) {
        boolean flag = true;
        Pattern firstNamePattern = Pattern.compile(ResourceBundle.getBundle("regex").getString("register.firstName"));
        Pattern secondNamePattern = Pattern.compile(ResourceBundle.getBundle("regex").getString("register.secondName"));
        Pattern loginPattern = Pattern.compile(ResourceBundle.getBundle("regex").getString("register.login"));
        Pattern passwordPattern = Pattern.compile(ResourceBundle.getBundle("regex").getString("register.password"));
        Locale locale = ThreadLocalWrapper.getLocale();
        if (!firstNamePattern.matcher(driver.getFirstName()).matches()) {
            request.setAttribute("wrongFirstName", ResourceBundle.getBundle("errors", locale).getString("register.firstName"));
            flag = false;
        }
        if (!secondNamePattern.matcher(driver.getSecondName()).matches()) {
            request.setAttribute("wrongSecondName", ResourceBundle.getBundle("errors", locale).getString("register.secondName"));
            flag = false;
        }
        if (!loginPattern.matcher(driver.getLogin()).matches()) {
            request.setAttribute("wrongLogin", ResourceBundle.getBundle("errors", locale).getString("register.login"));
            flag = false;
        }
        if (!passwordPattern.matcher(driver.getPassword()).matches()) {
            request.setAttribute("wrongPassword", ResourceBundle.getBundle("errors", locale).getString("register.password"));
            flag = false;
        }
        return flag;
    }

    private void setUserToSession(HttpServletRequest request, User userToSession) {
        userToSession.setPassword(null);
        request.getSession().setAttribute("user", userToSession);
        request.getSession().setAttribute("role", User.ROLE.driver);
    }

    private String informAboutWrongInput(HttpServletRequest request) {
        request.setAttribute("info", "This login already exist in system. Please log in or pick other username.");
        return "/register.jsp";
    }
}
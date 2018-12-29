package controller.commands.implementation;

import controller.commands.Command;
import model.entity.User;
import model.entity.enums.LicenseType;
import model.service.RegisterService;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
    RegisterService service = new RegisterService();
        User user = setUpUser(request);

        if(service.isLoginExist(user.getLogin())) {
            return informAboutWrongInput(request);
        }

        else {
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

    private void setUserToSession(HttpServletRequest request, User userToSession) {
        request.getSession().setAttribute("user", userToSession);
        request.getSession().setAttribute("role", User.ROLE.driver);
    }
    private String informAboutWrongInput(HttpServletRequest request) {
        request.setAttribute("info", "This login already exist in system. Please log in or pick other username.");
        return "/register.jsp";
    }
}

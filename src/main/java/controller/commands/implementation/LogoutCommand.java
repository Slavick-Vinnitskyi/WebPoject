package controller.commands.implementation;

import controller.commands.Command;
import controller.commands.utils.SecurityUtility;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        new SecurityUtility().logOut(request.getSession());

        return "redirect: /park/index";
    }
}

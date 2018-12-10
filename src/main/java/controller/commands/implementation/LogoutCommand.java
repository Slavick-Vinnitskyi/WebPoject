package controller.commands.implementation;

import controller.commands.Command;
import controller.commands.utils.LogoutUtil;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        LogoutUtil.makeLogout(request);

        return "redirect: /park/index";
    }
}

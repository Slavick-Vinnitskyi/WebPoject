package controller.commands.implementation;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class AdminAddingRouteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/add_route.jsp";
    }
}
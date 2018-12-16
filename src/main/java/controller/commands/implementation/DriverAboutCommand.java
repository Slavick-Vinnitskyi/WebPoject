package controller.commands.implementation;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class DriverAboutCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/driver/about.jsp";
    }
}
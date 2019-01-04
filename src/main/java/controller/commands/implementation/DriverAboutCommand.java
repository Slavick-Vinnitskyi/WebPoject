package controller.commands.implementation;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DriverAboutCommand implements Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/driver/about.jsp";
    }
}
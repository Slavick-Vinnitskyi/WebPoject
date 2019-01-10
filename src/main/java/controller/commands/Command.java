package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Command {
    int OFFSET = 10;
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}

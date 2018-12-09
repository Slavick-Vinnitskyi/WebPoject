package controller.commands.implementation;
import controller.commands.Command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DriverHistoryCommand implements Command {

//    private static final Logger log = Logger.getLogger(DriverHistoryCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
//        log.info("DriverHistoryPage");
        return "/WEB-INF/driver/history.jsp";
    }
}
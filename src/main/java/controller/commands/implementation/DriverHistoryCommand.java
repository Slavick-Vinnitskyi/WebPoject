package controller.commands.implementation;
import controller.commands.Command;
import model.entity.Assignment;
import model.entity.User;
import model.service.DriverHistoryPageService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DriverHistoryCommand implements Command {

//    private static final Logger log = Logger.getLogger(DriverHistoryCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            DriverHistoryPageService service = new DriverHistoryPageService();
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getId();
            List <Assignment> assignments = service.getPastAssignmentsForDriver(userId);
            request.setAttribute("assignmentsHistory", assignments);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/WEB-INF/driver/history.jsp";
    }
}
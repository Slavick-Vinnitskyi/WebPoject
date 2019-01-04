package controller.commands.implementation;
import controller.commands.Command;
import model.entity.Assignment;
import model.entity.User;
import model.service.DriverHistoryPageService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class DriverHistoryCommand implements Command {

//    private static final Logger log = Logger.getLogger(DriverHistoryCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            DriverHistoryPageService service = new DriverHistoryPageService();
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getId();
            List <Assignment> assignments = service.getPastAssignmentsForDriver(userId);
            setTotalPageNumber(request, assignments);
            handlePageNumber(request, assignments);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/WEB-INF/driver/history.jsp";
    }

    private void handlePageNumber(HttpServletRequest request, List<Assignment> assignments) {
        int page = Integer.valueOf(Optional.ofNullable(request.getParameter("page")).orElse("0"));
        int end = Math.min(page + 2 , assignments.size());
        request.setAttribute("assignmentsHistory", assignments.subList(page, end));
    }

    private void setTotalPageNumber(HttpServletRequest request, List<Assignment> assignments) {
        int totalPages = (int) Math.ceil((float)assignments.size()/2);
        request.setAttribute("totalPages", totalPages);
    }
}
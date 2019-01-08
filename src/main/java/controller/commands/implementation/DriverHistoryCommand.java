package controller.commands.implementation;
import controller.commands.Command;
import model.entity.Assignment;
import model.entity.User;
import model.service.DriverHistoryPageService;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class DriverHistoryCommand implements Command {

    private static final Logger log = Logger.getLogger(DriverHistoryCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            DriverHistoryPageService service = new DriverHistoryPageService();
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getId();
            List <Assignment> assignments = service.getPastAssignmentsForDriver(userId);
            setTotalPageNumber(request, assignments);
            handlePageNumber(request, assignments);
            log.info("Driver \'" + user.getLogin() + "\' is watching history");
        } catch (Exception e) {
            log.info(e);
//            e.printStackTrace();
        }

        return "/WEB-INF/driver/history.jsp";
    }

    private void handlePageNumber(HttpServletRequest request, List<Assignment> assignments) {
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int start = (page - 1) * OFFSET;
        int end = Math.min(start + OFFSET, assignments.size());
        request.setAttribute("assignmentsHistory", assignments.subList(start, end));
    }

    private void setTotalPageNumber(HttpServletRequest request, List<Assignment> assignments) {
        int totalPages = (int) Math.ceil((float)assignments.size()/OFFSET);
        request.setAttribute("totalPages", totalPages);
    }
}
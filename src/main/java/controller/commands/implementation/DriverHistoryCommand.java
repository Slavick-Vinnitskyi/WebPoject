package controller.commands.implementation;
import controller.commands.Command;
import model.dto.IndexDto;
import model.entity.Assignment;
import model.entity.User;
import model.service.DriverHistoryPageService;
import org.apache.log4j.Logger;
import util.QueryManager;
import util.ThreadLocalWrapper;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class DriverHistoryCommand implements Command {

    private DriverHistoryPageService service = new DriverHistoryPageService();
    private static final Logger log = Logger.getLogger(DriverHistoryCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();

        int limit = Integer.parseInt(QueryManager.getProperty("select.limit"));

        setTotalPageNumber(request,userId, limit);
        setPageHistory(request, userId, limit);

        log.info(user.getLogin() + " is watching history");

        return "/WEB-INF/driver/history.jsp";
    }

    private void setPageHistory(HttpServletRequest request, int driverId, int limit) {
        int page = Integer.valueOf(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int offset = (page - 1) * limit;
        try {
            List<Assignment> assignments = service.getPastAssignmentsForDriver(driverId, limit, offset);
            request.setAttribute("assignmentsHistory", assignments);
        } catch (RuntimeException ex) {
            ResourceBundle errors = ResourceBundle.getBundle("errors", ThreadLocalWrapper.getLocale());
            request.setAttribute("selectError", errors.getString("index.select"));
        }
    }

    private void setTotalPageNumber(HttpServletRequest request, int driverId, int limit) {
        try {
            request.setAttribute("pages", service.getTotalPagesNumber(driverId, limit));
        } catch (RuntimeException ex) {
            ResourceBundle errors = ResourceBundle.getBundle("errors", ThreadLocalWrapper.getLocale());
            request.setAttribute("pagesError", errors.getString("index.pages"));
        }
    }
}
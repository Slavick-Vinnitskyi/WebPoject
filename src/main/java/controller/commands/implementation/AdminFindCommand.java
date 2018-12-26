package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Route;
import model.service.AdminMainPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdminFindCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        AdminMainPageService service = new AdminMainPageService();
        Route route = parseRoute(request, service);
        LocalDate date = parseDate(request);
        setToSession(request, route, date);

        return "redirect: /park/admin";
    }

    private void setToSession(HttpServletRequest request, Route route, LocalDate date) {
        HttpSession currentSession = request.getSession();
        currentSession.setAttribute("selectedRoute", route);
        currentSession.setAttribute("selectedDate", date);
    }

    private Route parseRoute(HttpServletRequest request, AdminMainPageService service) throws SQLException, ClassNotFoundException {
        int routeId = Integer.parseInt(request.getParameter("route"));
        return service.getRouteById(routeId);
    }

    private LocalDate parseDate(HttpServletRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return LocalDate.parse(request.getParameter("date"), formatter);
    }
}

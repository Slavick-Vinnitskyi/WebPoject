package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Route;
import model.entity.User;
import model.service.AdminMainPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdminFindCommand implements Command {
    AdminMainPageService service = new AdminMainPageService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Route route = parseRoute(request, service);
        LocalDate date = parseDate(request);
        setToSession(request, route, date);
        List<User> freeDrivers = service.getFreeCarsAndDriver(date);
        HttpSession session = request.getSession();
        session.setAttribute("drivers", freeDrivers);
        return "redirect: /park/admin";
    }

    private void setToSession(HttpServletRequest request, Route route, LocalDate date) {
        HttpSession currentSession = request.getSession();
        currentSession.setAttribute("selectedRoute", route);
        currentSession.setAttribute("selectedDate", date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
    }

    private Route parseRoute(HttpServletRequest request, AdminMainPageService service) throws SQLException {
        int routeId = Integer.parseInt(request.getParameter("route"));
        return service.getRouteById(routeId);
    }

    private LocalDate parseDate(HttpServletRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        return LocalDate.parse(request.getParameter("date"), formatter);
    }
}

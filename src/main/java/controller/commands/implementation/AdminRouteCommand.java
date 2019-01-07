package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Car;
import model.entity.Route;
import model.service.AdminMainPageService;
import model.service.AdminRoutePageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminRouteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            AdminRoutePageService service = new AdminRoutePageService();
            List<Route> routeList = service.getAllRoutes();
            setTotalPageNumber(request, routeList);
            handlePageNumber(request, routeList);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/WEB-INF/admin/add_route.jsp";
    }
    private void handlePageNumber(HttpServletRequest request, List<Route> assignments) {
        int page = Integer.valueOf(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int start = (page - 1) * OFFSET;
        int end = Math.min(start + OFFSET, assignments.size());
        request.setAttribute("routeList", assignments.subList(start, end));
    }

    private void setTotalPageNumber(HttpServletRequest request, List<Route> assignments) {
        int totalPages = (int) Math.ceil((float)assignments.size() / OFFSET);
        request.setAttribute("totalPages", totalPages);
    }
}
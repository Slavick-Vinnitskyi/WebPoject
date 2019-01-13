package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Car;
import model.entity.Route;
import model.service.AdminMainPageService;
import model.service.AdminRoutePageService;
import util.QueryManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminRouteCommand implements Command {
    AdminRoutePageService service = new AdminRoutePageService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int offset = Integer.parseInt(QueryManager.getProperty("select.limit"));
        List<Route> routeList = service.getAllRoutes();
        setTotalPageNumber(request, routeList, offset);
        handlePageNumber(request, routeList, offset);

        return "/WEB-INF/admin/add_route.jsp";
    }
    private void handlePageNumber(HttpServletRequest request, List<Route> assignments, int offset) {
        int page = Integer.valueOf(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int start = (page - 1) * offset;
        int end = Math.min(start + offset, assignments.size());
        request.setAttribute("routeList", assignments.subList(start, end));
    }

    private void setTotalPageNumber(HttpServletRequest request, List<Route> assignments, int offset) {
        int totalPages = (int) Math.ceil((float)assignments.size() / offset);
        request.setAttribute("totalPages", totalPages);
    }
}
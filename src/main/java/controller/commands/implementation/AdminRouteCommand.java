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
//            request.setAttribute("routeList", routeList);
            setTotalPageNumber(request, routeList);
            handlePageNumber(request, routeList);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/WEB-INF/admin/add_route.jsp";
    }
    private void handlePageNumber(HttpServletRequest request, List<Route> assignments) {
        int page = Integer.valueOf(Optional.ofNullable(request.getParameter("page")).orElse("0"));
        int end = Math.min(page + 2 , assignments.size());
        request.setAttribute("routeList", assignments.subList(page, end));
    }

    private void setTotalPageNumber(HttpServletRequest request, List<Route> assignments) {
        int totalPages = (int) Math.ceil((float)assignments.size()/2);
        request.setAttribute("totalPages", totalPages);
    }
}
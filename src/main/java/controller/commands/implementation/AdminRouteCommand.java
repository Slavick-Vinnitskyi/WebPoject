package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Route;
import model.service.AdminMainPageService;
import model.service.AdminRoutePageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AdminRouteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {

            AdminRoutePageService service = new AdminRoutePageService();
            List<Route> routeList = service.getAllRoutes();
            request.setAttribute("routeList", routeList);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/WEB-INF/admin/add_route.jsp";
    }
}
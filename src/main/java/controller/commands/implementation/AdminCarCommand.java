package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Car;
import model.entity.Route;
import model.service.AdminCarPageService;
import model.service.AdminRoutePageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AdminCarCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            AdminCarPageService service = new AdminCarPageService();
            List<Car> routeList = service.getAllRoutes();
            request.setAttribute("carList", routeList);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "/WEB-INF/admin/add_car.jsp";
    }
}
package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Route;
import model.service.AdminMainPageService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class AdminCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        try {
            AdminMainPageService adminMainPageService = new AdminMainPageService();
            List<Route> routeList = adminMainPageService.getAllRoutes();
            request.setAttribute("routeList", routeList);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "/WEB-INF/admin/admin.jsp";
    }
}

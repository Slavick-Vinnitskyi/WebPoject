package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Route;
import model.service.AdminRoutePageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddingRouteButtonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Route routeToInsert = new Route();

        String start = request.getParameter("start");
        String finish = request.getParameter("finish");
        String startUa = request.getParameter("start_ua");
        String finishUa = request.getParameter("finish_ua");
        routeToInsert.setStart(start);
        routeToInsert.setFinish(finish);
        routeToInsert.setStartUa(startUa);
        routeToInsert.setFinishUa(finishUa);
        AdminRoutePageService service = new AdminRoutePageService();
        service.insertRoute(routeToInsert);
        return "redirect: /park/admin/add_route";
    }
}

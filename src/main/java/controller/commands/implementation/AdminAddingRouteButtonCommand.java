package controller.commands.implementation;

import controller.commands.Command;
import model.dto.LocalizedRoute;
import model.entity.Route;
import model.service.AdminRoutePageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class AdminAddingRouteButtonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String start = request.getParameter("start");
        String finish = request.getParameter("finish");
        String startUa = request.getParameter("start_ua");
        String finishUa = request.getParameter("finish_ua");

        HashMap<String, LocalizedRoute.LocalizedPart> localizedPartMap = new HashMap<>();

        LocalizedRoute routeWrapper = new LocalizedRoute(new Route(start, finish), localizedPartMap);
        LocalizedRoute.LocalizedPart localizedPart = routeWrapper.new LocalizedPart(startUa, finishUa);

        localizedPartMap.put("ua", localizedPart);

        AdminRoutePageService service = new AdminRoutePageService();
        service.insertRoute(routeWrapper);
        return "redirect: /park/admin/add_route";
    }
}

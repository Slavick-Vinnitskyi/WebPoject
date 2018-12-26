package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Assignment;
import model.entity.Route;
import model.entity.User;
import model.service.AdminMainPageService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;


public class AdminCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        AdminMainPageService service = new AdminMainPageService();
        try {

            List<Assignment> assigned = service.getAssignmentsByStatus(Assignment.Status.assigned);
            List<Assignment> applied = service.getAssignmentsByStatus(Assignment.Status.applied);
            request.setAttribute("assigned", assigned);
            request.setAttribute("applied", applied);

            List<Route> routeList = service.getAllRoutes();
            request.setAttribute("routes", routeList);
            LocalDate date = (LocalDate) request.getSession().getAttribute("selectedDate");
            List<User> freeDrivers = service.getFreeCarsAndDriver(date);
            request.setAttribute("drivers", freeDrivers);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/WEB-INF/admin/admin.jsp";
    }
}

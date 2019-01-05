package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Assignment;
import model.entity.Car;
import model.entity.Route;
import model.service.AdminMainPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class AdminCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AdminMainPageService service = new AdminMainPageService();
        try {

            List<Assignment> assigned = service.getAssignmentsByStatus(Assignment.Status.assigned);
            List<Assignment> applied = service.getAssignmentsByStatus(Assignment.Status.applied);

            setTotalAssignedPagesNumber(request, assigned);
            setTotalAppliedPagesNumber(request, applied);

            handleAssignedPageNumber(request, assigned);
            handleAppliedPageNumber(request, applied);

            setRoutesToRequest(request, service);

            setCarsToSession(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/WEB-INF/admin/admin.jsp";
    }

    private void setCarsToSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Car> cars = (List<Car>) session.getAttribute("cars");
        request.setAttribute("allcars", cars);
    }

    private void setRoutesToRequest(HttpServletRequest request, AdminMainPageService service) throws SQLException, ClassNotFoundException {
        List<Route> routeList = service.getAllRoutes();
        request.setAttribute("routes", routeList);
    }

    private void handleAssignedPageNumber(HttpServletRequest request, List<Assignment> assignedList) {
        int page = Integer.valueOf(Optional.ofNullable(request.getParameter("assignedPage")).orElse("0"));
        if(page == 1) page = 0;

        int end = Math.min(page + 2, assignedList.size());
        request.setAttribute("assigned", assignedList.subList(page, end));
    }

    private void handleAppliedPageNumber(HttpServletRequest request, List<Assignment> appliedList) {
        int page = Integer.valueOf(Optional.ofNullable(request.getParameter("appliedPage")).orElse("0"));
        if(page == 1) page = 0;
        int end = Math.min(page + 2, appliedList.size());
        request.setAttribute("applied", appliedList.subList(page, end));
    }

    private void setTotalAssignedPagesNumber(HttpServletRequest request, List<Assignment> assignedList) {
        int totalPages = (int) Math.ceil((float)assignedList.size() / 2);
        request.setAttribute("totalAssignedPages", totalPages);
    }
    private void setTotalAppliedPagesNumber(HttpServletRequest request, List<Assignment> appliedList) {
        int totalPages = (int) Math.ceil((float)appliedList.size() / 2);
        request.setAttribute("totalAppliedPages", totalPages);
    }
}

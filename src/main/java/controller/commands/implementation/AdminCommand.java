package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Assignment;
import model.entity.Car;
import model.entity.Route;
import model.service.AdminMainPageService;
import util.QueryManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


public class AdminCommand implements Command {

    AdminMainPageService service = new AdminMainPageService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int offset = Integer.parseInt(QueryManager.getProperty("select.limit"));
        try {

            List<Assignment> assigned = service.getAssignmentsByStatus(Assignment.Status.assigned);
            List<Assignment> applied = service.getAssignmentsByStatus(Assignment.Status.applied);

            setTotalAssignedPagesNumber(request, assigned, offset);
            setTotalAppliedPagesNumber(request, applied, offset);

            handleAssignedPageNumber(request, assigned, offset);
            handleAppliedPageNumber(request, applied, offset);

            setRoutesToRequest(request);
            handleErrors(request);

            setCarsToSession(request);
            setDate(request);
            setDriversToRequest(request);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return "/WEB-INF/admin/admin.jsp";
    }

    private void handleErrors(HttpServletRequest request) {
        Optional.ofNullable((String)request.getSession().getAttribute("error")).ifPresent(x-> {
            request.getSession().removeAttribute("error");
            request.setAttribute("error", x);
        });
    }

    private void setCarsToSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Car> cars = (List<Car>) session.getAttribute("cars");
        request.setAttribute("allcars", cars);
    }

    private void setRoutesToRequest(HttpServletRequest request) {
        List<Route> routeList = service.getAllRoutes();
        request.setAttribute("routes", routeList);
    }
    private void setDriversToRequest(HttpServletRequest request) {
        request.setAttribute("drivers", request.getSession().getAttribute("drivers"));
    }

    private void setDate(HttpServletRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        if(request.getSession().getAttribute("selectedDate") == null)
            request.getSession().setAttribute("selectedDate", LocalDate.now().format(formatter));
    }

    private void handleAssignedPageNumber(HttpServletRequest request, List<Assignment> assignedList, int offset) {
        int page = Integer.valueOf(Optional.ofNullable(request.getParameter("assignedPage")).orElse("1"));
        int start = (page - 1) * offset;
        int end = Math.min(start + offset, assignedList.size());
        request.setAttribute("assigned", assignedList.subList(start, end));
    }

    private void handleAppliedPageNumber(HttpServletRequest request, List<Assignment> appliedList, int offset) {
        int page = Math.max(Integer.valueOf(Optional.ofNullable(request.getParameter("appliedPage")).orElse("1")), 1 );
        page = Math.min(page, (Integer) Optional.ofNullable(request.getAttribute("totalAppliedPages")).orElse(Integer.MAX_VALUE));
        int start = (page - 1) * offset;
        int end = Math.min(start + offset, appliedList.size());
        request.setAttribute("applied", appliedList.subList(start, end));
    }

    private void setTotalAssignedPagesNumber(HttpServletRequest request, List<Assignment> assignedList, int offset) {
        int totalPages = (int) Math.ceil((float)assignedList.size() / offset);
        request.setAttribute("totalAssignedPages", totalPages);
    }
    private void setTotalAppliedPagesNumber(HttpServletRequest request, List<Assignment> appliedList, int offset) {
        int totalPages = (int) Math.ceil((float)appliedList.size() / offset);
        request.setAttribute("totalAppliedPages", totalPages);
    }
}

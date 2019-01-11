package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Assignment;
import model.entity.User;
import model.service.DriverMainPageService;
import org.apache.log4j.Logger;
import util.QueryManager;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class DriverCommand implements Command {
    private DriverMainPageService service = new DriverMainPageService();
    private static final Logger log = Logger.getLogger(DriverCommand.class);


    /**
     * @param request request from client
     * @return - driver page without redirect if form was`n used
     * - driver page with redirect in order to reset request
     * to prevent form resending(Some implementation of 'Post/Redirect/Get' pattern )
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            int userId = getUserId(request);
            int limit = Integer.parseInt(QueryManager.getProperty("select.limit"));

            int offsetForAssigned = getOffsetForAssigned(request, limit);
            List<Assignment> assignedList = service.getAssignmentsForDriverByStatus(userId, limit, offsetForAssigned, Assignment.Status.assigned);

            int offsetForApplied = getOffsetForApplied(request, limit);

            List<Assignment> appliedList = service.getAssignmentsForDriverByStatus(userId, limit, offsetForApplied, Assignment.Status.applied);

            setTotalAssignedPages(request, userId, limit);
            setTotalAppliedPages(request, userId, limit);
            setAssignedPage(request, assignedList);
            setAppliedPage(request, appliedList);
            handleErrors(request);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return "/WEB-INF/driver/driver.jsp";
    }

    private void handleErrors(HttpServletRequest request) {
        Optional.ofNullable((String)request.getSession().getAttribute("error")).ifPresent(x-> {
            request.getSession().removeAttribute("error");
            request.setAttribute("error", x);
        });
    }
    private int getUserId(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user.getId();
    }

    private int getOffsetForAssigned(HttpServletRequest request, int limit) {
        int page = Integer.valueOf(Optional.ofNullable(request.getParameter("assignedPage")).orElse("1"));
        return (page - 1) * limit;
    }

    private int getOffsetForApplied(HttpServletRequest request, int limit) {
        int page = Integer.valueOf(Optional.ofNullable(request.getParameter("appliedPage")).orElse("1"));
        return (page - 1) * limit;
    }

    private void setAssignedPage(HttpServletRequest request, List<Assignment> assignments) {
        Locale locale = ThreadLocalWrapper.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("driver", locale);
        if (assignments.size() == 0)
            request.setAttribute("messageToUser", bundle.getString("driver.message.emptyTable"));
        else
            request.setAttribute("messageToUser", bundle.getString("driver.assignments"));

        request.setAttribute("assignmentsAssignedList", assignments);

        log.info("Set up assigned list");
    }


    private void setAppliedPage(HttpServletRequest request, List<Assignment> appliedList) {
        Locale locale = ThreadLocalWrapper.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("driver", locale);
        if (appliedList.size() == 0)
            request.setAttribute("appliedMessageToUser", bundle.getString("driver.message.emptyRoutes"));
        else
            request.setAttribute("appliedMessageToUser", bundle.getString("driver.routes"));
        request.setAttribute("assignmentsAppliedList", appliedList);
        log.info("Set up applied list");
    }

    private void setTotalAssignedPages(HttpServletRequest request, int driverId, int limit) {
        try {
            request.setAttribute("totalAssignedPages", service.getAssignmentsPagesNumber(limit, driverId, Assignment.Status.assigned));
            log.info("Set up number of assigned pages ");
        } catch (RuntimeException ex) {
            log.error(ex);
            ResourceBundle errors = ResourceBundle.getBundle("errors", ThreadLocalWrapper.getLocale());
            request.setAttribute("pagesError", errors.getString("index.pages"));
        }
    }

    private void setTotalAppliedPages(HttpServletRequest request, int driverId, int limit) {
        try {
            request.setAttribute("totalAppliedPages", service.getAssignmentsPagesNumber(limit, driverId, Assignment.Status.applied));
            log.info("Set up number of applied pages ");
        } catch (RuntimeException ex) {
            log.error(ex);
            ResourceBundle errors = ResourceBundle.getBundle("errors", ThreadLocalWrapper.getLocale());
            request.setAttribute("pagesError", errors.getString("index.pages"));
        }
    }
}
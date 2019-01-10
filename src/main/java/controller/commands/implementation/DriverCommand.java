package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Assignment;
import model.entity.User;
import model.service.DriverMainPageService;
import util.ThreadLocalWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

//TODO: оптимизировать работу страницы(не переиспользовать данные с базы)
//TODO: Попробовать возвращать Optional
public class DriverCommand implements Command {
    /**
     * @param request request from client
     * @return - driver page without redirect if form was`n used
     * - driver page with redirect in order to reset request
     * to prevent form resending(Some implementation of 'Post/Redirect/Get' pattern )
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            DriverMainPageService service = new DriverMainPageService();
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getId();

            List<Assignment> assignedList = Optional
                    .of(service.getAssignmentsForDriverByStatus(userId, Assignment.Status.assigned))
                    .orElse(new ArrayList<>());
            request.setAttribute("assignmentsAssignedList", assignedList);
            setMessageToUser(request, assignedList);
            List<Assignment> appliedList = service.getAssignmentsForDriverByStatus(userId, Assignment.Status.applied);
            setAppliedMessageToUser(request, appliedList);
            request.setAttribute("assignmentsAppliedList", appliedList);
            setTotalAssignedPagesNumber(request, assignedList);
            setTotalAppliedPagesNumber(request, appliedList);
            handleAssignedPageNumber(request, assignedList);
            handleAppliedPageNumber(request, appliedList);

        } catch (NullPointerException e) {
            System.out.println("bad");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/WEB-INF/driver/driver.jsp";
    }

    private void setMessageToUser(HttpServletRequest request, List<Assignment> assignedList) {
        Locale locale = ThreadLocalWrapper.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("driver", locale);

        if (assignedList.size() == 0)
            request.setAttribute("messageToUser", bundle.getString("driver.message.emptyTable"));
        else
            request.setAttribute("messageToUser", bundle.getString("driver.assignments"));

    }
    private void setAppliedMessageToUser(HttpServletRequest request, List<Assignment> appliedList) {
        Locale locale = ThreadLocalWrapper.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("driver", locale);

        if (appliedList.size() == 0)
            request.setAttribute("appliedMessageToUser", bundle.getString("driver.message.emptyRoutes"));
        else
            request.setAttribute("appliedMessageToUser", bundle.getString("driver.routes"));

    }

    private void handleAssignedPageNumber(HttpServletRequest request, List<Assignment> assignedList) {
        int page = Integer.valueOf(Optional.ofNullable(request.getParameter("assignedPage")).orElse("1"));
        int start = (page - 1) * OFFSET;
        int end = Math.min(start + OFFSET, assignedList.size());
        request.setAttribute("routeList", assignedList.subList(start, end));
    }

    private void handleAppliedPageNumber(HttpServletRequest request, List<Assignment> appliedList) {
        int page = Integer.valueOf(Optional.ofNullable(request.getParameter("appliedPage")).orElse("1"));
        int start = (page - 1) * OFFSET;
        int end = Math.min(start + OFFSET, appliedList.size());
        request.setAttribute("routeList", appliedList.subList(start, end));
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
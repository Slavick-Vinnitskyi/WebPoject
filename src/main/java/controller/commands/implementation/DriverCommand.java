package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Assignment;
import model.entity.User;
import model.service.DriverMainPageService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    public String execute(HttpServletRequest request) {

        try {
            DriverMainPageService service = new DriverMainPageService();
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getId();

            List<Assignment> assignedList = service.getAssignmentsForDriverByStatus(userId, Assignment.Status.assigned);
            request.setAttribute("assignmentsAssignedList", assignedList);
            List<Assignment> applied = service.getAssignmentsForDriverByStatus(userId, Assignment.Status.applied);
            request.setAttribute("assignmentsAppliedList", applied);

        } catch (NullPointerException e) {
            System.out.println("bad");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/WEB-INF/driver/driver.jsp";
    }
}
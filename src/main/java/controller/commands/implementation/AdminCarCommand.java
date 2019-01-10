package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Car;
import model.service.AdminCarPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminCarCommand implements Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        AdminCarPageService service = new AdminCarPageService();
        List<Car> routeList = service.getAllRoutes();
        setTotalPageNumber(request, routeList);
        handlePageNumber(request, routeList);

        return "/WEB-INF/admin/add_car.jsp";
    }
    private void handlePageNumber(HttpServletRequest request, List<Car> assignments) {
        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int start = (page - 1) * OFFSET;
        int end = Math.min(start + OFFSET, assignments.size());
        request.setAttribute("carList", assignments.subList(start, end));
    }

    private void setTotalPageNumber(HttpServletRequest request, List<Car> assignments) {
        int totalPages = (int) Math.ceil((float)assignments.size()/OFFSET);
        request.setAttribute("totalPages", totalPages);
    }
}
package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Car;
import model.service.AdminCarPageService;
import util.QueryManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class AdminCarCommand implements Command {
    AdminCarPageService service = new AdminCarPageService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        List<Car> routeList = service.getAllRoutes();
        int offset = Integer.parseInt(QueryManager.getProperty("select.limit"));
        setTotalPageNumber(request, routeList, offset);
        handlePageNumber(request, routeList, offset);

        return "/WEB-INF/admin/add_car.jsp";
    }

    private void handlePageNumber(HttpServletRequest request, List<Car> assignments, int offset) {

        int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("1"));
        int start = (page - 1) * offset;
        int end = Math.min(start + offset, assignments.size());
        request.setAttribute("carList", assignments.subList(start, end));
    }

    private void setTotalPageNumber(HttpServletRequest request, List<Car> assignments, int offset) {
        int totalPages = (int) Math.ceil((float) assignments.size() / offset);
        request.setAttribute("totalPages", totalPages);
    }
}
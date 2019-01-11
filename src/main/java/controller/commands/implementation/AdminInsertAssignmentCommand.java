package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Assignment;
import model.entity.Car;
import model.entity.Route;
import model.entity.User;
import model.service.AdminMainPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class AdminInsertAssignmentCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AdminMainPageService service = new AdminMainPageService();
        handleDataToInsert(request, service);
        return "redirect: /park/admin";
    }

    private void handleDataToInsert(HttpServletRequest request, AdminMainPageService service) {
        LocalDate date = (LocalDate) request.getSession().getAttribute("selectedDate");
        Route route = (Route) request.getSession().getAttribute("selectedRoute");
        int driverId = Integer.parseInt(request.getParameter("driver_id"));
        int carId = Integer.parseInt(request.getParameter("car_id"));
        Assignment assignment = new Assignment();
        assignment.setStatus(Assignment.Status.assigned);
        assignment.setDate(date);
        assignment.setRoute(route);
        User driver = new User();
        driver.setId(driverId);
        assignment.setDriver(driver);
        Car car = new Car();
        car.setId(carId);
        assignment.setBus(car);
        service.insertAssignment(assignment);
    }
}

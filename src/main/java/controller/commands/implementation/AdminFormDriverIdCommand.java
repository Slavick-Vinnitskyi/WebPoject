package controller.commands.implementation;

import java.util.List;
import controller.commands.Command;
import model.entity.Car;
import model.entity.User;
import model.service.AdminMainPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AdminFormDriverIdCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        User selectedDriver = getDriverFromListById(request, driverId).orElse(new User());
        List<Car> cars = selectedDriver.getCars();
        HttpSession session = request.getSession();
        session.setAttribute("cars", cars);
        System.out.println("ajax session : " + session.getId());
        return "redirect: /park/admin";
    }

    private Optional<User> getDriverFromListById(HttpServletRequest request, int driverId) {
        List<User> driversInRequest = (List<User>) request.getSession().getAttribute("drivers");
        return driversInRequest.stream().filter(x -> x.getId() == driverId).findAny();
    }
}

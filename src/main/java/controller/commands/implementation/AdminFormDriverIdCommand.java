package controller.commands.implementation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import controller.commands.Command;
import model.entity.Car;
import model.entity.Route;
import model.entity.User;
import model.service.AdminCarPageService;
import model.service.AdminMainPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AdminFormDriverIdCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter("driverId").equals("-"))
            return null;
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        User selectedDriver = getDriverFromListById(request, driverId).orElse(new User());
        List<Car> cars = selectedDriver.getCars();
        cars.forEach(x->x.setYear(null));

//       request.getSession().setAttribute("cars", cars);
       response.setContentType("application/json");
       Gson json = new Gson();
       String stringJson = json.toJson(cars);
       try(PrintWriter writer = response.getWriter()) {
           writer.print(stringJson);
           System.out.println(stringJson);
       } catch (IOException e) {
           e.printStackTrace();
       }
        return null;
    }

    private Optional<User> getDriverFromListById(HttpServletRequest request, int driverId) {
        List<User> driversInRequest = (List<User>) request.getSession().getAttribute("drivers");
        return driversInRequest.stream().filter(x -> x.getId() == driverId).findAny();
    }
}

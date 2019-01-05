package controller.commands.implementation;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.google.gson.*;
import controller.commands.Command;
import model.entity.Car;
import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AdminFormDriverIdCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("driverId").equals("-"))
            return null;
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        User selectedDriver = getDriverFromListById(request, driverId).orElse(new User());
        List<Car> cars = selectedDriver.getCars();
        response.setContentType("application/json");
        Gson json = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        String stringJson = json.toJson(cars);
        try (PrintWriter writer = response.getWriter()) {
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

/**
 * Solution was inspired by https://github.com/google/gson/blob/master/UserGuide.md#TOC-Writing-a-Serializer
 */
class LocalDateAdapter implements JsonSerializer<LocalDate> {

    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}

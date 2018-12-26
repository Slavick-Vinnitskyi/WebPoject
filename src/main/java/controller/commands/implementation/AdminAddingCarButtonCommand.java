package controller.commands.implementation;

import controller.commands.Command;
import model.entity.Car;
import model.entity.enums.LicenseType;
import model.service.AdminCarPageService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdminAddingCarButtonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate date = LocalDate.parse(request.getParameter("date"), formatter);
        String mark = request.getParameter("mark");
        LicenseType type = LicenseType.valueOf(request.getParameter("sample-radio"));
        Car carToAdd = new Car();
        carToAdd.setYear(date);
        carToAdd.setLicenseType(type);
        carToAdd.setModel(mark);
        AdminCarPageService service = new AdminCarPageService();
        service.insertCar(carToAdd);
        return "redirect: /park/admin/add_car";
    }
}
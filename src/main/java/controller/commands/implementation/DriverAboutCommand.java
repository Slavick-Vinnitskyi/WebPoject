package controller.commands.implementation;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class DriverAboutCommand implements Command {


    @Override
    public String execute(HttpServletRequest request) {
//        User u = (User) request.getSession().getAttribute("user");
//        int userId = u.getId();

//        try {
//            DriverAboutService service = new DriverAboutService();
//            User user = service.getInfoAboutUser(userId);
//            System.out.println("DriverAboutPage");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return "/WEB-INF/driver/about.jsp";
    }
}
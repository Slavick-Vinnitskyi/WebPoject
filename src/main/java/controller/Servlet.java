package controller;

import controller.commands.*;
import controller.commands.implementation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(urlPatterns = "/park/*" , loadOnStartup = 1)
public class Servlet extends HttpServlet {

    private static ServletContext context;
    private Map<String, Command> commands = new HashMap<>();

    public static ServletContext getContext() {
        return context;
    }

    @Override
    public void init(ServletConfig servletConfig) {
        context = servletConfig.getServletContext();
        context.setAttribute("loggedUsers", new ConcurrentHashMap<Integer, HttpSession>());

        commands.put("index", new IndexCommand());
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("driver", new DriverCommand());
        commands.put("driver/history", new DriverHistoryCommand());
        commands.put("driver/about", new DriverAboutCommand());
        commands.put("admin", new AdminCommand());
        commands.put("admin/add_car", new AdminAddingCarCommand());
        commands.put("admin/add_route", new AdminAddingRouteCommand());
        commands.put("exception", new ExceptionCommand());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Command command = getCommand(request);
        String page = null;
        try {
            page = command.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Objects.requireNonNull(page).contains("redirect: ")) {
            response.sendRedirect(request.getContextPath() + page.replaceAll("redirect: ", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    private Command getCommand(HttpServletRequest request) {
        String ipAdress = request.getRemoteAddr();
        System.out.println("ip = " + ipAdress);
        String path = request.getRequestURI();
        System.out.println("processRequest, path=" + path);
        path = path.replaceAll(".*/park/", "");
        path = path.replaceAll(".*/park", "");
        return commands.getOrDefault(path, (r) -> "/park/index");
    }
}
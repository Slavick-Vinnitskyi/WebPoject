package controller.filters;

import model.exception.PageAccessException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Filter implements authentication mechanism
 */
@WebFilter(urlPatterns = {"/park/*"})
public class AuthenticationFilter implements Filter {
    private Map<String, List<String>> permissions;
    private String path;
    private HttpSession session;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        permissions = new ConcurrentHashMap<>();
        permissions.put("guest", Arrays.asList("login", "register", "", "index", "logout"));
        permissions.put("driver", Arrays.asList("driver", "driver/history", "driver/about", "logout", "", "index", "login"));
        permissions.put("admin", Arrays.asList("admin", "admin/add_car", "admin/add_route", "logout", "", "index", "login"));
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        path = req.getRequestURI();
        session = req.getSession();
        String role = getUserRole();

        cleanUpPath();

        if (!checkPermission(role)) {
            throw new PageAccessException("You don`t have permission to visit this page");
        }

        filterChain.doFilter(request, response);
    }

    private void cleanUpPath() {
        path = path.replaceAll(".*/park/", "");
        path = path.replaceAll(".*/park", "");
    }

    private String getUserRole() {
        return session.getAttribute("role") != null ?
                session.getAttribute("role").toString() : "guest";
    }

    private boolean checkPermission(String role) {
        return permissions.get(role).contains(path);
    }


    @Override
    public void destroy() {

    }
}

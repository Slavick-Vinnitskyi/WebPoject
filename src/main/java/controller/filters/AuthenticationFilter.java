package controller.filters;

import model.entity.User;
import model.exception.PageAccessException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Filter implements authentication mechanism
 TODO: сделать ЗАПРЕЩЕНИЕ для прохода а не РАЗРЕШЕНИЕ
 */
//@WebFilter(urlPatterns = {"/park/*"}, filterName = "authFilter")
public class AuthenticationFilter implements Filter {
    private Map<User.ROLE, List<String>> allowed;
    private String path;
    private HttpSession session;

    @Override
    public void init(FilterConfig filterConfig) {
        allowed = new ConcurrentHashMap<>();
        allowed.put(User.ROLE.guest, Arrays.asList("login", "register", "", "index", "logout"));
        allowed.put(User.ROLE.driver, Arrays.asList("driver", "driver/history", "driver/about", "logout", "", "index", "login"));
        allowed.put(User.ROLE.admin, Arrays.asList("admin", "admin/add_car", "admin/add_route", "logout", "", "index", "login"));
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;

        path = req.getRequestURI();
        session = req.getSession();
        User.ROLE role = getUserRole();

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

    private User.ROLE getUserRole() {
        return session.getAttribute("role") != null ?
                (User.ROLE) session.getAttribute("role") : User.ROLE.guest;
    }

    /**
     * @param role user role that try to access to some page
     * @return true if user is allowed to visit page
     */
    private boolean checkPermission(User.ROLE role) {
        return allowed.get(role).contains(path);
    }

    @Override
    public void destroy() {

    }
}

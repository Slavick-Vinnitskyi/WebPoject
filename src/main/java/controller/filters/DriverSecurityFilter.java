package controller.filters;

import model.entity.User;
import model.exception.PageAccessException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  Filter implements restriction access to the driver branch
 */
@WebFilter(urlPatterns = {"/park/driver/*"})
public class DriverSecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = getSession(servletRequest);

        User.ROLE role = getUserRole(session);

        if (!checkPermission(role)) {
            throw new PageAccessException("You don`t have permission to visit driver page");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private HttpSession getSession(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getSession();
    }

    private boolean checkPermission(User.ROLE role) {
        return role.toString().equals("driver");
    }

    @Override
    public void destroy() {

    }

    private User.ROLE getUserRole(HttpSession session) {
        return session.getAttribute("user") != null ?
                ((User) session.getAttribute("user")).getRole() : User.ROLE.guest;
    }

}

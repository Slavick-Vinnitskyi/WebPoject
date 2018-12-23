package controller.filters;

import model.entity.User;
import model.exception.PageAccessException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  Filter implements restriction access to the admin branch
 */
@WebFilter(urlPatterns = {"/park/admin/*"})
public class AdminSecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpSession session = getSession(servletRequest);

        User.ROLE role = getUserRole(session);

        if (!checkPermission(role)) {
            throw new PageAccessException("You don`t have permission to visit admin page");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private HttpSession getSession(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getSession();
    }

    /**
     * @param role user role that try to access to some page
     * @return true if user is allowed to visit page
     */
    private boolean checkPermission(User.ROLE role) {
        return role.toString().equals("admin");
    }

    @Override
    public void destroy() {

    }

    private User.ROLE getUserRole(HttpSession session) {
        return session.getAttribute("role") != null ?
                (User.ROLE) session.getAttribute("role") : User.ROLE.guest;
    }

}

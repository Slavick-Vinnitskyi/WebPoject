package controller.commands.implementation;

import model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LoginCommandTest {

    @Test
    public void execute() {
    }

    private LoginCommand loginCommand = new LoginCommand();
    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    private HttpSession session = Mockito.mock(HttpSession.class);
    private ServletContext context = Mockito.mock(ServletContext.class);
    private ArrayList<User> users = new ArrayList<>();


    @Before
    public void setUp() {
        Mockito.when(request.getServletContext()).thenReturn(context);
        Mockito.when(context.getAttribute("loggedUsers")).thenReturn(users);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getParameter("username")).thenReturn("asdf");
        Mockito.when(request.getParameter("password")).thenReturn("asdf");
    }

    @Test
    public void process() {
        String uri = loginCommand.execute(request, response);
        assertEquals("/login.jsp", uri);
//        assertTrue(!users.isEmpty());
    }
}
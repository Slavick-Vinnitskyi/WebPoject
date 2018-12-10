package controller.listener;

import controller.commands.utils.ServletUtility;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Set;


public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        Set<String> loggedUsers = ServletUtility.getLoggedUsers(httpSessionEvent);
        System.out.println("Session closed");
        String userName = ServletUtility.getUserName(session);
        loggedUsers.remove(userName);

        ServletUtility.setLoggedUsers(session, loggedUsers);
    }
}

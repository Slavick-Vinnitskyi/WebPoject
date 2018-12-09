package model.service;

import model.entity.User;
import model.entity.dao.DaoFactory;
import model.entity.dao.UserDao;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public UserService() throws Exception {
    }

    public List<User> getAllUser() throws SQLException, ClassNotFoundException {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }

    public User getUser(String name) throws SQLException, ClassNotFoundException {
        Stream<User> userStream = daoFactory.createUserDao().findAll().stream();
        return (User) userStream.filter(x->x.getLogin().equals(name)).toArray()[0];
    }
}
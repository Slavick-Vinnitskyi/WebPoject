package model.entity.dao;

import model.entity.User;
import model.entity.dao.implementation.UserNotFoundException;

import java.sql.SQLException;

public interface UserDao extends GenericDao<User> {
    User findByName(String login);
    User findByLoginAndPassword(String login, String password) throws UserNotFoundException, SQLException;
}
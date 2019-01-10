package model.entity.dao;

import model.entity.User;
import model.exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findByName(String login);
    User findByLoginAndPassword(String login, String password) throws UserNotFoundException;
    List<User> findAllCarToDriver();
}
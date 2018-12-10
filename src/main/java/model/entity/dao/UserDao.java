package model.entity.dao;

import model.entity.User;

public interface UserDao extends GenericDao<User> {
    User findByName(String login);
}
package model.service;

import model.entity.User;
import model.entity.dao.DaoFactory;
import model.entity.dao.UserDao;
import model.exception.UserNotFoundException;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Будет валидировать введенные данные на коректность
 */
public class LoginService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * @param login user login
     * @param password user password
     * @return User if he exist in database
     * @throws UserNotFoundException when user does`nt exist in database
     */
    public Optional<User> validateUser(String login, String password) {
        try(UserDao userDao = daoFactory.createUserDao()) {
            return Optional.ofNullable(userDao.findByLoginAndPassword(login, password));
        }
    }

}

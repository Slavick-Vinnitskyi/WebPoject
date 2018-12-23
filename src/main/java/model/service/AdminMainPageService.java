package model.service;

import model.entity.Assignment;
import model.entity.Route;
import model.entity.dao.AssignmentDao;
import model.entity.dao.DaoFactory;
import model.entity.dao.RouteDao;

import java.sql.SQLException;
import java.util.List;

/**
 *  Выводить маршруты
 */
public class AdminMainPageService {
    private DaoFactory daoFactory = DaoFactory.getInstance();


    public List<Assignment> getAssignmentsByStatus(Assignment.Status status) throws SQLException, ClassNotFoundException {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            return dao.findByStatus(status);
        }
    }


}

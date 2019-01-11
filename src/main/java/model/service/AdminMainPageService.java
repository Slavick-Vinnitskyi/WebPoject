package model.service;

import model.entity.Assignment;
import model.entity.Route;
import model.entity.User;
import model.entity.dao.AssignmentDao;
import model.entity.dao.DaoFactory;
import model.entity.dao.RouteDao;
import model.entity.dao.UserDao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *  Выводить маршруты
 */
public class AdminMainPageService {
    private DaoFactory daoFactory = DaoFactory.getInstance();


    public List<Assignment> getAssignmentsByStatus(Assignment.Status status) {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            return dao.findByStatus(status);
        }
    }

    public boolean deleteAssignment(int assignmentId) {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            dao.delete(assignmentId);
            return true;
        }
    }

    public List<Route> getAllRoutes() {
        try (RouteDao dao = daoFactory.createRouteDao()) {
            return dao.findAll();
        }
    }

    public Route getRouteById(int id) {
        try (RouteDao dao = daoFactory.createRouteDao()) {
            return dao.findById(id);
        }
    }

    public List<User> getFreeCarsAndDriver(LocalDate date) {
        List<User> allDriversWithCars;
        List<Assignment> assignmentsForThisDate = new ArrayList<>();
        try (UserDao dao = daoFactory.createUserDao()) {
            allDriversWithCars = dao.findAllCarToDriver();
            try (AssignmentDao assignmentDao = daoFactory.createAssignmentDao()) {
                assignmentsForThisDate = assignmentDao.findAllByDate(Date.valueOf(date));

            } catch (NullPointerException ex) {
                return null;
            }
            return allDriversWithCars;
        }
    }

    public Assignment insertAssignment(Assignment entity) {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            return dao.create(entity);
        }
    }
}
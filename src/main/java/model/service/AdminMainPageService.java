package model.service;

import model.entity.Assignment;
import model.entity.Car;
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
import java.util.stream.Collectors;

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
        List<Assignment> assignmentsForThisDate;

        try (UserDao dao = daoFactory.createUserDao()) {
            allDriversWithCars = dao.findAllCarToDriver();
            try (AssignmentDao assignmentDao = daoFactory.createAssignmentDao()) {

                assignmentsForThisDate = assignmentDao.findAllByDate(Date.valueOf(date));
                List<User> busyDrivers = assignmentsForThisDate.stream().map(Assignment::getDriver).collect(Collectors.toList());
                List<Car> busyCars = assignmentsForThisDate.stream().map(Assignment::getBus).collect(Collectors.toList());
                removeBusyDrivers(allDriversWithCars, busyDrivers);
                removeBusyCars(allDriversWithCars, busyCars);
            }
            return allDriversWithCars;
        }
    }

    private void removeBusyCars(List<User> allDriversWithCars, List<Car> busyCars) {
        for (User allDriversWithCar : allDriversWithCars) {
            List<Car> allCars = allDriversWithCar.getCars();
            for (int j = 0; j < allCars.size(); j++) {
                for (Car busyCar : busyCars) {
                    if (allCars.get(j).equals(busyCar)) {
                        allDriversWithCar.getCars().remove(j);
                    }
                }
            }
        }
    }

    private void removeBusyDrivers(List<User> allDriversWithCars, List<User> busyDrivers) {
        for (int i = 0; i < allDriversWithCars.size(); i++) {
            for (User busyDriver : busyDrivers) {
                if (allDriversWithCars.get(i).equals(busyDriver)) {
                    allDriversWithCars.remove(i);
                }
            }
        }
    }

    public Assignment insertAssignment(Assignment entity) {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            return dao.create(entity);
        }
    }
}
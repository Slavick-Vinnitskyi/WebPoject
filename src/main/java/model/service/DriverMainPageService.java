package model.service;

import model.entity.Assignment;
import model.entity.Route;
import model.entity.User;
import model.entity.dao.AssignmentDao;
import model.entity.dao.DaoFactory;
import model.entity.dao.RouteDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Будет :
 *  выводить назначения
 *  сортировать по :
 *      Городу отправления
 *      Городу прибытия
 *      Автомобилю
 *      Дате
 *  Осуществлять принятие (выполнение) водителем назначения
 *
 */
public class DriverMainPageService {
    private DaoFactory daoFactory = DaoFactory.getInstance();


    public List<Assignment> getAssignmentsForDriverByStatus(int id, Assignment.Status status) throws SQLException, ClassNotFoundException {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            return dao.findForUser(id, status);
        }
    }
    public Assignment getAssignmentById(int assignmentId) throws SQLException, ClassNotFoundException {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            return dao.findById(assignmentId);
        }
    }

    public void updateAssignment(Assignment assignment) throws SQLException, ClassNotFoundException {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            dao.updateToAppliedForUser(assignment);
        }
    }

}

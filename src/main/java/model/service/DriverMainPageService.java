package model.service;

import model.entity.Assignment;
import model.entity.dao.AssignmentDao;
import model.entity.dao.DaoFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Assignment> getAssignmentsForDriverByStatus(int id, int limit, int offset, Assignment.Status status) {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            return Optional.ofNullable(dao.findForUser(id, limit, offset, status)).orElse(new ArrayList<>());
        }
    }

    public Assignment getAssignmentById(int assignmentId) {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            return dao.findById(assignmentId);
        }
    }

    public void updateAssignment(int assignmentId) {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            dao.updateToApplied(assignmentId);
        }
    }

    public int getAssignmentsPagesNumber(int limit, int driverId, Assignment.Status status) {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            int count = dao.getCountRowsForDriverByStatus(driverId, status);
            return (int) Math.ceil((float) count / limit);
        }
    }
}
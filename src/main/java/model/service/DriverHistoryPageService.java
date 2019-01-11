package model.service;

import model.entity.Assignment;
import model.entity.dao.AssignmentDao;
import model.entity.dao.DaoFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Будет выводить историю поездок
 * сортировать по :
 *  Городу отправления
 *  Городу прибытия
 *  Автомобилю
 *  Дате
 */
public class DriverHistoryPageService {

    private DaoFactory daoFactory = DaoFactory.getInstance();



    public List<Assignment> getPastAssignmentsForDriver(int driverId, int limit, int offset) {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            return dao.findPastForUser(driverId, limit, offset);
        }
    }

    public int getTotalPagesNumber(int driverId, int limit) {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            int count = dao.getCountHistoryRows(driverId);
            return (int) Math.ceil((float) count / limit);
        }
    }
}

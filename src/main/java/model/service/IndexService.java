package model.service;

import model.entity.Assignment;
import model.entity.dao.AssignmentDao;
import model.entity.dao.DaoFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Выводить для гостя все бующие назначения
 */
public class IndexService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public IndexService() throws Exception {
    }

    public List<Assignment> getFutureAssignments() throws SQLException, ClassNotFoundException {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            return dao.findAll();
        }
    }
}

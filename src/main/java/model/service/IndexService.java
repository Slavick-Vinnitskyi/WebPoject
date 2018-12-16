package model.service;

import model.dto.IndexDto;
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

    public List<IndexDto> getFutureAssignments() throws SQLException, ClassNotFoundException {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            return dao.findAllFutureApplied();
        }
    }
}

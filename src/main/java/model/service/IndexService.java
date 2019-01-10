package model.service;

import model.dto.IndexDto;
import model.entity.dao.AssignmentDao;
import model.entity.dao.DaoFactory;

import java.util.List;

/**
 * Выводит для гостя все бующие назначения
 */
public class IndexService {
    private DaoFactory daoFactory = DaoFactory.getInstance();


    public List<IndexDto> getFutureAssignments(int limit, int offset) {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            return dao.findAllFutureApplied(limit, offset);
        }
    }

    public int getTotalPagesNumber(int limit) {
        try (AssignmentDao dao = daoFactory.createAssignmentDao()) {
            int count = dao.getCountRows();
            return (int) Math.ceil((float) count / limit);
        }
    }
}
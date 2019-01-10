package model.entity.dao;

import model.dto.IndexDto;
import model.entity.Assignment;

import java.sql.Date;
import java.util.List;

public interface AssignmentDao extends GenericDao<Assignment> {
    List<Assignment> findForUser(int id, Assignment.Status status);
    List<Assignment> findPastForUser(int id);
    void updateToAppliedForUser(Assignment entity);

    List<IndexDto> findAllFutureApplied(int limit, int offset);

    List<Assignment> findByStatus(Assignment.Status status);

    List<Assignment> findAllByDate(Date date);

    int getCountRows();
}

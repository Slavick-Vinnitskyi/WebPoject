package model.entity.dao;

import model.dto.IndexDto;
import model.entity.Assignment;

import java.sql.Date;
import java.util.List;

public interface AssignmentDao extends GenericDao<Assignment> {
    List<Assignment> findForUser(int id, int limit, int offset, Assignment.Status status);
    List<Assignment> findPastForUser(int id);
    void updateToApplied(int assignmentId);

    List<IndexDto> findAllFutureApplied(int limit, int offset);

    List<Assignment> findByStatus(Assignment.Status status);

    List<Assignment> findAllByDate(Date date);

    int getCountRows();

    int getCountRowsForDriverByStatus(int driverId, Assignment.Status status);
}

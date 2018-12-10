package model.entity.dao;

import model.entity.Assignment;

import java.util.List;

public interface AssignmentDao extends GenericDao<Assignment> {
    List<Assignment> findForUser(int id, Assignment.Status status);
    void updateToAppliedForUser(Assignment entity, int id);
}

package model.entity.dao.mappers.implementation;


import model.dto.IndexDto;
import model.entity.Assignment;
import model.entity.Route;
import model.entity.dao.mappers.ObjectMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Этот маппер будет собирать DTO обьект нужный для index страницы
 */
public class IndexDtoMapper implements ObjectMapper<IndexDto> {

    @Override
    public IndexDto extractFromResultSet(ResultSet resultSet) throws SQLException {
        IndexDto dto = new IndexDto();
        dto.setAssignmentId(resultSet.getInt("assignment_id"));
        dto.setDate((resultSet.getDate("date_start").toLocalDate()));
        dto.setStatus(Assignment.Status.valueOf(resultSet.getString("status")));
        Route route = new RouteMapper().extractFromResultSet(resultSet);
        dto.setRoute(route);
        return dto;
    }

    @Override
    public IndexDto makeUnique(Map<Integer, IndexDto> cache, IndexDto user) {
        return null;
    }
}

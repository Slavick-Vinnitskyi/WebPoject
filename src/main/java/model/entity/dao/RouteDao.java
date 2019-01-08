package model.entity.dao;

import model.dto.LocalizedRoute;
import model.entity.Route;

public interface RouteDao extends GenericDao<Route> {
    LocalizedRoute createLocalizedRoute(LocalizedRoute route);
}

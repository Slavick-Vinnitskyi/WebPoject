package model.service;

import model.dto.LocalizedRoute;
import model.entity.Route;
import model.entity.dao.DaoFactory;
import model.entity.dao.RouteDao;

import java.sql.SQLException;
import java.util.List;

public class AdminRoutePageService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Route> getAllRoutes() {
        try (RouteDao dao = daoFactory.createRouteDao()) {
            return dao.findAll();
        }
    }

    public LocalizedRoute insertRoute(LocalizedRoute route) {
        try(RouteDao dao = daoFactory.createRouteDao()){
           return dao.createLocalizedRoute(route);
        }
    }

}

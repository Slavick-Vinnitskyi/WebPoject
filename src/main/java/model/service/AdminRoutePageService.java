package model.service;

import model.entity.Route;
import model.entity.dao.DaoFactory;
import model.entity.dao.RouteDao;

import java.sql.SQLException;
import java.util.List;

public class AdminRoutePageService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Route> getAllRoutes() throws SQLException, ClassNotFoundException {
        try (RouteDao dao = daoFactory.createRouteDao()) {
            return dao.findAll();
        }
    }

    public void insertRoute(Route route) throws SQLException, ClassNotFoundException {
        try(RouteDao dao = daoFactory.createRouteDao()){
            dao.create(route);
        }
    }

}

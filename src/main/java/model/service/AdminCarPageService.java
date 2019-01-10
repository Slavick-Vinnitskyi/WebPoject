package model.service;

import model.entity.Car;
import model.entity.dao.CarDao;
import model.entity.dao.DaoFactory;

import java.sql.SQLException;
import java.util.List;

public class AdminCarPageService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Car> getAllRoutes() {
        try (CarDao dao = daoFactory.createCarDao()) {

            return dao.findAll();
        }
    }

    public void insertCar(Car entity) {
        try (CarDao dao = daoFactory.createCarDao()) {
            dao.create(entity);
        }
    }
}

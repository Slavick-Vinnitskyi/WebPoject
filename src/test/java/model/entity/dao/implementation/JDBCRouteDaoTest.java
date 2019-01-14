package model.entity.dao.implementation;

import config.DBConfigurer;
import model.dto.LocalizedRoute;
import model.entity.Route;
import model.entity.dao.DaoFactory;
import model.entity.dao.DaoFactoryTest;
import model.entity.dao.RouteDao;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class JDBCRouteDaoTest {
    private static DBConfigurer dbRunner;
    private static DaoFactory daoFactory;

    @BeforeClass
    public static void beforeAllTests() {
        dbRunner = new DBConfigurer();
        dbRunner.setUp();
        daoFactory = new DaoFactoryTest(dbRunner);
    }

    @Before
    public void beforeEachTest() {
        dbRunner.refresh();
    }

    @AfterClass
    public static void afterAllTests() {
        dbRunner.tearDown();
    }

    @Test
    public void findAll() {
        RouteDao dao = daoFactory.createRouteDao();
        int expectedCount = 1;
        int actualCount = dao.findAll().size();
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void findById() {
        RouteDao dao = daoFactory.createRouteDao();
        int testedId = 1;
        String expectedStart = "Krakov";
        String expectedEnd = "Berlin";
        Route route = dao.findById(testedId);
        assertEquals(expectedStart, route.getStart());
        assertEquals(expectedEnd, route.getFinish());
    }

    @Test
    public void create() {
        RouteDao dao = daoFactory.createRouteDao();
        Route route = new Route();
        route.setStart("Kyiv");
        route.setFinish("London");
        assertEquals(1, dao.findAll().size());
        Route actual = dao.create(route);
        assertEquals(2,dao.findAll().size());
        assertEquals(route, actual);

    }

    @Test
    public void createLocalizedRoute() {
        RouteDao dao = daoFactory.createRouteDao();
        HashMap<String, LocalizedRoute.LocalizedPart> localizedPartMap = new HashMap<>();

        LocalizedRoute routeWrapper = new LocalizedRoute(new Route("Kyiv", "London"), localizedPartMap);
        LocalizedRoute.LocalizedPart localizedPart = routeWrapper.new LocalizedPart( "Київ", "Лондон");

        localizedPartMap.put("ua", localizedPart);

        assertEquals(1, dao.findAll().size());
        LocalizedRoute actual = dao.createLocalizedRoute(routeWrapper);
        assertEquals(2,dao.findAll().size());
        assertEquals(routeWrapper, actual);
    }
}
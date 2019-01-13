package model.entity.dao.implementation;

import config.DBConfigurer;
import model.entity.Route;
import model.entity.dao.DaoFactory;
import model.entity.dao.DaoFactoryTest;
import model.entity.dao.RouteDao;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        Assert.assertEquals(expectedCount, actualCount);
    }

    @Test
    public void findById() {
        RouteDao dao = daoFactory.createRouteDao();
        int testedId = 1;
        String expectedStart = "Krakov";
        String expectedEnd = "Berlin";
        Route route = dao.findById(testedId);
        Assert.assertEquals(expectedStart, route.getStart());
        Assert.assertEquals(expectedEnd, route.getFinish());
    }

}
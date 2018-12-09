package model.entity.dao.implementation;

import org.apache.commons.dbcp.BasicDataSource;
import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static BasicDataSource dataSource;

    public static DataSource getDataSource() {

        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    dataSource = new BasicDataSource();
                    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
                    dataSource.setUrl("jdbc:mysql://localhost:3306/edited_car_park");
                    dataSource.setUsername("root");
                    dataSource.setPassword("root");
                    dataSource.setMaxOpenPreparedStatements(100);
                }
            }
        }
        return dataSource;
    }
}
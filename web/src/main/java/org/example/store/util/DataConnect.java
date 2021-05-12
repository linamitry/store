package org.example.store.util;

import org.example.store.config.DbConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnect {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            DbConfig config = new ReadConfigUtil().getDbConfig();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(config.getUrl(),config.getUserName(),config.getPassword());
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

package org.example.store.utils;

import org.apache.log4j.Logger;
import org.example.store.config.DbConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnect {
    static Logger log = Logger.getLogger(DataConnect.class.getName());

    public static Connection getConnection() {
        Connection connection = null;
        try {
            DbConfig config = new ReadConfigUtil().getDbConfig();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(config.getUrl(),config.getUserName(),config.getPassword());
        } catch (SQLException | ClassNotFoundException | IOException e) {
            log.error("Exception while trying to get connection",e);
        }
        return connection;
    }
}

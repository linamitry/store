package org.example.store.utils;

import org.apache.log4j.Logger;
import org.example.store.config.DbConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigUtil {
    static Logger log = Logger.getLogger(ReadConfigUtil.class.getName());

    public DbConfig getDbConfig() throws IOException {
        String propFileName = "configuration.properties";

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)){
            Properties prop = new Properties();

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            return new DbConfig(prop.getProperty("postgres.url"),
                                prop.getProperty("postgres.username"),
                                prop.getProperty("postgres.password"));

        } catch (Exception e) {
            log.error("Exception while trying to read configuration.properties",e);
        }
        return null;
    }
}

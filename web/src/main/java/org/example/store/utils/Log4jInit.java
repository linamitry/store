package org.example.store.utils;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

@WebListener
public class Log4jInit implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String fullPath = sce.getServletContext().getRealPath("")+ File.separator+"WEB-INF/classes/log4j.properties";
        PropertyConfigurator.configure(fullPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

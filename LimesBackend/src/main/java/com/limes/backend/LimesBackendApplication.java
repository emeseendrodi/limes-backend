package com.limes.backend;

import com.limes.backend.persistence.DatabaseFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LimesBackendApplication {

    protected static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    private static Properties persistenceProperties;

    public static void main(String[] args) {
        SpringApplication.run(LimesBackendApplication.class, args);
        readPersistenceProperties();
        DatabaseFactory.init();
    }

    private static void readPersistenceProperties() {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(new File(System.getProperty("user.dir")+"\\persistence.properties")));
            persistenceProperties = new Properties();
            persistenceProperties.load(in);
        } catch (FileNotFoundException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                logger.error(ex);
            }
        }
    }

    public static Properties getPersistenceProperties() {
        return persistenceProperties;
    }
}

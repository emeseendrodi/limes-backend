package com.limes.backend;

import com.limes.backend.persistence.DatabaseFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class LimesBackendApplication {

    private static Properties persistenceProperties;

    public static void main(String[] args) {
        SpringApplication.run(LimesBackendApplication.class, args);
        readPersistenceProperties();
        DatabaseFactory.init();
    }

    private static void readPersistenceProperties() {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(new File(System.getProperty("user.dir") + "\\persistence.properties")));
            persistenceProperties = new Properties();
            persistenceProperties.load(in);
        } catch (FileNotFoundException ex) {
            log.error(ex.getLocalizedMessage());
        } catch (IOException ex) {
            log.error(ex.getLocalizedMessage());
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                log.error(ex.getLocalizedMessage());
            }
        }
    }

    public static Properties getPersistenceProperties() {
        return persistenceProperties;
    }
}

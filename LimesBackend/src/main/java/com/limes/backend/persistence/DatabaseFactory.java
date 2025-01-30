/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.persistence;

import com.limes.backend.LimesBackendApplication;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.spi.PersistenceProvider;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.HibernatePersistenceProvider;

/**
 *
 * @author Mate Forster
 */
@Slf4j
public class DatabaseFactory {

    private static EntityManagerFactory emf;

    public static EntityManager getEntityManager() {

        if (emf == null) {
            PersistenceProvider provider = new HibernatePersistenceProvider();
            Map prop = new HashMap();
            prop.put("jakarta.persistence.jdbc.url", LimesBackendApplication.getPersistenceProperties().get("javax.persistence.jdbc.url"));
            prop.put("jakarta.persistence.jdbc.user", LimesBackendApplication.getPersistenceProperties().get("javax.persistence.jdbc.user"));
            prop.put("jakarta.persistence.jdbc.driver", LimesBackendApplication.getPersistenceProperties().get("javax.persistence.jdbc.driver"));
            prop.put("jakarta.persistence.jdbc.password", LimesBackendApplication.getPersistenceProperties().get("javax.persistence.jdbc.password"));

            try {
                emf = provider.createEntityManagerFactory("com.limes_LimesBackend_jar_0.0.1-SNAPSHOTPU", prop);
                log.info("Persistence properties has been read, EM set up.");
            } catch (Exception e) {
                emf = Persistence.createEntityManagerFactory("com.limes_LimesBackend_jar_0.0.1-SNAPSHOTPU");
                log.warn("Failed to initialize hibernate with properties file, going with basic settings!");
            }
        }
        return emf.createEntityManager();
    }
    
    public static boolean init(){
       return getEntityManager() != null;
    }
}

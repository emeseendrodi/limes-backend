/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceConfiguration;

/**
 *
 * @author Mate Forster
 */
public class DatabaseFactory {

    private static EntityManagerFactory emf;
    public static void setUp() {
        emf = Persistence.createEntityManagerFactory("com.limes_LimesBackend_jar_0.0.1-SNAPSHOTPU");
    }
    
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}

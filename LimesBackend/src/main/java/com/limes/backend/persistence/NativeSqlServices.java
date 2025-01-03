/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.persistence;

import com.limes.backend.exception.persistence.LimesPersistenceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Mate Forster
 */
public class NativeSqlServices {

    @PersistenceContext
    static EntityManager em = DatabaseFactory.getEntityManager();

    public static List<?> executeNativeQueryWithClassEnforce(String sql, Class c) {
        return em.createNativeQuery(sql, c).getResultList();
    }

    public static int insertNative(String sql) throws LimesPersistenceException {
        em.getTransaction().begin();
        int rowsChanged = em.createNativeQuery(sql).executeUpdate();
        switch (rowsChanged) {
            case 0 -> throw new LimesPersistenceException("Error during native insertion, no rows were affected!");
            default -> {
                return rowsChanged;
            }
        }      
    }
}

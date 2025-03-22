/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.limes.backend.persistence;

import com.limes.backend.constants.MessageConstants;
import com.limes.backend.exception.persistence.LimesPersistenceException;
import jakarta.persistence.EntityManager;
import java.util.List;

public class NativeSqlServices {

//    @PersistenceContext
//    static EntityManager em;
//    static {
//        em = DatabaseFactory.getEntityManager();
//    }
    public static List<?> executeNativeQueryWithClassEnforce(String sql, Class c) {
//        resetEm();
        EntityManager em = DatabaseFactory.getEntityManager();
        return em.createNativeQuery(sql, c).getResultList();
    }

    public static Object executeNativeQueryWithClassEnforceOneLiner(String sql, Class c) {
//        resetEm();
        EntityManager em = DatabaseFactory.getEntityManager();
        List result = em.createNativeQuery(sql, c).getResultList();
        em.close();
        if (result == null || result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public static List<?> executeNativeQuery(String sql) {
//        resetEm();
        EntityManager em = DatabaseFactory.getEntityManager();
        return em.createNativeQuery(sql).getResultList();
    }

    public static Object executeNativeQueryOneLiner(String sql) {
//        resetEm();
        EntityManager em = DatabaseFactory.getEntityManager();
        List result = em.createNativeQuery(sql).getResultList();
        em.close();
        if (result == null || result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public static int insertNative(String sql) throws LimesPersistenceException {
        EntityManager em = DatabaseFactory.getEntityManager();
        em.getTransaction().begin();
        int rowsChanged = em.createNativeQuery(sql).executeUpdate();
        switch (rowsChanged) {
            case 0 -> {
                em.getTransaction().rollback();
                em.close();
                throw new LimesPersistenceException(MessageConstants.LOG_ERROR_DURING_NATIVE_INSERT);
            }
            default -> {
                em.getTransaction().commit();
                em.close();
                return rowsChanged;
            }
        }
    }

    public static int deleteNative(String sql) throws LimesPersistenceException {
        EntityManager em = DatabaseFactory.getEntityManager();
        em.getTransaction().begin();
        int rowsChanged = em.createNativeQuery(sql).executeUpdate();
        switch (rowsChanged) {
            case 0 -> {
                em.getTransaction().rollback();
                em.close();
                throw new LimesPersistenceException(MessageConstants.LOG_ERROR_DURING_NATIVE_INSERT);
            }
            default -> {
                em.getTransaction().commit();
                em.close();
                return rowsChanged;
            }
        }
    }

    public static Object deleteNativeWithCustomResult(String sql, Class c) throws LimesPersistenceException {
        EntityManager em = DatabaseFactory.getEntityManager();
        em.getTransaction().begin();
        List result = em.createNativeQuery(sql, c).getResultList();
        if (result == null || result.isEmpty()) {
            em.getTransaction().rollback();
            em.close();
            throw new LimesPersistenceException(MessageConstants.LOG_ERROR_DURING_NATIVE_DELETE);
        }
        em.getTransaction().commit();
        em.close();
        return result.get(0);
    }

//    private static void resetEm() {
//        em.clear();
//    }
}

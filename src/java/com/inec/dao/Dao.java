/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inec.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
public class Dao {
    
    public EntityManagerFactory emf;
    public EntityManager em;
    
    public Dao()
    {
         this.emf = Persistence.createEntityManagerFactory("sisgedPU");
         this.em = emf.createEntityManager();
    }
    
    public boolean guardar(Serializable entity)
    {
        boolean resp = true;
        try {
            EntityTransaction tr = em.getTransaction();
            tr.begin();
            em.persist(entity);
            tr.commit();
        } catch (Exception e) {
            resp = false;
            System.err.println("**Error: " + e.toString());
        }
        return resp;
    }
    
    public boolean actualizar(Serializable entity)
    {
        boolean resp = true;
        try {
            EntityTransaction tr = em.getTransaction();
            tr.begin();
            em.merge(entity);
            tr.commit();
        } catch (Exception e) {
            resp = false;
            System.err.println("**Error: " + e.toString());
        }
        return resp;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inec.dao;

import com.inec.mapping.Area;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
public class AreaDao extends Dao{
    
     public List<Area> listarAreas()
    {
        List<Area> lista = null;
        try {
            EntityTransaction tr = this.em.getTransaction();
                tr.begin();
                Query q = this.em.createQuery("SELECT ar from Area ar ORDER BY ar.nombre");
                lista = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error: "+e.toString());
        }
        
        return lista;
    }
    
}

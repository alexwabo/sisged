/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inec.dao;

import com.inec.mapping.Area;
import com.inec.mapping.Departamento;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
public class DepartamentosDao extends Dao{
    
    public List<Departamento> listarDepartamentosxArea(Area area)
    {
        List<Departamento> lista = null;
        try {
            EntityTransaction tr = this.em.getTransaction();
                tr.begin();
                Query q = this.em.createQuery("SELECT dep from Departamento dep WHERE dep.idarea = :area ORDER BY dep.nombre");
                q.setParameter("area", area);
                lista = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error: "+e.toString());
        }
        
        return lista;
    }
    
}

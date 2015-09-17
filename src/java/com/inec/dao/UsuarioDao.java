
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inec.dao;

import com.inec.mapping.Usuarios;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
public class UsuarioDao extends Dao{
    
    
    
    public Usuarios Login(Usuarios usuario)
    {
        Usuarios usr = null;
        try {
            EntityTransaction tr = this.em.getTransaction();
                tr.begin();
                Query q = this.em.createQuery("SELECT usr FROM Usuarios usr WHERE usr.identificador = ?1 AND usr.password = ?2 and usr.estado = 'A'");
                q.setParameter("1", usuario.getIdentificador());
                q.setParameter("2", usuario.getPassword());
                usr = (Usuarios)q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: "+e.toString());
        }
        return usr;
    }
    
    //Lista usuarios dependiendo del rol
    public List<Usuarios> listarUsuariosxRol(Usuarios usuario)
    {
        List<Usuarios> lista = null;
        Query q=null;
        if(usuario.getIdrol().getIdrol() == 1) //SuperAdmin Todos
        {
            q = this.em.createQuery("SELECT usr FROM Usuarios usr ORDER BY usr.apellidos");
        }
        
        if(usuario.getIdrol().getIdrol() == 1) //Admin los que el creo
        {
            q = this.em.createQuery("SELECT usr FROM Usuarios usr where usr.usuarioCrea = :usuario");
            q.setParameter("usuario", usuario);
        }
        
        try {
            EntityTransaction tr = this.em.getTransaction();
                tr.begin();    
                 lista = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error: "+e.toString());
        }
        
        return lista;
    }
    
}

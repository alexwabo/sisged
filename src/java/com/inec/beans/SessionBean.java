/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inec.beans;

import com.inec.mapping.Rol;
import com.inec.mapping.Usuarios;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class SessionBean implements Serializable{

    private static final long serialVersionUID = 1L;
 
    private boolean mostrarMenu=false;
    private Usuarios usuario;
    private Rol rol;
    private HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public boolean isMostrarMenu() {
        return mostrarMenu;
    }

    public void setMostrarMenu(boolean mostrarMenu) {
        this.mostrarMenu = mostrarMenu;
    }
    
    /**
     * Creates a new instance of SessionBean
     */
    public SessionBean() {
        usuario = new Usuarios();
        rol = new Rol();
        try {
            usuario = (Usuarios) sess.getAttribute("usuario");
            if(usuario != null)
            {
                mostrarMenu = true;
                rol = (Rol) sess.getAttribute("rol");
            }
        } catch (Exception e) {
        }   
    }
    
    public void cerrarSesion() throws IOException
    {
        try {
            sess.removeAttribute("usuario");
            sess.removeAttribute("rol");
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } catch (Exception e) {
            System.out.println("***Error: "+e.getStackTrace());
        }
            FacesContext.getCurrentInstance().getExternalContext().redirect("/sisged/faces/login.xhtml");
    }
    
}

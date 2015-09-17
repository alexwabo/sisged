/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inec.beans;

import com.inec.dao.UsuarioDao;
import com.inec.mapping.Usuarios;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String usuario;
    private String clave;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
  
    /**
     * Creates a new instance of loginDao
     */
    public LoginBean() {
    }
    
    public void login() throws IOException
    {
        Usuarios usr = new Usuarios();
        usr.setPassword(clave);
        usr.setIdentificador(usuario);
        usr = new UsuarioDao().Login(usr);
        if(usr==null)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos Incorrectos"));
        }
        {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",usr);
            FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
        }
        
        
    }
    
}

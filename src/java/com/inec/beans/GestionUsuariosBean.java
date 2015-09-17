/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inec.beans;

import com.inec.dao.AreaDao;
import com.inec.dao.DepartamentosDao;
import com.inec.dao.UsuarioDao;
import com.inec.mapping.Area;
import com.inec.mapping.Departamento;
import com.inec.mapping.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
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
public class GestionUsuariosBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private Usuarios usuario;
    private HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    private Departamento departamento;
    private Area area;
    private String claveAux;
    private List<Departamento> listaDepartamentos;
    private List<Usuarios> listaUsuarios;
    private List<Area> listaArea;
    private Usuarios usuariosSession;
    
    public String getClaveAux() {
        return claveAux;
    }

    public void setClaveAux(String claveAux) {
        this.claveAux = claveAux;
    }

    
    
    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Departamento> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List<Departamento> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List<Area> getListaArea() {
        return listaArea;
    }

    public void setListaArea(List<Area> listaArea) {
        this.listaArea = listaArea;
    }
    
    
    
    public GestionUsuariosBean() {
        
        usuario = new Usuarios();
        listaDepartamentos = new ArrayList<Departamento>();
        usuariosSession = (Usuarios) sess.getAttribute("usuario");
        listaUsuarios = new UsuarioDao().listarUsuariosxRol(usuariosSession);
        listaArea = new AreaDao().listarAreas();
        departamento = new Departamento();
        area = new Area();
        
    }
    
    public void guardarUsuario()
    {
        
        if(usuario.getId() != null)
        {
            usuario.setFechaModificacion(new Date());
            usuario.setUsuarioModifica(usuariosSession);
            
            if(new UsuarioDao().actualizar(usuario))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Respuesta: ", "Datos actualizados correctamente"));
                usuario = new Usuarios();
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Respuesta: ", "Ocurrió un error al guardar el registro"));    
            }
        }
        else
        {
            usuario.setUsuarioCrea(usuariosSession);
            usuario.setFechaCreacion(new Date());
            usuario.setEstado("A");
            if(new UsuarioDao().guardar(usuario))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Respuesta: ", "Usuario creado correctamente"));
                usuario = new Usuarios();
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Respuesta: ", "Ocurrió un error al guardar el registro"));
            }
        }
        
        
        
    }
    
    public void estableceUsuario(Usuarios usr)
    {
        usuario = usr;
    }
    
    
    public void estableceDepartamento(Departamento dep)
    {
        departamento = dep;
    }
    
     public void listarDepartamentos()
    {
        listaDepartamentos = new DepartamentosDao().listarDepartamentosxArea(area);
    }
}

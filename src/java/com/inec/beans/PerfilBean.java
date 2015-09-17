/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inec.beans;

import com.inec.dao.AreaDao;
import com.inec.dao.Dao;
import com.inec.dao.DepartamentosDao;
import com.inec.mapping.Area;
import com.inec.mapping.Departamento;
import com.inec.mapping.Usuarios;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class PerfilBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private Usuarios usuario;
    private HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    private String claveActual;
    private String nuevaClave;
    private String auxClave;
    private Departamento departamento;
    private Area area;
    private List<Departamento> listaDepartamentos;
    private List<Area> listaArea;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    public List<Area> getListaArea() {
        return listaArea;
    }

    public void setListaArea(List<Area> listaArea) {
        this.listaArea = listaArea;
    }
    
    public List<Departamento> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List<Departamento> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }
    
    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }
    
    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public String getAuxClave() {
        return auxClave;
    }

    public void setAuxClave(String auxClave) {
        this.auxClave = auxClave;
    }
    
    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Creates a new instance of PerfilBean
     */
    public PerfilBean() {
    
        usuario = (Usuarios) sess.getAttribute("usuario");
        departamento = usuario.getIddepartamento();
        area = departamento.getIdarea();
        listaArea = new AreaDao().listarAreas();
        listaDepartamentos = new DepartamentosDao().listarDepartamentosxArea(area);
    }
    
    public void listarDepartamentos()
    {
        listaDepartamentos = new DepartamentosDao().listarDepartamentosxArea(area);
    }
    
    public void actualizar()
    {
        usuario.setIddepartamento(departamento);
        usuario.setApellidos(usuario.getApellidos().toUpperCase());
        usuario.setNombres(usuario.getNombres().toUpperCase());
        usuario.setUsuarioModifica(usuario);
        usuario.setFechaModificacion(new Date());
        
        if(!nuevaClave.isEmpty())
        {
            if(nuevaClave.matches(auxClave))
            {
                if(claveActual.matches(usuario.getPassword()))
                {
                        if(new Dao().actualizar(usuario))
                        {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Respuesta: ", "Datos actualizados correctamente"));
                        }
                        else
                        {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", "Ocurrió un error al guardar los datos"));
                        }
                }
                else
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseñas actual no es correcta"));
                }
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden"));
            }
        }
        else
        {
            if(new Dao().actualizar(usuario))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Datos actualizados correctamente"));
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos Incorrectos"));
            }
        }
    }
    
    public void seleccionDepartamento(Departamento dep)
    {
        usuario.setIddepartamento(dep);
    }
    
}

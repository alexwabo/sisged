/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inec.mapping;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "opciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opciones.findAll", query = "SELECT o FROM Opciones o"),
    @NamedQuery(name = "Opciones.findByIdopcion", query = "SELECT o FROM Opciones o WHERE o.idopcion = :idopcion"),
    @NamedQuery(name = "Opciones.findByNombre", query = "SELECT o FROM Opciones o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "Opciones.findByAccion", query = "SELECT o FROM Opciones o WHERE o.accion = :accion")})
public class Opciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idopcion")
    private Integer idopcion;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "accion")
    private String accion;
    @JoinTable(name = "opcion_rol", joinColumns = {
        @JoinColumn(name = "idopcion", referencedColumnName = "idopcion")}, inverseJoinColumns = {
        @JoinColumn(name = "idrol", referencedColumnName = "idrol")})
    @ManyToMany
    private List<Rol> rolList;

    public Opciones() {
    }

    public Opciones(Integer idopcion) {
        this.idopcion = idopcion;
    }

    public Opciones(Integer idopcion, String nombre, String accion) {
        this.idopcion = idopcion;
        this.nombre = nombre;
        this.accion = accion;
    }

    public Integer getIdopcion() {
        return idopcion;
    }

    public void setIdopcion(Integer idopcion) {
        this.idopcion = idopcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    @XmlTransient
    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idopcion != null ? idopcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opciones)) {
            return false;
        }
        Opciones other = (Opciones) object;
        if ((this.idopcion == null && other.idopcion != null) || (this.idopcion != null && !this.idopcion.equals(other.idopcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.inec.mapping.Opciones[ idopcion=" + idopcion + " ]";
    }
    
}

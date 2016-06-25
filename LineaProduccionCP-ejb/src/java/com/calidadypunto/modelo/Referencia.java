/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "referencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Referencia.findAll", query = "SELECT r FROM Referencia r"),
    @NamedQuery(name = "Referencia.findByIdreferencia", query = "SELECT r FROM Referencia r WHERE r.idreferencia = :idreferencia"),
    @NamedQuery(name = "Referencia.findByNombreReferencia", query = "SELECT r FROM Referencia r WHERE r.nombreReferencia = :nombreReferencia"),
    @NamedQuery(name = "Referencia.findByDescripcion", query = "SELECT r FROM Referencia r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "Referencia.findByTabla", query = "SELECT r FROM Referencia r WHERE r.tabla = :tabla")})
public class Referencia implements Serializable {

    @OneToMany(mappedBy = "referencia")
    private Collection<Tejido> tejidoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idreferencia")
    private Integer idreferencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_referencia")
    private String nombreReferencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tabla")
    private String tabla;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "referencia")
    private Collection<Hilo> hiloCollection;

    public Referencia() {
    }

    public Referencia(Integer idreferencia) {
        this.idreferencia = idreferencia;
    }

    public Referencia(Integer idreferencia, String nombreReferencia, String descripcion, String tabla) {
        this.idreferencia = idreferencia;
        this.nombreReferencia = nombreReferencia;
        this.descripcion = descripcion;
        this.tabla = tabla;
    }

    public Integer getIdreferencia() {
        return idreferencia;
    }

    public void setIdreferencia(Integer idreferencia) {
        this.idreferencia = idreferencia;
    }

    public String getNombreReferencia() {
        return nombreReferencia;
    }

    public void setNombreReferencia(String nombreReferencia) {
        this.nombreReferencia = nombreReferencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    @XmlTransient
    public Collection<Hilo> getHiloCollection() {
        return hiloCollection;
    }

    public void setHiloCollection(Collection<Hilo> hiloCollection) {
        this.hiloCollection = hiloCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreferencia != null ? idreferencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Referencia)) {
            return false;
        }
        Referencia other = (Referencia) object;
        if ((this.idreferencia == null && other.idreferencia != null) || (this.idreferencia != null && !this.idreferencia.equals(other.idreferencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.calidadypunto.modelo.Referencia[ idreferencia=" + idreferencia + " ]";
    }

    @XmlTransient
    public Collection<Tejido> getTejidoCollection() {
        return tejidoCollection;
    }

    public void setTejidoCollection(Collection<Tejido> tejidoCollection) {
        this.tejidoCollection = tejidoCollection;
    }
    
}

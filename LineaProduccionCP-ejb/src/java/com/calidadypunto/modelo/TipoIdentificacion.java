/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "tipo_identificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoIdentificacion.findAll", query = "SELECT t FROM TipoIdentificacion t"),
    @NamedQuery(name = "TipoIdentificacion.findByIdtipoIdentificacion", query = "SELECT t FROM TipoIdentificacion t WHERE t.idtipoIdentificacion = :idtipoIdentificacion"),
    @NamedQuery(name = "TipoIdentificacion.findByCodigo", query = "SELECT t FROM TipoIdentificacion t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoIdentificacion.findByDescripcion", query = "SELECT t FROM TipoIdentificacion t WHERE t.descripcion = :descripcion")})
public class TipoIdentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipo_identificacion")
    private Integer idtipoIdentificacion;
    @Size(max = 1)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "tipoIdProveedor")
    private Collection<Proveedor> proveedorCollection;

    public TipoIdentificacion() {
    }

    public TipoIdentificacion(Integer idtipoIdentificacion) {
        this.idtipoIdentificacion = idtipoIdentificacion;
    }

    public Integer getIdtipoIdentificacion() {
        return idtipoIdentificacion;
    }

    public void setIdtipoIdentificacion(Integer idtipoIdentificacion) {
        this.idtipoIdentificacion = idtipoIdentificacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Proveedor> getProveedorCollection() {
        return proveedorCollection;
    }

    public void setProveedorCollection(Collection<Proveedor> proveedorCollection) {
        this.proveedorCollection = proveedorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoIdentificacion != null ? idtipoIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoIdentificacion)) {
            return false;
        }
        TipoIdentificacion other = (TipoIdentificacion) object;
        if ((this.idtipoIdentificacion == null && other.idtipoIdentificacion != null) || (this.idtipoIdentificacion != null && !this.idtipoIdentificacion.equals(other.idtipoIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.calidadypunto.modelo.TipoIdentificacion[ idtipoIdentificacion=" + idtipoIdentificacion + " ]";
    }
    
}

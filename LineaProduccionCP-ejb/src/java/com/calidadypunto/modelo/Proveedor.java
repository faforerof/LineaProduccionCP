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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByIdproveedor", query = "SELECT p FROM Proveedor p WHERE p.idproveedor = :idproveedor"),
    @NamedQuery(name = "Proveedor.findByNombreProveedor", query = "SELECT p FROM Proveedor p WHERE p.nombreProveedor = :nombreProveedor"),
    @NamedQuery(name = "Proveedor.findByNumeroIdProveedor", query = "SELECT p FROM Proveedor p WHERE p.numeroIdProveedor = :numeroIdProveedor")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproveedor")
    private Integer idproveedor;
    @Size(max = 200)
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero_id_proveedor")
    private String numeroIdProveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "distribuidor")
    private Collection<Hilo> hiloCollection;
    @JoinColumn(name = "tipo_id_proveedor", referencedColumnName = "idtipo_identificacion")
    @ManyToOne
    private TipoIdentificacion tipoIdProveedor;

    public Proveedor() {
    }

    public Proveedor(Integer idproveedor) {
        this.idproveedor = idproveedor;
    }

    public Proveedor(Integer idproveedor, String numeroIdProveedor) {
        this.idproveedor = idproveedor;
        this.numeroIdProveedor = numeroIdProveedor;
    }

    public Integer getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Integer idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getNumeroIdProveedor() {
        return numeroIdProveedor;
    }

    public void setNumeroIdProveedor(String numeroIdProveedor) {
        this.numeroIdProveedor = numeroIdProveedor;
    }

    @XmlTransient
    public Collection<Hilo> getHiloCollection() {
        return hiloCollection;
    }

    public void setHiloCollection(Collection<Hilo> hiloCollection) {
        this.hiloCollection = hiloCollection;
    }

    public TipoIdentificacion getTipoIdProveedor() {
        return tipoIdProveedor;
    }

    public void setTipoIdProveedor(TipoIdentificacion tipoIdProveedor) {
        this.tipoIdProveedor = tipoIdProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproveedor != null ? idproveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idproveedor == null && other.idproveedor != null) || (this.idproveedor != null && !this.idproveedor.equals(other.idproveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.calidadypunto.modelo.Proveedor[ idproveedor=" + idproveedor + " ]";
    }
    
}

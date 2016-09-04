/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "orden_hilo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenHilo.findAll", query = "SELECT o FROM OrdenHilo o"),
    @NamedQuery(name = "OrdenHilo.findByIdordenHilo", query = "SELECT o FROM OrdenHilo o WHERE o.idordenHilo = :idordenHilo"),
    @NamedQuery(name = "OrdenHilo.findByFecha", query = "SELECT o FROM OrdenHilo o WHERE o.fecha = :fecha"),
    @NamedQuery(name = "OrdenHilo.findByCondicionPago", query = "SELECT o FROM OrdenHilo o WHERE o.condicionPago = :condicionPago"),
    @NamedQuery(name = "OrdenHilo.findByUsuario", query = "SELECT o FROM OrdenHilo o WHERE o.usuario = :usuario")})
public class OrdenHilo implements Serializable {

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "ordenHilo")
    private OrdenHiloRegistro ordenHiloRegistro;

    @JoinColumn(name = "estado", referencedColumnName = "idestado")
    @ManyToOne
    private Estado estado;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorden_hilo")
    private Integer idordenHilo;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 5)
    @Column(name = "condicion_pago")
    private String condicionPago;
    @Size(max = 45)
    @Column(name = "usuario")
    private String usuario;
    @JoinColumn(name = "proveedor", referencedColumnName = "idproveedor")
    @ManyToOne(optional = false)
    private Proveedor proveedor;

    public OrdenHilo() {
    }

    public OrdenHilo(Integer idordenHilo) {
        this.idordenHilo = idordenHilo;
    }

    public OrdenHilo(Integer idordenHilo, Date fecha) {
        this.idordenHilo = idordenHilo;
        this.fecha = fecha;
    }

    public Integer getIdordenHilo() {
        return idordenHilo;
    }

    public void setIdordenHilo(Integer idordenHilo) {
        this.idordenHilo = idordenHilo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCondicionPago() {
        return condicionPago;
    }

    public void setCondicionPago(String condicionPago) {
        this.condicionPago = condicionPago;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idordenHilo != null ? idordenHilo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenHilo)) {
            return false;
        }
        OrdenHilo other = (OrdenHilo) object;
        if ((this.idordenHilo == null && other.idordenHilo != null) || (this.idordenHilo != null && !this.idordenHilo.equals(other.idordenHilo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.calidadypunto.modelo.OrdenHilo[ idordenHilo=" + idordenHilo + " ]";
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public OrdenHiloRegistro getOrdenHiloRegistro() {
        return ordenHiloRegistro;
    }

    public void setOrdenHiloRegistro(OrdenHiloRegistro ordenHiloRegistro) {
        this.ordenHiloRegistro = ordenHiloRegistro;
    }
    
}

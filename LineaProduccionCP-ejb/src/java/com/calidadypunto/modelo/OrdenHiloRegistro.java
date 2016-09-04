/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "orden_hilo_registro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenHiloRegistro.findAll", query = "SELECT o FROM OrdenHiloRegistro o"),
    @NamedQuery(name = "OrdenHiloRegistro.findByIdordenHiloRegistro", query = "SELECT o FROM OrdenHiloRegistro o WHERE o.idordenHiloRegistro = :idordenHiloRegistro"),
    @NamedQuery(name = "OrdenHiloRegistro.findByOrden", query = "SELECT o FROM OrdenHiloRegistro o WHERE o.orden = :orden"),
    @NamedQuery(name = "OrdenHiloRegistro.findByReferencia", query = "SELECT o FROM OrdenHiloRegistro o WHERE o.referencia = :referencia"),
    @NamedQuery(name = "OrdenHiloRegistro.findByPeso", query = "SELECT o FROM OrdenHiloRegistro o WHERE o.peso = :peso"),
    @NamedQuery(name = "OrdenHiloRegistro.findByValor", query = "SELECT o FROM OrdenHiloRegistro o WHERE o.valor = :valor")})
public class OrdenHiloRegistro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idorden_hilo_registro")
    private Integer idordenHiloRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private int orden;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "referencia")
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "peso")
    private int peso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private long valor;
    @JoinColumn(name = "idorden_hilo_registro", referencedColumnName = "idorden_hilo", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private OrdenHilo ordenHilo;

    public OrdenHiloRegistro() {
    }

    public OrdenHiloRegistro(Integer idordenHiloRegistro) {
        this.idordenHiloRegistro = idordenHiloRegistro;
    }

    public OrdenHiloRegistro(Integer idordenHiloRegistro, int orden, String referencia, int peso, long valor) {
        this.idordenHiloRegistro = idordenHiloRegistro;
        this.orden = orden;
        this.referencia = referencia;
        this.peso = peso;
        this.valor = valor;
    }

    public Integer getIdordenHiloRegistro() {
        return idordenHiloRegistro;
    }

    public void setIdordenHiloRegistro(Integer idordenHiloRegistro) {
        this.idordenHiloRegistro = idordenHiloRegistro;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public OrdenHilo getOrdenHilo() {
        return ordenHilo;
    }

    public void setOrdenHilo(OrdenHilo ordenHilo) {
        this.ordenHilo = ordenHilo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idordenHiloRegistro != null ? idordenHiloRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenHiloRegistro)) {
            return false;
        }
        OrdenHiloRegistro other = (OrdenHiloRegistro) object;
        if ((this.idordenHiloRegistro == null && other.idordenHiloRegistro != null) || (this.idordenHiloRegistro != null && !this.idordenHiloRegistro.equals(other.idordenHiloRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.calidadypunto.modelo.OrdenHiloRegistro[ idordenHiloRegistro=" + idordenHiloRegistro + " ]";
    }
    
}

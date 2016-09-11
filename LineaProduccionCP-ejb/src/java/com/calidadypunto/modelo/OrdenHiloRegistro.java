/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Freddy
 */
@Entity
@Table(name = "orden_hilo_registro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenHiloRegistro.findAll", query = "SELECT o FROM OrdenHiloRegistro o"),
    @NamedQuery(name = "OrdenHiloRegistro.findByIdordenHiloRegistro", query = "SELECT o FROM OrdenHiloRegistro o WHERE o.idordenHiloRegistro = :idordenHiloRegistro"),
    @NamedQuery(name = "OrdenHiloRegistro.findByPeso", query = "SELECT o FROM OrdenHiloRegistro o WHERE o.peso = :peso"),
    @NamedQuery(name = "OrdenHiloRegistro.findByReferencia", query = "SELECT o FROM OrdenHiloRegistro o WHERE o.referencia = :referencia"),
    @NamedQuery(name = "OrdenHiloRegistro.findByValor", query = "SELECT o FROM OrdenHiloRegistro o WHERE o.valor = :valor")})
public class OrdenHiloRegistro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorden_hilo_registro")
    private Integer idordenHiloRegistro;
    @Column(name = "peso")
    private Integer peso;
    @Size(max = 255)
    @Column(name = "referencia")
    private String referencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @JoinColumn(name = "orden", referencedColumnName = "idorden_hilo")
    @ManyToOne
    private OrdenHilo orden;

    public OrdenHiloRegistro() {
    }

    public OrdenHiloRegistro(Integer idordenHiloRegistro) {
        this.idordenHiloRegistro = idordenHiloRegistro;
    }

    public Integer getIdordenHiloRegistro() {
        return idordenHiloRegistro;
    }

    public void setIdordenHiloRegistro(Integer idordenHiloRegistro) {
        this.idordenHiloRegistro = idordenHiloRegistro;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public OrdenHilo getOrden() {
        return orden;
    }

    public void setOrden(OrdenHilo orden) {
        this.orden = orden;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
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

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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name = "tejido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tejido.findAll", query = "SELECT t FROM Tejido t"),
    @NamedQuery(name = "Tejido.findByIdtejido", query = "SELECT t FROM Tejido t WHERE t.idtejido = :idtejido"),
    @NamedQuery(name = "Tejido.findByFactura", query = "SELECT t FROM Tejido t WHERE t.factura = :factura"),
    @NamedQuery(name = "Tejido.findByLote", query = "SELECT t FROM Tejido t WHERE t.lote = :lote"),
    @NamedQuery(name = "Tejido.findByPrograma", query = "SELECT t FROM Tejido t WHERE t.programa = :programa"),
    @NamedQuery(name = "Tejido.findByCantidad", query = "SELECT t FROM Tejido t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "Tejido.findByValor", query = "SELECT t FROM Tejido t WHERE t.valor = :valor"),
    @NamedQuery(name = "Tejido.findByColor", query = "SELECT t FROM Tejido t WHERE t.color = :color")})
public class Tejido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtejido")
    private Integer idtejido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "factura")
    private String factura;
    @Size(max = 45)
    @Column(name = "lote")
    private String lote;
    @Size(max = 45)
    @Column(name = "programa")
    private String programa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Column(name = "valor")
    private BigDecimal valor;
    @Size(max = 45)
    @Column(name = "color")
    private String color;
    @JoinColumn(name = "hilo", referencedColumnName = "idhilo")
    @ManyToOne
    private Hilo hilo;
    @JoinColumn(name = "proveedor", referencedColumnName = "idproveedor")
    @ManyToOne
    private Proveedor proveedor;
    @JoinColumn(name = "referencia", referencedColumnName = "idreferencia")
    @ManyToOne
    private Referencia referencia;

    public Tejido() {
    }

    public Tejido(Integer idtejido) {
        this.idtejido = idtejido;
    }

    public Tejido(Integer idtejido, String factura) {
        this.idtejido = idtejido;
        this.factura = factura;
    }

    public Integer getIdtejido() {
        return idtejido;
    }

    public void setIdtejido(Integer idtejido) {
        this.idtejido = idtejido;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Hilo getHilo() {
        return hilo;
    }

    public void setHilo(Hilo hilo) {
        this.hilo = hilo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Referencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Referencia referencia) {
        this.referencia = referencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtejido != null ? idtejido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tejido)) {
            return false;
        }
        Tejido other = (Tejido) object;
        if ((this.idtejido == null && other.idtejido != null) || (this.idtejido != null && !this.idtejido.equals(other.idtejido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.calidadypunto.modelo.Tejido[ idtejido=" + idtejido + " ]";
    }
    
}

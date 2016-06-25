/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "hilo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hilo.findAll", query = "SELECT h FROM Hilo h"),
    @NamedQuery(name = "Hilo.findByIdhilo", query = "SELECT h FROM Hilo h WHERE h.idhilo = :idhilo"),
    @NamedQuery(name = "Hilo.findByFactura", query = "SELECT h FROM Hilo h WHERE h.factura = :factura"),
    @NamedQuery(name = "Hilo.findByPeso", query = "SELECT h FROM Hilo h WHERE h.peso = :peso"),
    @NamedQuery(name = "Hilo.findByPesoUsado", query = "SELECT h FROM Hilo h WHERE h.pesoUsado = :pesoUsado"),
    @NamedQuery(name = "Hilo.findByValorTotal", query = "SELECT h FROM Hilo h WHERE h.valorTotal = :valorTotal")})
public class Hilo implements Serializable {

    @Lob
    @Column(name = "Documento")
    private byte[] documento;
    @OneToMany(mappedBy = "hilo")
    private Collection<Tejido> tejidoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhilo")
    private Integer idhilo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "factura")
    private String factura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "peso")
    private BigDecimal peso;
    @Column(name = "peso_usado")
    private BigDecimal pesoUsado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "extension")
    private String extension;
    @JoinColumn(name = "distribuidor", referencedColumnName = "idproveedor")
    @ManyToOne(optional = false)
    private Proveedor distribuidor;
    @JoinColumn(name = "referencia", referencedColumnName = "idreferencia")
    @ManyToOne(optional = false)
    private Referencia referencia;

    public Hilo() {
    }

    public Hilo(Integer idhilo) {
        this.idhilo = idhilo;
    }

    public Hilo(Integer idhilo, String factura, BigDecimal peso, BigDecimal valorTotal) {
        this.idhilo = idhilo;
        this.factura = factura;
        this.peso = peso;
        this.valorTotal = valorTotal;
    }

    public Integer getIdhilo() {
        return idhilo;
    }

    public void setIdhilo(Integer idhilo) {
        this.idhilo = idhilo;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getPesoUsado() {
        return pesoUsado;
    }

    public void setPesoUsado(BigDecimal pesoUsado) {
        this.pesoUsado = pesoUsado;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }


    public Proveedor getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Proveedor distribuidor) {
        this.distribuidor = distribuidor;
    }

    public Referencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Referencia referencia) {
        this.referencia = referencia;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhilo != null ? idhilo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hilo)) {
            return false;
        }
        Hilo other = (Hilo) object;
        if ((this.idhilo == null && other.idhilo != null) || (this.idhilo != null && !this.idhilo.equals(other.idhilo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.calidadypunto.modelo.Hilo[ idhilo=" + idhilo + " ]";
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    @XmlTransient
    public Collection<Tejido> getTejidoCollection() {
        return tejidoCollection;
    }

    public void setTejidoCollection(Collection<Tejido> tejidoCollection) {
        this.tejidoCollection = tejidoCollection;
    }
    
}

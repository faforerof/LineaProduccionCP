/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.servlets;

import com.calidadypunto.modelo.OrdenHilo;
import com.calidadypunto.modelo.OrdenHiloRegistro;
import com.calidadypunto.modelo.Proveedor;
import com.calidadypunto.modelo.Referencia;
import com.calidadypunto.session.EstadoFacade;
import com.calidadypunto.session.OrdenHiloFacade;
import com.calidadypunto.session.ParametrosFacade;
import com.calidadypunto.session.ProveedorFacade;
import com.calidadypunto.session.ReferenciaFacade;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author lenovo
 */
@ManagedBean(name="HiloOrdenBean")
@ViewScoped
public class HiloOrdenBean {

    @EJB
    private OrdenHiloFacade ordenHiloFacade;
    
    @EJB
    private ParametrosFacade parametrosFacade;
    
    @EJB
    private ProveedorFacade proveedorFacade;
    
    @EJB
    private EstadoFacade estadoFacade;
    
    @EJB
    private ReferenciaFacade referenciaFacade;
    
    private OrdenHilo newOrdenHilo;
    private List<Proveedor> proveedores;
    private List<OrdenHiloRegistro> registros;
    private Map<String,String> referencias;
    
    public HiloOrdenBean(){
        newOrdenHilo = new OrdenHilo();
        referencias = new HashMap<>();
        registros = new ArrayList<>();
        newOrdenHilo.setOrdenHiloRegistro(registros);
    }

    public OrdenHilo getNewOrdenHilo() {
        return newOrdenHilo;
    }

    public void setNewOrdenHilo(OrdenHilo newOrdenHilo) {
        this.newOrdenHilo = newOrdenHilo;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public List<OrdenHiloRegistro> getRegistros() {
        
        return registros;
    }

    public void setRegistros(List<OrdenHiloRegistro> registros) {
        this.registros = registros;
    }

    public Map<String, String> getReferencias() {
        if(referencias == null || referencias.isEmpty()){
            List<Referencia> refs = referenciaFacade.findByTabla("Orden Hilo");
            for(Referencia r : refs){
                referencias.put(r.getNombreReferencia(), r.getDescripcion());
            }
        }
        return referencias;
    }

    public void setReferencias(Map<String, String> referencias) {
        this.referencias = referencias;
    }
    
    public String addHiloRegistro() {
        OrdenHiloRegistro order = new OrdenHiloRegistro();
        order.setReferencia("18/1 Cardado");
        order.setPeso(0);
        order.setValor(BigDecimal.ZERO);
        order.setOrden(newOrdenHilo);
        registros.add(order);
        return null;
    }
    
    public String removeHiloRegistro(OrdenHiloRegistro orden) {
        registros.remove(orden);
        recalcularTotales();
        return null;
    }
    
    public void onCellEdit(CellEditEvent event) {
        OrdenHiloRegistro fila = registros.get(event.getRowIndex());
        fila.setValorTotal(fila.getValor().multiply(new BigDecimal(fila.getPeso())).setScale(2));
        this.recalcularTotales();
    }
    
    public List<Proveedor> completeProveedor(String query) {
        if(proveedores == null){
            proveedores = proveedorFacade.findAll();
        }
        List<Proveedor> proveedoresFiltrados = new ArrayList<>();
         
        for (int i = 0; i < proveedores.size(); i++) {
            Proveedor proveedor = proveedores.get(i);
            if(proveedor.getNombreProveedor().toLowerCase().contains(query.toLowerCase()) || proveedor.getNumeroIdProveedor().toLowerCase().startsWith(query)) {
                proveedoresFiltrados.add(proveedor);
            }
        }
        return proveedoresFiltrados;
    }
    
    public String createOrdenHilo(HttpServletRequest request){
        try{
            if(!newOrdenHilo.getOrdenHiloRegistro().isEmpty()){
                HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                newOrdenHilo.setEstado(estadoFacade.find(1));
                newOrdenHilo.setUsuario((String) session.getAttribute("username"));
                newOrdenHilo.setFecha(new Date());
                ordenHiloFacade.create(newOrdenHilo);
            } else {
                addMessage("¡Advertencia!", "Por favor agregue registros.", FacesMessage.SEVERITY_WARN);
                return "";
            }
        } catch (Exception ex) {
            addMessage("¡Error!", "No se puede registrar el hilo.", FacesMessage.SEVERITY_ERROR);
            return "";
        }
        return "home.xhtml?faces-redirect=true";
    }

    
    public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    private void recalcularTotales(){
        BigDecimal vBruto = BigDecimal.ZERO;
        BigDecimal iva = BigDecimal.ZERO;
        for(OrdenHiloRegistro r : registros){
            vBruto = vBruto.add(r.getValorTotal());            
        }
        String ivaP = parametrosFacade.findByNombre("IVA").getValor();
        iva = vBruto.multiply(new BigDecimal(ivaP));
        String valor = parametrosFacade.findByNombre("Retencion").getValor();
        String valorP = parametrosFacade.findByNombre("RetencionP").getValor();
        BigDecimal retencion = new BigDecimal(valor);
        BigDecimal retencionPorcentaje = new BigDecimal(valorP);
        BigDecimal vRetencion = BigDecimal.ZERO;
        BigDecimal vTotalNeto = BigDecimal.ZERO;
        if(vBruto.compareTo(retencion) == 1 && newOrdenHilo.getProveedor().getAutoretenedor().equals("N")){//Si vBruto es mayor que la retención
            vRetencion = vBruto.multiply(retencionPorcentaje);
        }
        
        vTotalNeto = vBruto.add(vRetencion).add(vTotalNeto).add(iva);
        
        newOrdenHilo.setValorBruto(vBruto.setScale(2, RoundingMode.CEILING));
        newOrdenHilo.setRetencion(vRetencion.setScale(2, RoundingMode.CEILING));
        newOrdenHilo.setTotalNeto(vTotalNeto.setScale(2, RoundingMode.CEILING));
        newOrdenHilo.setIva(iva.setScale(2, RoundingMode.CEILING));
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.servlets;

import com.calidadypunto.modelo.Proveedor;
import com.calidadypunto.modelo.Referencia;
import com.calidadypunto.modelo.TipoIdentificacion;
import com.calidadypunto.session.ReferenciaFacade;
import com.calidadypunto.session.TipoIdentificacionFacade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author lenovo
 */
@ManagedBean(name="ReferenciaBean")
@ViewScoped
public class ReferenciaBean {
    @EJB
    private ReferenciaFacade referenciaFacade;
    @EJB
    private TipoIdentificacionFacade tipoIdentificacionFacade;
    private List<TipoIdentificacion> identificaciones;
    private List<Referencia> referenciaList;
    private Referencia selectedReferencia;
    private Referencia newReferencia;
    private Referencia modifyReferencia;
    private Map<String,String> tablas;
    
    public ReferenciaBean(){
        newReferencia = new Referencia();
        modifyReferencia = new Referencia();
        tablas  = new HashMap<>();
        tablas.put("Hilo", "Hilo");
        tablas.put("Tejido", "Tejido");
    }

    public List<Referencia> getReferenciaList() {
        if(referenciaList == null || referenciaList.size() == 0){
            referenciaList = referenciaFacade.findAll();
        }
        return referenciaList;
    }

    public void setProveedorList(List<Referencia> referenciaList) {
        this.referenciaList = referenciaList;
    }

    public Referencia getSelectedReferencia() {
        return selectedReferencia;
    }

    public void setSelectedReferencia(Referencia selectedProveedor) {
        this.selectedReferencia = selectedProveedor;
    }
    
    public Referencia getNewReferencia() {
        return newReferencia;
    }

    public void setNewProveedor(Referencia newReferencia) {
        this.newReferencia = newReferencia;
    }

    public Referencia getModifyProveedor() {
        return modifyReferencia;
    }

    public void setModifyProveedor(Referencia modifyReferencia) {
        this.modifyReferencia = modifyReferencia;
    }

    public Map<String, String> getTablas() {
        return tablas;
    }

    public void setTablas(Map<String, String> tablas) {
        this.tablas = tablas;
    }
    
    public List<TipoIdentificacion> getIdentificaciones() {
        if(identificaciones == null){
            identificaciones = tipoIdentificacionFacade.findAll();
        }
        return identificaciones;
    }

    public void setIdentificaciones(List<TipoIdentificacion> identificaciones) {
        this.identificaciones = identificaciones;
    }
    
    public void onRowSelect(SelectEvent event){
        selectedReferencia = referenciaFacade.find(((Referencia) event.getObject()).getIdreferencia());
        modifyReferencia = selectedReferencia;
    }
    
    public String createReferencia(){
        try{
            referenciaFacade.create(newReferencia);
        } catch (Exception ex) {
            addMessage("¡Error!", "No se puede crear el proveedor.", FacesMessage.SEVERITY_ERROR);
            return "";
        }
        return "home.xhtml?faces-redirect=true";
    }
    
    public void addMessage(String summary, String detail, Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
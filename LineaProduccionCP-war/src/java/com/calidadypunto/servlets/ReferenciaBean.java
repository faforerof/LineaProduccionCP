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
import java.util.List;
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
    private Proveedor selectedProveedor;
    private Proveedor newProveedor;
    private Proveedor modifyProveedor;
    
    public ReferenciaBean(){
        newProveedor = new Proveedor();
        modifyProveedor = new Proveedor();
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

    public Proveedor getSelectedProveedor() {
        return selectedProveedor;
    }

    public void setSelectedProveedor(Proveedor selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }
    
    public Proveedor getNewProveedor() {
        return newProveedor;
    }

    public void setNewProveedor(Proveedor newProveedor) {
        this.newProveedor = newProveedor;
    }

    public Proveedor getModifyProveedor() {
        return modifyProveedor;
    }

    public void setModifyProveedor(Proveedor modifyProveedor) {
        this.modifyProveedor = modifyProveedor;
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
        selectedProveedor = proveedorFacade.find(((Proveedor) event.getObject()).getIdproveedor());
        modifyProveedor = selectedProveedor;
    }
    
    public String createProveedor(){
        try{
            proveedorFacade.create(newProveedor);
        } catch (Exception ex) {
            addMessage("¡Error!", "No se puede crear el proveedor.", FacesMessage.SEVERITY_ERROR);
            return "";
        }
        return "home.xhtml?faces-redirect=true";
    }
    
    public String editProveedor(){
        try{
            selectedProveedor.setNombreProveedor(modifyProveedor.getNombreProveedor());
            selectedProveedor.setNumeroIdProveedor(modifyProveedor.getNumeroIdProveedor());
            selectedProveedor.setTipoIdProveedor(modifyProveedor.getTipoIdProveedor());
            proveedorFacade.edit(selectedProveedor);
        } catch (Exception ex) {
            addMessage("¡Error!", "No se puede crear el proveedor.", FacesMessage.SEVERITY_ERROR);
            return "";
        }
        return "home.xhtml?faces-redirect=true";
    }
    
    public String deleteProveedor(){
        try {
            proveedorFacade.remove(selectedProveedor);
            return "home.xhtml?faces-redirect=true";
        } catch (Exception e) {
            addMessage("¡Error!", "Error con la conexión a base de datos.", FacesMessage.SEVERITY_FATAL);
            return "";
        }
    }
    
    public void addMessage(String summary, String detail, Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
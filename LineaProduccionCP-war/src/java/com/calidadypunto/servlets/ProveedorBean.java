/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.servlets;

import com.calidadypunto.modelo.Proveedor;
import com.calidadypunto.modelo.TipoIdentificacion;
import com.calidadypunto.modelo.Users;
import com.calidadypunto.session.ProveedorFacade;
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
@ManagedBean(name="ProveedorBean")
@ViewScoped
public class ProveedorBean {
    @EJB
    private ProveedorFacade proveedorFacade;
    @EJB
    private TipoIdentificacionFacade tipoIdentificacionFacade;
    private List<TipoIdentificacion> identificaciones;
    private List<Proveedor> proveedorList;
    private Proveedor selectedProveedor;
    private Proveedor newProveedor;
    private Proveedor modifyProveedor;
    
    public ProveedorBean(){
        newProveedor = new Proveedor();
        modifyProveedor = new Proveedor();
    }

    public List<Proveedor> getProveedorList() {
        if(proveedorList == null || proveedorList.size() == 0){
            proveedorList = proveedorFacade.findAll();
        }
        return proveedorList;
    }

    public void setProveedorList(List<Proveedor> proveedorList) {
        this.proveedorList = proveedorList;
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
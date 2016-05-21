/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.servlets;

import com.calidadypunto.modelo.Proveedor;
import com.calidadypunto.modelo.TipoIdentificacion;
import com.calidadypunto.session.ProveedorFacade;
import com.calidadypunto.session.TipoIdentificacionFacade;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
    private Map<String,String> identificaciones;
    
    private Proveedor newProveedor;
    
    public ProveedorBean(){
        newProveedor = new Proveedor();
    }
    
    public Proveedor getNewProveedor() {
        return newProveedor;
    }

    public void setNewProveedor(Proveedor newProveedor) {
        this.newProveedor = newProveedor;
    }

    public Map<String, String> getIdentificaciones() {
        if(identificaciones == null || identificaciones.isEmpty()){
            identificaciones = new HashMap<>();
            for(TipoIdentificacion ti : tipoIdentificacionFacade.findAll()){
                identificaciones.put(ti.getDescripcion(), ti.getCodigo());
            }
        }
        return identificaciones;
    }

    public void setIdentificaciones(Map<String, String> roles) {
        this.identificaciones = roles;
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
    
    public void addMessage(String summary, String detail, Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.servlets;

import com.calidadypunto.modelo.OrdenHilo;
import com.calidadypunto.modelo.Proveedor;
import com.calidadypunto.session.EstadoFacade;
import com.calidadypunto.session.OrdenHiloFacade;
import com.calidadypunto.session.ProveedorFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lenovo
 */
@ManagedBean(name="HiloOrdenBean")
@SessionScoped
public class HiloOrdenBean {

    @EJB
    private OrdenHiloFacade ordenHiloFacade;
    
    @EJB
    private ProveedorFacade proveedorFacade;
    
    @EJB
    private EstadoFacade estadoFacade;
    private OrdenHilo newOrdenHilo;
    private List<Proveedor> proveedores;
    
    public HiloOrdenBean(){
        newOrdenHilo = new OrdenHilo();
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
    
    public String createHilo(HttpServletRequest request){
        try{
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            newOrdenHilo.setEstado(estadoFacade.find(1));
            newOrdenHilo.setUsuario((String) session.getAttribute("username"));
            newOrdenHilo.setFecha(new Date());
            ordenHiloFacade.create(newOrdenHilo);
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
    
}
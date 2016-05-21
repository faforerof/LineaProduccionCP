/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.servlets;

import com.calidadypunto.session.HiloFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ejb.EJB;
import com.calidadypunto.modelo.Hilo;
import com.calidadypunto.modelo.Proveedor;
import com.calidadypunto.modelo.Referencia;
import com.calidadypunto.session.ProveedorFacade;
import com.calidadypunto.session.ReferenciaFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author lenovo
 */
@ManagedBean(name="HiloBean")
@ViewScoped
public class HiloBean {
    @EJB
    private HiloFacade hiloFacade;
    @EJB
    private ProveedorFacade proveedorFacade;
    @EJB
    private ReferenciaFacade referenciaFacade;
    private List<Referencia> referencias;
    private List<Proveedor> proveedores;
    private UploadedFile file;
    
    private Hilo newHilo;
    
    public HiloBean(){
        newHilo = new Hilo();
    }

    public Hilo getNewHilo() {
        return newHilo;
    }

    public void setNewHilo(Hilo newHilo) {
        this.newHilo = newHilo;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
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
    
    public List<Referencia> completeReferencia(String query) {
        if(referencias == null){
            referencias = referenciaFacade.findAll();
        }
        List<Referencia> referenciasFiltradas = new ArrayList<>();
         
        for (int i = 0; i < referencias.size(); i++) {
            Referencia referencia = referencias.get(i);
            if(referencia.getNombreReferencia().toLowerCase().contains(query.toLowerCase()) || referencia.getDescripcion().toLowerCase().startsWith(query)) {
                referenciasFiltradas.add(referencia);
            }
        }
        return referenciasFiltradas;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public String createHilo(){
        try{
            newHilo.setPesoUsado(new BigDecimal(0));
            newHilo.setDocumento(file.getContents());
            hiloFacade.create(newHilo);
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
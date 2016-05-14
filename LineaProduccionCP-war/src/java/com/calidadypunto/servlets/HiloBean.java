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
import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author lenovo
 */
@ManagedBean(name="HiloBean")
@ViewScoped
public class HiloBean {
    @EJB
    private HiloFacade hiloFacade;
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
    
    
    public String createHilo(){
        try{
            newHilo.setPesoUsado(new BigDecimal(0));
            hiloFacade.create(newHilo);
        } catch (Exception ex) {
            addMessage("¡Error!", "No se puede crear el usuario.", FacesMessage.SEVERITY_ERROR);
            return "";
        }
        return "home.xhtml?faces-redirect=true";
    }
    
    public void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}

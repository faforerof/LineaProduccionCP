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

/**
 *
 * @author lenovo
 */
@ManagedBean(name="HiloBean")
@ViewScoped
public class HiloBean {
    @EJB
    private HiloFacade hiloFacade;
    
    Hilo newHilo = new Hilo();
    
    public HiloBean(){
        newHilo = new Hilo();
    }

    
    
    
    
    public Hilo getNewHilo() {
        return newHilo;
    }

    public void setNewHilo(Hilo newHilo) {
        this.newHilo = newHilo;
    }
    
}

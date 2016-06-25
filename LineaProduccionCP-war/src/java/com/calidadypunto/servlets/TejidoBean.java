/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.servlets;

import com.calidadypunto.modelo.Tejido;
import com.calidadypunto.session.HiloFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author lenovo
 */
@ManagedBean(name="TejidoBean")
@ViewScoped
public class TejidoBean {
    private Tejido newTejido;
    private List<Tejido> tejidoList;
    private Tejido selectedTejido;
    private Tejido modifyTejido;
    @EJB
    private HiloFacade hiloFacade;
    
    public TejidoBean(){
        newTejido = new Tejido();
        modifyTejido = new Tejido();
    }

    public Tejido getNewTejido() {
        return newTejido;
    }

    public void setNewTejido(Tejido newTejido) {
        this.newTejido = newTejido;
    }

    public List<Tejido> getTejidoList() {
        return tejidoList;
    }

    public void setTejidoList(List<Tejido> tejidoList) {
        this.tejidoList = tejidoList;
    }

    public Tejido getSelectedTejido() {
        return selectedTejido;
    }

    public void setSelectedTejido(Tejido selectedTejido) {
        this.selectedTejido = selectedTejido;
    }

    public Tejido getModifyTejido() {
        return modifyTejido;
    }

    public void setModifyTejido(Tejido modifyTejido) {
        this.modifyTejido = modifyTejido;
    }
    
    
    
}

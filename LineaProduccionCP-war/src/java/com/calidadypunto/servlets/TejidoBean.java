/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.servlets;

import com.calidadypunto.modelo.Hilo;
import com.calidadypunto.modelo.Tejido;
import com.calidadypunto.session.HiloFacade;
import java.util.ArrayList;
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
    private List<Hilo> hilos;
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
    
    
    
    public List<Hilo> completeHilo(String query) {
        if(hilos == null){
            hilos = hiloFacade.findAll();
        }
        List<Hilo> hilosFiltrados = new ArrayList<>();
         
        for (int i = 0; i < hilos.size(); i++) {
            Hilo hilo = hilos.get(i);
            hilosFiltrados.add(hilo);
            //if(proveedor.getNombreProveedor().toLowerCase().contains(query.toLowerCase()) || proveedor.getNumeroIdProveedor().toLowerCase().startsWith(query)) {
            //    proveedoresFiltrados.add(proveedor);
            //}
        }
        return hilosFiltrados;
    }
}

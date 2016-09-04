/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.servlets;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author lenovo
 */
@ManagedBean(name="HomeBean")
@SessionScoped
public class HomeBean {
    
    public void hideAllDialogs(String idComponente){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('RegistrarHilo').close();"
                + "PF('OrdenHilo').close();"
                + "PF('AdministrarHilos').close();"
                + "PF('RegistrarTejido').close();"
                + "PF('AdministrarTejido').close();"
                + "PF('RegistrarProveedor').close();"
                + "PF('RegistrarReferencia').close();"
                + "PF('AdministrarReferencia').close();"
                + "PF('AdministrarProveedor').close();");
        context.execute("PF('"+idComponente+"').show();");
    }
}

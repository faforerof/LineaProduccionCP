/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.converters;

import com.calidadypunto.modelo.TipoIdentificacion;
import com.calidadypunto.session.TipoIdentificacionFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author lenovo
 */
@ManagedBean(name="tipoIdentificacionConverter")
@RequestScoped
public class TipoIdentificacionConverter implements Converter{
    
    @EJB
    private TipoIdentificacionFacade tipoIdentificacionFacade;
    
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            return tipoIdentificacionFacade.find(Integer.valueOf(value));
        }
        else {
            return null;
        }
    }
 
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((TipoIdentificacion) object).getIdtipoIdentificacion());
        }
        else {
            return null;
        }
    }
}

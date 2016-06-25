/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.session;

import com.calidadypunto.modelo.Tejido;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lenovo
 */
@Stateless
public class TejidoFacade extends AbstractFacade<Tejido> {

    @PersistenceContext(unitName = "LineaProduccionCP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TejidoFacade() {
        super(Tejido.class);
    }
    
}

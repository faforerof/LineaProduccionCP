/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.session;

import com.calidadypunto.modelo.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Freddy
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "LineaProduccionCP-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public Users findByUserName(String userName){
        Query nq = getEntityManager().createNamedQuery("Users.findByUsername");
        nq.setParameter("username", userName);
        Users user = (Users)nq.getSingleResult();
        return user;
    }
    
    public List<Users> findAll(String userName){
        Query nq = getEntityManager().createNamedQuery("Users.findAll");
        List<Users> user = (List<Users>)nq.getResultList();
        return user;
    }
}

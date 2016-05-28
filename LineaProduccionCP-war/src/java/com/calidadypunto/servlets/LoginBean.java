/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calidadypunto.servlets;

import com.calidadypunto.modelo.Users;
import com.calidadypunto.session.UsersFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author lenovo
 */
@ManagedBean(name="LoginBean")
@SessionScoped
public class LoginBean implements Serializable{

    @EJB
    private UsersFacade usersFacade;
    
    private String userName;
    private String password;
    private String nombres;
    private String apellidos;
    private Integer rol;
    
    /**
     * Loggea al usuario dentro de la aplicación
     * @param request
     * @return
     * @throws IOException 
     */
    public String login(HttpServletRequest request) throws IOException{
        Crypt c = new Crypt();
        Users useras;
        try {
            useras = usersFacade.findByUserName(userName);
        }catch(Exception ex) {
            useras = null;
        }
        
        if(useras != null){
            if(useras.getPassword().equals(c.cryptWithMD5(password))){
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("nombre", useras.getNombres());
                session.setAttribute("apellido", useras.getApellidos());
                session.setAttribute("id", useras.getIdusers());
                session.setAttribute("administrator", useras.getRol()==0);
                return "home.xhtml?faces-redirect=true";
            }
        }
        password = "";
        FacesContext.getCurrentInstance().addMessage("loginMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Error de usuario y contraseña, por favor intente de nuevo o contacte a su administrador",  
                "Sample error message"));
        RequestContext.getCurrentInstance().update("loginMessage");
        return "";
    }
    
    /**
     *  Crea el usuario nuevo
     * @param request
     * @return
     * @throws IOException 
     */
    public String crearUsuario(HttpServletRequest request) throws IOException{
        Crypt c = new Crypt();
        try{
            usersFacade.create(new Users(null, userName, c.cryptWithMD5(password), nombres, apellidos, rol));
        } catch(Exception e){
            return "error.xhtml?faces-redirect=true";
        }
        return "Usuario creado correctamente.";
    }
    
    public String mostrarNombre(HttpServletRequest request) {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session.getAttribute("nombre") == null){
            session.invalidate();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            return (String)session.getAttribute("nombre") + " " + (String)session.getAttribute("apellido") + "  ";
        }
        return "";
    }
    
    public String logout(HttpServletRequest request){
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "login.xhtml?faces-redirect=true";
    }
    
    public boolean isAdministrator(HttpServletRequest request){
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (boolean) session.getAttribute("administrator");
    }
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsersFacade getUsersFacade() {
        return usersFacade;
    }

    public void setUsersFacade(UsersFacade usersFacade) {
        this.usersFacade = usersFacade;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }
    
}

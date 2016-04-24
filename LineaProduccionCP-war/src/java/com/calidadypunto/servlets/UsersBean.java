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
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author lenovo
 */
@ManagedBean(name="UsersBean")
@ViewScoped
public class UsersBean implements Serializable{

    @EJB
    private UsersFacade usersFacade;
    
    private List<Users> usersList;
    private Users selectedUser;
    private Users newUser;
    private String newPassword;
    private String newPassword2;
    
    public UsersBean(){
        newUser = new Users();
        selectedUser = new Users();
    }
    
    public List<Users> getUsersList() {
        if(usersList == null || usersList.size() == 0){
            usersList = usersFacade.findAll();
        }
        return usersList;
    }
    public void setUsersList(List<Users> userasList) {
        this.usersList = userasList;
    }

    public Users getSelectedUser() {
        return selectedUser;
    }
    
    public void onRowSelect(SelectEvent event){
        selectedUser = usersFacade.findByUserName(((Users) event.getObject()).getUsername());
    }

    public void setSelectedUser(Users selectedUser) {
        if(selectedUser != null)
            this.selectedUser = selectedUser;
    }
    
    public String hasRights() throws IOException{
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(!(boolean)session.getAttribute("administrator")){
            FacesContext.getCurrentInstance().getExternalContext().redirect("errorAdminPage.xhtml");
        }
        return "";
    }
    
    public void showCreateDialog(){
        RequestContext.getCurrentInstance().execute("createDialog.show()");
    }

    public Users getNewUser() {
        return newUser;
    }

    public void setNewUser(Users newUser) {
        this.newUser = newUser;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }   
    
    
    public String createUser(){
        try{
            newUser.setPassword(new Crypt().cryptWithMD5(newUser.getPassword()));
            usersFacade.create(newUser);
        } catch (Exception ex) {
            addMessage("¡Error!", "No se puede crear el usuario.", FacesMessage.SEVERITY_ERROR);
            return "";
        }
        return "useradmin.xhtml?faces-redirect=true";
    }
    
    
    public void editUsers(){
        if(selectedUser != null){
            if(newPassword!= null && newPassword2 != null){
                if(newPassword.equals(newPassword2)){
                    Crypt c = new Crypt();
                    selectedUser.setPassword(c.cryptWithMD5(newPassword));
                    usersFacade.edit(selectedUser);
                    addMessage("¡Exito!", "Se cambió la contraseña.", FacesMessage.SEVERITY_INFO);
                }else{
                    addMessage("¡Error!", "No se puede cambiar la contraseña.", FacesMessage.SEVERITY_ERROR);
                }
            }else {
                addMessage("¡Error!", "Por favor escriba una contraseña.", FacesMessage.SEVERITY_WARN);
            }
            RequestContext.getCurrentInstance().execute("modifyDialog.hide()");
        } else {
            addMessage("¡Error!", "Error al seleccionar.", FacesMessage.SEVERITY_FATAL);
        }
    }
    
    public void changePassword(){
        if(newPassword!= null && newPassword2 != null){
            if(newPassword.equals(newPassword2)){
                HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                Users currentUser = usersFacade.find(session.getAttribute("id"));
                Crypt c = new Crypt();
                currentUser.setPassword(c.cryptWithMD5(newPassword));
                usersFacade.edit(currentUser);
                addMessage("¡Exito!", "Se cambió la contraseña.", FacesMessage.SEVERITY_INFO);
            } else {
                addMessage("¡Error!", "No se puede cambiar la contraseña.", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            addMessage("¡Error!", "Error al seleccionar.", FacesMessage.SEVERITY_FATAL);
        }
    }
    
    public String deleteUseras(){
        try {
            usersFacade.remove(selectedUser);
            return "useradmin.xhtml?faces-redirect=true";
        } catch (Exception e) {
            addMessage("¡Error!", "Error con la conexión a base de datos.", FacesMessage.SEVERITY_FATAL);
            return "";
        }
    }
    
    public void addMessage(String summary, String detail, Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}

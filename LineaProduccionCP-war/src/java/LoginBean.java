
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lenovo
 */
@ManagedBean(name="LoginBean")
@SessionScoped
public class LoginBean implements Serializable{
    
    private String userName;
    private String password;

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
    
    
    public String login(HttpServletRequest request) throws IOException{
        System.err.println(userName + ":" + password);
        return "login.xhtml?faces-redirect=true";
    }
    
}

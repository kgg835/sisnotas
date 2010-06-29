package ccuni.java.sysNotas.actionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ccuni.java.sysNotas.constantes.*;


public class AdministradorForm extends ActionForm {
    private String username;
    private String password;
    private String password2;
    private String nombres;
    private String apellidos;

    public void reset() {
    	username="";
        password="";
        password2="";
        nombres="";
        apellidos="";
    }

    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (username == null ||username.equals("") ) {
            errors.add("username", 
                       new ActionError("error.username.required"));
        } else if (username.length() < Constantes.MIN_LONG_USERNAME) {
            errors.add("username", 
                       new ActionError("error.username.minlength"));
        }
        if (password == null || password.equals("")) {
            errors.add("password", 
                       new ActionError("error.password.required"));
        } else if (password.length() < Constantes.MIN_LONG_PASSWORD) {
            errors.add("password", 
                       new ActionError("error.password.minlength"));
        } 
        if (nombres == null || nombres.equals(""))
            errors.add("nombres", 
                       new ActionError("error.nombres.required"));

        if (apellidos == null || apellidos.equals(""))
            errors.add("apellidos", 
                       new ActionError("error.apellidos.required"));
        return errors;
    }
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPassword2() {
        return password2;
    }
}

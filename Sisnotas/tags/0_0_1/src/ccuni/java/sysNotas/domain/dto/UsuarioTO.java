package ccuni.java.sysNotas.domain.dto;
import  ccuni.java.sysNotas.constantes.*;

import java.util.*;


public class UsuarioTO implements java.io.Serializable{
    private String codUsr;
    private String codGrpUsr;
    private String nombreGrupoUsuario;
    private String nombres;
    private String apellidos;
    private String codDoc;
    private String userName;
    private String password;
    private int logDel;
    private String usrIns;
    private Date dteIns;
    private String usrUpd;
    private Date dteUpd;
    private String nombreCompleto;
    private String nombreEstado;

    
    public String getNombreEstado() {
		if (logDel==0)
			return Constantes.NOMBRE_ESTADO_INACTIVO;
		else
			return Constantes.NOMBRE_ESTADO_ACTIVO;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public String getNombreCompleto() {
		return apellidos+" ,"+nombres;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public void setCodUsr(String codUsr) {
        this.codUsr = codUsr;
    }

    public String getCodUsr() {
        return codUsr;
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

    public void setCodDoc(String codDoc) {
        this.codDoc = codDoc;
    }

    public String getCodDoc() {
        return codDoc;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setLogDel(int logDel) {
        this.logDel = logDel;
    }

    public int getLogDel() {
        return logDel;
    }

    public void setUsrIns(String usrIns) {
        this.usrIns = usrIns;
    }

    public String getUsrIns() {
        return usrIns;
    }

    public void setDteIns(Date dteIns) {
        this.dteIns = dteIns;
    }

    public Date getDteIns() {
        return dteIns;
    }

    public void setUsrUpd(String usrUpd) {
        this.usrUpd = usrUpd;
    }

    public String getUsrUpd() {
        return usrUpd;
    }

    public void setDteUpd(Date dteUpd) {
        this.dteUpd = dteUpd;
    }

    public Date getDteUpd() {
        return dteUpd;
    }
    
    public void validate(){
        if(codGrpUsr.equals(Constantes.ADMINISTRADORES)){
            codDoc=null;
            codUsr=userName;
        }
        else{
            //
        }
    }

    public void setCodGrpUsr(String codGrpUsr) {
        this.codGrpUsr = codGrpUsr;
    }

    public String getCodGrpUsr() {
        return codGrpUsr; 
    }
    
    public boolean equals(Object obj){
    	UsuarioTO user =(UsuarioTO)obj;
    	if(this.codUsr.equals(user.getCodUsr()))
    		return true;
    	return false;
    }

	public String getNombreGrupoUsuario() {
		return nombreGrupoUsuario;
	}

	public void setNombreGrupoUsuario(String nombreGrupoUsuario) {
		this.nombreGrupoUsuario = nombreGrupoUsuario;
	}
}

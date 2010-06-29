package ccuni.java.sysNotas.domain.dto;

import java.io.Serializable;

public class DocenteTO implements Serializable{
    public DocenteTO() {
    }
    
    private String codDoc;
    private String nombres;
    private String apellidos;
    private String nombreCompleto;



    public String getNombreCompleto() {
    	nombreCompleto = apellidos+", "+nombres;
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCodDoc() {
		return codDoc;
	}

	public void setCodDoc(String codDoc) {
		this.codDoc = codDoc;
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
}

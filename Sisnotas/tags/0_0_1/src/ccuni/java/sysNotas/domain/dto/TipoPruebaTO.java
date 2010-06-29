package ccuni.java.sysNotas.domain.dto;

import java.io.Serializable;
import java.util.*;

import ccuni.java.sysNotas.constantes.Constantes;
public class TipoPruebaTO implements Serializable{
    public TipoPruebaTO() {
    }
    private String codTipPba;
    private String nombre;
    private int logdel;
    private String usrins;
    private Date dteins;
    private String usrupd;
    private Date dteupd;
    private String nombreTipoPrueba;
    
    //añadidos por luis
    private CursoTipoPruebaTO cursoTipoPrueba;
    private ArrayList<PruebaTO> pruebas;
    //-----


    public void setCodTipPba(String codTipPba) {
        this.codTipPba = codTipPba;
    }

    public String getCodTipPba() {
        return codTipPba;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setLogdel(int logdel) {
        this.logdel = logdel;
    }

    public int getLogdel() {
        return logdel;
    }

    public void setUsrins(String usrins) {
        this.usrins = usrins;
    }

    public String getUsrins() {
        return usrins;
    }

    public void setDteins(Date dteins) {
        this.dteins = dteins;
    }

    public Date getDteins() {
        return dteins;
    }

    public void setUsrupd(String usrupd) {
        this.usrupd = usrupd;
    }

    public String getUsrupd() {
        return usrupd;
    }

    public void setDteupd(Date dteupd) {
        this.dteupd = dteupd;
    }

    public Date getDteupd() {
        return dteupd;
    }

	public CursoTipoPruebaTO getCursoTipoPrueba() {
		return cursoTipoPrueba;
	}

	public void setCursoTipoPrueba(CursoTipoPruebaTO cursoTipoPrueba) {
		this.cursoTipoPrueba = cursoTipoPrueba;
	}

	public ArrayList<PruebaTO> getPruebas() {
		return pruebas;
	}

	public void setPruebas(ArrayList<PruebaTO> pruebas) {
		this.pruebas = pruebas;
	}
	
	public boolean equals(Object obj){
    	TipoPruebaTO to =(TipoPruebaTO)obj;
    	if(this.codTipPba.equals(to.getCodTipPba()))
    		return true;
    	return false;
	}
	public boolean isExamen(){
		if(codTipPba.equals(Constantes.EX_FINAL)|| codTipPba.equals(Constantes.EX_PARCIAL)|| codTipPba.equals(Constantes.EX_SUSTITORIO))
			return true;
		else return false;
	}

	public String getNombreTipoPrueba() {
		if(this.isExamen()){
			return Constantes.EXAMEN;
		}
		else
		return Constantes.PRACTICA;
	}

	public void setNombreTipoPrueba(String nombreTipoPrueba) {
		this.nombreTipoPrueba = nombreTipoPrueba;
	}
}

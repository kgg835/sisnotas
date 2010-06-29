package ccuni.java.sysNotas.domain.dto;

import java.io.Serializable;
import java.util.*;

public class CursoTipoPruebaTO implements Serializable{
    public CursoTipoPruebaTO() {
    }
    private String codCur;
    private String seccion;
    private String codpba;
    private int numero;
    private int numelim;
    private int logdel;
    private String usrins;
    private Date dteins;
    private String usrupd;
    private Date dteupd;
    private int peso;
    
    public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public void setCodCur(String codCur) {
        this.codCur = codCur;
    }

    public String getCodCur() {
        return codCur;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setCodpba(String codpba) {
        this.codpba = codpba;
    }

    public String getCodpba() {
        return codpba;
    }

    public int getNumelim() {
		return numelim;
	}

	public void setNumelim(int numelim) {
		this.numelim = numelim;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
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
}

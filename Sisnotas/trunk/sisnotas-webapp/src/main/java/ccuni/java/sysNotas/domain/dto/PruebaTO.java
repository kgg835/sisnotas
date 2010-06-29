package ccuni.java.sysNotas.domain.dto;

import java.io.Serializable;
import java.util.Date;

public class PruebaTO implements Serializable
{
    public PruebaTO()
    {
    }

    private String codCur;

    private String seccion;

    private String perAcad;

    private String codtippba;

    private int numtippba;

    private int peso;

    private int logdel;

    private String usrins;

    private Date dteins;

    private String usrupd;

    private Date dteupd;

    private int logRec;

    private int accionId;

    private EstadoPrueba estado;

    private int nuevoEstado;

    public int getNuevoEstado()
    {
        return nuevoEstado;
    }

    public void setNuevoEstado(int nuevoEstado)
    {
        this.nuevoEstado = nuevoEstado;
    }

    public EstadoPrueba getEstado()
    {
        return estado;
    }

    public void setEstado(EstadoPrueba estado)
    {
        this.estado = estado;
    }

    public void setCodCur(String codCur)
    {
        this.codCur = codCur;
    }

    public String getCodCur()
    {
        return codCur;
    }

    public void setCodtippba(String codtippba)
    {
        this.codtippba = codtippba;
    }

    public String getCodtippba()
    {
        return codtippba;
    }

    public void setNumtippba(int numtippba)
    {
        this.numtippba = numtippba;
    }

    public int getNumtippba()
    {
        return numtippba;
    }

    public void setLogdel(int logdel)
    {
        this.logdel = logdel;
    }

    public int getLogdel()
    {
        return logdel;
    }

    public void setUsrins(String usrins)
    {
        this.usrins = usrins;
    }

    public String getUsrins()
    {
        return usrins;
    }

    public void setDteins(Date dteins)
    {
        this.dteins = dteins;
    }

    public Date getDteins()
    {
        return dteins;
    }

    public void setUsrupd(String usrupd)
    {
        this.usrupd = usrupd;
    }

    public String getUsrupd()
    {
        return usrupd;
    }

    public void setDteupd(Date dteupd)
    {
        this.dteupd = dteupd;
    }

    public Date getDteupd()
    {
        return dteupd;
    }

    public void setPeso(int peso)
    {
        this.peso = peso;
    }

    public int getPeso()
    {
        return peso;
    }

    public void setSeccion(String seccion)
    {
        this.seccion = seccion;
    }

    public String getSeccion()
    {
        return seccion;
    }

    public void setPerAcad(String perAcad)
    {
        this.perAcad = perAcad;
    }

    public String getPerAcad()
    {
        return perAcad;
    }

    public int getAccionId()
    {
        return accionId;
    }

    public void setAccionId(int accionId)
    {
        this.accionId = accionId;
    }

    public int getLogRec()
    {
        return logRec;
    }

    public void setLogRec(int logRec)
    {
        this.logRec = logRec;
    }
}

package ccuni.java.sysNotas.domain.dto;

import java.util.Date;

public class EvaluacionTO
{
    public EvaluacionTO()
    {
    }

    private String codTipPba;

    private String codAlum;

    private String nombre;

    private String codCur;

    private String seccion;

    private String peracad;

    private String nota;

    private String notaRec;

    private String notaFinal;

    private String codcon;

    private String condicion;

    private String lognotrec;

    private int numtippba;

    private int logdel;

    private String usrins;

    private Date dteins;

    private String usrupd;

    private Date dteupd;

    public void setCodTipPba(String codTipPba)
    {
        this.codTipPba = codTipPba;
    }

    public String getCodTipPba()
    {
        return codTipPba;
    }

    public void setCodAlum(String codAlum)
    {
        this.codAlum = codAlum;
    }

    public String getCodAlum()
    {
        return codAlum;
    }

    public void setCodCur(String codCur)
    {
        this.codCur = codCur;
    }

    public String getCodCur()
    {
        return codCur;
    }

    public void setSeccion(String seccion)
    {
        this.seccion = seccion;
    }

    public String getSeccion()
    {
        return seccion;
    }

    public void setPeracad(String peracad)
    {
        this.peracad = peracad;
    }

    public String getPeracad()
    {
        return peracad;
    }

    public int getNumtippba()
    {
        return numtippba;
    }

    public void setNumtippba(int numtippba)
    {
        this.numtippba = numtippba;
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

    public String getNota()
    {
        return nota;
    }

    public void setNota(String nota)
    {
        this.nota = nota;
    }

    public String getNotaRec()
    {
        return notaRec;
    }

    public void setNotaRec(String notaRec)
    {
        this.notaRec = notaRec;
    }

    public String getCondicion()
    {
        return condicion;
    }

    public void setCondicion(String condicion)
    {
        this.condicion = condicion;
    }

    public String getLognotrec()
    {
        return lognotrec;
    }

    public void setLognotrec(String lognotrec)
    {
        this.lognotrec = lognotrec;
    }

    public String getCodcon()
    {
        return codcon;
    }

    public void setCodcon(String codcon)
    {
        this.codcon = codcon;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNotaFinal()
    {
        return notaFinal;
    }

    public void setNotaFinal(String notaFinal)
    {
        this.notaFinal = notaFinal;
    }
}

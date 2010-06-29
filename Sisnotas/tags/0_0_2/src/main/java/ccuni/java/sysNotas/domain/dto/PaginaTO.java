package ccuni.java.sysNotas.domain.dto;

import java.util.Date;

public class PaginaTO
{
    public PaginaTO()
    {
    }

    private String codPag;

    private String path;

    private String nombre;

    private int logdel;

    private String usrins;

    private Date dteins;

    private String usrupd;

    private Date dteupd;

    public void setCodPag(String codPag)
    {
        this.codPag = codPag;
    }

    public String getCodPag()
    {
        return codPag;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return path;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
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
}

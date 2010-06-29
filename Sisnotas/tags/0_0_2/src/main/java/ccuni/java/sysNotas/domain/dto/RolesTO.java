package ccuni.java.sysNotas.domain.dto;

import java.util.Date;

public class RolesTO
{
    public RolesTO()
    {
    }

    private String codusr;

    private String codgrpsr;

    private int logdel;

    private String usrins;

    private Date dteins;

    private String usrupd;

    private Date dteupd;

    public void setCodusr(String codusr)
    {
        this.codusr = codusr;
    }

    public String getCodusr()
    {
        return codusr;
    }

    public void setCodgrpsr(String codgrpsr)
    {
        this.codgrpsr = codgrpsr;
    }

    public String getCodgrpsr()
    {
        return codgrpsr;
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

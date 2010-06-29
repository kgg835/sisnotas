package ccuni.java.sysNotas.domain.business;

public class PruebaLogic
{
    private int id;

    private String action;

    private int usrins;

    private int usrupd;

    private int logusrins;

    private int logusrupd;

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getLogusrins()
    {
        return logusrins;
    }

    public void setLogusrins(int logusrins)
    {
        this.logusrins = logusrins;
    }

    public int getLogusrupd()
    {
        return logusrupd;
    }

    public void setLogusrupd(int logusrupd)
    {
        this.logusrupd = logusrupd;
    }

    public int getUsrins()
    {
        return usrins;
    }

    public void setUsrins(int usrins)
    {
        this.usrins = usrins;
    }

    public int getUsrupd()
    {
        return usrupd;
    }

    public void setUsrupd(int usrupd)
    {
        this.usrupd = usrupd;
    }
}

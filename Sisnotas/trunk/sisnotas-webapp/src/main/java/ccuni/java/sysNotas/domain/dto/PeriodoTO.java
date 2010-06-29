package ccuni.java.sysNotas.domain.dto;

public class PeriodoTO implements java.io.Serializable
{
    private String peracad;

    private String detalle;

    private String usrins;

    private String nombreperacad;

    public PeriodoTO()
    {
    }

    public String getDetalle()
    {
        return detalle;
    }

    public void setDetalle(String detalle)
    {
        this.detalle = detalle;
    }

    public String getNombreperacad()
    {
        return nombreperacad;
    }

    public void setNombreperacad(String nombreperacad)
    {
        this.nombreperacad = nombreperacad;
    }

    public String getPeracad()
    {
        return peracad;
    }

    public void setPeracad(String peracad)
    {
        this.peracad = peracad;
    }

    public String getUsrins()
    {
        return usrins;
    }

    public void setUsrins(String usrins)
    {
        this.usrins = usrins;
    }
}

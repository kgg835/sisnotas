package ccuni.java.sysNotas.domain.dto;

public class EstadoPruebaTO
{
    private int codigo;

    private String accion;

    private String descripcion;

    public String getAccion()
    {
        return accion;
    }

    public void setAccion(String accion)
    {
        this.accion = accion;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
}

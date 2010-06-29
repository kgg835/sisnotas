package ccuni.java.sysNotas.domain.dto;

import java.io.Serializable;

public class ParametrosTO implements Serializable
{
    private int migracion_docentes;

    private String peracad;

    private int migracion_cursos;

    private int parametros_cursos;

    private int inicio_periodo;

    private int tipos_prueba_definidos;

    public int getTipos_prueba_definidos()
    {
        return tipos_prueba_definidos;
    }

    public void setTipos_prueba_definidos(int tipos_prueba_definidos)
    {
        this.tipos_prueba_definidos = tipos_prueba_definidos;
    }

    private String nombre_periodo;

    public int getInicio_periodo()
    {
        return inicio_periodo;
    }

    public void setInicio_periodo(int inicio_periodo)
    {
        this.inicio_periodo = inicio_periodo;
    }

    public int getMigracion_cursos()
    {
        return migracion_cursos;
    }

    public void setMigracion_cursos(int migracion_cursos)
    {
        this.migracion_cursos = migracion_cursos;
    }

    public String getPeracad()
    {
        return peracad;
    }

    public void setPeracad(String peracad)
    {
        this.peracad = peracad;
    }

    public int getMigracion_docentes()
    {
        return migracion_docentes;
    }

    public void setMigracion_docentes(int migracion_docentes)
    {
        this.migracion_docentes = migracion_docentes;
    }

    public int getParametros_cursos()
    {
        return parametros_cursos;
    }

    public void setParametros_cursos(int parametros_cursos)
    {
        this.parametros_cursos = parametros_cursos;
    }

    public String getNombre_periodo()
    {
        return nombre_periodo;
    }

    public void setNombre_periodo(String nombre_periodo)
    {
        this.nombre_periodo = nombre_periodo;
    }
}

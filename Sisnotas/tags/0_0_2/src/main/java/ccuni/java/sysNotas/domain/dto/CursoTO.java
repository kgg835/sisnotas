package ccuni.java.sysNotas.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class CursoTO implements Serializable
{
    public CursoTO()
    {
    }

    private String cursoId;

    private String seccion;

    private String perAcad;

    private String nombre;

    private boolean paramExist;

    private int param_definidos;

    private ArrayList<TipoPruebaTO> tiposDePrueba;

    private ArrayList<PruebaTO> pruebas;

    private DocenteTO docenteResp;

    private boolean hayTiposDePruebaDefinidos;

    private int estado;

    public int getEstado()
    {
        return estado;
    }

    public void setEstado(int estado)
    {
        this.estado = estado;
    }

    public boolean isHayTiposDePruebaDefinidos()
    {
        if (tiposDePrueba != null)
        {
            if (tiposDePrueba.size() > 0)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    //
    public void setCursoId(String cursoId)
    {
        this.cursoId = cursoId;
    }

    public String getCursoId()
    {
        return cursoId;
    }

    public void setPerAcad(String perAcad)
    {
        this.perAcad = perAcad;
    }

    public String getPerAcad()
    {
        return perAcad;
    }

    public void setSeccion(String seccion)
    {
        this.seccion = seccion;
    }

    public String getSeccion()
    {
        return seccion;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    public boolean isParamExist()
    {
        return paramExist;
    }

    public void setParamExist(boolean paramExist)
    {
        this.paramExist = paramExist;
    }

    public ArrayList<TipoPruebaTO> getTiposDePrueba()
    {
        return tiposDePrueba;
    }

    public void setTiposDePrueba(ArrayList<TipoPruebaTO> tiposDePrueba)
    {
        this.tiposDePrueba = tiposDePrueba;
    }

    public TipoPruebaTO getTipoPrueba(String codTipPba)
    {
        ArrayList<TipoPruebaTO> list = this.getTiposDePrueba();
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getCodTipPba().equals(codTipPba))
                return list.get(i);
        }
        return null;
    }

    public DocenteTO getDocenteResp()
    {
        return docenteResp;
    }

    public void setDocenteResp(DocenteTO docenteResp)
    {
        this.docenteResp = docenteResp;
    }

    public ArrayList<TipoPruebaTO> getExamenes()
    {
        if (tiposDePrueba != null)
        {
            ArrayList<TipoPruebaTO> list = new ArrayList<TipoPruebaTO>(tiposDePrueba);
            for (int i = 0; i < list.size(); i++)
            {
                if (!list.get(i).isExamen())
                    list.remove(i);
            }
            return list;
        }
        else
            return null;
    }

    public boolean equals(Object obj)
    {
        CursoTO to = (CursoTO) obj;
        if (this.getCursoId().equals(to.getCursoId()) && this.getSeccion().equals(to.getSeccion())
                && this.getPerAcad().equals(to.getPerAcad()))
        {
            return true;
        }
        return false;
    }

    public ArrayList<PruebaTO> getPruebas()
    {
        return pruebas;
    }

    public void setPruebas(ArrayList<PruebaTO> pruebas)
    {
        this.pruebas = pruebas;
    }

    public int getParam_definidos()
    {
        return param_definidos;
    }

    public void setParam_definidos(int param_definidos)
    {
        this.param_definidos = param_definidos;
    }
}

package ccuni.java.sysNotas.dao;

import java.util.ArrayList;

import ccuni.java.sysNotas.domain.dto.CursoTO;
import ccuni.java.sysNotas.domain.dto.PruebaTO;
import ccuni.java.sysNotas.domain.dto.TipoPruebaTO;
import ccuni.java.sysNotas.domain.dto.UsuarioTO;

public interface CursoDAO extends GenericDAO
{
    public boolean existenParametrosCalificacion(String codigoCurso, String seccion) throws TransactionException;

    public boolean existeDocente(String codigoCurso, String seccion) throws TransactionException;

    public CursoTO obtenerParametros(String codigoCurso, String seccion) throws TransactionException;

    public ArrayList<PruebaTO> obtenerParametros(String codigoCurso, String seccion, String codTipPba)
            throws TransactionException;

    public Integer updatePruebas(String codigoCurso, String seccion, ArrayList<PruebaTO> pruebas)
            throws TransactionException;

    public ArrayList<TipoPruebaTO> obtenerTiposDePrueba() throws TransactionException;

    public Integer addTipoPrueba(String codCur, String secc, TipoPruebaTO to, String codUser, String perAcad)
            throws TransactionException;

    public Integer updateTipoPrueba(String codCur, String secc, TipoPruebaTO to, String codUser, String perAcad)
            throws TransactionException;

    public Integer eliminarTipoPrueba(String codCur, String secc, String codTipPba, String codUser)
            throws TransactionException;

    public TipoPruebaTO find(String codCur, String secc, TipoPruebaTO tipoprueba) throws TransactionException;

    // public int insertDocente(String codDoc, String codCur, String
    // seccion)throws TransactionException;
    public int updateDocente(String codDoc, String codCur, String seccion) throws TransactionException;

    public ArrayList<CursoTO> selectCursos(UsuarioTO usuario, String perAcad) throws TransactionException;

    public int actualizarEstado(String cursoId, String seccion, int estado) throws TransactionException;
}

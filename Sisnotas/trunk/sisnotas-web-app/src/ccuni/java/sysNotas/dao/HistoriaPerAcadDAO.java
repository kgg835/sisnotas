package ccuni.java.sysNotas.dao;

public interface HistoriaPerAcadDAO extends GenericDAO{
	
	public int insert(String perAcad,String detalle)throws TransactionException;

}

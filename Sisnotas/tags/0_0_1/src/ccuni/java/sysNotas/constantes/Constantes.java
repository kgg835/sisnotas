package ccuni.java.sysNotas.constantes;

public abstract  class Constantes {
    //constantes para los grupos de usarios del sistema
    public static final String ADMINISTRADOR_SISTEMA="1";
    public static final String ADMINISTRADORES="2";
    public static final String DOCENTES="3";
    public static final int PESO_DEFAULT=1;
    public static final int ACCION_DEFAULT=2;
    public static final int NO_MIGRADO=0;
    public static final int MIGRADO=1;
    
    //nombre de las tabla clipper
    
    public static final String TB_CURRICULA="CURRICS";
    public static final String TB_SECCION="SECC";
    
    //
    public static final int ACTIVO=1;
    public static final int INACTIVO=0;
    
    public static final int PARAM_DEF=1;
    public static final int PARAM_NO_DEF=0;
    
    public static final String EX_PARCIAL="EP";
    public static final String EX_FINAL="EF";
    public static final String EX_SUSTITORIO="ES";
    
    //
    public static final int LONG_CLAVE=6;
    
    public static final String NOMBRE_ESTADO_ACTIVO="ACTIVO";
    public static final String NOMBRE_ESTADO_INACTIVO="INACTIVO";
    
    public static final String EXAMEN="EXAMEN";
    public static final String PRACTICA="PRACTICA";
    
    public static final int MIN_LONG_USERNAME=5;
    public static final int MIN_LONG_PASSWORD=6;
    
}

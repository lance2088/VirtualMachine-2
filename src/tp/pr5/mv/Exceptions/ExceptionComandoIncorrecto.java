package tp.pr5.mv.Exceptions;
/**
 * Clase de excepcion que se lanzará cuando el usuario meta un comando incorrecto
 * @author usuario_local
 *
 */
@SuppressWarnings("serial")
public class ExceptionComandoIncorrecto extends Exception{
	
	public ExceptionComandoIncorrecto(String mensaje){
		super(mensaje);		
	}
	
}

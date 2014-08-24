package tp.pr5.mv.Exceptions;
/**
 * Excepcion que se lanzara caundo se haga una division por cero
 * @author David Rico & Sergio Fuentes
 *
 */
public class ExceptionDivisionByZero extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2328514079471771386L;

	public ExceptionDivisionByZero(String mensaje){
		super(mensaje);		
	}
	
}

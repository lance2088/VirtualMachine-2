package tp.pr5.mv.Exceptions;
/**
 * Clase para la excepción que se lanzara cuando el usuario meta un argumento no valido
 *@author David Rico & Sergio Fuentes
 *
 */
@SuppressWarnings("serial")
public class ExceptionArgumentoIncorrecto extends Exception {;

	public ExceptionArgumentoIncorrecto(String mensaje)
	{
		super(mensaje);
	}

}

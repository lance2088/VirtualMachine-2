package tp.pr5.mv.Exceptions;


/**
 * Excepcion para cuando se accede a una posicion no permitida al realizar un salto
 * @author David Rico & Sergio Fuentes
 *
 */
@SuppressWarnings("serial")
public class ExceptionIncorrectProgramCounter extends Exception {
	
	public ExceptionIncorrectProgramCounter(String mensaje)
	{
		super(mensaje);
	}

	
}

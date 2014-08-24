package tp.pr5.mv.Exceptions;
/**
 * Excepción que se lanza cuando la intruccion no es correcta.
 * @author usuario_local
 *
 */
@SuppressWarnings("serial")
public class ExceptionIntruccionIncorrecta extends Exception {

	public ExceptionIntruccionIncorrecta(String mensaje)
	{
		super(mensaje);
	}

}

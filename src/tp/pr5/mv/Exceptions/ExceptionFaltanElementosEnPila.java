package tp.pr5.mv.Exceptions;
/**
 * Excepción que se lanza cuando falta algun elemento en la pila. por ejemplo cuando queremos hacer una suma y solo 
 * tenemos un elemento.
 *@author David Rico & Sergio Fuentes
 *
 */
@SuppressWarnings("serial")
public class ExceptionFaltanElementosEnPila extends Exception{
	public ExceptionFaltanElementosEnPila (String mensaje)
	{
		super(mensaje);
	}

}

package tp.pr5.mv.Exceptions;
/**
 * Excepcion ques lanza si se intenta acceder a la pila vacía
 *@author David Rico & Sergio Fuentes
 *
 */
@SuppressWarnings("serial")
public class ExceptionEmptyStack extends Exception {
	
	public ExceptionEmptyStack(String mensaje){
		super(mensaje);
	}

}

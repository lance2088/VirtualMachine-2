package tp.pr5.mv.instrucciones.aritmeticas;

import tp.pr5.mv.Exceptions.ExceptionDivisionByZero;
import tp.pr5.mv.instrucciones.Instruction;
/**
 * Clase que hereda de Aritmeticas y representa la operacion aritmetica de la division.
 * @author David Rico & Sergio Fuentes
 *
 */
public class Div extends Aritmeticas{

	/**
	 * Divide n1/n2 siempre que n2 sea distinto de cero, si no devuelve false.
	 * @throws ExceptionDivisionByZero 
	 */
	@Override
	protected boolean execute(int n1, int n2) throws ExceptionDivisionByZero {
		if (n2==0)
			throw new ExceptionDivisionByZero("Error ejecutando DIV: division por cero!");
		else{
			this.result = (n1 / n2);	
			return true;
		}
	}

	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("DIV")))
			return new Div();
		else return null;
	}

	@Override
	public String toString() {		
		return "DIV";
	}

}

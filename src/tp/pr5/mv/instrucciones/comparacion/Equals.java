package tp.pr5.mv.instrucciones.comparacion;

import tp.pr5.mv.instrucciones.Instruction;

/**
 * Clase que representa la operacion de comparacion EQ
 * @author David Rico & Sergio Fuentes 
 */
public class Equals extends NumericCond {

	@Override
	/*
	 * Si la cima y la subcima son iguales devuelve true.
	 */
	protected boolean compare(int cima, int subcima) {		
		return cima == subcima;
	}

	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("EQ")))			
			return new Equals();
		
		else return null;
	}

	@Override
	public String toString() {
		
		return "EQUALS" ;
	}

}

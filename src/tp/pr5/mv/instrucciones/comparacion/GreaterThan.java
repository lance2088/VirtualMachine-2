package tp.pr5.mv.instrucciones.comparacion;

import tp.pr5.mv.instrucciones.Instruction;

/**
 * Clase que representa la operacion de comparacion GT
 * @author David Rico & Sergio Fuentes 
 */
public class GreaterThan extends NumericCond {

	@Override
	/*
	 * Si la subcima es mayor que la cima devuelve true.
	 */
	protected boolean compare(int cima, int subcima) {		
		return subcima > cima;
	}

	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("GT")))			
			return new GreaterThan();
		
		else return null;
	}

	@Override
	public String toString() {
		
		return "GT" ;
	}

}
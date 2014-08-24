package tp.pr5.mv.instrucciones.comparacion;

import tp.pr5.mv.instrucciones.Instruction;

/**
 * Clase que representa la operacion de comparacion LT
 * @author David Rico & Sergio Fuentes 
 */
public class LessThan extends NumericCond {

	@Override
	/*
	 * Si la subcima es menor estricto que la cima devuelve true.
	 */
	protected boolean compare(int cima, int subcima) {		
		return subcima < cima;
	}

	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("LT")))			
			return new LessThan();
		
		else return null;
	}

	@Override
	public String toString() {
		
		return "LT" ;
	}

}

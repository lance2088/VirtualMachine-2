package tp.pr5.mv.instrucciones.comparacion;

import tp.pr5.mv.instrucciones.Instruction;

/**
 * Clase que representa la operacion de comparacion LE
 * @author David Rico & Sergio Fuentes 
 */
public class LessOrEqual extends NumericCond {

	@Override
	/*
	 * Si la subcima es menor igual que la cima devuelve true.
	 */
	protected boolean compare(int cima, int subcima) {		
		return subcima <= cima;
	}

	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("LE")))			
			return new LessOrEqual();
		
		else return null;
	}

	@Override
	public String toString() {
		
		return "LE" ;
	}

}
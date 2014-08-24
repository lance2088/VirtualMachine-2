package tp.pr5.mv.instrucciones.booleanas;

import tp.pr5.mv.instrucciones.Instruction;

/**
 * Clase que representa la operacion OR. Es decir, si los dos son iguales a cero devuelve 0 y 1 en caso contrario.
 * @author David Rico & Sergio Fuentes
 *
 */
public class Or extends AndOr {
	
	// devuelve false cuando los dos parmetros son iguales de cero
	@Override
	protected boolean execute(int n1, int n2) {
		if ((n1 == 0) && (n2 == 0)) {
			return false;
		} else
			return true;
	}

	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("OR")))
			return new Or();
		else
			return null;
	}

	@Override
	public String toString() {

		return "OR";
	}

}

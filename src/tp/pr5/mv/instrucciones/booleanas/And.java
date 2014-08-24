package tp.pr5.mv.instrucciones.booleanas;

import tp.pr5.mv.instrucciones.Instruction;

/**
 * Clase que representa la operacion AND. Es decir, si los dos son distintos de cero devuelve 1 y 0 en caso contrario.
 * @author David Rico & Sergio Fuentes
 *
 */
public class And extends AndOr {

	// devuelve true cuando los dos parametros son distinto de cero
	@Override
	protected boolean execute(int n1, int n2) {
		if ((n1 != 0) && (n2 != 0)) {
			return true;
		} else
			return false;

	}

	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("AND")))
			return new And();
		else
			return null;
	}

	@Override
	public String toString() {

		return "AND";
	}

}

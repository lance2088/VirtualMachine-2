package tp.pr5.mv.instrucciones.salto;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.instrucciones.Instruction;
/**
 * Clase que realiza el salto relativo RBF, sumando a contador de pc lo pasado por parmetro si la cima de la pila es igual a cero.
 * @author David Rico & Sergio Fuentes
 *
 */
public class Rbf extends RelativeJumps {

	/**
	 * Constructor de la clase
	 * @param n (parmetro a donde se salta sumando pc)
	 */
	public Rbf(int n) {
		this.numSalto = n;
	}

	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 2) && (str[0].equalsIgnoreCase("RBF") && Constantes.isNumero(str[1]))) {
			int n = Integer.parseInt(str[1]);
			return new Rbf(n);
		}

		else
			return null;
	}

	@Override
	public String toString() {

		return "RBF " + this.numSalto;
	}

	/**
	 * Devuelve true si en la cima de la pila se almacena un cero y false en caso contrario
	 */
	@Override
	protected boolean execute(int n) {
		if (n == 0) return true;
		else return false;
	}

}

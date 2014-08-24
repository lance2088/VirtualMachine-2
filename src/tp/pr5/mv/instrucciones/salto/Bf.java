package tp.pr5.mv.instrucciones.salto;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.instrucciones.Instruction;

/**
 * Clase que realiza el salto condicional BF, saltando al valor indicado por parametro si en la cima de la pila se encuentra un cero.
 * @author David Rico & Sergio Fuentes
 *
 */
public class Bf extends ConditionalJumps {

	/**
	 * Constructor de la clase
	 * @param n (parametro a donde se salta)
	 */
	public Bf(int n) {
		this.numSalto = n;
	}

	@Override
	public Instruction parse(String[] str) {
		// Se parsea que se salte a un numero y ademas que no sea un numero negativo ya que no se podra acceder.
		if ((str.length == 2) && (str[0].equalsIgnoreCase("BF")) && (Constantes.isNumero(str[1])) && Constantes.isNumeroPositivo(str[1])) {
			int n = Integer.parseInt(str[1]);
			return new Bf(n);
		}
		else
			return null;
	}

	@Override
	public String toString() {

		return "BF " + this.numSalto;
	}

	/**
	 * Devuelve true si en la cima de la pila se almacena un cero y false en caso contrario
	 */
	@Override
	protected boolean execute(int n) {
		if (n == 0){		
			return true;
		}
		else return false;
	}

}

package tp.pr5.mv.instrucciones.salto;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.instrucciones.Instruction;

/**
 * Clase que realiza el salto condicional BT, saltando al valor indicado por parametro si en la cima de la pila se encuentra un valor distinto de cero.
 * @author David Rico & Sergio Fuentes
 *
 */
public class Bt extends ConditionalJumps{
	
	/**
	 * Constructor de la clase
	 * @param n (posicion a donde se salta)
	 */
	public Bt(int n) {
		this.numSalto = n;
	}
	
	
	
	public Instruction parse(String[] str) {
		// Se parsea que se salte a un numero y ademas que no sea un numero negativo ya que no se podra acceder.
		if ( (str.length == 2 ) && (str[0].equalsIgnoreCase("BT") && Constantes.isNumero(str[1]))  && Constantes.isNumeroPositivo(str[1])){
			int n = Integer.parseInt(str[1]);
			return new Bt(n);
		}
					
		else return null;
	}
	
	@Override
	public String toString() {
		
		return "BT " + this.numSalto;
	}

	/**
	 * Devuelve false si en la cima de la pila se almacena un cero y true en caso contrario
	 */
	@Override
	protected boolean execute(int n) {
		if (n != 0)
			return true;

		else
			return false;
	}



	

}

package tp.pr5.mv.instrucciones.aritmeticas;

import tp.pr5.mv.instrucciones.Instruction;

/**
 * Clase que hereda de Aritmeticas y representa la operacion aritmetica de la resta.
 * @author David Rico & Sergio Fuentes
 *
 */
public class Sub extends Aritmeticas {
	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("SUB")) )
			return new Sub();		
		else return null;
	}

	/**
	 * Resta n1-n2 y lo guarda en el atributo protegido result.
	 */
	@Override
	protected boolean execute(int n1, int n2) {
		this.result = n1 - n2;
		return true;
	}

	@Override
	public String toString() {
		
		return "SUB";
	}
	
	

}
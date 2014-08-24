package tp.pr5.mv.instrucciones.resto;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;
/**
 * clase que implementa los metodos execute, parse y tostring, Apila un numero.
 * @author David & Sergio
 *
 */
public class Push extends RestSeq {

	private int num;
	/**
	 * Constructor
	 * @param n . Numero a apilar.
	 */
	public Push(int n){
		this.num = n;
	}
	
	@Override
	/*
	 * Apila num .
	 */
	protected void executeAux(CPU cpu) {
		cpu.push(num);
		
	}

	@Override
	public Instruction parse(String[] str) {
		if ( (str.length == 2 ) && (str[0].equalsIgnoreCase("PUSH")) && Constantes.isNumero(str[1] )){
			int n = Integer.parseInt(str[1]);
			return new Push(n);
		}
					
		else return null;
	}

	@Override
	public String toString() {
		return "PUSH " + this.num;
	}

}

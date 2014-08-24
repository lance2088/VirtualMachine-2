package tp.pr5.mv.instrucciones.resto;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;
/**
 * clase que implementa los metodos execute, parse y tostring, Hace exit para salir del programa.
 * @author David & Sergio
 *
 */
public class Halt extends RestSeq {

		
	@Override	
	/*
	 * Ejecuta el exit
	 */
	protected void executeAux(CPU cpu) {
		cpu.exit();
		
	}

	@Override
	public Instruction parse(String[] str) {
		if ( (str.length == 1 ) && (str[0].equalsIgnoreCase("HALT")) ){
			return new Halt();
		}
					
		else return null;
	}

	@Override
	public String toString() {
		return "HALT";
	}

}


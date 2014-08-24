package tp.pr5.mv.instrucciones.resto;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;

/**
 * clase que implementa los metodos execute, parse y tostring. Consigue duplicar la cima de la pila.
 * @author David & Sergio
 *
 */
public class Dup extends RestSeq {

		
	@Override
	/*
	 * Duplica la cima de la pila.
	 */
	protected void executeAux(CPU cpu) throws ExceptionEmptyStack {
		if (cpu.getSizePila() < 1)
			throw new ExceptionEmptyStack ("Error ejecutando DUP: Pila vacia ");
		else
		{
			int n = cpu.pop();
			cpu.push(n);
			cpu.push(n);
			
		}
		
	}
	
	@Override
	public Instruction parse(String[] str) {
		if ( (str.length == 1 ) && (str[0].equalsIgnoreCase("DUP")) ){
			return new Dup();
		}
					
		else return null;
	}

	@Override
	public String toString() {
		return "DUP";
	}
	
}
 


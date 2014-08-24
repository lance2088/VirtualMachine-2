package tp.pr5.mv.instrucciones.resto;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;
/**
 * clase que implementa los metodos execute, parse y tostring, Quita la cima de la pila.
 * @author David & Sergio
 *
 */
public class Pop extends RestSeq {

	@Override
	protected void executeAux(CPU cpu) throws ExceptionEmptyStack {
		if (cpu.getSizePila() < 1)
			throw new ExceptionEmptyStack("Error ejecutando POP: Pila vacia");
		else{
			cpu.pop();
			
		}
	}

	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("POP")) )
			return new Pop();		
		else return null;
	}

	@Override
	public String toString() {
		return "POP";
	}

}

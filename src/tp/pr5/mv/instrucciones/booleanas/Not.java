package tp.pr5.mv.instrucciones.booleanas;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;

/**
 * Clase que representa la operacion NOT. Es decir, si en la cima de la pila se
 * encuentra un cero metera un uno y si es distinto de cero metera un cero.
 * 
 * @author David Rico & Sergio Fuentes
 * 
 * 
 */
public class Not implements Instruction {

	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("NOT"))) {
			return new Not();
		} else
			return null;
	}

	@Override
	public String toString() {
		return "NOT";
	}

	/**
	 * Devuelve true siempre que se realice la operacion es decir, siempre que
	 * en la pila halla al menos un elemento. Para ello coje el elemento de la
	 * cima y mira si es un cero para meter un uno o si es distinto de cero para
	 * meter un cero. El contador de programa siempre se incrementa.
	 * @throws ExceptionEmptyStack 
	 */
	@Override
	public void execute(CPU cpu) throws ExceptionEmptyStack {

	
			if (cpu.pop() == 0)
				cpu.push(1);
			else
				cpu.push(0);
			
			cpu.increaseProgramCounter();
			
		

	}

}

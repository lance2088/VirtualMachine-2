package tp.pr5.mv.instrucciones.resto;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;

/**
 * clase que implementa los metodos execute, parse y tostring. Consigue
 * intercambiar la cima y la subcima de la pila.
 * 
 * @author David & Sergio
 * 
 */
public class Flip extends RestSeq {

	@Override
	/*
	 * Intercambia el valor de la cima con el de la subcima.
	 */
	protected void executeAux(CPU cpu) throws ExceptionFaltanElementosEnPila, ExceptionEmptyStack {

			if (cpu.getSizePila() < 2) {
				throw new ExceptionFaltanElementosEnPila("Error ejecutando FLIP: falta un operando!");
			} else {
				int n1 = cpu.pop();
				int n2 = cpu.pop();
				cpu.push(n1);
				cpu.push(n2);
				
			}

	}

	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("FLIP"))) {
			return new Flip();
		}

		else
			return null;
	}

	@Override
	public String toString() {
		return "FLIP";
	}

}

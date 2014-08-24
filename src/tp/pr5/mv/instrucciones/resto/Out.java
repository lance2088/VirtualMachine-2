package tp.pr5.mv.instrucciones.resto;

import java.io.IOException;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;
/**
 * clase que implementa los metodos execute, parse y tostring, Muestra por pantalla el caracter asociado al numero 
 * de la cima.
 * @author David & Sergio
 *
 */
public class Out extends RestSeq {

	
	@Override
	/*
	 * Muestra por pantalla el valor del caracter de la cima de la pila.
	 */
	protected void executeAux(CPU cpu) throws ExceptionEmptyStack, IOException {
		/*if (cpu.getSizePila() < 1)
			throw new ExceptionEmptyStack("Error ejecutando OUT: Pila vacia");
		else
		{*/		
		cpu.getMetodoSalida().write(cpu.pop());		
	}

	@Override
	public Instruction parse(String[] str) {
		if ( (str.length == 1 ) && (str[0].equalsIgnoreCase("OUT")) ){
			return new Out();
		}
					
		else return null;
	}

	@Override
	public String toString() {
		return "OUT";
	}
	
}

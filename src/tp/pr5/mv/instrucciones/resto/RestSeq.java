package tp.pr5.mv.instrucciones.resto;

import java.io.IOException;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;

/**
 * Clase abstracta con metodo executeAux, Implementa el execute de la clase
 * instruction
 * 
 * @author David & Sergio
 * 
 */
public abstract class RestSeq implements Instruction {
	/**
	 * Es un ejecuta auxiliar que devuelve true si se ejecuta sin errores, se
	 * utilza dentro del Ejecuta principal.
	 * 
	 * @param cpu
	 * @throws ExceptionFaltanElementosEnPila 
	 * @throws IOException 
	 * @throws ExceptionIncorrectProgramCounter 
	 */
	abstract protected void executeAux(CPU cpu) throws ExceptionEmptyStack, ExceptionFaltanElementosEnPila, IOException, ExceptionIncorrectProgramCounter;

	@Override
	public void execute(CPU cpu) throws ExceptionEmptyStack, ExceptionFaltanElementosEnPila, IOException, ExceptionIncorrectProgramCounter {
		
		this.executeAux(cpu);
		cpu.increaseProgramCounter();		
	
	}
}

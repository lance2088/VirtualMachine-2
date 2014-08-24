package tp.pr5.mv.instrucciones.indirectas;

import tp.pr5.mv.Exceptions.ExceptionDivisionByZero;
import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;

/**
 * Clase encargada de los saltos indirectos, es decir, todas las operaciones no estáticas, en el sentido de que la dirección
 * de salto no está prefijada en el código.
 * @author David Rico & Sergio Fuentes
 *
 */
public abstract class Indirectos implements Instruction {
	/**
	 * Se encarga de executar una de los 3 tipos de instrucciones indirectas:
	 * 1) JUMPIND:		
	 * 2) LOADING
	 * 3) STOREING
	 * @param cpu
	 * @throws ExceptionEmptyStack
	 * @throws ExceptionFaltanElementosEnPila
	 * @throws ExceptionIncorrectProgramCounter
	 */
	protected abstract void executeAux(CPU cpu) throws ExceptionEmptyStack, ExceptionFaltanElementosEnPila, ExceptionIncorrectProgramCounter;
	
	@Override
	public void execute(CPU cpu) throws ExceptionFaltanElementosEnPila,ExceptionEmptyStack, ExceptionDivisionByZero, ExceptionIncorrectProgramCounter {
		this.executeAux(cpu);
		
	}

}

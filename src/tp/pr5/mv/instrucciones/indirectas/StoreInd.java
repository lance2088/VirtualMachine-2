package tp.pr5.mv.instrucciones.indirectas;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;

/**
 * Operación relacionada con STORE, escribe en la posicion de memoria indicada por la subcima
 *	el valor que aparece en la cima de la pila.
 * @author David Rico && Sergio Fuentes
 *
 */
public class StoreInd extends Indirectos {

	public StoreInd() {
	}

	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("STOREIND")))
			return new StoreInd();
		else
			return null;
	}

	@Override
	protected void executeAux(CPU cpu) throws ExceptionEmptyStack,ExceptionFaltanElementosEnPila, ExceptionIncorrectProgramCounter {
		
			int valor = cpu.pop();
			int posicion = cpu.pop();
			if (posicion < 0)
				throw new ExceptionIncorrectProgramCounter("Error ejecutando " + cpu.getCurrentInstruction().toString() + " : salto a una posicion negativa");
			else{
				cpu.insertarEnMemoria(posicion, valor);
				cpu.increaseProgramCounter();
			}
			
	}
	
	public String toString(){
		return "STOREIND";
	}

}

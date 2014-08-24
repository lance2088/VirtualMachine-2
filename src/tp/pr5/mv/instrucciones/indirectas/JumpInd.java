package tp.pr5.mv.instrucciones.indirectas;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;
/**
 * Instruccion que permite decidir el lugar al que se salta en el
 *	momento de la ejecucion. JUMPIND lee de la cima de la pila el valor del
 *	nuevo contador de programa en lugar de cogerlo desde el parámetro de la propia
 *	instrucción. Antes de saltar elimina el dato de la pila.
 * @author David Rico & Sergio Fuentes
 *
 */
public class JumpInd extends Indirectos {
	public JumpInd() {

	}

	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("JUMPIND")))
			return new JumpInd();
		else
			return null;
	}

	@Override
	protected void executeAux(CPU cpu) throws ExceptionEmptyStack,
			ExceptionIncorrectProgramCounter {
		int numSalto = cpu.pop();
		if (numSalto >= cpu.numeroInstrucciones() || (numSalto < 0)) {
			cpu.exit();
			throw new ExceptionIncorrectProgramCounter("Error ejecutando "
					+ cpu.getCurrentInstruction().toString()
					+ " :  salto no permitido. Fin de la ejecucion");
			// return false;
		} else {
			// Modificamos el contador de programa
			cpu.setContadorPc(numSalto);
			
		}
	}
	
	public String toString(){
		return "JUMPIND";
	}

}

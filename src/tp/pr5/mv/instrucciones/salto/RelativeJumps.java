package tp.pr5.mv.instrucciones.salto;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;

/**
 * Clase abstracta para los saltos relativos, que en vez de saltar a la posicion
 * n del array de instrucciones suman n. Es decir si n = 10, con un JUMP 4-->
 * n=4, pero con un RJUMP 4--> n = 14
 * 
 * @author Sergio Fuentes & David Rico
 * 
 */
public abstract class RelativeJumps implements Instruction {

	// Atributo protegido que representa el valor de la posicin a la que hay que
	// saltar.
	protected int numSalto;

	/**
	 * Mtodo diseado para las intrucciones de salto relativo condionales RBT y
	 * RBF, porque segn el parmetro que le entra van a devolver falso o true
	 * dependiendo en la instruccin que nos encontremos.
	 * 
	 * @param n
	 *            (cima de la pila)
	 * @return boolean
	 */
	protected abstract boolean execute(int n);

	/**
	 * Devuelve falso en dos casos :
	 *  1) Si la pila esta vacia 
	 *  2) Si al proceder el salto se accede a una posicion mayor al numero de instrucciones o si es
	 * 	una posicin negativa.
	 * Devuelve true en dos casos:
	 *  1) Si se ha realizado un salto condicional sin acceder a una posicin no permita
	 *  2) Si simplemente no se realiza el salto porque no se cumple la condicin
	 * 	correspondiente a BT BF y en este caso se incrementar el contador de
	 *	programa para pasar a la siguiente instruccin.
	 * 
	 * @throws ExceptionEmptyStack
	 * @throws ExceptionIncorrectProgramCounter 
	 */
	@Override
	public void execute(CPU cpu) throws ExceptionEmptyStack, ExceptionIncorrectProgramCounter {

		
		int n;
		n = cpu.pop();
		if (this.execute(n)) { // se salta pero hay que mirar a donde
			if ((this.numSalto + cpu.getContadorPc() >= 0) && (this.numSalto + cpu.getContadorPc() < cpu.numeroInstrucciones()))
				// se suma al contador de pc actual el numSalto
				cpu.setContadorPc(cpu.getContadorPc() + this.numSalto);
			else {
				 // no se puede saltar a un numero negativo
				cpu.exit();
				//throw new ExceptionIncorrectProgramCounter("Error ejecutando " + cpu.getCurrentInstruction().toString() + " : salto no permitido. Fin de la ejecucion");
			}
		} else
			// incrementamos porque no se realiza el salto
			cpu.increaseProgramCounter();
		

	}
}

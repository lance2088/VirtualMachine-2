package tp.pr5.mv.instrucciones.salto;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;

/**
 * Clase abstracta que hereda de Instrucction la cual se encarga de ejecutar
 * instrucciones de salto.
 * 
 * @author David Rico & Sergio Fuentes
 * 
 */
public abstract class ConditionalJumps implements Instruction {

	// Atributo protegido que representa el valor de la posici n a la que hay
	// que saltar.
	protected int numSalto;

	/**
	 * Metodo diseniado para las intrucciones de salto condionales BT y BF,
	 * porque segun el parametro que le entra van a devolver falso o true
	 * dependiendo en la instruccion que nos encontremos.
	 * 
	 * @param n
	 *            (cima de la pila)
	 * @return boolean
	 */
	protected abstract boolean execute(int n);

	/**
	 * Devuelve  1) Si la pila esta vacia 2) Si al proceder
	 * el salto se accede a una posicion mayor al numero de instrucciones o si
	 * es una posicion negativa. Devuelve true en dos casos: 1) Si se ha
	 * realizado un salto condicional sin acceder a una posicion no permita 2)
	 * Si simplemente no se realiza el salto porque no se cumple la condicion
	 * correspondiente a BT BF y en este caso se incrementar el contador de
	 * programa para pasar a la siguiente instruccion.
	 * 
	 * @throws ExceptionEmptyStack
	 */
	@Override
	public void execute(CPU cpu) throws ExceptionEmptyStack,
			ExceptionIncorrectProgramCounter {

		int n = cpu.pop(); // Aqui ya salta la excepción si la pila esta vacia
		if (this.execute(n)) {
			// cpu.setContadorPc(this.numSalto);
			// Si despues de cambiar el contador este se excede devolvemos false
			// y salimos
			if (numSalto >= cpu.numeroInstrucciones() || (numSalto < 0)) {

				cpu.exit();
				/*throw new ExceptionIncorrectProgramCounter("Error ejecutando "
						+ cpu.getCurrentInstruction().toString()
						+ " : salto no permitido. Fin de la ejecucion");*/
			} else
				// el salto se puede realizar
				cpu.setContadorPc(this.numSalto);
		} else
			// incrementamos porque NO se realiza el salto
			cpu.increaseProgramCounter();

	}
}

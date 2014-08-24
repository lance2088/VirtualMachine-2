package tp.pr5.mv.instrucciones.booleanas;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;

/**
 * Clase abstracta de la que heredan las operaciones AND y OR, las cuales Cogen
 * de la pila los dos operandos y lo interpretan como un valor binario (donde 0
 * indica falso y cualquier otra cosa es cierto). Realizan la operacion booleana
 * y apilan el resultado (0=falso, 1=cierto).
 * 
 * @author David Rico & Sergio Fuentes
 * 
 */
public abstract class AndOr implements Instruction {

	/**
	 * Metodo abstracto que realiza propiamente la operacion AND o OR con los
	 * dos parametros que le entran. Es decir: AND :si los dos son distintos de
	 * cero devuelve true y falso en caso contrario. OR: si los dos son iguales
	 * a cero devuelve false y true en caso contrario
	 * 
	 * @param n1
	 *            (operando uno que sera la subcima)
	 * @param n2
	 *            (operando dos que sera la cima)
	 * @return boolean.
	 */
	abstract protected boolean execute(int n1, int n2);

	/**
	 * Si en la pila hay mas de un elemento realizara si o si la operacion por
	 * lo que se incrementara el contador de pc. Para realizar un AND o un OR,
	 * extraemos los dos ultimos elementos de la pila. Seguidamente si la
	 * operacion con dichos operandos devuelve true meteremos un 1 en la pila y
	 * en caso contrario meteremos un 0.
	 * @throws ExceptionFaltanElementosEnPila 
	 * @throws ExceptionEmptyStack 
	 */
	public void execute(CPU cpu) throws ExceptionFaltanElementosEnPila, ExceptionEmptyStack {
		
		// Se extraen los dos ultimos elementos de la pila

		int n1 = cpu.pop();
		int n2 = cpu.pop();
		if (this.execute(n1, n2))
			cpu.push(1);
		else
			cpu.push(0);

		cpu.increaseProgramCounter(); // incrementamos siempre

	}
}

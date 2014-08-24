package tp.pr5.mv.instrucciones.aritmeticas;

import tp.pr5.mv.Exceptions.ExceptionDivisionByZero;
import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;

/**
 * Clase abstracta que se encarga de las operaciones Aritmeticas: ADD, DIV, MUL
 * y SUB. Todas ellas como operadores la subcima y cima de la pila de operandos
 * que son sustituidos por el resultado de la operacion. El primer operando es
 * la subcima.
 * 
 * @author David Rico & Sergio Fuentes
 * 
 */
public abstract class Aritmeticas implements Instruction {

	// Atributo protegido que toma el valor de la operacin que se realice.
	protected int result;

	/**
	 * Metodo abstracto que se encarga de realizar con los dos parametros que
	 * recibe. Es decir: En ADD: n1+n2 En SUB: n1-n2 En MUL: n1*n2 En DIV: n1/n2
	 * 
	 * @param n1
	 * @param n2
	 * @return Devuelve siempre true, excepto en el caso de la division porque
	 *         si en el denominador es un cero, no se puede realizar la
	 *         operacion y devolvera false.
	 */
	abstract protected boolean execute(int n1, int n2)
			throws ExceptionDivisionByZero;

	/**
	 * Coge de la pila los dos ultimos valores para seguidamente realizar la
	 * operacion. Siempre que se pueda realizar la operacion, se metera el valor
	 * de la misma en el cima de la pila y se incrementara el contador de
	 * programa. Si en la pila hay menos de dos operandos no se podra realizar
	 * ninguna operacion, por lo que devolvera false.
	 * @throws ExceptionFaltanElementosEnPila 
	 * @throws ExceptionEmptyStack 
	 * @throws ExceptionDivisionByZero 
	 */
	public void execute(CPU cpu) throws ExceptionFaltanElementosEnPila, ExceptionEmptyStack, ExceptionDivisionByZero {
		
		int n2 = cpu.pop();
		int n1 = cpu.pop();
		this.execute(n1, n2);
		cpu.push(this.result);
		cpu.increaseProgramCounter();			

	}
}

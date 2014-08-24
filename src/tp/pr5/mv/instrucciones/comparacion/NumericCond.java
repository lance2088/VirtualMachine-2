package tp.pr5.mv.instrucciones.comparacion;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;

/**
 * Clase abstracta ideada para las operaciones de comparacion : LT, GT, EQ, LE.
 * Todas cogen la subcima y la cima de la pila y comparan sus valores enteros.
 * Si la comparacion es satisfactoria, apilan un 1. Si no lo es, apilan un 0. LT
 * (less-than) equivale a la comparacion subcima < cima; LT (less-than) equivale
 * a la comparacion subcima < cima; GT (greater-than) a subcima > cima; EQ
 * (equals to) subcima == cima LE (less-or-equal) a subcima >= cima.
 * 
 * @author David Rico & Sergio Fuentes
 * 
 */
public abstract class NumericCond implements Instruction {
	/**
	 * Metodo abstracto que compara la cima con la subcima. Devuelve booleano
	 * dependiendo de la comparacion.
	 * 
	 * @param cima
	 * @param subcima
	 * @return booleano
	 */
	protected abstract boolean compare(int cima, int subcima);

	@Override
	/*
	 * Coge la cima y la subcima y ejecuta el Compare, si se cumple apila 1, si
	 * no se apila 0.
	 */
	public void execute(CPU cpu) throws ExceptionFaltanElementosEnPila,ExceptionEmptyStack {

		int cima = cpu.pop();
		int subcima = cpu.pop();
		if (compare(cima, subcima))
			cpu.push(1);
		else
			cpu.push(0);

		cpu.increaseProgramCounter();

	}

}

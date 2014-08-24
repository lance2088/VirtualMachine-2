package tp.pr5.mv.instrucciones;

import java.io.IOException;

import tp.pr5.mv.Exceptions.ExceptionDivisionByZero;
import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
import tp.pr5.mv.virtualMachine.CPU;


/**
 * Clase abstracta con los metodos execute y parse, de esta heredaran todas las demas instrucciones.
 * @author David Rico & Sergio Fuentes
 *
 */
public interface Instruction
{	
	/**	
	 * metodo que ejecuta una instruccion
	 * @param cpu
	 * @throws ExceptionFaltanElementosEnPila 
	 * @throws ExceptionEmptyStack 
	 * @throws ExceptionDivisionByZero 
	 * @throws IOException 
	 */
	abstract public void execute (CPU cpu) throws ExceptionFaltanElementosEnPila, ExceptionEmptyStack, ExceptionDivisionByZero, ExceptionIncorrectProgramCounter, IOException;
	/**
	 * Parsea un string y devuelve una instruccion si se consigue parsearla bien, si no null.
	 * @param str . String a parsear
	 * @return instruccion parseada
	 */
	abstract  public Instruction parse (String[] str);

	
}









package tp.pr5.mv.comandos;

import java.io.IOException;

import tp.pr5.mv.Exceptions.ExceptionDivisionByZero;
import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
import tp.pr5.mv.virtualMachine.CPU;

/**
 * Clase abstracta que representa los distintos comandos que podemos ejecutar sobre la maquina virtual.
 *  Entre sus atributos tendr� a la CPU cpu y dispondra de un metodo abstracto public abstract boolean executeCommand() que
 *  implementaran sus distintas subclases de acuerdo con el comando que se desee ejecutar.
 *  Esta clase ofrece el metodo public static void congureCommandInterpreter para 
 *  inicializar el valor del atributo cpu una vez que la CPU dispone del programa a ejecutar.
 *  De esta clase heredar�n las subclases Run, Step, Steps, Quit, PushComando, PopComando, Write que implementan, respectivamente, 
 *  los comandos con el mismo nombre. 
 *  Las clases Steps y Run heredan de la clase Step.
 * @author David Rico & Sergio Fuentes
 *
 */
abstract public class CommandInterpreter  {

	protected boolean isFinished;
	protected static CPU cpu;
	
	/**
	 * Metodo que ejecuta un comando.
	 * @throws IOException 
	 */
	public abstract void executeCommand()throws ExceptionFaltanElementosEnPila, ExceptionEmptyStack, ExceptionDivisionByZero, ExceptionIncorrectProgramCounter, IOException ;
	
	/**
	 * Parsea un comando desde un array de strings.
	 * @param str (array de strings)
	 * @return Comando parseado
	 */
	public abstract CommandInterpreter parse(String[] str);
	
	/**
	 * Constructor de la clase, inicializa isFinished a false.
	 */
	public CommandInterpreter ()
	{
		this.isFinished = false;
	}
	
	/**
	 * Configura el comando, asignando el cpu del comando al que pasamos por paramtro.
	 * @param cpu
	 */
	public static void configureCommandInterpreter(CPU cpu)
	{
		CommandInterpreter.cpu = cpu;
	}
	
	/**
	 * Metodo que devuelve el atributo isFinished
	 * @return boolean.
	 */
	public boolean isFinished()
	{
		return this.isFinished;	
	}
	
	/**
	 * Muestra por pantalla el estado de la maquina.
	 */
	public static void printStateMachine()
	{
		System.out.println(cpu.toString());
	}
}

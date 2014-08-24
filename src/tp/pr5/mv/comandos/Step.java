package tp.pr5.mv.comandos;

import java.io.IOException;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.Exceptions.ExceptionDivisionByZero;
import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
import tp.pr5.mv.main.Main;

/**
 * Clase step que implementa los metodos abstractos de la clase @see
 * CommandInterpreter Hace que la tpmv ejecute una instrucci�n (la siguiente del
 * programa-mv de acuerdo al contador de programa).
 * 
 * @author Sergio & David
 * 
 */
public class Step extends CommandInterpreter {

	@Override
	/**
	 * Metodo que ejecuta el comando step.
	 */
	public void executeCommand() throws ExceptionFaltanElementosEnPila,
			ExceptionEmptyStack, ExceptionDivisionByZero,
			ExceptionIncorrectProgramCounter, IOException {

		if (Main.modeName.equalsIgnoreCase("interactive")) {
			System.out.println("####################"	+ Constantes.SALTO_LINEA + "Comienza la ejecucion de " + CommandInterpreter.cpu.getCurrentInstruction());
			CommandInterpreter.cpu.step();
			CommandInterpreter.printStateMachine();
		}
		else CommandInterpreter.cpu.step();
	}

	@Override
/**	
	 * Metodo que parsea un array de strings y devuelve un nuevo comando Step o null.
	 * @return new Step()
	 */
	public CommandInterpreter parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("STEP")))
			return new Step();
		else
			return null;

	}

}

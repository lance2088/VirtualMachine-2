package tp.pr5.mv.comandos;

import java.io.IOException;

import tp.pr5.mv.Exceptions.ExceptionDivisionByZero;
import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;

/**
 * Clase que representa el comando RUN, que ejecuta el programa-mv completo
 * hasta su terminacion.
 * 
 * @author David Rico & Sergio Fuentes
 * 
 */
public class Run extends Step {

	@Override
	/*
	 * Ejecuta el comando step hasta que se terminen las instrucciones o haya un
	 * error.
	 */
	public void executeCommand() throws ExceptionFaltanElementosEnPila, ExceptionEmptyStack, ExceptionDivisionByZero, ExceptionIncorrectProgramCounter, IOException {
		
		//cpu.resetCPU();
		while (!CommandInterpreter.cpu.abortComputation() ) {
			super.executeCommand(); // devuelve falso si falla alguna instruccion por ej un add con solo una instruccion
		}

		//this.isFinished = true;
	}

	@Override
	public CommandInterpreter parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("RUN")))
			return new Run();
		else
			return null;
	}

}

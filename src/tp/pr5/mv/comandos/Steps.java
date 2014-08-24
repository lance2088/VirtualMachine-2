package tp.pr5.mv.comandos;

import java.io.IOException;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.Exceptions.ExceptionDivisionByZero;
import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
/**
 * Clase parecida a Step, pero que en vez de ejecutar una sola vez el comando lo hace n veces.
 *  @author Sergio Fuentes & David Rico
 */
public class Steps extends Step {
	private int operando;
	
	
	/**
	 * Constructor de la clase, se incializa el atributo operando, que es
	 * las veces que se va ejecutar el comando Step()
	 * @param op
	 */
	public Steps(int op) {
		this.operando = op;
	}

	@Override
	/**
	 * Ejecuta step n veces, donde n = operando. Se sale de la ejecucion si ocurre algun error.
	 */
	public void executeCommand() throws ExceptionFaltanElementosEnPila, ExceptionEmptyStack, ExceptionDivisionByZero, ExceptionIncorrectProgramCounter, IOException {
		
		int i = 0;
		while (i < this.operando && !CommandInterpreter.cpu.abortComputation() )
		{
			super.executeCommand();
			i++;
		}
	}

	@Override
	/**
	 * Mtodo que parsea un array de strings y devuelve un nuevo comando Steps o null.
	 * @return new Steps(n)
	 */
	public CommandInterpreter parse(String[] str) {
		if ((str.length == 2) && (str[0].equalsIgnoreCase("STEP") && Constantes.isNumeroPositivo(str[1]) && Constantes.isNumero(str[1])))
			return new Steps(Integer.parseInt(str[1]));
		else return null;
	}

}

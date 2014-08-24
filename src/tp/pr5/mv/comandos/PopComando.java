package tp.pr5.mv.comandos;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;

/**
 * Clase que representa el comando Pop, que eliminar� la cima de la pila (si
 * existe).
 * 
 * @author David Rico & Sergio Fuentes
 * 
 */
public class PopComando extends CommandInterpreter {

	@Override
	/**
	 * ejecuta un pop del cpu, que elimina la cima de la pila
	 */
	public void executeCommand() throws ExceptionEmptyStack {
		
		if (cpu.getSizePila() < 1)
			throw new ExceptionEmptyStack("Error ejecutando el comando POP : pila vacia");
		else
			CommandInterpreter.cpu.pop();
			

	}

	@Override
	/**
	 * parsea un array de string en un nuevo comando Pop
	 */
	public CommandInterpreter parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("POP")))
			return new PopComando();
		else
			return null;
	}

}

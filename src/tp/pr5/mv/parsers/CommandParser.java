package tp.pr5.mv.parsers;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.Exceptions.ExceptionComandoIncorrecto;
import tp.pr5.mv.comandos.CommandInterpreter;
import tp.pr5.mv.comandos.PopComando;
import tp.pr5.mv.comandos.PushComando;
import tp.pr5.mv.comandos.Quit;
import tp.pr5.mv.comandos.Run;
import tp.pr5.mv.comandos.Step;
import tp.pr5.mv.comandos.Steps;
import tp.pr5.mv.comandos.Write;

/**
 * Clase que se encarga de parsear strings a comandos, es decir:se encarga de generar los comandos. 
 * Contiene el m�todo est�tico public static CommandInterpreter parseCommand(String line), que procesa el
 * string de entrada y o bien devuelve un objeto de la clase CommandInterpreter o nullen caso de que la l�nea de entrada
 * no se corresponda con ning�n comando.
 * @author David Rico & Sergio Fuentes *
 */
public class CommandParser {
	
	/**
	 * Array esttico de tipo Commando
	 */
	private static CommandInterpreter commandSet[] = 
		{
			new Quit(), new Run(), new Step(), new Steps(0), new PushComando(0), new PopComando(), new Write(0, 0)
		};
	

	/**
	 * Mtodo estatico que parsea un string en una instruccion
	 * @param posibleCommand
	 * @return Comando parseado si existe y si no devolver null.
	 * @throws ExceptionComandoIncorrecto 
	 */
	public static CommandInterpreter parsePosibleCommand(String  posibleCommand) throws ExceptionComandoIncorrecto //ej: push 5 
	{
		int i = 0;
		boolean stop = false;
		String[] comando_posible = Constantes.separarString(posibleCommand);
		CommandInterpreter comand = null;
		
		while (i<CommandParser.commandSet.length && !stop)
		{
			comand = CommandParser.commandSet[i].parse(comando_posible);
			if (comand != null) 
				stop = true;
			else 
				i++;
		}
		
		if (comand == null)
			throw new ExceptionComandoIncorrecto("Error: " + posibleCommand + " comando incorrecto.");
		
		return comand;
	}
	

	

}

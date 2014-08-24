package tp.pr5.mv.comandos;


/**
 * Clase que representa en comando QUIT para salir.
 * @author David
 *
 */
public class Quit extends CommandInterpreter{

	@Override
	/*
	 * Pone el atributo isFinished a true.
	 */
	public void executeCommand() {
		this.isFinished = true;
	}

	@Override
	public CommandInterpreter parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("QUIT")) )
			return new Quit();		
		else return null;
	}

}

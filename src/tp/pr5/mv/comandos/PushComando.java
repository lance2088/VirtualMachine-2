package tp.pr5.mv.comandos;

import tp.pr5.mv.Constantes;

/**
 * Clase que representa el comando PUSH n, que a�adir� un valor a la cima de la pila.
 *  @author David Rico & Sergio Fuentes
 *
 */
public class PushComando extends CommandInterpreter{
	
	protected int operando;
	
	/**
	 * Constructor, inicializa el operando del comando.
	 * @param op
	 */
	public PushComando(int op){
		this.operando = op;
	}
	@Override
	/*
	 * Apila el op en la pila.
	 */
	public void executeCommand() {
		CommandInterpreter.cpu.push(operando);
	}

	@Override
	public CommandInterpreter parse(String[] str) {
		if ((str.length == 2) && (str[0].equalsIgnoreCase("Push") && Constantes.isNumero(str[1])))
			return new PushComando(Integer.parseInt(str[1]));
		else return null;
	}
	

	

}

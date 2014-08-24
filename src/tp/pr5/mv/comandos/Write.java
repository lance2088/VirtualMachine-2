package tp.pr5.mv.comandos;

import tp.pr5.mv.Constantes;

/**
 * Clase que representa el comando Write,
 * WRITE pos value: escribir� en la posici�n de memoria indicada el valor dado.
 * @author David Rico & Sergio Fuentes
 *
 */
public class Write extends CommandInterpreter{
	protected int posicion;
	protected int value;
	
	/**
	 * Constructor que asigna posicion=p y value=v
	 * @param p
	 * @param v
	 */
	public Write(int p, int v){
		this.posicion = p;
		this.value = v;
	}
	@Override
	/*
	 * Inserta en la posicion de memoria un valor.
	 */
	public void executeCommand() {
		CommandInterpreter.cpu.insertarEnMemoria(this.posicion, this.value);
	}

	@Override
	public CommandInterpreter parse(String[] str) {
		if ((str.length == 3) && (str[0].equalsIgnoreCase("WRITE") && Constantes.isNumero(str[1]) && Constantes.isNumero(str[2])))
			return new Write(Integer.parseInt(str[1]), Integer.parseInt(str[2]));
		else return null;
	}

}

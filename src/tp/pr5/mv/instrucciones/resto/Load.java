package tp.pr5.mv.instrucciones.resto;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;
/**
 * Clase que implementa los metodos execute, parse y tostring, Extrae un valor de una posicion de memoria y la apila.
 * @author David & Sergio
 *
 */
public class Load extends RestSeq {

	private int posMemoria;
	/**
	 * Constructor.
	 * @param n . Posicion de memoria de la que extrae el valor.
	 */ 
	public Load(int n){
		this.posMemoria = n;
	}
	
	@Override
	/*
	 * Inserta en una posicion de memoria de la cpu un valor.
	 */
	protected void executeAux(CPU cpu) throws ExceptionIncorrectProgramCounter {
		if (this.posMemoria < 0)
			throw new ExceptionIncorrectProgramCounter("Error ejecutando: " + cpu.getCurrentInstruction().toString() + " direccion incorrecta " + '(' +this.posMemoria + ')');
		else{
		int valorApila = cpu.valorDeUnaPosicionDeMemoria(this.posMemoria);
		cpu.push(valorApila); // si la posicion no existe apilamos un cero
		}	
	}

	
	@Override
	public Instruction parse(String[] str) {
		// Se parsea que se salte a un nmero y ademas que no sea un nmero negativo ya que no se podra acceder.
		if ( (str.length == 2 ) && (str[0].equalsIgnoreCase("LOAD"))  && Constantes.isNumero(str[1]) ){
			int n = Integer.parseInt(str[1]);
			return new Load(n);
		}
					
		else return null;
	}

	@Override
	public String toString() {
		return "LOAD " + this.posMemoria;
	}



}

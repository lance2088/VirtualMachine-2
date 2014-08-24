package tp.pr5.mv.instrucciones.indirectas;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;

/**
 * Operación permite calcular la dirección durante la ejecución, 
 * de forma que se lee de la posición de memoria indicada en la	cima de la pila.
 * @author David Rico && Sergio Fuentes
 *
 */
public class LoadInd extends Indirectos {
	public LoadInd(){
		
	}
	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 1) && (str[0].equalsIgnoreCase("LOADIND")))
			return new LoadInd();
		else
			return null;
	}

	@Override
	protected void executeAux(CPU cpu) throws ExceptionEmptyStack,
			ExceptionFaltanElementosEnPila {
	
			int valorApila = cpu.valorDeUnaPosicionDeMemoria(cpu.pop()); //cogemos la cima con el pop, y buscamos en la posicion de memoria 
			cpu.push(valorApila);//pusheamos ese el valor que tiene en esa posicion
			cpu.increaseProgramCounter();
			
		
	}
	
	public String toString(){
		return "LOADIND";
	}

}

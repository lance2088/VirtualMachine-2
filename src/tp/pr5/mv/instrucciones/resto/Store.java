package tp.pr5.mv.instrucciones.resto;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;
/**
 * clase que implementa los metodos execute, parse y tostring, consigue duplicar la cima de la pila.
 * @author David & Sergio
 *
 */
public class Store extends RestSeq {

	private int num;
	
	public Store(int n){
		this.num = n;
	}
	
	@Override
	protected void executeAux(CPU cpu) throws ExceptionEmptyStack {
		if (cpu.getSizePila() < 1)
			throw new ExceptionEmptyStack("Error ejecutando STORE: Pila vacia.");
		else
		{
			int valorAinsertar = cpu.pop();
			cpu.insertarEnMemoria(num, valorAinsertar);
			
		}
	}

	@Override
	public Instruction parse(String[] str) {
		// Se parsea que se salte a un nmero y ademas que no sea un nmero negativo ya que no se podra acceder.
		if ( (str.length == 2 ) && (str[0].equalsIgnoreCase("STORE")) && Constantes.isNumero(str[1]) &&(Constantes.isNumeroPositivo(str[1]))){
			int n = Integer.parseInt(str[1]);
			return new Store(n);
		}
					
		else return null;
	}

	@Override
	public String toString() {
		return "STORE " + this.num;
	}

}

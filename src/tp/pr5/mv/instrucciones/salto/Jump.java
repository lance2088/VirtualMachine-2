package tp.pr5.mv.instrucciones.salto;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;
/**
 * Clase que realiza el salto incondicional JUMP, provocando un cambio en el contador de programa segn el parmetro de la instruccin. Tras el salto, la siguiente 
 * instruccin a ejecutar ser la que ocupe la posicin "n" en el programa.
 * @author David Rico && Sergio Fuentes
 *
 */
public class Jump extends ConditionalJumps{
	
	/**
	 * Constructor de la clase
	 * @param n (parmetro a donde se salta)
	 */	
	public Jump(int n) {
		this.numSalto = n;
	}

	
	@Override
	public Instruction parse(String[] str) {
		// Se parsea que se salte a un nmero y ademas que no sea un nmero negativo ya que no se podra acceder.
		if ( (str.length == 2 ) && (str[0].equalsIgnoreCase("JUMP") && (Constantes.isNumero(str[1]) &&  Constantes.isNumeroPositivo(str[1]))) ){
			int n = Integer.parseInt(str[1]);
			return new Jump(n);
		}
					
		else return null;
	}
	
	@Override
	public String toString() {
		
		return "JUMP " + this.numSalto;
	}

	
	@Override
	public void execute(CPU cpu) throws ExceptionIncorrectProgramCounter {
		
		// Si la modificacin anterior resulta imposible porque se accede a una posicin demasiado alta que no hay instruccion o 
		// si es un numero negativo tendremos que que salir y devolver false. En caso contrario devolvemos true.
		if (this.numSalto >= cpu.numeroInstrucciones() || (this.numSalto < 0)) { 
			cpu.exit();
			//throw new ExceptionIncorrectProgramCounter("Error ejecutando " + cpu.getCurrentInstruction().toString() + " :  salto no permitido. Fin de la ejecucion");
		//	return false;
		} else{
			// Modificamos el contador de programa
			cpu.setContadorPc(this.numSalto); 
			
		}
	}
	
	// Devuelve true porque es un mtodo heredado que lo ignoramos.
	protected boolean execute(int n){return true;}	

}

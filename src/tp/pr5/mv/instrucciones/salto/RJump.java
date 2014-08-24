package tp.pr5.mv.instrucciones.salto;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;

/**
 * Clase encargada de salto RJUMP, que realizar un salto relativoincondicional a
 * la posicin que guarda el contador de programa (pc) + el operando en s.
 * 
 * @author David Rico && Sergio Fuentes
 * 
 */
public class RJump extends RelativeJumps {

	/**
	 * Construcctor de la clae
	 * 
	 * @param n
	 *            (int al que hay que sumarle el contador de programa para
	 *            realizar el salto)
	 */
	public RJump(int n) {
		this.numSalto = n;
	}

	// Ignoramos este mtodo hereado porque esta principalmente diseado para las
	// instrucciones condicionales.
	@Override
	protected boolean execute(int n) {
		return true;
	}

	@Override
	public String toString() {
		return "RJUMP " + this.numSalto;
	}

	@Override
	public Instruction parse(String[] str) {
		if ((str.length == 2)
				&& (str[0].equalsIgnoreCase("RJUMP") && Constantes
						.isNumero(str[1]))) {
			int n = Integer.parseInt(str[1]);
			return new RJump(n);
		}

		else
			return null;
	}

	@Override
	/**
	 * Suma al pc el num salto. En esta caso si se podra introducir un operando negativo mientras no se llegue a acceder a una posicion negativa.
	 */
	public void execute(CPU cpu) throws ExceptionIncorrectProgramCounter {

		if ((this.numSalto + cpu.getContadorPc() >= 0)
				&& (this.numSalto + cpu.getContadorPc() < cpu
						.numeroInstrucciones())) {
			cpu.setContadorPc(cpu.getContadorPc() + this.numSalto);
			
		} else {
			cpu.exit();
			//throw new ExceptionIncorrectProgramCounter("Error ejecutando " + cpu.getCurrentInstruction().toString() + " : salto no permitido. Fin de la ejecucion");
		}
		
		//return false;
	}

}

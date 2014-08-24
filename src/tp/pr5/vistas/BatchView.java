package tp.pr5.vistas;

import tp.pr5.mv.Observers.CPUObserver;
import tp.pr5.mv.Observers.Observable;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.Memory;
import tp.pr5.mv.virtualMachine.OperandStack;
import tp.pr5.mv.virtualMachine.ProgramMV;

public class BatchView implements CPUObserver {

	public BatchView (Observable<CPUObserver> cpu)
	{
		cpu.addObserver(this);
	}
	
	
	/**
	 * Para avisar que ha empezado la ejecución de una instrucción (al principio
	 * del método step del CPU por ejemplo). Pasamos la instrucción actual
	 * porque en el modo interactive necesitamos escribir un mensaje que indica
	 * que instrucción vamos a ejecutar, etc.
	 */
	@Override
	public void onStartInstrExecution(Instruction instr) {}

	/**
	 * para avisar a los oyentes que ha ocurrido algún error durante la
	 * ejecución de cualquier método del CPU. El parámetro msg es el mensaje
	 * adecuado (normalmente obtenemos el mensaje de la excepción que
	 * corresponde, usando getMessage() por ejemplo).
	 * 
	 */
		@Override
		public void onError(String msg) {
			System.err.println(msg);
		}

	/**
	 * para avisar que ha empezado la ejecución de
	método run del CPU. No necesitamos pasar parámetros en
	este caso -- se usa para desactivar lo botones en la vista
	cuando empieza la ejecución, etc.
	 */	
	@Override
	public void onStartRun() {
		// TODO Auto-generated method stub

	}

	

	
	/**
	 * para avisar que la ejecución del programa ha
terminado -- se puede avisar al final del método step por
ejemplo.
	 */
	@Override
	public void onHalt() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReset(ProgramMV program) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onEndRun() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * para avisar que ha terminado la ejecución de una instrucción (al final
	 * del método step por ejemplo). Pasamos el valor del PC porque lo
	 * necesitamos para actualizar las vistas. Se puede pasar más información
	 * como la Pila y la Memoria para escribirlas, etc.
	 */

	@Override
	public void onEndInstrExecution(int pc, OperandStack<Integer> pila,
			Memory<Integer> mem) {
		// TODO Auto-generated method stub
		
	}


	

}

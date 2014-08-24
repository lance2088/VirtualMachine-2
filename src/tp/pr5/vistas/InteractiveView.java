package tp.pr5.vistas;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.Observers.CPUObserver;
import tp.pr5.mv.Observers.Observable;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.Memory;
import tp.pr5.mv.virtualMachine.OperandStack;
import tp.pr5.mv.virtualMachine.ProgramMV;

public class InteractiveView implements CPUObserver {
	
	// Implementar los métodos de CPUObserver para tener el efecto del modo
	// interactive (como en la práctica 4).

	public InteractiveView (Observable<CPUObserver> cpu)
	{
		cpu.addObserver(this);
		// ...
	}
	
	
	@Override
	public void onStartInstrExecution(Instruction instr) {
		System.out.println("Comienza la ejecucion de la instruccion: " + instr.toString());

	}
	@Override
	public void onEndInstrExecution(int pc, OperandStack<Integer> pila,
			Memory<Integer> mem) {
		String str;

		str = Constantes.SALTO_LINEA +"El estado de la maquina tras ejecutar la instruccion es:"
				+ Constantes.SALTO_LINEA + "Memoria: " + mem.toString()
				+ Constantes.SALTO_LINEA + "Pila de operandos: "
				+ pila.toString();
		
		System.out.println(str);	
	}


	@Override
	public void onError(String msg) {
		System.err.println(msg);

	}

	@Override
	public void onEndRun() {
		//System.out.println(" **** FIN de la ejecucion de RUN");
		
	}
	
	@Override
	public void onStartRun() {
		System.out.println("Comienza la ejecucion de RUN");

	}


	@Override
	public void onHalt() {
		System.out.println(" **** FIN DE LA EJECUCIÓN!!!");

	}

	@Override
	public void onReset(ProgramMV program) {
		// TODO Auto-generated method stub

	}







	
}

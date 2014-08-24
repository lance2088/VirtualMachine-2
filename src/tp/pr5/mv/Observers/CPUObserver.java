package tp.pr5.mv.Observers;

import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.Memory;
import tp.pr5.mv.virtualMachine.OperandStack;
import tp.pr5.mv.virtualMachine.ProgramMV;

public interface CPUObserver {

	public void onStartInstrExecution(Instruction instr);
	public void onEndInstrExecution(int pc, OperandStack<Integer> pila, Memory<Integer> mem);
	public void onStartRun();
	public void onEndRun();
	public void onError(String msg);
	public void onHalt();
	public void onReset(ProgramMV program); // OPCIONAL
}

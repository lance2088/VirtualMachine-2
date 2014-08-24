package tp.pr5.controladores;

import tp.pr5.mv.virtualMachine.CPU;

public class BatchControler extends Controler {

	public BatchControler(CPU cpu) {
		super(cpu);		
	}

	@Override
	public void start() {
		super.run();
	}
	
	

}

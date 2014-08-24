package tp.pr5.controladores;

import tp.pr5.mv.virtualMachine.CPU;

public class GUIControler extends Controler
{

	public GUIControler(CPU cpu) {
		super(cpu);
		cpu.setDelay(200);
	}

	@Override
	public void start() {
		// no hace nada, swing se encarga de la comunicación con el usuario en este caso
	}
	
}

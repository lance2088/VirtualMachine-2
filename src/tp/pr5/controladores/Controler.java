package tp.pr5.controladores;

import java.io.IOException;

import tp.pr5.mv.Exceptions.ExceptionDivisionByZero;
import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
import tp.pr5.mv.Exceptions.ExceptionIntruccionIncorrecta;
import tp.pr5.mv.Exceptions.ExceptionMV;

import tp.pr5.mv.escritura.OutStream;
import tp.pr5.mv.lectura.InStream;
import tp.pr5.mv.virtualMachine.CPU;
import tp.pr5.mv.virtualMachine.ProgramMV;

public abstract class Controler {
	private CPU cpu;
	
	public Controler(CPU cpu) {
		 this.cpu = cpu;
	}

	
	public abstract void start();
	
	// OPCIONAL
	public void pause(){
		cpu.pause();
	}		
	
	// OPCIONAL
	public void reset(){
		cpu.resetCPU();
	}
	
	// OPCIONAL
	public void loadPrograma(String fname) throws IOException, ExceptionIntruccionIncorrecta{
		cpu.loadProgramOpcional(ProgramMV.readProgram( fname ));
	}
		
	public void run() {
		try{
			cpu.run();
		} catch (ExceptionFaltanElementosEnPila | ExceptionEmptyStack | ExceptionDivisionByZero | ExceptionIncorrectProgramCounter
				| IOException e) {}				
	}
	
	public void step(){
		try {
			cpu.step();
		} catch (ExceptionFaltanElementosEnPila | ExceptionEmptyStack | ExceptionDivisionByZero | ExceptionIncorrectProgramCounter 
				| IOException e) {}		
	}
	public void pop()  {
		try {
			cpu.pop();
		} catch (ExceptionEmptyStack e) {}
	}
	
	public void steps(int n){
		try {
			cpu.stepn(n);
		} catch (ExceptionFaltanElementosEnPila | ExceptionEmptyStack | ExceptionDivisionByZero | ExceptionIncorrectProgramCounter 
				| IOException e) {}		
		
	}
	
	
	public void memorySet(int i, int v) {
		cpu.insertarEnMemoria(i, v);
		
	} // ejecuta el setMem del cpu
	
	public void push(int s) {
			
		cpu.push(s);
		
	}
	
	public boolean programFinished(){				
		return cpu.abortComputation();		
	}
	
	public void quit() {   // tiene que cerrar los InStream y OutStream   showConfirmDialog
		
		try {
			cpu.getMetodoEntrada().close();
			cpu.getMetodoSalida().close();
		} catch (IOException e) {}
		
		 System.exit(0);
}	

	public int getPC() { return cpu.getContadorPc(); }
	
	public void setInStream(InStream in)   { 
		try {
			cpu.setInStream(in);
		} catch (ExceptionMV e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setOutStream(OutStream out) { 
		 try {
			cpu.setOutStream(out);
		} catch (ExceptionMV e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public InStream getInStream() {
		return cpu.getMetodoEntrada();  }
	
	public OutStream getOutStream() {
		return cpu.getMetodoSalida();  }
	
	public ProgramMV getProgram() {
		return cpu.getProgram(); 
		}
	
	
}

	





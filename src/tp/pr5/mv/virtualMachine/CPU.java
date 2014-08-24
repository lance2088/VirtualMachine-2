package tp.pr5.mv.virtualMachine;

import java.io.IOException;
import java.util.ArrayList;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.Exceptions.ExceptionDivisionByZero;
import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.Exceptions.ExceptionIncorrectProgramCounter;
import tp.pr5.mv.Exceptions.ExceptionMV;
import tp.pr5.mv.Observers.CPUObserver;
import tp.pr5.mv.Observers.Observable;
import tp.pr5.mv.escritura.OutStream;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.lectura.InStream;

/**
 * Clase compuesta por una una memoria y una pila. Tambin tiene un atributo
 * booleano que toma el valor true en caso de salir de la aplicacin.
 * 
 * @author Sergio Fuentes & David Rico
 * 
 */

public class CPU implements Observable<CPUObserver> {
	
	 
	private Memory <Integer> memory;
	private OperandStack <Integer> stack;
	
	private boolean exit;
	private ProgramMV program;
	private int pc;// contador de programa
	private boolean correctPC; // para que el contador de programa est dentro del lmite

	private OutStream metodoSalida;

	private InStream metedoEntrada;
	
	private ArrayList<CPUObserver> observers;
	
	
	private int delay = 0;
	private boolean pause;
	
	/**
	 * Constructor de la clase
	 */
	public CPU() {	
		this.exit = false;
		this.memory = new Memory<Integer>();
		this.stack = new OperandStack<Integer>();
		this.correctPC = true;
		this.pc = 0;
		
		this.observers =  new ArrayList<>();
		
	}	
	
	@Override
	public void addObserver(CPUObserver o) {		
		observers.add(o);
	}

	@Override
	public void removeObserver(CPUObserver o) {
		observers.remove(o);		
	}
	
	
	public ProgramMV getProgram() {
		return program;
	}
	
	public OperandStack<Integer> getStack() {
		return stack;
	}
	
	public Memory<Integer> getMemory() {
		return memory;
	}
	

	/**
	 * Get del tamao de la pila
	 * 
	 * @return la posicin de cima de la pila
	 */
	public int getSizePila() {
		return stack.getSize();
	}

	/**
	 * Ejecuta la instruccin pop, que devuelve la cima de la pila quitando el
	 * elemento de esta.
	 * 
	 * @return la cima
	 * @throws ExceptionEmptyStack
	 */
	public int pop() throws ExceptionEmptyStack {
		if (this.stack.getSize() == 0) {
			//for (CPUObserver o: observers) // Para el caso de que se pulse el boton POP de Swing o el comando pop en modo intetactive
				//o.onError("Error ejecutando POP : la pila esta vacia ");
			throw new ExceptionEmptyStack("Error ejecutando "
					+ this.getCurrentInstruction().toString()
					+ " : pila vacia ");
		} else
			return this.stack.pop();
	}

	/**
	 * Metodo que nos devuelve el nmero de instrucciones que tiene almacenadas
	 * ProgramMV * @return int (nmero de instrucciones de la programMV )
	 */
	public int numeroInstrucciones() {
		return this.program.getSizeProgram();
	}

	/**
	 * Ejecuta el push, que mete num en la cima de la pila
	 * 
	 * @param num
	 */
	public void push(int num) {
		this.stack.push(num);
	}

	/**
	 * Incrementea en 1 el valor del contador del programa. Si este no est
	 * dentro del limite permitido, es decir que pc sea menor que el numero de
	 * instrucciones en el programa, correctPc ser false
	 */
	public void increaseProgramCounter() {
		this.pc++;
		if (this.pc >= this.program.getSizeProgram())
			this.correctPC = false;
	}

	/**
	 * Inserta el elemento n en la posicion pos de memoria.
	 * 
	 * @param pos
	 * @param n
	 */
	public void insertarEnMemoria(int pos, int n) {
		this.memory.setValue(pos, n);
	}

	/**
	 * Devuleve el elemento de la posicion posDeMemoria
	 * 
	 * @param posDeMemoria
	 * @return elemento (int)
	 */
	public int valorDeUnaPosicionDeMemoria(int posDeMemoria) {
		
		if (memory.getValue(posDeMemoria)== null)
			return 0;
		else return memory.getValue(posDeMemoria); // si no existe la posicion
												// metemos un cero en la pila
	}

	/**
	 * Se inicializa el atributo de la clase progam segun el parametro
	 * 
	 * @param p
	 *            (tipo programMV)
	 */
	public void loadProgram(ProgramMV p) {
		this.program = p;
	}

	/**
	 * El atributo booleano exit se pone a true
	 */
	public void exit() {
		this.exit = true;
	}

	/**
	 * Set del contador de programa, se le asigna el valor pc.
	 * 
	 * @param pc
	 */
	public void setContadorPc(int pc) {
		this.pc = pc;
	}

	/**
	 * Mtodo que resetea la CPU, es decir, se pone el contador de programa a
	 * 0,se vacia la pila y la memoria.
	 */
	public void resetCPU() {
		this.pc = 0;
		this.stack.reset();
		this.memory.reset();
		// Para que despues de una ejecucion entera y pulsemos reset se pueda volver  a ejecutar
		this.correctPC = true;
		this.exit= false;
		//
		this.metedoEntrada.reset();
		this.metodoSalida.reset();
		
		
		for (CPUObserver o : observers)
			o.onReset(program);
	}
	
	public void loadProgramOpcional(ProgramMV program  ){
		this.program = program;
		this.pc = 0;
		this.stack.reset();
		this.memory.reset();
		// Para que despues de una ejecucion entera y pulsemos cagar se pueda volver  a ejecutar el nuevo programa
		this.correctPC = true;
		this.exit= false;
		
		for (CPUObserver o : observers)
			o.onReset(program);		
	}	

	/**
	 * Devuelve la siguiente instruccin a ejecutar, la que est en contador de
	 * programa.
	 * 
	 * @return si esta bien el contador la instruccion, si esta mal null.
	 */
	public Instruction getCurrentInstruction() {
		if (correctPC)
			return this.program.get(pc);
		else
			return null;
	}
	public OutStream getMetodoSalida(){
		return this.metodoSalida;
	}
	
	public InStream getMetodoEntrada(){
		return this.metedoEntrada;
	}
	
	public void setInStream(InStream s) throws ExceptionMV { 
		 if (s == null) throw new ExceptionMV("Cannot set inStream to null");
		 else this.metedoEntrada = s;
	}
	
	public void setOutStream(OutStream s) throws ExceptionMV { 
		 if (s == null) throw new ExceptionMV("Cannot set outStream to null");
		 else this.metodoSalida = s;
	}
	
	
	
	/**
	 * Devuelve el contador de programa
	 * 
	 * @return pc
	 */
	public int getContadorPc() {
		return pc;
	}

	/**
	 * Devuelve el valor que almacena el atributo exit
	 * 
	 * @return boolean exit
	 */
	public boolean isFinishedCorrectly() {
		return this.exit;
	}

	/**
	 * Metodo para saber si hay que salir de la ejecucin. Mira si correct pc es
	 * true y si exit es true. Si alguno de los dos son true entonces el mtodo
	 * devolver true.
	 * 
	 * @return boolean
	 */
	public boolean abortComputation() {
		return ((!this.correctPC) || (this.exit == true)); // la ejecucion debe detenerse		
	}

	/**
	 * Ejecuta la siguiente instruccin, es decir la que esta en la posicion del
	 * array del contador de programa.
	 * @throws ExceptionIncorrectProgramCounter 
	 * @throws ExceptionDivisionByZero 
	 * @throws ExceptionEmptyStack 
	 * @throws ExceptionFaltanElementosEnPila 
	 * @throws IOException 
	 */
	public void step() throws ExceptionFaltanElementosEnPila,
			ExceptionEmptyStack, ExceptionDivisionByZero,
			ExceptionIncorrectProgramCounter, IOException {
				
		for (CPUObserver o : observers)
			o.onStartInstrExecution(getCurrentInstruction());
		
		
		try {
			if (this.pc >= this.program.getSizeProgram() || (this.pc < 0)) 	this.correctPC = false;
			else if (this.getCurrentInstruction() == null) {} 
			else this.getCurrentInstruction().execute(this);
			
		}
		catch (ExceptionFaltanElementosEnPila | ExceptionEmptyStack | ExceptionDivisionByZero | ExceptionIncorrectProgramCounter
				| IOException e){
			for (CPUObserver o : observers)
				o.onError(e.getMessage());
			throw e;
		}
		
		for (CPUObserver o : observers)
			o.onEndInstrExecution(pc, this.stack, this.memory);
		
		if(abortComputation() ){
			for (CPUObserver o : observers)
				o.onHalt();
		}
		
	}
	
	
	
	public void run() throws ExceptionFaltanElementosEnPila, ExceptionEmptyStack, ExceptionDivisionByZero, ExceptionIncorrectProgramCounter, IOException{
		for (CPUObserver o : observers)
			o.onStartRun();
		
		this.pause = false;
		
//		CommandInterpreter comando = new Run();
//		comando.executeCommand();
		
		while (!abortComputation() && !this.pause) { // Cuando pulsamos pause no va a entrar en el bucle
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {}
			
			this.step();
			
		}
		
		for (CPUObserver o : observers)
			o.onEndRun();
	}
	
	public void setDelay (int n){
		this.delay = n;
	}
	
	
	public void stepn(int n) throws ExceptionFaltanElementosEnPila, ExceptionEmptyStack, ExceptionDivisionByZero, ExceptionIncorrectProgramCounter, IOException {
			
		int i = 0;
		while ( i < n && !this.abortComputation()) {
			this.step();
			i++;
		}
	}
	
	
	/**
	 * Mtodo que devuelve un string con el estado de la pila y de la memoria.
	 * Para ello utiliza toString de pila y de memoria
	 * 
	 * @return String.
	 */
	public String toString() {
		String str;

		str = "El estado de la maquina tras ejecutar la instruccion es:"
				+ Constantes.SALTO_LINEA + "Memoria: " + this.memory.toString()
				+ Constantes.SALTO_LINEA + "Pila de operandos: "
				+ this.stack.toString();

		return str;
	}
	public int getCima(){
		return this.stack.getElementoCima();
	}

	public void pause() {
		pause = true;
		
	}

	
	
}

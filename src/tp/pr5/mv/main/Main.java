package tp.pr5.mv.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.SwingUtilities;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import tp.pr5.controladores.BatchControler;
import tp.pr5.controladores.GUIControler;
import tp.pr5.controladores.InteractiveControler;
import tp.pr5.mv.Exceptions.ExceptionArgumentoIncorrecto;
import tp.pr5.mv.Exceptions.ExceptionIntruccionIncorrecta;
import tp.pr5.mv.Exceptions.ExceptionMV;
import tp.pr5.mv.comandos.CommandInterpreter;
import tp.pr5.mv.escritura.OutStrategyFile;
import tp.pr5.mv.escritura.OutStrategyNada;
import tp.pr5.mv.escritura.OutStrategyStd;
import tp.pr5.mv.escritura.OutStream;
import tp.pr5.mv.lectura.InStrategyFile;
import tp.pr5.mv.lectura.InStrategyNada;
import tp.pr5.mv.lectura.InStrategyStd;
import tp.pr5.mv.lectura.InStream;
import tp.pr5.mv.virtualMachine.CPU;
import tp.pr5.mv.virtualMachine.ProgramMV;
import tp.pr5.vistas.BatchView;
import tp.pr5.vistas.InteractiveView;
import tp.pr5.vistas.SwingView;

public class Main {

	private static final int _BATCH_MODE = 0;
	private static final int _INTER_MODE = 1;
	private static final int _WINDOW_MODE = 2;
	

	private static CPU cpu;

	private static int executionMode = _INTER_MODE;
	
	private static String inStreamFileName;
	private static String outStreamFileName;
	private static String programFileName;
	
	public static String modeName ;

	public static void main(String[] args) {
		startMV(args);
	}

	public static void startMV(String[] args) {

		cpu = new CPU();
		try {
			parseOptions(args);
			tipoDeOUT_IN(); // le damos a cpu el tipo de objeto de in y out
			switch (executionMode) {
			case _INTER_MODE:
				ejecutaInteractive();
				break;
			case _BATCH_MODE:
				ejecutaBatch();
				break;
			case _WINDOW_MODE:
				windowMode(); break;
			}

		} catch (ExceptionIntruccionIncorrecta e){// La capturo aqui porque en modo batch o en modo interactive por ficheroASM se tiene que salir
			System.err.println(e.getMessage());
			System.exit(2);
		} catch (FileNotFoundException e) { // error que controla un
											// asmincorrecto
			System.err.println("Uso incorrecto: Error al acceder al fichero de entrada ("+ programFileName + ")");
			System.err.println("Use -h/--help para más detalles");
			System.exit(2);
		} catch (IOException e) { // Se captura por el br.readLine(); de //
									// ASMFileParser
			System.err.println(e.getMessage());
		} catch (ExceptionMV e) {
			System.err.println(e.getMessage());
		} catch (ExceptionArgumentoIncorrecto e) {
			System.err.println(e.getMessage());
		} 
	}

	
	public static void windowMode() throws IOException, ExceptionMV, ExceptionArgumentoIncorrecto, ExceptionIntruccionIncorrecta{
		if (programFileName == null)
			throw new ExceptionArgumentoIncorrecto("MODO WINDOW : archivo ASM obligatorio");
		else{
			ProgramMV program = ProgramMV.readProgram(programFileName);
			cpu.loadProgram(program);
			CommandInterpreter.configureCommandInterpreter(cpu);
		
			cpu.getMetodoEntrada().open();
			cpu.getMetodoSalida().open();
		
			// Crear el controlador y la vista
			final GUIControler ctrl = new GUIControler(cpu);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					@SuppressWarnings("unused")
					SwingView view = new SwingView(ctrl,cpu, cpu.getStack(),cpu.getMemory());					
				}
			});
			ctrl.start(); // en este caso start no hace nada, swing se encarga
		}		
	}
	
	
	public static void parseOptions(String[] args)  {
		CommandLineParser parser = new BasicParser();
		Options option = new Options();
		configurarMenu(option);
		try {
			CommandLine commandLine = parser.parse(option, args);

			if (commandLine.hasOption("h") || commandLine.hasOption("help")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("tp.pr3.mv.Main [-a <asmfile>] [-h] [-i <infile>] [-m <mode>] [-o<outfile>]",option);
			}

			inStreamFileName = commandLine.getOptionValue("i");
			outStreamFileName = commandLine.getOptionValue("o");
			programFileName = commandLine.getOptionValue("a");
			modeName = commandLine.getOptionValue("m");

			if (modeName == null){// si no ponemos nada
				executionMode = _INTER_MODE;
				modeName = "interactive";
			}
			else if (modeName.equalsIgnoreCase("batch"))
				executionMode = _BATCH_MODE;
			else if (modeName.equalsIgnoreCase("interactive"))
				executionMode = _INTER_MODE;
			else if (modeName.equalsIgnoreCase("window"))
				executionMode = _WINDOW_MODE;
			else
				throw new ExceptionArgumentoIncorrecto("Uso incorrecto: modo incorrecto (-m|--mode '"+ modeName + "' )");

		} catch (ParseException e) {
			System.err.println(e.getMessage());
			System.err.println("Use -h/--help para más detalles");
			System.exit(1);
		} catch (ExceptionArgumentoIncorrecto e) {
			System.err.println(e.getMessage());
			System.err.println("Use -h/--help para más detalles");
			System.exit(1);
		}
	}

	private static void configurarMenu(Options op) {
		op.addOption("a","asm",true, "Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en modo batch.");
		op.addOption("h", "help", false, "Muestra esta ayuda");
		op.addOption("i", "in", true, "Entrada del programa de la maquina-p.");
		op.addOption("m", "mode", true, "Modo de funcionamiento (batch | interactive). Por defecto, batch.");
		op.addOption("o", "out", true, "Fichero donde se guarda la salida del programa de la maquina-p");

		// Para que muestre en < > lo que queramos
		op.getOption("a").setArgName("asmfile");
		op.getOption("i").setArgName("infile");
		op.getOption("m").setArgName("mode");
		op.getOption("o").setArgName("outfile");
	}
	
	public static void tipoDeOUT_IN() throws ExceptionMV, IOException {

		OutStream out = null;
		InStream in = null;
		
		if (outStreamFileName != null)
			out = new OutStrategyFile(outStreamFileName);
		else if (modeName.equalsIgnoreCase("window" ))
			out = new OutStrategyNada();
		else 
			out = new OutStrategyStd(); // no existe fichero donde escribir la salida de OUT
				
		if (inStreamFileName != null)
			in = new InStrategyFile(inStreamFileName);
		else if (modeName.equalsIgnoreCase("batch")|| modeName.equalsIgnoreCase("window" ))
			in = new InStrategyNada() ;
		else
			in = new InStrategyStd();
			
		cpu.setInStream(in);
		cpu.setOutStream(out);
		
	}
	

	public static void ejecutaInteractive() throws IOException, ExceptionIntruccionIncorrecta {
				
		ProgramMV programMv = null;

		if (programFileName != null) // cargamos el programa por fichero
			programMv = ProgramMV.readProgram(programFileName);
		else {// metemos el programa a ejecutar por consola por consola
			System.out.println("Introduce el programa fuente: ");
			System.out.print("> ");
			programMv = ProgramMV.readProgram();// leemos todas las instrucciones
		}
		
		cpu.getMetodoEntrada().open();
		cpu.getMetodoSalida().open();
		
		//CommandInterpreter.configureCommandInterpreter(cpu);
		cpu.loadProgram(programMv);
		//CommandInterpreter comando = null;

		System.out.println(programMv.toSring()); // mostramos el programa a ejecutar
				
		//Crear el controlador y la vista
		InteractiveControler ctrl = new InteractiveControler(cpu);
		@SuppressWarnings("unused")
		InteractiveView view = new InteractiveView(cpu);
		ctrl.start();
		
		
		cpu.getMetodoEntrada().close();
		cpu.getMetodoSalida().close();

	}

	// Método Batch.
	public static void ejecutaBatch() throws IOException,
			ExceptionArgumentoIncorrecto, ExceptionIntruccionIncorrecta {
				
		ProgramMV programMv = null;

		// En modo batch es obligatorio un archivo ASM
		if (programFileName != null)
			programMv = ProgramMV.readProgram(programFileName);
		else
			throw new ExceptionArgumentoIncorrecto(
					"Uso incorrecto: File ASM no especificado ");

		cpu.getMetodoEntrada().open();
		cpu.getMetodoSalida().open();
		
		cpu.loadProgram(programMv);
		CommandInterpreter.configureCommandInterpreter(cpu);
		
		//Crear el controlador y la vista
		BatchControler ctrl = new BatchControler(cpu);
		@SuppressWarnings("unused")
		BatchView view = new BatchView(cpu);
		ctrl.start();
				
				
		cpu.getMetodoEntrada().close();
		cpu.getMetodoSalida().close();
		
		
		
		
		/*CommandInterpreter comando = new Run(); // El modo batch va a funcionar
												// como un RUN

		try {
			comando.executeCommand(); // No ponemos try-catch porque cuando
										// salte una excepcion se debe salir del
										// programa
		} catch (ExceptionIncorrectProgramCounter e) {
			System.err.println(e.getMessage());
		} catch (ExceptionEmptyStack e) {
			System.err.println(e.getMessage());
		} catch (ExceptionDivisionByZero e) {
			System.err.println(e.getMessage());
		} catch (ExceptionFaltanElementosEnPila e) {
			System.err.println(e.getMessage());
		}*/

		

	}

}// end class Prueba


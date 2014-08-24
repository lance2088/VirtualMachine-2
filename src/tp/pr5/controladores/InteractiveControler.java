package tp.pr5.controladores;

import java.util.Scanner;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.Exceptions.ExceptionComandoIncorrecto;
import tp.pr5.mv.virtualMachine.CPU;

public class InteractiveControler extends Controler {

	// - Un bucle donde en cada iteración se pide un comando (STEP, RUN, PUSH, etc.)
	// al usuario y el método que corresponde (de Controler).
	// - Es como lo que tenéis en la práctica 4 en el modo interactive.
	// - No muestra nada en la consola, la vista se encarga de esto
	
	public InteractiveControler(CPU cpu) {
		super(cpu);
	}

	@Override
	public void start() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String str;
		boolean pedirOtroComando = false;
		boolean salir = false;
		
		//do {
			
			do {
				System.out.print("-Comando > ");
				pedirOtroComando = true;

				str = sc.nextLine();
				String[] tokens = str.split("\\s+");
				try {
					if ( tokens[0].equalsIgnoreCase("push") && tokens.length == 2 && Constantes.isNumero(tokens[1]))
						super.push(Integer.parseInt(tokens[1]));
					else if ( tokens[0].equalsIgnoreCase("pop") && tokens.length == 1 ){
					     super.pop();
					}
					else if ( tokens[0].equalsIgnoreCase("step") && tokens.length == 1)	{
						super.step();
					}
					else if ( tokens[0].equalsIgnoreCase("quit") && tokens.length == 1 ){
						//super.quit();
						salir=true;
					}
					else if ( tokens[0].equalsIgnoreCase("run") && tokens.length == 1 ){
						super.run();
					}
					else if ( tokens[0].equalsIgnoreCase("write") && tokens.length == 3 && Constantes.isNumero(tokens[1]) &&
							Constantes.isNumero(tokens[2])){
						super.memorySet(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));				
					}
					else if (tokens[0].equalsIgnoreCase("step") && tokens.length == 2 && Constantes.isNumero(tokens[1]))
						super.steps(Integer.parseInt(tokens[1]));
					
					else throw new ExceptionComandoIncorrecto ("El comando introducido es incorrecto");
										
					
				} catch (ExceptionComandoIncorrecto e) {	
					System.out.println(e.getMessage());
					pedirOtroComando=true; 
				}
					

			} while (pedirOtroComando && !salir); // para cuando metemos un comando incorrecto

			
		//} while (!super.programFinished());
		
		super.quit();

				
	}

}

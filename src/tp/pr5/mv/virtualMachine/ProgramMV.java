package tp.pr5.mv.virtualMachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tp.pr5.mv.Constantes;
import tp.pr5.mv.Exceptions.ExceptionIntruccionIncorrecta;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.parsers.InstructionParser;
/**
 * Clase ProgramMV que tiene el array de instrucciones y los metodo correspondientes
 * para poder tratarlas.
 * @author Sergio Fuentes & David Rico
 *
 */
public class ProgramMV {

	 private ArrayList<Instruction> program;

	/**
	 * Constructor de la clase
	 */
	public ProgramMV() {
		this.program = new ArrayList<>();

	}
	
	public void push(Instruction ins){
		this.program.add(ins);
	}

	/**
	 * Introduce una instruccin en el arrayList de instrucciones
	 * @param instr
	 */
	

	/**
	 * Mtodo que devuelve la instrucin i del arraylist.
	 * 
	 * @param i
	 *            Se asume que i es un indice correcto ya que el control se
	 *            hace desde fuera.
	 * @return Instruction
	 */
	public Instruction get(int i) {
		if (this.program.isEmpty())
			return null;
	
		else return this.program.get(i);
	}

	/**
	 * Mtodo que devuelve el nmero de instrucciones en el array
	 *  
	 * @return nmero de instrucciones.
	 */
	public int getSizeProgram() {
		return this.program.size();
	}

	/**
	 * Devuelve los valores de guarda ProgramMV en un string
	 * @return str
	 */
	public String toSring() {
		String str = "";

		for (int i = 0; i < this.program.size(); i++)
			str = str + i + ": " + this.program.get(i).toString()
					+ Constantes.SALTO_LINEA;

		return str;
	}
	
	static public ProgramMV readProgram() {
		
		Instruction ins = null;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String linea = "";
		ProgramMV program = new ProgramMV();
		while (!linea.equalsIgnoreCase("end")) {
			linea = sc.nextLine();
			if (!linea.equalsIgnoreCase("end")) {
				try {
					ins = InstructionParser.parseoUnaPosibleIntruccion(linea);
					program.push(ins);
				} catch (ExceptionIntruccionIncorrecta e) {
					System.err.println(e.getMessage());
				}
			}
		}
		return program;

	}
	
	public static ProgramMV readProgram(String FileName) throws IOException, ExceptionIntruccionIncorrecta {
		
		ProgramMV program = new ProgramMV();
		FileReader fr = null;
		BufferedReader br = null;
		
			fr = new FileReader(FileName); // Lanza una excepcion sino puede leer de tipo FileNotFoundException y lo captura en el main!
			// Metemos fr en br
			br = new BufferedReader(fr);
			String linea = br.readLine(); // Lanza una excepcion de tipo IOException y lo captura en el main!
			Instruction ins = null;

			while (linea != null) {
				// Quitamos espacios en blanco (los del principio y los del final
				linea = linea.trim(); 
				if (!linea.startsWith(";") && !linea.equals("")) {
					// Guardamos en pos la posicion donde se encuentra un ;
					int pos = linea.indexOf(';');
					if (pos > 0) // quiere decir que hay un ; en la linea
						linea = linea.substring(0, pos);
//					try {	
						ins = InstructionParser.parseoUnaPosibleIntruccion(linea);						
						if (ins != null)
							program.push(ins);	
						
//					}	
//					catch (ExceptionIntruccionIncorrecta e){// La capturo aqui porque en modo batch o en modo interactive por ficheroASM se tiene que salir
//						System.err.println(e.getMessage());
//						System.exit(2);
//					}
					
				}// if
				linea = br.readLine();
			}
			
			br.close();
			return program;
			
		}

}

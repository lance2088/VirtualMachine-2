package tp.pr5.mv.lectura;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class InStrategyFile implements InStream{

	String fName; 
	FileReader fr;
	
	public InStrategyFile(String fName){
		this.fName = fName;
	}
	@Override
	public void open() {	
		try{ // Lo hacemos aqui para controlar el error si es en el de entrada o en el de salida
			fr = new FileReader(fName); 	
		}catch (FileNotFoundException e){
			System.err.println("Uso incorrecto: Error al acceder al fichero de entrada (" + this.fName+ ")");
			System.err.println("Use -h/--help para más detalles");
			System.exit(1);
		}
	}

	@Override
	public int read() throws IOException {
		if ( fr.ready() ) return  fr.read();		
		 else return -1;
	}

	@Override
	public void close() throws IOException {
		fr.close();
	}
	@Override
	public void reset() {
		
		try {
			close();
			open();
		} catch (IOException e) {
			// Nunca va a caer aquí porque es el mismo fichero de entrada que al principio
		}
		
		
		
	}

	
	
	
}

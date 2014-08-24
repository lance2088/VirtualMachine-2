package tp.pr5.mv.escritura;

import java.io.FileWriter;
import java.io.IOException;

public class OutStrategyFile implements OutStream {

	String fName;
	FileWriter fw;
	
	public FileWriter getFw() {
		return fw;
	}

	public OutStrategyFile (String fName){
		this.fName = fName;
	}
	
	@Override
	public void open() throws IOException {
		this.fw = new FileWriter(fName);		
	}

	@Override
	public void write(int x) throws IOException {		
		fw.write((char) x);		
	}

	@Override
	public void close() throws IOException {
		fw.close();
	}

	@Override
	public void reset() {
		
			try {
				close();
				open();
			} catch (IOException e) {
				// Nunca va a caer aquí porque es el mismo fichero de salida que al principio
			}			
		
			
		}
		
	}




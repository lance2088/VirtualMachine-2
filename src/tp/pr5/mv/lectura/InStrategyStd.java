package tp.pr5.mv.lectura;

import java.io.FileNotFoundException;
import java.io.IOException;

public class InStrategyStd implements InStream {

	@Override
	public void open() throws FileNotFoundException {}

	@Override
	public int read() throws IOException {
		System.out.print("-In > ");
		int c = System.in.read();
		while ( System.in.available() > 0 ) System.in.read();
		return c;
	}

	@Override
	public void close() throws IOException {}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}

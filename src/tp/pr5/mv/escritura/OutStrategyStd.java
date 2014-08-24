package tp.pr5.mv.escritura;

public class OutStrategyStd implements OutStream {

	@Override
	public void open() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void write(int x) {
		System.out.print((char)x);
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	

}

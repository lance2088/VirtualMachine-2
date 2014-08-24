package tp.pr5.mv.escritura;

import java.io.IOException;
/**
 * Interfaz que define las estrategias para la intruccion Out
 * @author Sergio Fuentes & David Rico
 */
public interface OutStream {
	/**
	 * Abre el writer
	 * @throws IOException
	 */
	public void open() throws IOException;
	/**
	 * Escribe un caracter
	 * @param x
	 * @throws IOException
	 */
	public void write(int x) throws IOException;
	
	/**
	 * Cierra el writer
	 * @throws IOException
	 */
	public void close() throws IOException;
	
	public void reset();
}

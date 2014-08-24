package tp.pr5.mv.lectura;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interfaz que define las estrategias para leer de un archivo o de consola.
 * @author David Rico && Sergio Fuentes
 *
 */
public interface InStream {
	/**
	 * Método que se encarga de abrir el lector.
	 * @throws FileNotFoundException
	 */
	public void open() throws FileNotFoundException;
	/**
	 * Método que se encarga de leer un caracter y convertirlo en int
	 * @return caracter value
	 * @throws IOException
	 */
	public int read() throws IOException;
	/**
	 * Método que se encarga de cerrar el lector
	 * @throws IOException
	 */
	public void close() throws IOException;	
	
	public void reset();
	
}

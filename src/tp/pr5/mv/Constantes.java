package tp.pr5.mv;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Clase para declarar constantes.
 * @author Sergio Fuentes & David Rico 
 *
 */
public class Constantes {
	/**
	 * Atributo estatico, es un string que se traduce en un salto de linea.
	 */
	public static final String SALTO_LINEA = System.getProperty("line.separator");
	
	/**
	 * Metodo estatico que sirve para separar un string en un array de strings, utilizando el espacio como
	 * separador.
	 * @param str
	 * @return String[]. Cadena de strings
	 */
	public static String[] separarString(String str){
		return str.split(" ");
		
	}
	/**
	 * Metodo estatico que sirve para ver si un string es un numero o no, para ello compra cada caracter del string
	 * y si el valor de cada caracter esta entre el 0 y el 9, si es asi devuelve true.
	 * @param str
	 * @return booleano
	 */
	public static boolean isNumero(String str) {
		boolean esNumero = true;
		int i = 0;
		
		if (str.charAt(0) == '-')
			i=1;

		while (esNumero && (i < str.length())) {
			if ((str.charAt(i) < '0') || ((str.charAt(i) >'9'))) {
				esNumero = false;
			}
			i++;
		}
		return esNumero;
	}
	/**
	 * Metodo estatico que devuelve true si es un numero positivo.
	 * @param str 
	 * @return boolean.
	 */
	public static boolean isNumeroPositivo(String str) {
		boolean esPositivo = true;
				
		if (str.charAt(0) == '-')
			esPositivo = false;
		
		return esPositivo ;
	}
	
	public static void reportError(String msg, String title) { // Usa JDialog				
		JDialog dialog = new JDialog();
		JOptionPane.showMessageDialog(dialog,msg,title,JOptionPane.ERROR_MESSAGE);	
	}
	
}

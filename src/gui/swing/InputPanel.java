package gui.swing;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr5.controladores.GUIControler;
import tp.pr5.mv.lectura.InStream;

@SuppressWarnings("serial")
public class InputPanel extends JPanel {

	private GUIControler guiCtrl;
	private JTextArea inputTextArea;

	public class InStreamGUI implements InStream {

		StringBuilder content;
		int pos;
		InStream old;

		private void init(){
			content = new StringBuilder();
			
			pos = 0;
			int numAconvertir = 0;
			try{
			numAconvertir = old.read();
			while (numAconvertir != -1) {// final de fichero				
				content.append((char) numAconvertir);
				numAconvertir = old.read();
			}
			
			inputTextArea.setText(content.toString());
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		
		public InStreamGUI()  {
			old = guiCtrl.getInStream();
			
			init();
				
		}

		@Override
		public void open() throws FileNotFoundException { // suponemos que old ya está abierto
		}

		@Override
		public int read() throws IOException {
			int c = 0;
			// 1. si pos == content.length() entonce ya no hay más caracteres
			if (pos != content.length()) {
				// 2. consultar el carácter c el la posición pos del content
				c = content.charAt(pos);
				// 3. si c no es salto de linea (c!=10 y c!=13) lo cambiamos con * en content!
				if ((c != 10) && (c != 13)) {
					content.setCharAt(pos, '*');
					
				}
				inputTextArea.setText(content.toString());// 4. actualizar el inputTextArea;
				pos++; // 5. actualizar pos
			}
			
			else c= -1;
				
			return  c; // 6. devolver c;
		}

		@Override
		public void close() throws IOException {
			old.close();
		}

			
		@Override
		public void reset() {
			
			old.reset();
			
			init();
			
			
		}
	}// fin public class InStreamGUI

	// Constructor
	public InputPanel(GUIControler guiCtrl)  {
		this.guiCtrl = guiCtrl;
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout()); // tipo reparticion
		this.setBorder(new TitledBorder("Input"));
		inputTextArea = new JTextArea(6,6);
		this.add(new JScrollPane(inputTextArea));
		inputTextArea.setEditable(false);
		
		guiCtrl.setInStream(new InStreamGUI());
	}
}

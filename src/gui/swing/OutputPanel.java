package gui.swing;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr5.controladores.GUIControler;

import tp.pr5.mv.escritura.OutStream;

@SuppressWarnings("serial")
public class OutputPanel extends JPanel {
	private GUIControler guiCtrl ;
	private JTextArea outputTextArea;
	
	
	public class OutStreamGUI implements OutStream{

		StringBuilder content;
		OutStream old;
		
				
		public OutStreamGUI(){
			content = new StringBuilder();	
			this.old = guiCtrl.getOutStream(); // va a ser el viejo (pantalla o file)
		}
		
		@Override
		public void open() throws IOException {
			// no hacer nada, suponemos que old ya está abierto			
		}

		@Override
		public void write(int x) throws IOException {
			// 1. pasar x al OutStream original				
			old.write(x);
			// 2. concatenar c al contenido del outputTextArea
			this.content.append((char)x);
			outputTextArea.setText(content.toString());
			
		}

		@Override
		public void close() throws IOException {
			old.close();
			
		}

		@Override
		public void reset() {

			old.reset();			
			content = new StringBuilder();
			outputTextArea.setText(content.toString());
			
		}
		
	}
	
	public OutputPanel( GUIControler guiCtrl)  {
		this.guiCtrl = guiCtrl;
		initGUI();
	}

	private void initGUI() {
			
		this.setLayout(new BorderLayout()); // tipo reparticion
		this.setBorder(new TitledBorder("Output"));
		outputTextArea = new JTextArea(6,6);
		this.add(new JScrollPane(outputTextArea));		
		outputTextArea.setEditable(false);			
		
		guiCtrl.setOutStream(new OutStreamGUI()); // (va a ser de este tipo para que entre en el write de la clase interna)
	}
	
	
}

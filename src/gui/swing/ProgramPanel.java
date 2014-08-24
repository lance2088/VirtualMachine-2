package gui.swing;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.controladores.GUIControler;
import tp.pr5.mv.Constantes;
import tp.pr5.mv.Observers.CPUObserver;
import tp.pr5.mv.Observers.Observable;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.Memory;
import tp.pr5.mv.virtualMachine.OperandStack;
import tp.pr5.mv.virtualMachine.ProgramMV;

@SuppressWarnings("serial")
public class ProgramPanel extends JPanel implements CPUObserver {

	private GUIControler guiCtrl;
	private JTextArea programTextArea;
	private ProgramMV program;
	private int pc;
	
	//private Observable<CPUObserver> cpu;
	
	
	public ProgramPanel(GUIControler guiCtrl, Observable<CPUObserver> cpu)
	{
		this.guiCtrl = guiCtrl;
		cpu.addObserver(this);
		initGUI();		
		// ...		
		
		showProgram();
	}
	
	private void initGUI()
	{		
		this.setLayout(new BorderLayout()); // tipo reparticion
		this.setBorder(new TitledBorder("Program"));
		programTextArea = new JTextArea(20, 12);
		
		programTextArea.setFont(new Font("Courier", Font.PLAIN, 16));
		programTextArea.setEditable(false); // para que no se pueda escribir, solo que muestre
		this.add(new JScrollPane(programTextArea)); // que nuestro Panel, tenga un scrollPAnel (que es la pantalla y la barra) y ese tiene el textArea	
		
		program = guiCtrl.getProgram();
		pc = 0;
		showProgram();
		
	}
	
	private void showProgram() {
		// es como el updateView en la práctica 4, pero usa los atributos pc y program
		
		String strAmostrar = "";
		
		if (pc>=0){		
			for (int i=0; i<program.getSizeProgram(); i++){
				if (i==pc)
					strAmostrar = strAmostrar + (" *  " + i + ": "  + program.get(i).toString() + Constantes.SALTO_LINEA);
				else
					strAmostrar = strAmostrar + ( "    "+ i +": "+ program.get(i).toString() + Constantes.SALTO_LINEA);
			}
			this.programTextArea.setText(strAmostrar);
		}
		else // todavia no ha empezado el programa a ejecutar y lo mostramos
			this.programTextArea.setText(program.toSring());		
		
	}

	

	@Override
	public void onStartInstrExecution(Instruction instr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndInstrExecution(final int pcAux, OperandStack<Integer> pila,
			Memory<Integer> mem) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				pc = pcAux;
					
				showProgram();
			}
		});	
				
		
	}

	@Override
	public void onStartRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHalt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(ProgramMV programAux) {
		pc = 0;
		program = programAux; //guiCtrl.getProgram(); // volvemos a cargar para la parte opcional de cargar cuando pulsamos cargar
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showProgram();
				}
			});
		}
	
	
}

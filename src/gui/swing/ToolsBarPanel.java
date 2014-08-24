package gui.swing;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.controladores.GUIControler;
import tp.pr5.mv.Constantes;
import tp.pr5.mv.Exceptions.ExceptionIntruccionIncorrecta;
import tp.pr5.mv.Observers.CPUObserver;
import tp.pr5.mv.Observers.Observable;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.Memory;
import tp.pr5.mv.virtualMachine.OperandStack;
import tp.pr5.mv.virtualMachine.ProgramMV;
import tp.pr5.vistas.SwingView;

@SuppressWarnings("serial")
public class ToolsBarPanel extends JPanel implements CPUObserver {

	private GUIControler guiCtrl;
	private JButton stepButton;
	private JButton runButton;
	private JButton exitButton;
	private JButton pauseButton;
	private JButton resetButton;
	
	private JButton cargarButton;
	
	private Thread t;
	
	public ToolsBarPanel(GUIControler guiCtrl,Observable<CPUObserver> cpu) {
		this.guiCtrl = guiCtrl;
		
		cpu.addObserver(this);
		
		initGUI();
		//...
 }

	private void initGUI() {
			
		this.setBorder(new TitledBorder("Acciones"));
		this.setLayout(new BorderLayout());
		
		JPanel botones = new JPanel();
		
		this.stepButton = new JButton("Step");
		stepButton.setIcon(createImageIcon("step.png"));
		stepButton.setToolTipText("Step");
		botones.add(stepButton);
		
		this.runButton = new JButton("Run");
		runButton.setIcon(createImageIcon("run.png"));
		runButton.setToolTipText("Run");
		botones.add(runButton);
		
		this.pauseButton = new JButton("Pause");
		pauseButton.setIcon(createImageIcon("pause.png"));
		pauseButton.setToolTipText("pause");
		botones.add(pauseButton);
		
		this.exitButton = new JButton("Exit");
		exitButton.setIcon(createImageIcon("exit.png"));
		exitButton.setToolTipText("Exit");
		botones.add(exitButton);
		
		this.resetButton = new JButton("Reset");
		resetButton.setIcon(createImageIcon("reset.png"));
		resetButton.setToolTipText("Reset");
		botones.add(resetButton);
		
		this.cargarButton = new JButton("Cargar");
		cargarButton.setIcon(createImageIcon("ne.png"));
		cargarButton.setToolTipText("Cargar");
		botones.add(cargarButton);
		
		
		// *  Acciones para botones
		
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.step();
			}
		});
		
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					 t = new Thread() { // Cuando pulsamos run se crea un nuevo hilo
						public void run() {
							guiCtrl.run(); 
						}
					};
					t.start(); 
				
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				int confirmado = JOptionPane.showConfirmDialog( frame, "¿Desea salir de la aplicacion?");

				if (JOptionPane.OK_OPTION == confirmado){
					guiCtrl.quit();
				}				
			
			}
		});
		
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stepButton.setEnabled(true);
				runButton.setEnabled(true);
				resetButton.setEnabled(true);
				cargarButton.setEnabled(true);
				guiCtrl.pause();
				
				
			}
		});	
		
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.reset();		
		
			}
		});	
		
		
		cargarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						System.out.println(file.getName());
						//guiCtrl.loadPrograma( file.getName() );
						guiCtrl.loadPrograma(file.getAbsolutePath());
					} catch (ExceptionIntruccionIncorrecta e1) {
						Constantes.reportError(e1.getMessage(), "ERROR");
					} catch (IOException e1) {
						Constantes.reportError("Error al cargar ese fichero", "ERROR");
					} 										
				}				
			}
		});	
				
		// añadimos el Reloj
		Reloj reloj = new Reloj(0, 0, 0, 0);// Instancia de nuestra clase Reloj (0,0,0,0 ya que el layout es por defecto)
		reloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);// Centrado del texto
		reloj.setFont(new Font("Courier", Font.PLAIN, 12));;// tipo de letra y tamaño

		JPanel relojPanel = new JPanel();
		relojPanel.add(reloj);
		
		
		this.add(botones, BorderLayout.CENTER);
		this.add(relojPanel, BorderLayout.EAST);
		//this.add(cargarButton, BorderLayout.WEST);
	}
	
	
private static ImageIcon createImageIcon(String path) {
	java.net.URL imgURL = SwingView.class.getResource(path);
	if (imgURL != null) return new ImageIcon(imgURL);
	return null;
}

@Override
public void onStartInstrExecution(Instruction instr) {
	
	
}

@Override
public void onEndInstrExecution(int pc, OperandStack<Integer> pila,
		Memory<Integer> mem) {
	// TODO Auto-generated method stub
	
}

@Override
public void onStartRun() {
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			runButton.setEnabled(false);
			stepButton.setEnabled(false);
			resetButton.setEnabled(false);	
			cargarButton.setEnabled(false);
			pauseButton.setEnabled(true);
		}
	});	
}

@Override
public void onEndRun() {
	
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			//runButton.setEnabled(true);
			//stepButton.setEnabled(true);
			resetButton.setEnabled(true);
			cargarButton.setEnabled(true);
			pauseButton.setEnabled(false);
			
		}
	});	
	
	
}

@Override
public void onError(String msg) {
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			runButton.setEnabled(true);
			stepButton.setEnabled(true);
			resetButton.setEnabled(true);
			cargarButton.setEnabled(true);
			pauseButton.setEnabled(false);
		}
	});	
	
}

@Override
public void onHalt() {
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			runButton.setEnabled(false);
			stepButton.setEnabled(false);
			pauseButton.setEnabled(false);	
			
			resetButton.setEnabled(true);
			cargarButton.setEnabled(true);
		}
	});	
	
}

@Override
public void onReset(ProgramMV program) {
	runButton.setEnabled(true);
	stepButton.setEnabled(true);
	pauseButton.setEnabled(true);	
	
}


}
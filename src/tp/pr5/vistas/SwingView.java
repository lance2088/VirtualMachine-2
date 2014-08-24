package tp.pr5.vistas;

import gui.swing.InputPanel;
import gui.swing.MemoryPanel;
import gui.swing.OutputPanel;
import gui.swing.ProgramPanel;
import gui.swing.StackPanel;
import gui.swing.StatusBarPanel;
import gui.swing.ToolsBarPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.controladores.GUIControler;
import tp.pr5.mv.Constantes;
import tp.pr5.mv.Observers.CPUObserver;
import tp.pr5.mv.Observers.MemoryObserver;
import tp.pr5.mv.Observers.Observable;
import tp.pr5.mv.Observers.StackObserver;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.Memory;
import tp.pr5.mv.virtualMachine.OperandStack;
import tp.pr5.mv.virtualMachine.ProgramMV;

public class SwingView extends JFrame implements CPUObserver  {

	private static final long serialVersionUID = 1L;
	
	private GUIControler ctrl;
	private Observable<CPUObserver> cpu;
	private Observable<StackObserver<Integer>> stack;
	private Observable<MemoryObserver<Integer>> memory;
	
	private ProgramPanel programPanel;
	private StackPanel stackPanel;
	private MemoryPanel memoryPanel;
	private ToolsBarPanel toolsPanel;
	private InputPanel inputPanel;
	private OutputPanel outputPanel;
	private StatusBarPanel statusPanel;
		
	
	public SwingView (GUIControler ctrl, Observable<CPUObserver> cpu,
			Observable<StackObserver<Integer>> stack, Observable<MemoryObserver<Integer>> memory) 
	{
		super("Virtual Machine");
		this.cpu = cpu;
		this.stack = stack;
		this.memory = memory;
		this.ctrl = ctrl;
		initGUI();
		
		cpu.addObserver(this);		
	}	
	
	
	private void initGUI(){
			
		toolsPanel = new ToolsBarPanel(ctrl, cpu);
		programPanel = new ProgramPanel(ctrl, cpu);
		stackPanel = new StackPanel(ctrl, cpu,stack);
		memoryPanel = new MemoryPanel(ctrl, memory, cpu);
		toolsPanel = new ToolsBarPanel(ctrl, cpu);
		inputPanel = new InputPanel(ctrl);
		outputPanel = new OutputPanel(ctrl);
		statusPanel = new StatusBarPanel(cpu, stack, memory);		
		
		
		// Panel para meter InputPanel y OutputPanel
		JPanel ioPanel = new JPanel(new GridLayout(2, 1));
		ioPanel.add(inputPanel);
		ioPanel.add(outputPanel);	
				
				
		// Panel para meter MemoryPanel y StackPanel y ocupen lo mismo
		// utilizando GridLayout
		JPanel centerPanel2 = new JPanel(new GridLayout(1, 1));
		centerPanel2.add(stackPanel);
		centerPanel2.add(memoryPanel);

		// Panel para meter ioPanel y centerPanel2 (que contiene MemoryPanel y StackPanel
		JPanel centerPanel = new JPanel(new GridLayout(2, 1));
		centerPanel.add(centerPanel2);
		centerPanel.add(ioPanel);

		// Panel principal que metemos en el JFrame.
		// Le metemos el centerPanel, el programPanel y el toolsPanels
		JPanel mainPanel = new JPanel();
		this.setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(toolsPanel, BorderLayout.PAGE_START);
		mainPanel.add(programPanel, BorderLayout.WEST); // le añadimos al panel
														// un panel encima
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
	//
		mainPanel.add(statusPanel, BorderLayout.SOUTH);
				
		this.setSize(1250, 750);
		this.setVisible(true);

		// Para cerrar la ejecución al pulsar X
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				JFrame frame = new JFrame();
				int confirmado = JOptionPane.showConfirmDialog(frame,
						"¿Desea salir de la aplicacion?");

				if (JOptionPane.OK_OPTION == confirmado) {
					ctrl.quit();
				}

			}
		});
				
		Image icon = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("ucm.png"));
		setIconImage(icon);

		// this.pack();

		 this.setExtendedState(JFrame.MAXIMIZED_BOTH); // para que se ajuste a la resolución de tu ordenador

		// this.setSize (1250,750);

	}
	
	
	// Implementar los métodos de CPUObserver
	// - El MainWindow reacciona sólo cuando recibe el aviso onError (mostrando un
	// mensaje de error)
	
	
	
	
	@Override
	public void onError(final String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Constantes.reportError(msg, "EXCEPCION");			
			}
		});
		
	}
			
	@Override
	public void onStartInstrExecution(Instruction instr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartRun() {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void onHalt() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onReset(ProgramMV program) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onEndInstrExecution(int pc, OperandStack<Integer> pila,
			Memory<Integer> mem) {
		
		
	}


	@Override
	public void onEndRun() {
		// TODO Auto-generated method stub
		
	}

}

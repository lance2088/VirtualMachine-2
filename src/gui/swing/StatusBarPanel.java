package gui.swing;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import tp.pr5.mv.Observers.CPUObserver;
import tp.pr5.mv.Observers.MemoryObserver;
import tp.pr5.mv.Observers.Observable;
import tp.pr5.mv.Observers.StackObserver;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.Memory;
import tp.pr5.mv.virtualMachine.OperandStack;
import tp.pr5.mv.virtualMachine.ProgramMV;

public class StatusBarPanel extends JPanel implements StackObserver<Integer>, MemoryObserver<Integer>, CPUObserver {

	private static final long serialVersionUID = 1L;
	
	JLabel totalNumsInstrucionesLabeltext;
	JLabel statusLabel;
	int contadorTotalIntrucciones;
	
	JCheckBox memModificada, pilaModificada;
	
	public StatusBarPanel(Observable<CPUObserver> cpu,Observable<StackObserver<Integer>> stack,	Observable<MemoryObserver<Integer>> memory) {
		cpu.addObserver(this);
		stack.addObserver(this);
		memory.addObserver(this);
		initGUI();
		
	}

	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		
		this.add( new JLabel("   Status: "));
		statusLabel = new JLabel(" Maquina sin iniciar    ") ;
		statusLabel.setForeground(Color.BLUE);
		this.add(statusLabel);
		
		this.add( new JLabel("     Num. Instruciones ejecutadas: "));
		totalNumsInstrucionesLabeltext = new JLabel(contadorTotalIntrucciones+"");
		this.add(totalNumsInstrucionesLabeltext);
		
		this.memModificada = new JCheckBox("Memoria Modificada");
		this.add(memModificada);
			
		this.pilaModificada = new JCheckBox("Pila Modificada");
		this.add(pilaModificada);
	}

	// ----StackObserver----
	@Override
	public void onPush(Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				pilaModificada.setSelected(true);
			}
		});		
	}

	@Override
	public void onPop(Integer value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStackReset() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				pilaModificada.setSelected(false);
			}
		});
		
		
	}

	
	//-------MemoryObserver-------
	@Override
	public void onWrite(int index, Integer value) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				memModificada.setSelected(true);
			}
		});
			
	}

	@Override
	public void onMemReset() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				memModificada.setSelected(false);
			}
		});
		
	}

	// -------CPUObserver--------
	@Override
	public void onStartInstrExecution(Instruction instr) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				statusLabel.setForeground(Color.GREEN);
				statusLabel.setText("Maquina ejecutandose");
			}
		});
		
		
	}

	@Override
	public void onEndInstrExecution(int pc, OperandStack<Integer> pila,
			Memory<Integer> mem) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//this.contadorTotalIntrucciones++;
				totalNumsInstrucionesLabeltext.setText(contadorTotalIntrucciones++ + "");		
			}
		});
			
	}
	

	@Override
	public void onStartRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndRun() {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				statusLabel.setForeground(Color.RED);
				statusLabel.setText("Maquina Parada");		
			}
		});
		
		
	}

	@Override
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHalt() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				statusLabel.setForeground(Color.RED);
				statusLabel.setText("Maquina Parada");	
			}
		});		
		
		//this.memModificada.setSelected(false);
		//this.pilaModificada.setSelected(false);
		
	}

	@Override
	public void onReset(ProgramMV program) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				contadorTotalIntrucciones =0;
				totalNumsInstrucionesLabeltext.setText(contadorTotalIntrucciones + "");	
				statusLabel.setForeground(Color.BLUE);
				statusLabel.setText(" Maquina sin iniciar    ") ;
			}
		});
		
		
		
		
	}

}

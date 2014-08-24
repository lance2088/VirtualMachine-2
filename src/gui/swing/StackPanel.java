package gui.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.controladores.GUIControler;
import tp.pr5.mv.Constantes;
import tp.pr5.mv.Observers.CPUObserver;
import tp.pr5.mv.Observers.Observable;
import tp.pr5.mv.Observers.StackObserver;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.Memory;
import tp.pr5.mv.virtualMachine.OperandStack;
import tp.pr5.mv.virtualMachine.ProgramMV;


@SuppressWarnings("serial")
public class StackPanel extends JPanel implements StackObserver<Integer>, CPUObserver {

	private GUIControler guiCtrl;
	
	private JList<Integer> stackArea;
	
	private JTextField stackElem; // valor a introducir en la pila
	
	private DefaultListModel<Integer> model;
	
	private JButton botonPush;
	private JButton botonPop;	
	private JPanel panelPila;
	private JPanel panelBotones;

	
	public StackPanel (GUIControler guiCtrl, Observable<CPUObserver> cpu, Observable<StackObserver<Integer>> stack){
		this.guiCtrl = guiCtrl;
		
		cpu.addObserver(this);
		stack.addObserver(this); // añadimos observador a la pila
		
		initGUI();
		//..
	}

	private void initGUI() {
		JLabel labelValue = new JLabel("Value");
		
		this.stackElem = new JTextField();
		this.stackElem.setSize(10, 15);
		this.stackElem.setColumns(10);
		
		this.panelPila = new JPanel();
		this.panelBotones = new JPanel();
		
		this.panelPila.setLayout(new BorderLayout());
		this.panelBotones.setLayout( new FlowLayout( FlowLayout.CENTER, 10, 10 ) );
		
		this.setLayout(new BorderLayout()); // tipo reparticion
		this.setBorder(new TitledBorder("Operand Stack"));
		
		model = new DefaultListModel<Integer>();
		stackArea = new JList<Integer>(model);
		
		botonPush = new JButton ("Push");
		botonPop = new JButton ("Pop");		
		stackArea.setFont(new Font("Courier", Font.PLAIN, 16));
		
		this.panelBotones.add(labelValue);
		this.panelBotones.add(this.stackElem);
		this.panelBotones.add(this.botonPush);
		this.panelBotones.add(this.botonPop);
		
		
		this.panelPila.add(stackArea);
		this.panelPila.add(new JScrollPane(stackArea));
		//this.add(stackArea, BorderLayout.CENTER);
		
		this.add(panelPila, BorderLayout.CENTER);
		this.add(panelBotones, BorderLayout.SOUTH);	
		
		// Acciones de los botones
		accionBotonPush();
		accionBotonPop();		
		
	}
	
	private void accionBotonPush(){
		this.botonPush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					guiCtrl.push(Integer.parseInt(stackElem.getText()));
				}catch(NumberFormatException e){
					Constantes.reportError("Introduzca un valor numerico", "PUSH ERROR");
				}
				
			}			
		});
	}
	
	private void accionBotonPop(){
		this.botonPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.pop();
			}
		});
	}
	
	

	@Override
	public void onStartInstrExecution(Instruction instr) {
		// TODO Auto-generated method stub
		
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
				botonPop.setEnabled(false);
				botonPush.setEnabled(false);
			}
		});
		
		
		
	}

	@Override
	public void onEndRun() {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				botonPop.setEnabled(true);
				botonPush.setEnabled(true);
			}
		});
		
		
	}

	@Override
	public void onError(String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				botonPop.setEnabled(true);
				botonPush.setEnabled(true);
			}
		});
		
	}

	@Override
	public void onHalt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(ProgramMV program) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				botonPop.setEnabled(true);
				botonPush.setEnabled(true);
			 }
		});
		
		
		
	}

	@Override
	public void onPush(final Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				model.add(0, value);
			 }
		});
	}

	@Override
	public void onPop(Integer value) {
	
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				model.removeElementAt(0);
			}
		});
		
		
	}

	@Override
	public void onStackReset() {	
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				model.clear();
			}
		});
		
		
	}
	
	
}

package gui.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import tp.pr5.controladores.GUIControler;
import tp.pr5.mv.Constantes;
import tp.pr5.mv.Observers.CPUObserver;
import tp.pr5.mv.Observers.MemoryObserver;
import tp.pr5.mv.Observers.Observable;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.Memory;
import tp.pr5.mv.virtualMachine.OperandStack;
import tp.pr5.mv.virtualMachine.ProgramMV;

@SuppressWarnings("serial")
public class MemoryPanel extends JPanel implements MemoryObserver<Integer>, CPUObserver {

	private GUIControler guiCtrl;
	private TableModel model;
	private JTextField memPos;
	private JTextField memValue;
	
	private JButton botonSet; // lo ponemos como atributo para poder bloquearlo cuando acabe la ejecucion
	
	
	public MemoryPanel(GUIControler guiCtrl, Observable<MemoryObserver<Integer>> memory, Observable<CPUObserver> cpu) {
		this.guiCtrl = guiCtrl;
		initGUI();
		//..
		cpu.addObserver(this);
		memory.addObserver(this);
	}

	
	private class TableModel extends AbstractTableModel
	{
		
		TreeMap<Integer,Integer> content;
		String[] colNames = {"DIRECCION", "VALOR"};
				
		TableModel() {		
			content = new TreeMap<Integer,Integer>();
		}
		
		public void setValue(int index, int value) {
			content.put(index, value);
			fireTableStructureChanged();
		}

		public String getColumnName(int col) {
			return colNames[col].toString();
		}
		
		public int getColumnCount() {
			return colNames.length;
		}
		
		@Override
		public int getRowCount() {
			return content.size();
		}

		
		// Nos devuelve un Objeto (que va a ser o una clave o un valor). Este método es llamado por Java cuando pasamos por fireTableStructureChanged() en setvalue();
		// Lo que hace Java es un bucle donde va cambiando rowIndex y columnIndex.
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// - busca la información que corresponde en content y la devuelve		
							
			Collection<Integer> claves =  content.keySet();		
			
			int cont = 0;
			
			for(Integer i: claves){
				if (cont == rowIndex) // Cuando nos piden una fila.
					if (columnIndex == 0) // Si coincide la fila deberemos devolver o la clave o el valor
						return i; // devolvemos la clave 
					else
						return content.get(i); // devolvemos valor porque columIndex sera 1
				else
					cont++;
				
			}			
			
			return null; // no va a llegar nunca pero sino se queja.
		}
		
		public void reset() {		
			// - borrar todos los elementos de content
			content.clear();
			// - avisar al JTable que el modelo ha sido modificado		
			fireTableStructureChanged();
		}

	}		
		
		
	
	
	private void initGUI() {
		
		JPanel panelMemoria = new JPanel();
		panelMemoria.setLayout(new BorderLayout());
	
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout( FlowLayout.CENTER, 10, 10 ) );// para que los ponga en linea
		
		this.setLayout(new BorderLayout()); // tipo reparticion
		this.setBorder(new TitledBorder("Memory"));
		
		JLabel labelValue = new JLabel("Value");
		JLabel labelPosition = new JLabel("Position");
		
		botonSet = new JButton("Set");
		
		// le damos tamaño a los dos JtextField
		this.memPos = new JTextField();
		this.memPos.setSize(10, 15);
		this.memPos.setColumns(10);		
		this.memValue = new JTextField();
		this.memValue.setSize(10, 15);
		this.memValue.setColumns(10);
			
		model = new TableModel();
		JTable table = new JTable(model);
		
		// Añadimos al panelBotones
		panelBotones.add(labelPosition);
		panelBotones.add(this.memPos);
		panelBotones.add(labelValue);
		panelBotones.add(this.memValue);
		panelBotones.add(botonSet);
		
		// Añadimos al panelMemory
		panelMemoria.add(new JScrollPane(table));
		table.setFillsViewportHeight(true);
					
		this.add(panelMemoria,  BorderLayout.CENTER);
		this.add(panelBotones, BorderLayout.SOUTH);
		
		// Acciones de los botones
		accionBotonSet();
				
	}
	
	private void accionBotonSet(){
		this.botonSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					guiCtrl.memorySet(Integer.parseInt(memPos.getText()), Integer.parseInt(memValue.getText()));
				}
				catch (NumberFormatException excep){
					Constantes.reportError("Introduzca valor numerico", "Error SET");
				}
				
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
				botonSet.setEnabled(false);
			}
		});	
	
		
	}

	@Override
	public void onEndRun() {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				botonSet.setEnabled(true);
			}
		});
		
		
	}

	@Override
	public void onError(String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				botonSet.setEnabled(true);
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
				botonSet.setEnabled(true);
			}
		});
		
	}

	@Override
	public void onWrite(final int index, final Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				model.setValue(index, value);
			}
		});
		
		
	}

	@Override
	public void onMemReset() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				model.reset();
				botonSet.setEnabled(true);
			}
		});
		
		
	}
	
	
	
	
}

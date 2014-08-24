package tp.pr5.mv.parsers;


import tp.pr5.mv.Constantes;
import tp.pr5.mv.Exceptions.ExceptionIntruccionIncorrecta;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.instrucciones.aritmeticas.Add;
import tp.pr5.mv.instrucciones.aritmeticas.Div;
import tp.pr5.mv.instrucciones.aritmeticas.Mul;
import tp.pr5.mv.instrucciones.aritmeticas.Sub;
import tp.pr5.mv.instrucciones.booleanas.And;
import tp.pr5.mv.instrucciones.booleanas.Not;
import tp.pr5.mv.instrucciones.booleanas.Or;
import tp.pr5.mv.instrucciones.comparacion.Equals;
import tp.pr5.mv.instrucciones.comparacion.GreaterThan;
import tp.pr5.mv.instrucciones.comparacion.LessOrEqual;
import tp.pr5.mv.instrucciones.comparacion.LessThan;
import tp.pr5.mv.instrucciones.indirectas.JumpInd;
import tp.pr5.mv.instrucciones.indirectas.LoadInd;
import tp.pr5.mv.instrucciones.indirectas.StoreInd;
import tp.pr5.mv.instrucciones.resto.Dup;
import tp.pr5.mv.instrucciones.resto.Flip;
import tp.pr5.mv.instrucciones.resto.Halt;
import tp.pr5.mv.instrucciones.resto.In;
import tp.pr5.mv.instrucciones.resto.Load;
import tp.pr5.mv.instrucciones.resto.Out;
import tp.pr5.mv.instrucciones.resto.Pop;
import tp.pr5.mv.instrucciones.resto.Push;
import tp.pr5.mv.instrucciones.resto.Store;
import tp.pr5.mv.instrucciones.salto.Bf;
import tp.pr5.mv.instrucciones.salto.Bt;
import tp.pr5.mv.instrucciones.salto.Jump;
import tp.pr5.mv.instrucciones.salto.RJump;
import tp.pr5.mv.instrucciones.salto.Rbf;
import tp.pr5.mv.instrucciones.salto.Rbt;


/**
 * Clase que se encarga de parsear strings a instrucciones
 * @author Sergio Fuentes & David Rico
 *
 */
public class InstructionParser {
	
	
	/**
	 * Array de conjunto de instrucciones
	 */
	private static Instruction instructionSet[] = 
		{
		new Add(), new Sub(), new Mul(), new Div(),
		new And(), new Not(), new Or(),
		new Equals(), new GreaterThan(), new LessOrEqual(), new LessThan(),
		new Dup(), new Flip(), new Halt(), new Load(0), new Out(), new Pop(), new Push(0), 
		new Store(0), new Bf(0), new Bt(0), new Jump(0), new RJump(0), new Rbf(0), new Rbt(0),
		new LoadInd(), new StoreInd(), new JumpInd(), new In()
		
		};
	

	/**
	 * Mtodo esttico que parsea un string en una instruccion
	 * @param posibleInstruccion . String a parsear
	 * @return Instrucction. Instruccion parseada si existe y si no devolver null.
	 * @throws ExceptionIntruccionIncorrecta 
	 */
	public static Instruction parseoUnaPosibleIntruccion(String  posibleInstruccion) throws ExceptionIntruccionIncorrecta //ej: push 5 
	{
		int i = 0;
		boolean stop = false;
		String[] posibleInstr = Constantes.separarString(posibleInstruccion);
		Instruction ins = null;
		
		while (i<InstructionParser.instructionSet.length && !stop)
		{
			ins = InstructionParser.instructionSet[i].parse(posibleInstr);
			if (ins != null) 
				stop = true;
			else 
				i++;
		}
		
		if (ins == null) {
			throw new ExceptionIntruccionIncorrecta("Error: " + posibleInstruccion + " no es una instruccion correcta");
		}
		
		return ins;
	}
	
	
}




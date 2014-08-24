package tp.pr5.mv.instrucciones.resto;

import java.io.IOException;

import tp.pr5.mv.Exceptions.ExceptionEmptyStack;
import tp.pr5.mv.Exceptions.ExceptionFaltanElementosEnPila;
import tp.pr5.mv.instrucciones.Instruction;
import tp.pr5.mv.virtualMachine.CPU;

/**
*	Instruccion de entrada de usuario, IN: es la operación simetrica a OUT. Lo que
*	hace es leer un caracter de la entrada y apilarlo (convertido a entero) en la pila.
*	Es una operacion, pues, que no tiene parametros en ensamblador ni necesita que
*	haya operandos en la pila. Si se ha llegado al nal de la entrada, se apila un -1. La
*	operacion podria fallar si hay algun error en la lectura (tipicamente, cuando se estan
*	simulando las pulsaciones de teclas leyendo de un fichero y se produce algun error al
*	leer de el).
* 	@author David Rico & Sergio Fuentes
*
*/
public class In extends RestSeq{

	@Override
	public Instruction parse(String[] str) {
		if ( (str.length == 1 ) && (str[0].equalsIgnoreCase("IN")) ){
			return new In();
		}
					
		else return null;
	}

	@Override
	protected void executeAux(CPU cpu) throws ExceptionEmptyStack,ExceptionFaltanElementosEnPila, IOException {
				
		int ValorApilar = cpu.getMetodoEntrada().read();
		cpu.push(ValorApilar);		
		
	}
	
	@Override
	public String toString() {
		return "IN";
	}
	

}

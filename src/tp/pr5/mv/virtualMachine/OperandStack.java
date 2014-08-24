package tp.pr5.mv.virtualMachine;

import java.util.ArrayList;

import tp.pr5.mv.Observers.Observable;
import tp.pr5.mv.Observers.StackObserver;

/**
 * Esta clase es la pila de operandos que se encarga de implementar todas las
 * operaciones que se llevan acabo dentro de la pila, como desapilar, apilar
 * etc...
 * 
 * @author Sergio Fuentes & David Rico
 * 
 */
public class OperandStack <T> implements Observable<StackObserver<T>>{

	private ArrayList<T> num ; // Object[] 
	
	private ArrayList<StackObserver<T>> observers;
	
		/**
	 * Es el constructor de la clase, inicializa el tamao, el array con
	 * tamao, y la cima como el unico elemento del array.
	 */
	public OperandStack() {
		
		this.num = new ArrayList<>(); 
		this.observers = new ArrayList<>(); 
	}

	/**
	 * Inserta en la pila el elemento introducido por parametro.
	 * @param n es el nuevo elemento que se introduce en la pila.
	 */
	public void push(T n) {
		this.num.add(n);
			
		for (StackObserver<T> o  : observers )
			o.onPush(n);						
	}
	


	
	/**
	 * Devuelve el elemento de la cima y lo quita de la pila.	
	 * @return numCima que es el elemento que esto en la cima
	 */
	public T pop() {
		
		T numPila = this.num.remove(this.num.size()-1);
		
		for (StackObserver<T> o  : observers )
			o.onPop(numPila);
		
		
			
		return numPila;
		
		//return this.num.remove(this.num.size()-1);
		
		
	}
	
	/**
	 * Devuelve el tamao de la pila 
	 * @return cima
	 */
	public int getSize()
	{
		return this.num.size();
	}
	
	/**
	 * Devuelve el elemento que esta en la cima
	 * @return int (elemento de la cima)
	 */
	public T getElementoCima(){
	
		return this.num.get(this.num.size()-1);
	}
	
	public T dameDatoPila(int pos)
	{
		return this.num.get(pos);
	}


	
	/**
	 * Metodo que se encarga de mostrar el estado de la pila.
	 * @return str. String con el estado.
	 */
		public String toString()
		{
			String str = " " ;
			if (this.num.size() == 0)
				str = "<vacia>";
			else 
			{
				for (int i = 0; i < this.num.size(); i++) 
					str = str + this.num.get(i) + " ";
			}
			return str;
		}
	
	/**
	 * Mtodo que pone el atributo de cima a cero
	 */
	public void reset()
	{
		this.num.clear();
		
		for (StackObserver<T> o  : observers )
			o.onStackReset();
		
	}

	@Override
	public void removeObserver(StackObserver<T> o) {
		observers.remove(o);
		
	}

	@Override
	public void addObserver(StackObserver<T> o) {
		observers.add(o);
		
	}
}

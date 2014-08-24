package tp.pr5.mv.virtualMachine;


import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import tp.pr5.mv.Observers.MemoryObserver;
import tp.pr5.mv.Observers.Observable;


/**
 * Clase que simula una memoria interna de un computador, con una capacidad modificable, y con os
 * metodos propios de una memoria.
 * @author Sergio Fuentes & David Rico
 *
 */
public class Memory <T> implements Observable<MemoryObserver<T>>{
	
	private TreeMap<Integer, T> memory ;
	
	private ArrayList<MemoryObserver<T>> observers;
	
	/**
	 * Constructor de la clase, inicializa la memoria con todo a null
	 */
	public Memory(){
		this.memory = new TreeMap<>();	
		this.observers = new ArrayList<>();
	}
	
	/**
	 * Metodo que comprueba si la memoria esta vacia.
	 * @return booleano si esta vacia true, si no false
	 */
	public boolean estaVacia(){
		return memory.isEmpty();
	}
	
	/**
	 * Mtodo que muestra por pantalla el estado de la memoria
	 * @return string con el estado.
	 */
	public String toString(){
		String str = " ";
		
		if (estaVacia()){
			str = "<vacia>";
		}
		Collection<Integer> claves = this.memory.keySet();
		for(Integer inter: claves){
			str = str + "[" + inter + "]:" + this.memory.get(inter) + " ";	
		}	
		
		return str;
	}
	/**
	 * Metodo que devuelve el valor almacenado en una posicion de memoria que entra como parametro
	 * @param pos . De memoria en la que se accede
	 * @return el valor de la posicion introducida (int)
	 */
	public T getValue(int pos)
	{
		if(this.memory.get(pos) == null)
			return null;
		else
			return  this.memory.get(pos);
	}
	/**
	 * Mtodo que guarda en la posicion de memoria introducida como parametro, el valor
	 * tambien introducido por parametro
	 * @param pos de memoria
	 * @param n  .Valor a guardar
	 */
	public void setValue(Integer pos,T n){
		this.memory.put(pos, n);
		
		for (MemoryObserver<T> o : observers)
			o.onWrite( pos, n);
	}
	
	
	/**
	 * Mtodo que pone todas las posiciones de memoria a null
	 */
	public void reset(){
		this.memory.clear();
		for (MemoryObserver<T> o : observers)
			o.onMemReset();
	}
	
	
	
	@Override
	public void addObserver(MemoryObserver<T> o) {
		observers.add(o);
		
	}
	@Override
	public void removeObserver(MemoryObserver<T> o) {
		observers.remove(o);
		
	}
	
	
	

}

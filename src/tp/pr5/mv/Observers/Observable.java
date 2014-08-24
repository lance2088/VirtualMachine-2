package tp.pr5.mv.Observers;

public interface Observable<T>{
	
	public void addObserver(T o);
	public void removeObserver(T o);
}

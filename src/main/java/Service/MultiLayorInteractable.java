package Service;

public interface MultiLayorInteractable<T> {
	
	
	// return value for DB valid version(needs DB ID)
	public T add(T item);
	
	public void update(T item);
	
	// replacement for delete, Would not want to delete anything in this context + recordkeeping
	public void deactivate(int ID);
	
	public T get(int ID);
	
	public String errorText();
	

}

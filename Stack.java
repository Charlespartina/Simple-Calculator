package project03_ZiyiTang;
/**
 * This interface specifically indicates methods a class should 
 * include if it implements the Stack<T> interface.
 * @param <T>
 */
public interface Stack<T> {
	
    /**
     * Add an object of type T to the top of the stack
     * @param object to be added to the stack
     */
	public void push(T item);
    
	/**
	 * Remove and return an object of type T from the top of the Stack
	 * @return If the stack doesn't have any object, return null. 
	 * Otherwise, return the object from the top.
	 */
    public T pop();
    
    /**
	 * Return the object of type T from the top of the Stack
	 * @return If the stack doesn't have any object, return null. 
	 * Otherwise, return the object from the top.
	 */
    public T peek();
    
    /**
     * Print the Stack in a specific pattern.
     * @return
     */
    public String toString();
    
    
}

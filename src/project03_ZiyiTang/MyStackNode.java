package project03_ZiyiTang;

/**
 * This class serves to create nodes for building up stack linked lists.
 * 
 * @author Ziyi Tang (Charles)
 * 
 * @param <T>
 */
public class MyStackNode<T> {
	T data;
	MyStackNode<T> next;

	/**
	 * Constructor of MyStackNode<T> class
	 * 
	 * @param data
	 */
	public MyStackNode(T data) {
		this.data = data;
		next = null;
	}

	/**
	 * Get the data from the node.
	 * 
	 * @return The data stored in the node.
	 */
	public T getData() {
		return data;
	}

	/**
	 * Set the data in the node.
	 * 
	 * @param newdata
	 */
	public void setData(T newdata) {
		data = newdata;
	}

	/**
	 * Get the reference of the next node.
	 * 
	 * @return The reference of the next node.
	 */
	public MyStackNode<T> getNext() {
		return next;
	}

	/**
	 * Set the "next" reference to a node.
	 * 
	 * @param next
	 */
	public void setNext(MyStackNode<T> next) {
		this.next = next;
	}
	
	/**
     * Returns the string representation of the node.
     * @return The string representation of the node.
     */
    public String toString(){
    	
    	return "" + data;
    }
}

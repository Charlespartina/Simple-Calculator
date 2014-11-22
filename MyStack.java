package project03_ZiyiTang;

/**
 * This class serves to create Stack objects to temporarily store data for
 * several computing methods of this project.
 * 
 * @author Ziyi Tang (Charles)
 * 
 * @param <T>
 */
public class MyStack<T> implements Stack<T> {

	MyStackNode<T> head;

	public MyStack() {
		head = null;
	}

	@Override
	/**
	 * Add an object of type T to the top of the stack
	 * @param object to be added to the stack
	 */
	public void push(T item) {
		if (head == null) {
			head = new MyStackNode<T>(item);
		} else {
			MyStackNode<T> newnode = new MyStackNode<T>(item);
			newnode.setNext(head);
			head = newnode;
		}

	}

	@Override
	/**
	 * Remove and return an object of type T from the top of the Stack
	 * @return If the stack doesn't have any object, return null. 
	 * Otherwise, return the object from the top.
	 */
	public T pop() {
		if (head == null) {
			return null;
		} else {
			T tmp = head.getData();
			head = head.getNext();
			return tmp;
		}
	}

	@Override
	/**
	 * Return the object of type T from the top of the Stack
	 * @return If the stack doesn't have any object, return null. 
	 * Otherwise, return the object from the top.
	 */
	public T peek() {
		if (head == null)
			return null;
		return head.getData();
	}

	/**
	 * Returns a string representation of the stack.
	 * 
	 * @return A string representation of the stack.
	 */
	public String toString() {
		String tmp = "";
		MyStackNode<T> cur = head;
		while (cur != null) {
			tmp = tmp + cur.getData();
			cur = cur.getNext();
		}
		return tmp;
	}

}

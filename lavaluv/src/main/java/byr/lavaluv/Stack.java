package byr.lavaluv;

public class Stack<T>{
	private Node<T> top;
	public Stack() {};
	public Stack(Node<T> top) {
		this.top = top;
	}
	public Node<T> getTop() {
		return this.top;
	}
	public boolean isEmpty() {
		return this.top.nextNode() == null ? true:false;
	}
	public boolean pushStack(T input) {
		if (this.top.insertNode(0, input)) {
			return true;
		}
		else {
			return false;
		}
	}
	public T pullStack() {
		Node<T> node = this.top;
		try {
			T t = node.nextNode().getData(); 
			node.deleteNode(0);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void traverse() {
		this.top.traverse();
	}
	public int size() {
		return this.top.size();
	}
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.pushStack(1);
		stack.pushStack(2);
		stack.traverse();
		System.out.println(stack.pullStack());
		stack.pullStack();
		System.out.println(stack.isEmpty());
		
	}

}

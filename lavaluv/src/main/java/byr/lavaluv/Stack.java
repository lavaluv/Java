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
	public boolean isEmpty(Stack<T> stack) {
		return stack.getTop().nextNode() == null ? true:false;
	}
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>(new Node<>());
		System.out.println(stack.isEmpty(stack));
	}

}

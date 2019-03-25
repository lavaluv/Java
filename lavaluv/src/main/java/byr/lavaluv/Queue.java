package byr.lavaluv;

public class Queue<T> {
	private Node<T> head;
	private Node<T> tail;
	public Queue() {
		this.head = new Node<>();
		this.tail = new Node<>();
	}
	public boolean isEmpty() {
		return this.head.nextNode() == null ? true:false;
	}
	public boolean inputQueue(T data) {
		try {
			Node<T> newNode = new Node<T>(data);
			newNode.setNextNode(this.tail.nextNode());
			this.tail.setNextNode(newNode);
			if (this.head.nextNode() == null) {
				this.head.setNextNode(newNode);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String args[])throws Exception{
		Queue<Integer> queue = new Queue<>();
		queue.inputQueue(1);
		queue.inputQueue(2);
		System.out.println(queue.isEmpty());
	}
}

package byr.lavaluv;

public class Queue<T> {
	private TwoWayNode<T> head;
	private TwoWayNode<T> tail;
	public Queue() {
		this.head = new TwoWayNode<>();
		this.tail = new TwoWayNode<>();
	}
	public boolean isEmpty() {
		return this.head.nextNode() == null ? true:false;
	}
	public boolean inputQueue(T data) {
		try {
			TwoWayNode<T> newNode = new TwoWayNode<T>(data);
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

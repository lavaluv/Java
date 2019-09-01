package byr.lavaluv.node;

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
			TwoWayNode<T> next = this.tail.nextNode();
			if (next != null) {
				newNode.setNextNode(next);
				next.setPreNode(newNode);
			}
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
	public T outputQueue() {
		try {
			if (!this.isEmpty()) {
				T t = this.head.nextNode().getData();
				if (this.head.size() > 1) {
					this.head.setNextNode(this.head.nextNode().preNode());
					this.head.nextNode().setNextNode(null);
				}
				else {
					this.head.setNextNode(null);
					this.tail.setNextNode(null);
				}
				return t;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void traverse() {
		this.tail.traverse();
	}
	public int size() {
		return this.tail.size();
	}
	public static void main(String args[])throws Exception{
		Queue<Integer> queue = new Queue<>();
		queue.inputQueue(1);
		queue.inputQueue(2);
		System.out.println(queue.outputQueue());
		queue.outputQueue();
		queue.traverse();
	}
}

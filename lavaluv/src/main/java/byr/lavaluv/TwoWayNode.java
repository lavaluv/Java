package byr.lavaluv;

public class TwoWayNode<T> extends Node<T> {
	private TwoWayNode<T> preNode;
	private TwoWayNode<T> next;
	public TwoWayNode() {};
	public TwoWayNode(T data) {
		super(data);
	}
	public TwoWayNode<T> preNode() {
		return this.preNode;
	}
	public TwoWayNode<T> nextNode() {
		return this.next;
	}
	public void setPreNode(TwoWayNode<T> node) {
		this.preNode = node;
	}
	public void setNextNode(TwoWayNode<T> node) {
		this.next = node;
	}
	public boolean addData(T data) {
		TwoWayNode<T> node = this;
		TwoWayNode<T> pre = this;
		try {
			while (node.nextNode() != null) {
				node = node.nextNode();
				pre = node;
			}
			TwoWayNode<T> newNode = new TwoWayNode<T>(data);
			newNode.setPreNode(pre);
			node.setNextNode(newNode);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String args[])throws Exception {
		TwoWayNode<Integer> twoWayNode = new TwoWayNode<>();
		twoWayNode.addData(1);
		twoWayNode.addData(2);
		System.out.println(twoWayNode.nextNode().nextNode().preNode().getData());
	}
}

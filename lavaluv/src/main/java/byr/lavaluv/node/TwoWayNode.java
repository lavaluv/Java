package byr.lavaluv.node;

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
	public boolean insertNode(int i,T data) {
		TwoWayNode<T> node = this;
		TwoWayNode<T> pre = this;
		int j = -1;
		if (node.nextNode() == null && i == 0) {
			node.addData(data);
			return true;
		}
		else {
			while (node.nextNode() != null) {
				j++;
				if(j == i) {
					TwoWayNode<T> temp = node.nextNode();
					node.setNextNode(new TwoWayNode<T>(data));
					temp.setPreNode(node.nextNode());
					node.nextNode().setNextNode(temp);
					node.nextNode().setPreNode(pre);
					return true;
				}
				node = node.nextNode();
				pre = node;
			}
		}
		return false;
	}
	public boolean deleteNode(int i) {
		TwoWayNode<T> node = this;
		TwoWayNode<T> pre;
		int j = -1;
		while (node.nextNode() != null) {
			pre = node;
			node = node.nextNode();
			j++;
			if(j == i) {
				pre.setNextNode(node.nextNode());
				node.nextNode().setPreNode(pre);
				node = null;
				return true;
			}
		}
		return false;
	}
	public static void main(String args[])throws Exception {
		TwoWayNode<Integer> twoWayNode = new TwoWayNode<>();
		twoWayNode.addData(1);
		twoWayNode.addData(2);
		twoWayNode.insertNode(0, 3);
		twoWayNode.deleteNode(0);
		System.out.println(twoWayNode.nextNode().nextNode().preNode().getData());
		twoWayNode.traverse();
	}
}

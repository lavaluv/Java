package byr.lavaluv;

public class Node<T> {
	private  T data;
	private Node<T> next;
	public Node() {};
	public Node(T data){
		this.data = data;
	}
	public T getData() {
		return this.data;
	}
	public Node<T> nextNode() {
		return this.next;
	}
	public void setData(T data) {
		this.data = data;
	}
	//尾部增加节点
	public boolean addData(T data) {
		Node<T> node = this;
		try {
			while (node.nextNode() != null) {
				node = node.nextNode();
			}
			node.setNextNode(new Node<T>(data));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public void setNextNode(Node<T> node) {
		this.next = node;
	}
	//在指定位置插入节点
	public boolean insertNode(Node<T> head,int i,T data) {
		Node<T> node = this;
		int j = -1;
		if (node.nextNode() == null && i == 0) {
			node.addData(data);
			return true;
		}
		else {
			while (node.nextNode() != null) {
				j++;
				if(j == i) {
					Node<T> temp = node.nextNode();
					node.setNextNode(new Node<>(data));
					node.nextNode().setNextNode(temp);
					return true;
				}
				node = node.nextNode();
			}
		}
		return false;
	}
	//删除指定位置节点
	public boolean deleteNode(Node<T> head,int i) {
		Node<T> node = this;
		Node<T> pre;
		int j = -1;
		while (node.nextNode() != null) {
			pre = node;
			node = node.nextNode();
			j++;
			if(j == i) {
				pre.setNextNode(node.nextNode());
				node = null;
				return true;
			}
		}
		return false;
	}
	//遍历节点
	public void traverse() {
		Node<T> node = this;
		while (node.nextNode() != null) {
			node = node.nextNode();
			System.out.println(node.getData());
		}
	}
	public static void main(String args[])throws Exception {
		Node<Integer> head = new Node<Integer>();
		head.addData(1);
		head.addData(2);
		head.insertNode(head,0,3);
		head.traverse();
		head.deleteNode(head, 0);
		head.traverse();
	}
}

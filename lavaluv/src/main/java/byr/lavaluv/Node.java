package byr.lavaluv;

public class Node<T> {
	private  T data;
	private Node<T> next;
	public Node() {};
	public Node(T data){
		this.data = data;
	}
	public Node(T data,Node<T> next) {
		this.data = data;
		this.next = next;
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
	public void addData(T data) {
		Node<T> node = this;
		while (node.nextNode() != null) {
			node = node.nextNode();
		}
		node.setNextNode(new Node<T>(data));
	}
	public void setNextNode(Node<T> node) {
		this.next = node;
	}
	//在指定位置插入节点
	public void insertNode(Node<T> head,int i,T data) {
		Node<T> node = this;
		int j = -1;
		while (node.nextNode() != null) {
			node = node.nextNode();
			j++;
			if(j == i) {
				Node<T> temp = node.nextNode();
				node.setNextNode(new Node<>(data));
				node.nextNode().setNextNode(temp);
				break;
			}
		}
	}
	//删除指定位置节点
	public void deleteNode(Node<T> head,int i) {
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
				break;
			}
		}
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
		head.insertNode(head,1,3);
		head.traverse();
		head.deleteNode(head, 1);
		head.traverse();
	}
}

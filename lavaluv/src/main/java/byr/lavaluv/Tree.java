package byr.lavaluv;

public class Tree<T> {
	private T dataT;
	private Node<Tree<T>> treeList;
	public Tree() {
		this.treeList = new Node<Tree<T>>();
	}
	public Tree(T data) {
		this.dataT = data;
		this.treeList = new Node<Tree<T>>();
	}
	public void setData(T data) {
		this.dataT = data;
	}
	public T getData() {
		return this.dataT;
	}
	public boolean addBranch(Tree<T> tree,T data) {
		try {
			if (tree.treeList.nextNode() != null) {
				tree.treeList.addData(new Tree<T>(data));
				return true;
			}
			else {
				tree.treeList.setNextNode(new Node<Tree<T>>());
				tree.treeList.nextNode().setData(new Tree<>(data));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Tree<T> getSubTree(int index){
		try {
			return this.treeList.findDataByIndex(index);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public int getDepth(Tree<T> tree,int root) {
		Node<Tree<T>> node = tree.treeList.nextNode();
		int max = root;
		while (node != null) {
			if (node.getData() != null) {
				root ++;
				int subDepth = getDepth(node.getData(),root);
				if (max < subDepth) {
					max = subDepth;
				}
				root--;
			}
			node = node.nextNode();
		}
		return max;
	}
	public void traverseBylevel(Tree<T> tree) {
		System.out.println("Level :"+tree.getData());
		Node<Tree<T>> node = this.treeList.nextNode();
		while (node != null) {
			if (node.getData() != null) {
				System.out.println(node.getData().getData());
			}
			node = node.nextNode();
		}
		node = this.treeList.nextNode();
		while (node != null) {
			if (node.getData() != null) {
				System.out.println("ok");	
			}
			node = node.nextNode();
		}
	}
	public static void main(String args[])throws Exception{
		Tree<Integer> teTree = new Tree<>(1);
		teTree.addBranch(teTree, 2);
		teTree.addBranch(teTree, 3);
		teTree.addBranch(teTree.getSubTree(1), 4);
		System.out.println(teTree.getDepth(teTree,1));
		teTree.traverseBylevel(teTree);
	}
}

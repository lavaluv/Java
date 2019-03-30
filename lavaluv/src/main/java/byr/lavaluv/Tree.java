package byr.lavaluv;

public class Tree<T> {
	private T dataT;
	private Node<Tree<T>> treeList;
	public Tree() {};
	public Tree(T data) {
		this.dataT = data;
	}
	public void setData(T data) {
		this.dataT = data;
	}
	public boolean addBranch(Tree<T> tree,T data) {
		try {
			if (tree.treeList.nextNode() != null) {
				tree.treeList.addData(new Tree<T>(data));
				return true;
			}
			else {
				tree.treeList.setNextNode(new Node<Tree<T>>());
				tree.treeList.addData(new Tree<T>(data));
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
	public void traverseBylevel(Tree<T> tree) {
		System.out.println("Level :"+tree.dataT);
		while (this.treeList.nextNode() != null) {
			
		}
	}
	public void main(String args[])throws Exception{
		Tree<Integer> teTree = new Tree<>(1);
		teTree.addBranch(teTree, 2);
		teTree.addBranch(teTree, 3);
	}
}

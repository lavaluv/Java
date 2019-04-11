package byr.lavaluv;

import java.util.ArrayList;
import java.util.Iterator;

public class BinaryTree<T>{
	private T dataT;
	private BinaryTree<T> right;
	private BinaryTree<T> left;
	public BinaryTree(){};
	public BinaryTree(T daT) {
		this.dataT = daT;
	}
	public T getData() {
		return this.dataT;
	}
	public void setData(T daT) {
		this.dataT = daT;
	}
	public BinaryTree<T> getLeftNode(){
		return this.left;
	}
	public void setLeftNode(T daT) {
		this.left = new BinaryTree<T>(daT);
	}
	public BinaryTree<T> getRightNode(){
		return this.right;
	}
	public void setRightNode(T daT){
		this.right = new BinaryTree<T>(daT);
	}
	@SuppressWarnings("unchecked")
	public boolean addBranch(T daT) {
		try {
			ArrayList<BinaryTree<T>> trees = new ArrayList<BinaryTree<T>>();
			ArrayList<BinaryTree<T>> temp = new ArrayList<BinaryTree<T>>();
			trees.add(this);
			Iterator<BinaryTree<T>> iterator;
			while (!trees.isEmpty()) {
				iterator = trees.iterator();
				while (iterator.hasNext()) {
					BinaryTree<T> binaryTree = (BinaryTree<T>) iterator.next();
					if (binaryTree.left == null) {
						binaryTree.left = new BinaryTree<T>(daT);
						return true;
					}
					else if (binaryTree.right == null) {
						binaryTree.right = new BinaryTree<T>(daT);
						return true;
					}
					else {
						temp.add(binaryTree.left);
						temp.add(binaryTree.right);
					}
				}
				trees.clear();
				trees = (ArrayList<BinaryTree<T>>) temp.clone();
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean addBranchByIndex(T daT,int index) {
		try {
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public void traverseByDepth() {
		BinaryTree<T> tree = this;
		System.out.print("("+tree.dataT);
		if (tree.left != null) {
			tree.left.traverseByDepth();
		}
		if(tree.right != null){
			tree.right.traverseByDepth();
		}
		System.out.print(")");
	}
	public int size() {
		int size = 1;
		BinaryTree<T> tree = this;
		if (tree.left != null) {
			size += tree.left.size();
		}
		if(tree.right != null){
			size += tree.right.size();
		}
		return size;
	}
	public int degree() {
		return (this.left != null?1:0) + (this.right != null?1:0);
	}
	public static void main(String args[])throws Exception{
		BinaryTree<Integer> binaryTree = new BinaryTree<Integer>(1);
		binaryTree.addBranch(2);
		binaryTree.addBranch(3);
		binaryTree.addBranch(4);
		binaryTree.traverseByDepth();
	}
}

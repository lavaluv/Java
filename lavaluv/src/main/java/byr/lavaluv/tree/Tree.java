package byr.lavaluv.tree;

import java.util.ArrayList;
import java.util.Iterator;

import byr.lavaluv.node.Node;

public class Tree<T> {
	private ArrayList<T> dataT;
	private Node<Tree<T>> treeList;
	public Tree() {
		this.dataT = new ArrayList<T>();
		this.treeList = new Node<Tree<T>>();
	}
	public Tree(T data) {
		this.dataT = new ArrayList<T>();
		this.dataT.add(data);
		this.treeList = new Node<Tree<T>>();
	}
	public Tree(ArrayList<T> list) {
		this.dataT = list;
		this.treeList = new Node<Tree<T>>();
	}
	public void addData(T data) {
		this.dataT.add(data);
	}
	public ArrayList<T> getData() {
		return this.dataT;
	}
	public void setTreelist(Node<Tree<T>> node) {
		this.treeList = node;
	}
	public Node<Tree<T>> getTreeList(){
		return this.treeList;
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
	public boolean addTree(Tree<T> tree) {
		try {
			return this.treeList.addData(tree);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Tree<T> getSubTree(int index){
		try {
			return this.treeList.findDataByIndex(index);
		} catch (Exception e) {
			throw new IndexOutOfBoundsException("getSubTree out of index");
		}
	}
	public boolean deleteTree(int index) {
		try {
			return this.treeList.deleteNode(index);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public int getDepth() {
		int depth = 0;
		ArrayList<Tree<T>> tList = new ArrayList<Tree<T>>();
		ArrayList<Tree<T>> tempArrayList = new ArrayList<Tree<T>>();
		tList.add(this);
		Iterator<Tree<T>> iterator;
		while (!tList.isEmpty()) {
			depth++;
			iterator = tList.iterator();
			while (iterator.hasNext()) {
				Tree<T> tree = iterator.next();
				Node<Tree<T>> node = tree.treeList.nextNode();
				while (node != null) {
					tempArrayList.add(node.getData());
					node = node.nextNode();
				}
			}
			tList.clear();
			tList = (ArrayList<Tree<T>>) tempArrayList.clone();
			tempArrayList.clear();
		}
		return depth;
	}
	@SuppressWarnings("unchecked")
	public void traverseBylevel() {
		ArrayList<Tree<T>> tList = new ArrayList<Tree<T>>();
		ArrayList<Tree<T>> tempArrayList = new ArrayList<Tree<T>>();
		tList.add(this);
		Iterator<Tree<T>> iterator;
		while (!tList.isEmpty()) {
			iterator = tList.iterator();
			System.out.print("(");
			while (iterator.hasNext()) {
				Tree<T> tree = iterator.next();
				Node<Tree<T>> node = tree.treeList.nextNode();
				System.out.print(tree.getData());
				while (node != null) {
					tempArrayList.add(node.getData());
					node = node.nextNode();
				}
			}
			System.out.print(")");
			tList.clear();
			tList = (ArrayList<Tree<T>>) tempArrayList.clone();
			tempArrayList.clear();
		}
	}
	//先序遍历
	public void traverseByDepth() {
		System.out.print("("+this.getData());
		Node<Tree<T>> node = this.treeList.nextNode();
		while (node != null) {
			node.getData().traverseByDepth();
			node = node.nextNode();
		}
		System.out.print(")");
	}
	public int size() {
		int size = 1;
		Node<Tree<T>> node = this.treeList.nextNode();
		while (node != null) {
			size += node.getData().size();
			node = node.nextNode();
		}
		return size;
	}
	public int degree() {
		return this.treeList.size();
	}
	public static void main(String args[])throws Exception{
		Tree<Integer> teTree = new Tree<>(1);
		teTree.addBranch(teTree, 2);
		teTree.addBranch(teTree, 3);
		teTree.addBranch(teTree.getSubTree(0), 4);
		//teTree.deleteTree(1);
		Tree<Integer> newTree = new Tree<Integer>(5);
		newTree.addBranch(newTree, 6);
		teTree.addTree(newTree);
		System.out.println(teTree.degree());
		teTree.traverseByDepth();
	}
}

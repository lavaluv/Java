package byr.lavaluv.tree;
/*
 * 红黑树是二叉搜索树。
	根节点是黑色。
	每个叶子节点都是黑色的空节点（NIL节点）。
	每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)
	从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点(每一条树链上的黑色节点数量（称之为“黑高”）必须相等)。
 * 
 */
public class RedBlackTree extends SearchTree {
	private RedBlackTree rightTree;
	private RedBlackTree leftTree;
	private boolean color;
	public RedBlackTree(){}
	public RedBlackTree(int daT,boolean color) {
		super(daT);
		this.color = color;
	}
	public RedBlackTree getRightTree() {
		return this.rightTree;
	}
	public void setRightTree(RedBlackTree tree) {
		this.rightTree = tree;
	}
	public RedBlackTree getLeftTree() {
		return this.leftTree;
	}
	public void setLeftTree(RedBlackTree tree) {
		this.leftTree = tree;
	}
	public boolean getColor() {
		return this.color;
	}
	public void setRed() {
		this.color = true;
	}
	public void setBlack() {
		this.color = false;
	}
	public void colorFlip() {
		this.color = !this.color;
		this.leftTree.color = !this.leftTree.color;
		this.rightTree.color = !this.rightTree.color;
	}
	public RedBlackTree rotateLeft() {
		RedBlackTree root = this;
		RedBlackTree right = root.getRightTree();
		root.setRightTree(right.getLeftTree());
		right.setLeftTree(root);
		root.setRed();
		right.setBlack();
		return right;
	}
	public RedBlackTree rotateRight() {
		RedBlackTree root = this;
		RedBlackTree left = root.getLeftTree();
		root.setLeftTree(left.getRightTree());
		left.setRightTree(root);
		root.setRed();
		left.setBlack();
		return left;
	}
	public RedBlackTree insert(RedBlackTree root,int data) {
		if (root == null) {
			return new RedBlackTree(data, true);
		}
		if (root.leftTree != null && root.rightTree != null) {
			if (root.leftTree.getColor() && root.rightTree.getColor()) {
				root.colorFlip();
			}
		}
		if (root.getData() > data) {
			root.setLeftTree(insert(root.leftTree, data));
		}
		else if(root.getData() < data){
			root.setRightTree(insert(root.rightTree, data));
		}
		if (root.rightTree != null) {
			if (root.getRightTree().getColor()) {
				root = root.rotateLeft();
				System.out.println("left");
			}
		}
		if (root.leftTree != null) {
			if (root.leftTree.leftTree != null) {
				if (root.leftTree.getColor() && root.leftTree.getLeftTree().getColor()) {
					root = root.rotateRight();
					System.out.println("right");
				}
			}
		}
		return root;
	}
	public void traverseByDepth() {
		RedBlackTree tree = this;
		System.out.print("("+tree.getData()+tree.getColor());
		if (tree.getLeftTree() != null) {
			tree.getLeftTree().traverseByDepth();
		}
		if(tree.getRightTree() != null){
			tree.getRightTree().traverseByDepth();
		}
		System.out.print(")");
	}
	public static void main(String[] args)throws Exception{
		RedBlackTree tree = new RedBlackTree(0,false);
		tree = tree.insert(tree, -1);
		tree = tree.insert(tree, 1);
		tree.traverseByDepth();
		tree = tree.insert(tree, 2);
		tree.setBlack();
		tree.traverseByDepth();
	}
}

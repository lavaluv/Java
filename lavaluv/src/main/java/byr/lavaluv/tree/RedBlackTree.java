package byr.lavaluv.tree;

public class RedBlackTree extends SearchTree {
	private RedBlackTree rightTree;
	private RedBlackTree leftTree;
	private boolean color;
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
		this.color = true;
		this.leftTree.color = false;
		this.rightTree.color = false;
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
	public RedBlackTree add2Node(int data) {
		try {
			if (data < this.getData()) {
				this.setLeftTree(new RedBlackTree(data, true));
				return this;
			}
			else if (data > this.getData()) {
				this.setRightTree(new RedBlackTree(data,true));
				return this.rotateLeft();
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public RedBlackTree add3Node(int data) {
		try {
			RedBlackTree left = this.getLeftTree();
			if (data < this.getData()) {
				if (data < left.getData()) {
					left.setLeftTree(new RedBlackTree(data,true));
					RedBlackTree root = this.rotateRight();
					root.colorFlip();
					return root;
				}
				else if (data > left.getData()) {
					left.setRightTree(new RedBlackTree(data,true));
					left = left.rotateLeft();
					RedBlackTree root = this.rotateRight();
					root.colorFlip();
					return root;
				}
			}
			else if (data > this.getData()) {
				this.setRightTree(new RedBlackTree(data,true));
				this.colorFlip();
				return this;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

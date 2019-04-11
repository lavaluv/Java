package byr.lavaluv;

public class SearchTree extends BinaryTree<Integer> {
	public SearchTree(int daT) {
		super(daT);
	}
	public boolean addBranch(int daT) {
		try {
			BinaryTree<Integer> tree = this;
			while (tree != null) {
				if (tree.getData() > daT) {
					if (tree.getLeftNode() == null) {
						tree.setLeftNode(daT);
						return true;
					}
					else {
						tree = tree.getLeftNode();
					}
				}
				else if (tree.getData() < daT) {
					if (tree.getRightNode() == null) {
						tree.setRightNode(daT);
						return true;
					}
					else {
						tree = tree.getRightNode();
					}
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String args[])throws Exception{
		SearchTree searchTree = new SearchTree(5);
		searchTree.addBranch(3);
		searchTree.addBranch(7);
		searchTree.addBranch(4);
		searchTree.traverseByDepth();
		System.out.println(searchTree.degree());
	}
}

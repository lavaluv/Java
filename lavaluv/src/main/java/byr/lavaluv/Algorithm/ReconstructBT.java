package byr.lavaluv.Algorithm;

import org.junit.jupiter.api.Test;

public class ReconstructBT {
	@Test
	public void test() {
		int[] pre = {1,2,4,7,3,5,6,8};
		int[] in = {4,7,2,1,5,3,8,6};
		TreeNode treeNode = reConstructBinaryTree(pre, in);
		treeNode.traverseByDepth();
	}
	 public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
		  	public void traverseByDepth() {
				TreeNode tree = this;
				System.out.print("("+tree.val);
				if (tree.left != null) {
					tree.left.traverseByDepth();
				}
				if(tree.right != null){
					tree.right.traverseByDepth();
				}
				System.out.print(")");
			}
		  }
	    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
	    	if (in.length == 0) {
				return null;
			}
	        TreeNode tree = new TreeNode(pre[0]);
	        int index = getIndex(pre[0], in);
	        int[] left_pre = new int[index];
	        int[] left_in = new int[index];
	        for (int i = 0; i < index; i++) {
				left_pre[i] = pre[i+1];
				left_in[i] = in[i];
			}
	        int[] right_pre = new int[in.length - index - 1];
	        int[] right_in = new int[in.length - index - 1];
	        for (int i = 0; i < in.length - index - 1; i++) {
				right_pre[i] = pre[i+index+1];
				right_in[i] = in[i+index+1];
			}
	        tree.left = reConstructBinaryTree(left_pre, left_in);
	        tree.right = reConstructBinaryTree(right_pre, right_in);
	        return tree;
	    }
	    public int getIndex(int p,int[] in){
	        for (int i = 0; i < in.length; i++) {
				if (in[i] == p) {
					return i;
				}
			}
	        return -1;
	    }
}

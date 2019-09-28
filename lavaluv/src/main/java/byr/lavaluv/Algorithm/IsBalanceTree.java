package byr.lavaluv.Algorithm;

import org.junit.jupiter.api.Test;

import byr.lavaluv.tree.BinaryTree;

public class IsBalanceTree {
	@Test
	public void test() {
		Integer [] i = {1,2,3,4,5,6,7};
		BinaryTree<Integer> tree = new BinaryTree<Integer>(i);
		System.out.println(test(tree));
	}
	
    public int test(BinaryTree<Integer> t){
        if(t==null){
            return 0;
        }
        int left = test(t.getLeftNode());
        if(left < 0){
            return -1;
        }
        int right = test(t.getRightNode());
        if(right < 0){
            return -1;
        }
        return Math.abs(left - right) > 1?-1:1+Math.max(left,right);
    }
}

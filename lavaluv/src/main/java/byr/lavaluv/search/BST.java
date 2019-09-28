package byr.lavaluv.search;

import byr.lavaluv.tree.SearchTree;

/*
 * 
 */
public class BST {
	static public boolean search(int[] arr,int v) {
		SearchTree searchTree = new SearchTree(arr);
		searchTree.traverseByDepthMiddle();
		return searchTree.search(v);
	}
}

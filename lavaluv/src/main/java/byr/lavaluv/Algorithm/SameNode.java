package byr.lavaluv.Algorithm;

import org.junit.jupiter.api.Test;

public class SameNode {
	@Test
	public void test() {
		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		ListNode node2 = new ListNode(2);
		node2.next = node;
		ListNode rListNode = FindFirstCommonNode(node, node2);
		while (rListNode!=null) {
			System.out.println(rListNode.val);
			rListNode = rListNode.next;
		}
	}
	static public class ListNode {
	    public int val;
	    public ListNode next = null;

	    public ListNode(int val) {
	        this.val = val;
	    }
	}
	    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
	        ListNode t1 = pHead1,t2 = pHead2;
	         while(t1!=t2){
	             t1 = t1==null?t1=pHead2:t1.next;
	             t2 = t2==null?t2=pHead1:t2.next;
	         }
	        return t1;
	    }
}

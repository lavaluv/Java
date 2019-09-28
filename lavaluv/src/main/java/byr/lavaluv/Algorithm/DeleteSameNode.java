package byr.lavaluv.Algorithm;

import org.junit.jupiter.api.Test;

import byr.lavaluv.node.Node;

public class DeleteSameNode {
	@Test
	
    public <T> Node<T> deleteDuplication(Node<T> pHead)
    {
        Node<T> pre = null;
        Node<T> node = null;
        Node<T> temp = pHead;
        boolean delete = false;
        while(temp!=null || delete){
            if(node == null){
                node = temp;
                temp = temp.nextNode();
            }
            else if(temp!=null && node.getData() == temp.getData()){
                delete = true;
                temp = temp.nextNode();
            }
            else if(delete){
                if(pre == null){
                    pHead = temp;
                    node = null;
                    delete = false;
                }
                else{
                    pre.setNextNode(temp);
                    node = null;
                    delete = false;
                }
            }
            else{
                pre = node;
                node = temp;
                temp = temp.nextNode();
            }
        }
        return pHead;
    }
}

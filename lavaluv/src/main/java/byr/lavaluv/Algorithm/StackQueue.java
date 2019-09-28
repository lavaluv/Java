package byr.lavaluv.Algorithm;

import java.util.Stack;

public class StackQueue {
    Stack<Integer> left = new Stack<Integer>();
    Stack<Integer> right = new Stack<Integer>();
    
    public void push(int node) {
        while(!right.isEmpty()){
            left.push(right.pop());
        }
        left.push(node);
    }
    
    public int pop() {
        while(!left.isEmpty()){
            right.push(left.pop());
        }
        return right.pop();
    }
}

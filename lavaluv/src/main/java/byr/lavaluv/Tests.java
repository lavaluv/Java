package byr.lavaluv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;

import byr.lavaluv.Algorithm.ReconstructBT.TreeNode;
import byr.lavaluv.Algorithm.SameNode.ListNode;
import byr.lavaluv.tree.BinaryTree;
import byr.lavaluv.tree.SearchTree;

public class Tests {
	@Test
	public void main() throws IOException {
		int [] in = {};
		int [] ins = {1,1,-1,-1,3};
		String s = "-";
		System.out.println(threeSumClosest(ins, -1));
	}
    public int threeSumClosest(int[] nums, int target) {
        int sum = 0;
        int out = Integer.MAX_VALUE-Math.abs(target);
        for (int i = 0; i < nums.length-2; i++) {
        	sum+= nums[i];
        	for (int j = i+1; j < nums.length-1; j++) {
				sum+=nums[j];
				for (int j2 = j+1; j2 < nums.length; j2++) {
					sum+=nums[j2];
					out = Math.abs(out - target) > Math.abs(sum -target)?sum:out;
					sum-=nums[j2];
				}
				sum-=nums[j];
			}
        	sum-=nums[i];
		}
        return out;
    }
    public boolean isPalindrome(int x) {
        if (x < 0) {
			return false;
		}
        else {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = x; i > 0; i = i/10) {
				list.add(i%10);
			}
			int [] origin = new int[list.size()];
			int [] reverse = new int[list.size()];
			int [][] dp = new int[list.size()][list.size()];
			for (int i = list.size() -1; i >= 0; i--) {
				origin[i] = list.get(i);
				reverse[list.size()-1 - i] = list.get(i);
			}
			for (int i = 0; i < dp.length; i++) {
				if (origin[i] != reverse[i]) {
					return false;
				}
			}
			return true;
		}
    }
	public int firstStringToInt(String str) {
		String fomatString = str.trim();
		String [] fomatsStrings = fomatString.split("");
		boolean negative = false;
		int index = 0;
		if (fomatsStrings[0].matches("\\d") || fomatsStrings[0].matches("\\+") || fomatsStrings[0].matches("-")) {
			if (fomatsStrings[0].equals("-")) {
				negative = true;
			}
			index++;
			for (int i = 1; i < fomatString.length(); i++) {
				if (fomatsStrings[i].matches("\\d")) {
					index++;
				}
				else {
					break;
				}
			}
			try {
				int v = Integer.valueOf(fomatString.substring(0, index));
				return v;
			} catch (NumberFormatException e) {
				if (fomatsStrings[0].matches("\\+|-") && index == 1) {
					return 0;
				}
				return negative?Integer.MIN_VALUE:Integer.MAX_VALUE;
			}
		}
		else {
			return 0;
		}
	}
	public int reverseInt(int x) {
		String string = String.valueOf(x);
		boolean neative = false;
		if (string.charAt(0) == '-') {
			neative = true;
			string =string.substring(1);
		}
		for (int i = string.length()-1; i >= 0 && string.charAt(i) == '0'; i--) {
			string = string.substring(0, string.length()-1);
		}
		StringBuilder builder = new StringBuilder(string);
		String outString = builder.reverse().toString();
		try {
			int v = Integer.valueOf(outString);
			return neative?-v:v;
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	public String printZ(String s,int numRows) {
		if (numRows == 1) {
			return s;
		}
		String[] strings = s.split("");
		StringBuilder [] builders = new StringBuilder[numRows];
		for (int i = 0; i < numRows; i++) {
			builders[i] = new StringBuilder();
		}
		boolean reverse = false;
		int index = 0;
		for (int i = 0; i < s.length(); i++) {
			builders[index].append(strings[i]);
			if (i!=0 && i%(numRows-1) == 0) {
				reverse = !reverse;
			}
			if (reverse) {
				index--;
			}
			else {
				index++;
			}
		}
		StringBuilder outBuilder = new StringBuilder();
		for (StringBuilder stringBuilder : builders) {
			outBuilder.append(stringBuilder);
		}
		return outBuilder.toString();
	}
	public int mutiSum(int [] in,int times) {
		Arrays.sort(in);
		int l = 0,r = times*2 - 1,total = 0;
		for (int i = 0; i < times; i++) {
			total += in[l] * in[r];
			l++;
			r--;
		}
		return total;
	}
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	boolean is = (nums1.length + nums2.length)%2 == 0?false:true;
    	int mid = (nums1.length + nums2.length)/2;
    	int min = 0;
    	int one = 0,two = 0,temp1 = 0,temp2 = 0;
    	if (is) {
			mid++;
		}
    	while (one < nums1.length && two < nums2.length) {
    		System.out.println(one+" "+two+" "+mid+" "+temp1+" "+temp2);
    		if (mid == 1) {
				if (is) {
					return nums1[one]<=nums2[two]?nums1[one]:nums2[two];
				}
				else {
					if (nums1[one] >= nums2[two]) {
						min = nums2[two];
						two++;
					}
					else {
						min = nums1[one];
						one++;
					}
					if (one < nums1.length && two < nums2.length) {
						return nums1[one] >= nums2[two]?(nums2[two]+min)/2.0:(nums1[one]+min)/2.0;
					}
					else if(one == nums1.length){
						return (min + nums2[two])/2.0;
					}
					else {
						return (min + nums1[one])/2.0;
					}
				}
			}
			if (one + mid/2 >= nums1.length) {
				temp1 = nums1.length - 1;
			}
			else {
				temp1 = mid/2 + one - 1;
			}
			if(two + mid/2 >= nums2.length){
				temp2 = nums2.length - 1;
			}
			else {
				temp2 = mid/2 + two - 1;
			}
			if (nums1[temp1] >= nums2[temp2]) {
				mid = mid - (temp2 - two + 1);
				two = temp2 + 1;
			}
			else {
				mid = mid - (temp1 - one + 1);
				one = temp1 + 1;
			}
		}
    	if (nums1.length == one) {
			if (is) {
				return nums2[two+mid - 1];
			}
			else {
				return (nums2[two+mid - 1]+nums2[two+mid])/2.0;
			}
		}
    	else if(nums2.length == two){
			if (is) {
				return nums1[one+mid - 1];
			}
			else {
				return (nums1[one+mid - 1]+nums1[one+mid])/2.0;
			}
		}
        return 0 ;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int up = 0;
        ListNode node = null;
        while(l1 != null || l2 != null){
            if(l1 == null){
                node.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            else if(l2 == null){
                node.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            else{
                if(l1.val + l2.val + up >= 10){
                    if(node == null){
                        node = new ListNode(l1.val + l2.val + up - 10);
                    }
                    else{
                        node.next = new ListNode(l1.val + l2.val + up - 10);
                    }
                    up = 1;
                }
                else{
                    if(node == null){
                        node = new ListNode(l1.val + l2.val + up);
                    }
                    else{
                        node.next = new ListNode(l1.val + l2.val + up);
                    }
                    up = 0;
                }
                l1 = l1.next;
                l2 = l2.next;
            }
        }
        return node;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        ArrayList<List<Integer>> re = new ArrayList<List<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>(3);
        HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        for(int i = 0;i<nums.length - 2;i++){
        	System.out.println(nums[i]);
            if(nums[i] > 0){
                continue;
            }
            else{
                int l = i+1, r = nums.length - 1;
                while(l < r){
                    if(nums[i] + nums[l] + nums[r] > 0){
                        r--;
                    }
                    else if(nums[i] + nums[l] + nums[r] < 0){
                        l++;
                    }
                    else{
                        list = new ArrayList<Integer>(3);
                        list.add(nums[i]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        System.out.println(list);
                        set.add(list);
                        l++;
                    }
                }
            }
        }
        re.addAll(set);
        return re;
    }
	public ArrayList<Integer> longestSortArray(int [] in) {
		int[] dp = new int[in.length];
		ArrayList<Integer> out = new ArrayList<Integer>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < in.length; i++) {
			temp.clear();
			for (int j = 0; j < i; j++) {
				if (in[j] < in[i] && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
					temp.add(in[j]);
				}
				if (j+1 == i) {
					temp.add(in[i]);
					if (temp.size() > out.size()) {
						out.clear();
						out.addAll(temp);
					}
				}
			}
		}
		return out;
	}
    public boolean isNumeric(char[] str) {
        String string = new String(str);
        if (string.contains("e") || string.contains("E")) {
        	String[] in;
			if (string.contains("e")) {
				in = string.split("e");
			}
			else {
				in = string.split("E");
			}
			if (in.length != 2) {
				return false;
			}
			System.out.println(in[0]+" "+in[1]);
			return isNum(in[0],false) && isNum(in[1],true);
		}
        else {
			return isNum(string,false);
		}
    }
    public boolean isNum(String string,boolean isE) {
    	if (isE && string.contains(".")) {
			return false;
		}
		if (string.charAt(0) == '+' || string.charAt(0) == '-') {
			string = string.substring(1);
		}
		System.out.println(string);
		if (string.charAt(0) == '+' || string.charAt(0) == '-') {
			return false;
		}
		if (string.length() != 1 && string.charAt(0) == '0') {
			return false;
		}
		try {
			float out = Float.valueOf(string);
			System.out.println(out);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
    }
    public boolean match(char[] str, char[] pattern)
    {
        int i = 0, j = 0;
        while (i<str.length && j<pattern.length) {
			if (j + 1 < pattern.length && pattern[j+1] == "*".charAt(0)) {
				if (str[i] != pattern[j] && pattern[j] != ".".charAt(0)) {
					j = j+2;
				}
				else if(str[i] == pattern[j]) {
					i++;
				}
				else {
					if (j+2 == pattern.length) {
						i++;
						continue;
					}
					else {
						if(pattern[j+2] != str[i]) {
							while (i < str.length && str[i] == pattern[j]) {
								i++;
							}
							j = j + 2;
						}
                        else {
							while (i < str.length && str[i] == pattern[j]) {
								i++;
							}
							j = j + 3;
						}
					}
				}
			}
			else {
				if (pattern[j] == "*".charAt(0)) {
					j++;
				}
				else if (str[i] != pattern[j] && pattern[j] != ".".charAt(0)) {
					return false;
				}
				else {
					i++;
					j++;
				}
			}
		}
        if(i<str.length) {
        	return false;
        }
        else {
			while (j<pattern.length) {
				if(j+1 < pattern.length && pattern[j+1] == "*".charAt(0)) {
					j = j + 2;
				}
				else {
					if (pattern[j] == "*".charAt(0)) {
						j++;
					}
					else {
						return false;
					}
				}
			}
		}
        return true;
    }
    public int StrToInt(String str) {
    	StringBuilder builder = new StringBuilder(str);
        String[] strings = builder.reverse().toString().split("");
        HashSet<Integer> set = new HashSet<Integer>();
        int out = 0;
        int muti = 1;
        for (int i = 0; i < strings.length; i++) {
			if(i == (strings.length - 1) && (strings[i].equals("+") || strings[i].equals("-"))) {
				if (strings[i].equals("-")) {
					out = -out;
				}
				return out;
			}
			else {
				switch (strings[i]) {
				case "1":
					out += 1*muti;
					break;
				case "2":
					out += 2*muti;
					break;
				case "3":
					out += 3*muti;
					break;
				case "4":
					out += 4*muti;
					break;
				case "5":
					out += 5*muti;
					break;
				case "6":
					out += 6*muti;
					break;
				case "7":
					out += 7*muti;
					break;
				case "8":
					out += 8*muti;
					break;
				case "9":
					out += 9*muti;
					break;
				default:
					return 0;
				}
				muti = muti *10;
			}
		}
        return out;
    }
    public int LastRemaining_Solution(int n, int m) {
        if(n ==0){
            return -1;
        }
        if(n == 1){
            return 0;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        int num=0;
        int index = 0;
        for(int i = 0;i<n;i++){
            list.add(i);
        }
        while(list.size() > 1){
            if(num == m-1){
            	System.out.println(num+":"+index+":"+list.remove(index));
                num=-1;
                index--;
            }
            num++;
            index++;
            if(index == list.size()){
                index = 0;
            }
        }
        return list.get(0);
    }
    public boolean isContinuous(int [] numbers) {
        int zero = 0;
        int out = 0;
        Arrays.sort(numbers);
        for(int i = 0;i<numbers.length-1;i++){
            if(numbers[i] == 0){
                zero++;
            }
            else{
                if(numbers[i+1] == numbers[i]){
                    return false;
                }
                else{
                	System.out.println(numbers[i+1]+" "+numbers[i]);
                    out += numbers[i+1] - numbers[i] - 1;
                }
            }
        }
        return zero - out >= 0;
    }
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        int total = 0;
         ArrayList<Integer> list = new ArrayList<Integer>();
         ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
        for(int i = 1;i<sum;i++){
            for(int j = i;j<sum;j++){
                if(total+j == sum){
                    list.add(j);
                    ArrayList<Integer> list2 = new ArrayList<Integer>();
                    list2.addAll(list);
                    out.add(list2);
                    total += j;
                    break;
                }
                else if(total+j < sum){
                    total+=j;
                    list.add(j);
                }
                else{
                    break;
                }
            }
            total = 0;
            list.clear();
        }
         return out;
     }
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        StringBuilder s = new StringBuilder();
        boolean f = true;
        for(int i:array){
            s.append(i);
        }
        String[] str = s.toString().split("");
        for(int j = 0;j<array.length;j++){
            if(s.toString().indexOf(str[j]) == s.toString().lastIndexOf(str[j])){
                if(f){
                    num1[0] = array[j];
                    f=false;
                }
                else{
                    num2[0] = array[j];
                    break;
                }
            }
        }
    }
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array.length == 0){
            return 0;
        }
        int length = array.length;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int in:array){
            if(map.containsKey(in)){
                if(map.get(in) + 1 > length/2){
                    return in;
                }
                System.out.println("put"+in+" times:"+map.get(in));
                map.put(in,map.get(in)+1);
            }
            else{
            	System.out.println("put"+in);
                map.put(in,1);
            }
        }
        return 0;
    }
	HashSet<String> list = new HashSet<String>();
	public void stringType(String [] in,StringBuilder builders) {
		for (int i = 0; i < in.length; i++) {
			builders.append(in[i]);
			if (builders.length() == 3) {
				list.add(builders.toString());
			}
			StringBuilder builder = new StringBuilder();
			for (String string : in) {
				if (string != in[i]) {
					builder.append(string);
				}
			}
			if (builder.length() != 0) {
				stringType(builder.toString().split(""),builders);
			}
			builders.deleteCharAt(builders.length() - 1);
		}
	}
	public class RandomListNode {
	    int label;
	    RandomListNode next = null;
	    RandomListNode random = null;

	    RandomListNode(int label) {
	        this.label = label;
	    }
	}
    public RandomListNode Clone(RandomListNode pHead)
    {
        RandomListNode out = null;
        RandomListNode temp = pHead;
        RandomListNode oTemp = null;
        while(pHead!=null){
            if(out == null){
                out = new RandomListNode(pHead.label);
                oTemp = out;
            }
            else{
                out.next = new RandomListNode(pHead.label);
                out = out.next;
            }
            pHead = pHead.next;
        }
        pHead = temp;
        out = oTemp;
        temp = out;
        while(pHead!=null){
            while(oTemp!=null){
            	System.out.println(oTemp.label);
                if(pHead.random != null){
                	if (oTemp.label == pHead.random.label) {
                		out.random = new RandomListNode(oTemp.label);
					}
                    
                }
                oTemp = oTemp.next;
            }
            pHead = pHead.next;
            out = out.next;
            oTemp = out;
        }
        return temp;
    }
	boolean out = true;
    public void t(ArrayList<Integer> list){
        if(list.isEmpty()){
            return;
        }
        int root = list.get(list.size() - 1);
        ArrayList<Integer> low = new ArrayList<Integer>();
        ArrayList<Integer> high = new ArrayList<Integer>();
        boolean islow = true;
        for(int i = 0;i< list.size();i++){
            if(list.get(i) < root){
                if(islow == false){
                    out = false;
                }
                low.add(list.get(i));
            }
            else if(list.get(i) > root){
                high.add(list.get(i));
                islow = false;
            }
        }
        t(low);
        t(high);
    }
    public boolean IsPopOrder(int [] pushA,int [] popA) {
    	Stack<Integer> stack = new Stack<Integer>();
    	int i = 0,j = -1;
        while (i < popA.length) {
			if (stack.contains(popA[i])) {
				if (stack.lastElement() == popA[i]) {
					System.out.println("pop"+stack.pop());
					i++;
				}
				else {
					return false;
				}
			}
			else {
				do {
					if (j+1 < pushA.length) {
						j++;
					}
					else {
						return false;
					}
					System.out.println("push"+pushA[j]);
					stack.push(pushA[j]);
				} while (j< pushA.length &&pushA[j] != popA[i]);
			}
		}
        return true;
    }
    public void reOrderArray(int [] array) {
        int[] out = new int[array.length];
        int j = 0;
        for(int i = 0;i<array.length;i++){
            if(array[i]%2 == 1){
                out[j] = array[i];
                j++;
            }
        }
        for(int i = 1;i<array.length;i++){
            if(array[i]%2 == 0){
                out[j] = array[i];
                j++;
            }
        }
        for(int i = 0;i<array.length;i++){
            array[i] = out[i];
        }
    }
    public volatile double i;
    public double Power(double base, int exponent) {
        i = base;
        if(base == 0){
            return 0;
        }
        else if(exponent == 0){
            return 1;
        }
        else {
            CountDownLatch c  = new CountDownLatch(1);
            for(int j = 0;j < exponent;j++){
                new Thread(()->{
                    i = i * base;
                }).start();
                if(j + 1 == exponent){
                    c.countDown();
                }
            }
            try{
                c.await();
            }catch(Exception e){
                
            }
            return i;
        }
    }
    public double PowerE(double base, int exponent) {
        i = base;
        if(base == 0){
            return 0;
        }
        else if(exponent == 0){
            return 1;
        }
        else {
            for(int j = 0;j < exponent;j++){
                i = i * base;
            }
            return i;
        }
    }
}

package byr.lavaluv.Sorts;

import java.util.ArrayList;

public class RadixSort {
	static public void radixSort(int[] arr,int bucket) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> tempArrayList;
		for (int i = 0; i < 10; i++) {
			list.add(i, new ArrayList<Integer>());
		}
		int count = 1;
		for (int i = 0; i < bucket; i++) {
			for (int j = 0; j < arr.length; j++) {
				tempArrayList =list.get(arr[j]/count%10);
				tempArrayList.add(arr[j]);
			}
			int temp = 0;
			for (int j = 0; j < 10; j++) {
				tempArrayList = list.get(j);
				for (Integer integer : tempArrayList) {
					arr[temp] = integer;
					temp++;
				}
				tempArrayList.clear();
			}
			count = count * 10;
		}
	}
}

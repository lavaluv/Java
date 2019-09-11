package byr.lavaluv.Sorts;

import org.junit.jupiter.api.Test;

public class TestSort {
	@Test
	public void test() {
		int[] list = {6,5,3,1,8,7,2,4,10,0,9};
		//BubbleSort.bubbleSort(list);
		//InsertSort.insertSort(list);
		//QuickSort.quickSort(list, 0, list.length - 1);
		//MergeSort.sort(list, 0, list.length -1);
		//SelectSort.selectSort(list);
		RadixSort.radixSort(list, 2);
		for (int i : list) {
			System.out.println(i);
		}
	}
}

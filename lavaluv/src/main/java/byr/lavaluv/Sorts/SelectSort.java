package byr.lavaluv.Sorts;
/*
 * 
 */
public class SelectSort {
	static public void selectSort(int[] arr) {
		int min,temp;
		for (int i = 0; i < arr.length; i++) {
			min = i;
			for (int j = i; j < arr.length; j++) {
				if (arr[min] > arr[j]) {
					min = j;
				}
			}
			temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
	}
}

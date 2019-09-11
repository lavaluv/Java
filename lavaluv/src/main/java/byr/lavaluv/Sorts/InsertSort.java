package byr.lavaluv.Sorts;

/*
 * 每次选择一个元素，并且将这个元素和整个数组中的所有元素进行比较，然后插入到合适的位置，图片演示如上，
 * 时间复杂度 O(n^2)
 * 不稳定
 */
public class InsertSort {
	static public void insertSort(int[] list) {
		int temp,j;
		for (int i = 1; i < list.length; i++) {
			temp = list[i];
			j = i;
			//将大于temp的数右移
			while (j > 0 && list[j-1] > temp) {
				list[j] = list[j-1];
				if (list[j] > temp) {
					list[j-1] = temp;
				}
				j = j - 1;
			}
			list[j] = temp;
		}
	}
}

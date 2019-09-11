package byr.lavaluv.Sorts;
/*
 * 简称快排，时间复杂度并不固定，如果在最坏情况下（元素刚好是反向的）速度比较慢，达到 O(n^2)（和选择排序一个效率），但是如果在比较理想的情况下时间复杂度 O(nlogn)。

快排也是一个分治的算法，快排算法每次选择一个元素并且将整个数组以那个元素分为两部分，根据实现算法的不同，元素的选择一般有如下几种：

永远选择第一个元素
永远选择最后一个元素
随机选择元素
取中间值
整个快速排序的核心是分区（partition），分区的目的是传入一个数组和选定的一个元素，把所有小于那个元素的其他元素放在左边，大于的放在右边。
 */
public class QuickSort {
	static public void quickSort(int[] list,int low,int high) {
		if (low > high) {
			return;
		}
		int index = getIndex(list, low, high);
		quickSort(list, index + 1, high);
		quickSort(list, low, index - 1);
	}
	static private int getIndex(int[] list,int low,int high) {
		int p = list[low];
		while (low < high) {
			//找到右边比p小的数，赋值到low
			while (low < high & list[high] > p) {
				high = high - 1;
			}
			list[low] = list[high];
			//找到左边比p大的数，赋值到high
			while (low < high & list[low] < p) {
				low = low + 1;
			}
			list[high] = list[low];
		}
		//当low == high时即为p的位置
		list[low] = p;
		return low;
	}
}

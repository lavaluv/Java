package byr.lavaluv.Sorts;

/*
 * 每次选择两个元素，按照需求进行交换（比如需要升序排列的话，把较大的元素放在靠后一些的位置），
 * 循环 n 次（n 为总元素个数），这样小的元素会不断 “冒泡” 到前面来，
 * 时间复杂度O(n^2)
 * 具有稳定性
 */
public class BubbleSort{
	static public void bubbleSort(int[] list) {
		int temp;
		boolean swapp =true;
		while (swapp) {
			swapp = false;
			for (int i = 0; i < list.length - 1; i++) {
				if (list[i] > list[i+1]) {
					temp = list[i];
					list[i] = list[i+1];
					list[i+1] = temp;
					swapp = true;
				}
			}
		}
	}
}

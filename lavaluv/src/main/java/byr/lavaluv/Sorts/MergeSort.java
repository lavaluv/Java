package byr.lavaluv.Sorts;
/*
 * 归并排序相比较之前的排序算法而言加入了分治法的思想，其算法思路如下：

1.如果给的数组只有一个元素的话，直接返回（也就是递归到最底层的一个情况）

2.把整个数组分为尽可能相等的两个部分（分）

3.对于两个被分开的两个部分进行整个归并排序（治）

4.把两个被分开且排好序的数组拼接在一起
时间复杂度：O(nlogn)
稳定
 */
public class MergeSort {
	public static void sort(int[] arr, int L, int R) {
	    if(L == R) {
	        return;
	    }
	    int mid = (L + R)/2;
	    sort(arr, L, mid);
	    sort(arr, mid + 1, R);
	    merge(arr, L, mid, R);
	}

	public static void merge(int[] arr, int L, int mid, int R) {
	    int[] temp = new int[R - L + 1];
	    int i = 0;
	    int p1 = L;
	    int p2 = mid + 1;
	    // 比较左右两部分的元素，哪个小，把那个元素填入temp中
	    while(p1 <= mid && p2 <= R) {
	        temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
	    }
	    // 上面的循环退出后，把剩余的元素依次填入到temp中
	    // 以下两个while只有一个会执行
	    while(p1 <= mid) {
	        temp[i++] = arr[p1++];
	    }
	    while(p2 <= R) {
	        temp[i++] = arr[p2++];
	    }
	    // 把最终的排序的结果复制给原数组
	    for(i = 0; i < temp.length; i++) {
	        arr[L + i] = temp[i];
	    }
	}
}

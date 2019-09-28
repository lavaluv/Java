package byr.lavaluv.search;
/*
 * 说明：元素必须是有序的，如果是无序的则要先进行排序操作。

　　基本思想：也称为是折半查找，属于有序查找算法。用给定值k先与中间结点的关键字比较，中间结点把线形表分成两个子表，若相等则查找成功；
   若不相等，再根据k与该中间结点关键字的比较结果确定下一步查找哪个子表，这样递归进行，直到查找到或查找结束发现表中没有这样的结点。

　　复杂度分析：最坏情况下，关键词比较次数为log2(n+1)，且期望时间复杂度为O(log2n)；

　　注：折半查找的前提条件是需要有序表顺序存储，对于静态查找表，一次排序后不再变化，折半查找能得到不错的效率。但对于需要频繁执行插入或删除操作的数据集来说，维护有序的排序会带来不小的工作量，那就不建议使用。
 */
public class BinarySearch {
	static public int search(int[] arr,int value,int l,int h) {
		//int mid = (l+h)/2;
		//将上述的比例参数1/2改进为自适应的，根据关键字在整个有序表中所处的位置，让mid值的变化更靠近关键字key，这样也就间接地减少了比较次数,适用于数组元素均匀分布
		int mid = l+(value-arr[l])/(arr[h]-arr[l])*(h-l);
		if (arr[mid] == value) {
			return mid;
		}
		if(arr[mid] < value){
			return search(arr, value, mid + 1, h);
		}
		if(arr[mid] > value){
			return search(arr, value, l, mid - 1);
		}
		return -1;
	}
}

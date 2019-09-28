package byr.lavaluv.Algorithm;

import java.io.IOException;

import org.junit.jupiter.api.Test;
/**
 * @param mid 需要去除的不是中位数的数
 * @param one,two分别为数组1和2的指针，代表处理到的位数
 * @param temp1,temp2 分别为这一轮需要判断大小的标签
 */
public class MediaNum {
	@Test
	public void main() throws IOException {
		int [] in = {};
		int [] ins = {1,3,4,5,6};
		System.out.println(findMedianSortedArrays(in, ins));
	}
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	//判断是奇数还是偶数
    	boolean is = (nums1.length + nums2.length)%2 == 0?false:true;
    	int mid = (nums1.length + nums2.length)/2;
    	int min = 0;
    	int one = 0,two = 0,temp1 = 0,temp2 = 0;
    	if (is) {
			mid++;
		}
    	while (one < nums1.length && two < nums2.length) {
    		//System.out.println(one+" "+two+" "+mid+" "+temp1+" "+temp2);
    		//当需要去除的数量为一
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
}

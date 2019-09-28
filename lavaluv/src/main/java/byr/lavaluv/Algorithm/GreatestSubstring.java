package byr.lavaluv.Algorithm;

import org.junit.jupiter.api.Test;

public class GreatestSubstring {
	@Test
	public void test() {
		int [] i = {6,-3,-2,7,-15,1,2,2};
		System.out.println(FindGreatestSumOfSubArray(i));
	}
    public int FindGreatestSumOfSubArray(int[] array) {
        int out = array[0];
        int max = array[0];
        for(int i =1;i<array.length;i++){
            max = max + array[i] > array[i]?max + array[i]:array[i];
            out = out > max?out:max;
        }
        return out;
    }
}

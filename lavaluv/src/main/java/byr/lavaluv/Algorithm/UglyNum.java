package byr.lavaluv.Algorithm;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class UglyNum {
	@Test
	public void test() {
		System.out.print(GetUglyNumber_Solution(1500));
	}
    public int GetUglyNumber_Solution(int index) {
        ArrayList<Integer> outArrayList = new ArrayList<Integer>();
        ArrayList<Integer> twoArrayList = new ArrayList<Integer>();
        ArrayList<Integer> threeArrayList = new ArrayList<Integer>();
        ArrayList<Integer> fiveArrayList = new ArrayList<Integer>();
        while (outArrayList.size() < index) {
			if(outArrayList.size() == 0) {
				outArrayList.add(1);
				twoArrayList.add(2);
				threeArrayList.add(3);
				fiveArrayList.add(5);
			}
			else {
				int min = Math.min(Math.min(twoArrayList.get(0), threeArrayList.get(0)), fiveArrayList.get(0));
				if (min == twoArrayList.get(0)) {
					twoArrayList.remove(0);
				}
				if (min == threeArrayList.get(0)) {
					threeArrayList.remove(0);
				}
				if (min == fiveArrayList.get(0)) {
					fiveArrayList.remove(0);
				}
				twoArrayList.add(min*2);
				threeArrayList.add(min*3);
				fiveArrayList.add(min*5);
				outArrayList.add(min);
			}
		}
        return outArrayList.get(index-1);
    }
}

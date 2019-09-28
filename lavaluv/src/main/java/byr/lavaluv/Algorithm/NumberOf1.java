package byr.lavaluv.Algorithm;

import org.junit.jupiter.api.Test;

public class NumberOf1 {
	@Test
	public void test() {
		int i = 19;
		System.out.println(numberOf1(i));
	}
    public int numberOf1(int n) {
        int i = 0;
        while(n!=0){
            i++;
            n = n & (n - 1);
        }
        return i;
    }
}

package byr.lavaluv.Algorithm;

import org.junit.jupiter.api.Test;

public class Add {
	@Test
	public void test() {
		System.out.println(add(5, 7));
	}    
	public int add(int num1,int num2) {
		//当进位为0时，结束循环
        while (num2!=0) {
        	//不考虑进位值，二进制按位相加,^
            int temp = num1^num2;
            //计算进位值，二进制与，然后左移一位
            num2 = (num1&num2)<<1;
            num1 = temp;
        }
        return num1;
    }
}

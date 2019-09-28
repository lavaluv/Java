package byr.lavaluv.Algorithm;

import org.junit.jupiter.api.Test;

public class MatrixMuti {
	@Test
	public void test() {
		int [] A = {1,2,3,4,5};
		for (int i : multiply(A)) {
			System.out.println(i);
		}
	}
    public int[] multiply(int[] A) {
        int length = A.length;
        int [] b = new int[length];
        if(length != 0){
            b[0] = 1;
            for(int i = 1;i< length;i++){
                b[i] = b[i-1] * A[i-1];
            }
            int temp = 1;
            for(int j = length - 2;j>=0;j--){
                temp = temp * A[j+1];
                b[j] = b[j] * temp;
            }
        }
        return b;
    }
}

package byr.lavaluv.Algorithm;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class RotatePrinter {
	@Test
	public void test() {
		int [][]i = {{1,2,3,4,5},{6,7,8,9,10}};
		System.out.println(printMatrix(i));
	}
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<Integer>();
       int row = matrix.length;
        int col = matrix[0].length;
        System.out.println(row+" "+col);
        int i=0,j=0,t = 0;
        while(true){
            if(j==col || j<0 || i>=row)
                break;
            while(j!=col){
            	System.out.println(col+" "+j);
                list.add(matrix[i][j]);
                j++;
            }
            j--;
            i++;
            if(j==col || j<0 || i>=row)
                break;
            while(i!=row){
            	System.out.println(row+" "+i);
                list.add(matrix[i][j]);
                i++;
            }
            i--;
            j--;
            if(j==col || j<0 || i>=row)
                break;
            while(j!=t-1){
            	System.out.println(col+" "+j);
                list.add(matrix[i][j]);
                j--;
            }
            j++;
            i--;
            if(j==col || j<0 || i>=row)
                break;
            while(i!=t){
            	System.out.println(row+" "+i);
                list.add(matrix[i][j]);
                i--;
            }
            i++;
            j++;
            col--;
            row--;
            t++;
        }
        return list;
    }
}

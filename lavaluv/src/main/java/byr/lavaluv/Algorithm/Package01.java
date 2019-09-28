package byr.lavaluv.Algorithm;

import org.junit.jupiter.api.Test;

public class Package01 {
	@Test
	public void test() {
		int c = 6;
		int num = 5;
		int[] w = {1,2,3,4,5};
		int[] v = {5,4,3,6,7};
		System.out.println(packageDouble(c, num, w, v,1));
	}
	public int package01(int capacity,int num,int [] w,int [] v) {
		int [][] pack = new int[num+1][capacity+1];
		for (int i = 1; i <= num; i++) {
			for (int j = 0; j <= capacity; j++) {
				if (w[i-1] <= j) {
					pack[i][j] = Math.max(pack[i-1][j], pack[i-1][j - w[i-1]] + v[i-1]);
				}
				else {
					pack[i][j] = pack[i-1][j];
				}
			}
		}
		for (int[] is : pack) {
			for (int is2 : is) {
				System.out.print(is2 +" ");
			}
			System.out.println();
		}
		return pack[num][capacity];
	}
	public int packageDouble(int capacity,int num,int [] w,int [] v,int maxNum) {
		int [][][] pack = new int[num+1][capacity+1][maxNum+1];
		for (int i = 1; i <= num; i++) {
			for (int j = 0; j <= capacity; j++) {
				for (int k = 0; k <= maxNum; k++) {
					if (w[i-1] <= j && k-1 >= 0) {
						pack[i][j][k] = Math.max(pack[i-1][j][k], pack[i-1][j - w[i-1]][k-1] + v[i-1]);
					}
					else {
						pack[i][j][k] = pack[i-1][j][k];
					}
				}
			}
		}
		return pack[num][capacity][maxNum];
	}
	public int longestSameString(String s) {
		StringBuilder builder = new StringBuilder(s);
		String string = builder.reverse().toString();
		int max = 0;
		int index = 0;
		int [][] dp = new int[s.length()][s.length()];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				if (s.charAt(i) == string.charAt(j)) {
					if (j == 0 || i == 0) {
						dp[i][j] = 1;
					}
					else {
						dp[i][j] = dp[i-1][j-1] + 1;
					}
				}
				if (dp[i][j] > max) {
					max = dp[i][j];
					index = i;
					System.out.println(max+" "+index);
				}
			}
		}
		return max;
	}
	public String longestPalindrome(String s) {
		if (s.length() == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder(s);
		String string = builder.reverse().toString();
		int max = 0;
		int index = 0;
		int [][] dp = new int[s.length()][s.length()];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				if (s.charAt(i) == string.charAt(j)) {
					if (j == 0 || i == 0) {
						dp[i][j] = 1;
					}
					else {
						dp[i][j] = dp[i-1][j-1] + 1;
					}
				}
				if (dp[i][j] > max) {
					int before = s.length() - j - 1;
					if (before + dp[i][j] - 1 == i) {
						max = dp[i][j];
						index = i;
					}
				}
			}
		}
		return s.substring(index-max +1, index+1);
	}
	public String longestPalindromeBad(String s) {
		if (s.length() == 0) {
			return "";
		}
		String [] strings = s.split("");
		int max = 1;
		int num = 1;
		int temp = 0;
		int index = 0;
		for (int i = 0; i < strings.length && strings.length - i > max; i++) {
			int last = s.lastIndexOf(strings[i]);
			temp = last;
			if (i != last && last - i + 1 > max) {
				int j = i;
				while (j<=last && last - i + num > max) {
					if (strings[j].equals(strings[last])) {
						j++;
						if (j<=last) {
							num++;
						}
						last--;
						if (j<=last) {
							num++;
						}
					}
					else {
						num = 1;
						j = i;
						last = temp - 1;
						temp = last;
					}
				}
				index = max < num?i:index;
				max = max < num?num:max;
				num = 1;
			}
		}
		return s.substring(index, index+max);
	}
}

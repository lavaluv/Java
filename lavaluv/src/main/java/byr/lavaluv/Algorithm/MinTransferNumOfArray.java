package byr.lavaluv.Algorithm;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class MinTransferNumOfArray {
	@Test
	public void main() throws IOException {
		int [] in = {1,2,3,5,4};
		System.out.println(minTransferNumOfArray(in));
	}
	public int minTransferNumOfArray(int [] in) {
		int min = in.length -1;
		int[] dp = new int[in.length];
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < i; j++) {
				if (in[j] < in[i] && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
					if (min > in.length - dp[i] - 1) {
						min = in.length - dp[i] - 1;
					}
				}
			}
		}
		return min;
	}
	public ArrayList<Integer> longestSortArray(int [] in) {
		int[] dp = new int[in.length];
		ArrayList<Integer> out = new ArrayList<Integer>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < in.length; i++) {
			temp.clear();
			for (int j = 0; j < i; j++) {
				if (in[j] < in[i] && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
					temp.add(in[j]);
				}
				if (j+1 == i) {
					temp.add(in[i]);
					if (temp.size() > out.size()) {
						out.clear();
						out.addAll(temp);
					}
				}
			}
		}
		return out;
	}
}

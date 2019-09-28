package byr.lavaluv.Util;

import org.junit.jupiter.api.Test;

public class StringUtil {
	@Test
	public void test() {
		String[] strings = {"0 0 2 4 0","0 2 2 2 0","0 4 2 2 0","8 8 2 2 0","0 2 2 0 0"};
		int [][] i = stringTo2Arrays(strings, 5, 5);
		String out = ArraysToString(i);
		System.out.println(out);
	}
	static public int[][] stringTo2Arrays(String[] in,int one,int two){
		int [][] i = new int[one][two];
		for (int j = 0; j < one; j++) {
			String[] tempStrings = in[j].split(" ");
			for (int j2 = 0; j2 < two; j2++) {
				i[j][j2] = Integer.valueOf(tempStrings[j2]);
			}
		}
		return i;
	}
	static public String ArraysToString(int[][] i) {
		StringBuilder stringBuilder = new StringBuilder();
		for(int [] o:i) {
			for (int js : o) {
				stringBuilder.append(js);
			}
			stringBuilder.append("\n");
		}
		String out = stringBuilder.toString();
		return out;
	}
}

package byr.lavaluv.Algorithm;

import java.io.IOException;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class StringFirstAppearence {
	@Test
	public void main() throws IOException {
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			Insert((char)random.nextInt(100));
		}
		System.out.println(FirstAppearingOnce());
	}
	char[] in = new char[256];
	String string = "";
    public void Insert(char ch)
    {
    	//System.out.println(ch);
    	in[ch]++;
    	string+=String.valueOf(ch);
    }
  //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
    	for (int i = 0; i < string.length(); i++) {
			if (in[string.charAt(i)] == 1) {
				return string.charAt(i);
			}
		}
    	return '#';
    }
}

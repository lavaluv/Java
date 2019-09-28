package byr.lavaluv.Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

public class StringComparetor {
	@Test
	public void test() {
		int [] i = {3,323,32123};
		System.out.print(PrintMinNumber(i));
	}
    public String PrintMinNumber(int [] numbers) {
        ArrayList<String> list = new ArrayList<String>();
        for(int i :numbers){
            list.add(String.valueOf(i));
        }
        Collections.sort(list,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String t1 = o1+o2;
				String t2 = o2+o1;
				return t1.compareTo(t2);
			}
		});
        StringBuilder b = new StringBuilder();
        for(String s:list){
            b.append(s);
        }
        return b.toString();
    }
}

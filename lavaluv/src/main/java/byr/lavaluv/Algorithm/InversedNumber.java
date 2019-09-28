package byr.lavaluv.Algorithm;

import org.junit.jupiter.api.Test;

public class InversedNumber {
	@Test
	public void test() {
		int [] i = {627126,415347,850134,371085,279048,705820,453064,944751,92317,58592,167988,284065,992573,78043,190215,104546,607528,391775,701214,849731,231053,603058,975374,199773,479544,143961,206797,325662,90035,69615,429916,717161,484962,796403,604598,280362,502223,57662,741466,594540,632606,909454,394957,625180,503849,585172,729726,627729,976947,947293,477461,724352,66703,452835,440478,62599,596797,163627,388261,203184,233243,334529};
		sort(i, 0, i.length-1);
		System.out.print(times);
		
	}
	long times = 0;
    public void sort(int [] in,int l,int r){
        if(l >= r){
            return;
        }
        int mid = (l+r)/2;
        sort(in,l,mid);
        sort(in,mid+1,r);
        merge(in,l,mid,r);
    }
    public void merge(int [] in,int l,int mid,int r){
        int [] temp = new int[r-l+1];
        int i = r - l;
        int p1 = mid;
        int p2 = r;
        while(l<=p1&&mid<p2){
            if(in[p1] > in[p2]){
                temp[i]= in[p1];
                times+=p2-mid;
                p1--;
                i--;
            }
            else{
                temp[i] = in[p2];
                p2--;
                i--;
            }
        }
        while(l<=p1){
            temp[i] = in[p1];
            p1--;
            i--;
        }
        while(mid<p2){
            temp[i] = in[p2];
            p2--;
            i--;
        }
        for(int j = 0;j<temp.length;j++){
            in[l+j] = temp[j];
        }
    }
}

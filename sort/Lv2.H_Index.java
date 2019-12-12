package sort;

import java.util.Arrays;

public class H_Index {

    public int solution(int[] citations) {
    	// save length of the given array into two variables
        int hIndex = citations.length;
        int len = hIndex;
        
        // sort the given array in order
        Arrays.sort(citations);
        
        // repeat the loop until it finds H-Index satisfying requirements;
        //  an index of array H or more elements of size H or more 
        while(hIndex > 0) {
        	// 'length - H'th element has to be equal or bigger than H if it fulfills the conditions
        	if(citations[len - hIndex] >= hIndex) {
        		return hIndex;
        	}
        	hIndex--;
        }
        return hIndex;
    }
	
}

package heap;

import java.util.PriorityQueue;

public class Spicier {

    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for(int scov : scoville) {
        	que.add(scov);
        }
        
        while(que.peek() < K) {
        	if(que.size() == 1) {
        		return -1;
        	}
        	int first = que.poll();
        	int second = que.poll();
        	que.add(first + second*2);
        	answer++;
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		int[] scoville = {9, 10, 12, 1, 2, 3};
		int K = 7;
		System.out.println(solution(scoville, K));
	}

}

package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class RamenFactory {
	public static class Import implements Comparable<Import> {
		int date;
		int supply;
		
		public Import(int date, int supply) {
			this.date = date;
			this.supply = supply;
		}
		
		@Override
		public int compareTo(Import target) {
			return this.supply <= target.supply ? 1 : -1;
		}
	}
	
    public static int solution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;
        PriorityQueue<int[]> que = new PriorityQueue<>();
        for(int i = 0; i < dates.length; i++) {
        	que.add(new int[] {dates[i], supplies[i]});
        }
        
        List<int[]> tmp = new ArrayList<>();
        while(stock < k) {
        	int[] pop = que.poll();
        	if(pop[0] <= stock) {
        		stock += pop[1];
        		answer++;
        		for(int[] imp : tmp) {
        			que.add(imp);
        		}
        		tmp.clear();
        		continue;
        	}
        	tmp.add(pop);
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		int stock = 4;
		int[] dates = {4,10,15};
		int[] supplies = {20,5,10};
		int k = 30;
		System.out.println(solution(stock, dates, supplies, k));
		
	}

}

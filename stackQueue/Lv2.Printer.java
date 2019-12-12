package stackQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Printer {

    public static int solution(int[] priorities, int location) {
        int answer = 0;
        //declare a queue to put printing order.
        Queue<int[]> que = new LinkedList<>();
        //declare a priority queue to compare with the queue if the peek node of the queue has a top priority level.
        PriorityQueue<Integer> pque = new PriorityQueue<>((o1, o2) -> o1 <= o2 ? 1 : -1); // reverse order
        //put nodes into que and priority que. in case of the queue, put array instance as a node including priority and original location of each node.
        for(int i = 0; i < priorities.length; i++) {
        	que.add(new int[] {priorities[i], i});
        	pque.add(priorities[i]);
        }
        
        while(!que.isEmpty()) {
        	//do pop with the queue and check if the pop node has a top priority level in existence.
        	int[] pop = que.poll();
        	int check = pque.peek();
        	//if the node has a top priority, ++ on answer, the number of times to have printed. 
        	if(pop[0] == check) {
        		answer++;
        		//if the pop node's location equals with the operation user wants to track, return the answer immediately.
        		if(pop[1] == location) {
        			return answer;
        		}
        		//if not, poll also from the priority queue and continue the while loop.
        		pque.poll();
        		continue;
        	} else {
        		//if the pop node isn't a top priority level in existence, re-put into the end of the queue.
        		que.add(pop);
        	}
        }
        return answer;
    }

    //different ver. replacing priority queue to array list. 
    public static int solution2(int[] priorities, int location) {
        int answer = 0;
        Queue<int[]> que = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < priorities.length; i++) {
        	que.add(new int[] {priorities[i], i});
        	list.add(priorities[i]);
        }
        list.sort(Comparator.reverseOrder());
        
        while(!que.isEmpty()) {
        	int[] pop = que.poll();
        	int check = list.get(0);
        	if(pop[0] == check) {
        		answer++;
        		if(pop[1] == location) {
        			return answer;
        		}
        		list.remove(0);
        		continue;
        	} else {
        		que.add(pop);
        	}
        }
        return answer;
    }
    
	public static void main(String[] args) {
		//test case
		int[] priorities = {2, 1, 3, 2};
		int location = 2;
		System.out.println(solution(priorities, location));
		System.out.println(solution2(priorities, location));
	}

}

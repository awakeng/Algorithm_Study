package stackQueue;

import java.util.LinkedList;
import java.util.Queue;

public class IronBars {

    public static int solution(String arrangement) {
        int answer = 0;
        // a number of layers cut by laser at each point
        int layer = 1;
        // a queue to have a structure of iron bars and lasers
        Queue<Character> que = new LinkedList<>();
        // save the given structure 
        for(char arrgmt : arrangement.toCharArray()) {
        	que.add(arrgmt);
        }
        // poll the very first node into prev(ious) before while loop
        char prev = que.poll();
        
        // read the entire given structure
        while(!que.isEmpty()) {
        	// poll the each node
        	char pop = que.poll();
        	if(pop == '(') {
        		layer++;
        	} else {
        		layer--;
        		if(prev == '(') {	// when '()' come, it's laser
        			answer += layer;
        		} else {			// when '))' come, it means the end of an iron bar
        			answer++;
        		}
        	}
        	// current node will be next prev
        	prev = pop;
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		String arrangement = "()(((()())(())()))(())";
		System.out.println(solution(arrangement));	// 17
	}

}

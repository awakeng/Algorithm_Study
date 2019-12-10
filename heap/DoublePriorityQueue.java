package heap;

import java.util.PriorityQueue;

public class DoublePriorityQueue {

    public static int[] solution(String[] operations) {
        int len = operations.length;
        int size = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o1 >= o2 ? 1 : -1);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o1 <= o2 ? 1 : -1);
        for(int i = 0; i < len; i++) {
        	String[] tmp = operations[i].split(" ");
        	int num = Integer.parseInt(tmp[1]);
        	if(tmp[0].equals("I")) {
        		minHeap.add(num);
        		maxHeap.add(num);
        		size++;
        	} else if(tmp[0].equals("D") && size > 0) {
        		if(num == 1) {
        			maxHeap.poll();
        		} else {
        			minHeap.poll();
        		}
        		size--;
        		if(size == 0) {
        			maxHeap.clear();
        			minHeap.clear();
        		}
        	}
        }
        if(size == 0) {
        	return new int[] {0,0};
        } else if(size == 1) {
        	return new int[] {maxHeap.poll(), maxHeap.poll()};
        } else {
        	return new int[] {maxHeap.poll(), minHeap.poll()};
        }
    }
	
	public static void main(String[] args) {
		String[] operations = {"I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"};
		for(int i : solution(operations)) {
			System.out.println(i);
		}
	}

}

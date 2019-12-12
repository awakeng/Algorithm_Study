package stackQueue;

import java.util.LinkedList;
import java.util.Queue;

public class BridgeCrossing {

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        // 현재까지 경과 시간
        // the time elapsed
        int cnt = 0;
        // 다리 위 트럭들의 무게
        // total weight of trucks on the bridge 
        int wei = 0;
        // 다리의 길이를 로컬 변수화
        // to localize length of the bridge
        int len = bridge_length;
        // 다리를 건너기 전의 트럭들
        // trucks before starting to cross the bridge
        Queue<Integer> weights = new LinkedList<>();
        // 다리 위의 트럭들
        // trucks crossing on the bridge
        Queue<int[]> ontheB = new LinkedList<>();

        // int[] 형태로 주어진 대기 중인 트럭들을 큐에 넣어줌
        // to put given trucks(int[]) into a queue
        for(int truck_weight : truck_weights) {
        	weights.add(truck_weight);
        }
        
        // 첫 번째로 대기 중인 트럭이 다리를 건너기 시작함
        // the first truck starts to cross the bridge
        wei += weights.peek();
        ontheB.add(new int[] {weights.poll(), ++cnt + len});
        
        // 대기 중인 모든 트럭들이 다리를 건너기 시작할 때 까지 반복
        // repeat until all trucks begin to go across the bridge
        while(!weights.isEmpty()) {
        	// 시간의 경과
        	// time elapsing
        	cnt++;
        	// 다리 위 가장 앞에 트럭이 다리를 건너 갔다면, 다리 위 트럭 큐에서 빼주고 해당 트럭의 무게를 다리 위에서 제거해줌
        	// if a head of trucks on the bridge moved across, pop the node from the queue(ontheB) 
        	//  and except its weight from the total
        	if(ontheB.peek()[1] == cnt) {
        		wei -= ontheB.poll()[0];
        	}
        	
        	// 다리 허용 무게가 여유가 있으면 새로운 트럭이 다리를 건너기 시작
        	// new truck begins to cross if the bridge affords it
        	if(wei + weights.peek() <= weight) {
        		int pop = weights.poll();
        		// 새로 건너는 트럭의 무게를 더해줌
        		// add the weight to total
        		wei += pop;
        		// 다리 위 트럭 큐에 추가
        		// add the new node(truck) into the queue(ontheB)
        		ontheB.add(new int[] {pop, cnt + len });
        	}
        	
        }
        
        // 모든 트럭들이 다리를 지나기 시작했으므로 가장 마지막 트럭이 다리를 건넌 시간을 answer에 저장
        // since all trucks started crossing the bridge, save time when the last truck went across the bridge into 'answer' 
        while(!ontheB.isEmpty()) {
        	answer = ontheB.poll()[1];
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		int bridge_length = 100;
		int weight = 100;
		int[] truck_weights = {10,10,10,10,10,10,10,10,10,10};
		System.out.println(solution(bridge_length, weight, truck_weights));
	}

}

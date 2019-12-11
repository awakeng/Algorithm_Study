package stackQueue;

import java.util.LinkedList;
import java.util.Queue;

public class BridgeCrossing {

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        // ������� ��� �ð�
        // the time elapsed
        int cnt = 0;
        // �ٸ� �� Ʈ������ ����
        // total weight of trucks on the bridge 
        int wei = 0;
        // �ٸ��� ���̸� ���� ����ȭ
        // to localize length of the bridge
        int len = bridge_length;
        // �ٸ��� �ǳʱ� ���� Ʈ����
        // trucks before starting to cross the bridge
        Queue<Integer> weights = new LinkedList<>();
        // �ٸ� ���� Ʈ����
        // trucks crossing on the bridge
        Queue<int[]> ontheB = new LinkedList<>();

        // int[] ���·� �־��� ��� ���� Ʈ������ ť�� �־���
        // to put given trucks(int[]) into a queue
        for(int truck_weight : truck_weights) {
        	weights.add(truck_weight);
        }
        
        // ù ��°�� ��� ���� Ʈ���� �ٸ��� �ǳʱ� ������
        // the first truck starts to cross the bridge
        wei += weights.peek();
        ontheB.add(new int[] {weights.poll(), ++cnt + len});
        
        // ��� ���� ��� Ʈ������ �ٸ��� �ǳʱ� ������ �� ���� �ݺ�
        // repeat until all trucks begin to go across the bridge
        while(!weights.isEmpty()) {
        	// �ð��� ���
        	// time elapsing
        	cnt++;
        	// �ٸ� �� ���� �տ� Ʈ���� �ٸ��� �ǳ� ���ٸ�, �ٸ� �� Ʈ�� ť���� ���ְ� �ش� Ʈ���� ���Ը� �ٸ� ������ ��������
        	// if a head of trucks on the bridge moved across, pop the node from the queue(ontheB) 
        	//  and except its weight from the total
        	if(ontheB.peek()[1] == cnt) {
        		wei -= ontheB.poll()[0];
        	}
        	
        	// �ٸ� ��� ���԰� ������ ������ ���ο� Ʈ���� �ٸ��� �ǳʱ� ����
        	// new truck begins to cross if the bridge affords it
        	if(wei + weights.peek() <= weight) {
        		int pop = weights.poll();
        		// ���� �ǳʴ� Ʈ���� ���Ը� ������
        		// add the weight to total
        		wei += pop;
        		// �ٸ� �� Ʈ�� ť�� �߰�
        		// add the new node(truck) into the queue(ontheB)
        		ontheB.add(new int[] {pop, cnt + len });
        	}
        	
        }
        
        // ��� Ʈ������ �ٸ��� ������ ���������Ƿ� ���� ������ Ʈ���� �ٸ��� �ǳ� �ð��� answer�� ����
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

package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class DiskController {

    public static int solution(int[][] jobs) {
    	int answer = 0; // ��� �۾��� �� ���� �ð�
        int cnt = 0;	// ���� �۾� ���� ���ۺ��� ��� �ð�
        
        // �۾� �ҿ� �ð��� �켱���� + �������� �������� ����Ǵ� �켱���� ť ����
        // ���ٽ����� new Comparator()���� �� ���� ����ȭ
        PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> o1[1] >= o2[1] ? 1: -1);
        // �۾����� �켱���� ť�� �־���
        for(int[] job : jobs) {
        	que.add(job);
        }
        
        // �۾��ҿ�ð��� ª���� ���� ��û�� ������ ���� �۾����� �ӽ÷� ������ �� �ٸ� �켱���� ť ����
        PriorityQueue<int[]> tmps = new PriorityQueue<>((o1, o2) -> o1[0] >= o2[0] ? 1: -1);
        // ��� �۾��� ����� ������ while�� ����
        while(!que.isEmpty()) {
        	// 1. �ּ� �ҿ� �ð� �۾� ����
        	int[] pop = que.poll();
        	// 3. ��û�� ���� �۾����� �˻�
        	if(pop[0] <= cnt) {
        		cnt += pop[1];
        		answer += cnt - pop[0];
        		for(int[] tmp : tmps) {
        			que.add(tmp);
        		}
        		tmps.clear();
        		continue;
        	}
        	// 2. ���� ��û�� ������ ���� �۾��̾��� ��� �ӽ� ť�� �־��� 
        	tmps.add(pop);
        	// 4. ���� ���� ť�� �� ����� ���, �ӽ� ť�� �� ������� Ȯ�������ν� ��� �۾��� ������ �����ߴ��� �˻�
        	// ���� �ӽ� ť�� ������� ���� ��� �ڴ���(�� ��������) �۾� ��û�� �����Ѵٴ� �ǹ�
        	if(!tmps.isEmpty() && que.isEmpty()) {
        		// ���� �۾� ��û �ð��� ���� ���� �۾� �������� ��� �ð��� ����
        		cnt = tmps.peek()[0];
        		// �ӽ� ť�� �����ϴ� �۾����� ���� ť�� �ٽ� �־���
        		for(int[] tmp : tmps) {
        			que.add(tmp);
        		}
        		// �ӽ� ť Ŭ���� �� �ٽ� while�� �ݺ�
        		tmps.clear();
        	}
        }
        // (�� �۾� �ð�) / (������ �۾� ��) �� return 
        return answer/jobs.length;
    }
	
	public static void main(String[] args) {
		int[][] jobs = {{0, 3}, {1, 9}, {2, 6}, {3,1}, {40, 2}};
		System.out.println(solution(jobs));
		Arrays.sort(a);
	}

}

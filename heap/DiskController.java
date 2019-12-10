package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class DiskController {

    public static int solution(int[][] jobs) {
    	int answer = 0; // 모든 작업의 총 수행 시간
        int cnt = 0;	// 최초 작업 수행 시작부터 경과 시간
        
        // 작업 소요 시간을 우선순위 + 오름차순 기준으로 저장되는 우선순위 큐 선언
        // 람다식으로 new Comparator()선언 및 설정 간소화
        PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> o1[1] >= o2[1] ? 1: -1);
        // 작업을들 우선순위 큐에 넣어줌
        for(int[] job : jobs) {
        	que.add(job);
        }
        
        // 작업소요시간이 짧지만 아직 요청이 들어오지 않은 작업들을 임시로 저장할 또 다른 우선순위 큐 선언
        PriorityQueue<int[]> tmps = new PriorityQueue<>((o1, o2) -> o1[0] >= o2[0] ? 1: -1);
        // 모든 작업이 수행될 때까지 while문 수행
        while(!que.isEmpty()) {
        	// 1. 최소 소요 시간 작업 추출
        	int[] pop = que.poll();
        	// 3. 요청이 들어온 작업인지 검사
        	if(pop[0] <= cnt) {
        		cnt += pop[1];
        		answer += cnt - pop[0];
        		for(int[] tmp : tmps) {
        			que.add(tmp);
        		}
        		tmps.clear();
        		continue;
        	}
        	// 2. 아직 요청이 들어오지 않은 작업이었을 경우 임시 큐에 넣어줌 
        	tmps.add(pop);
        	// 4. 만약 메인 큐가 다 비었을 경우, 임시 큐도 다 비었는지 확인함으로써 모든 작업이 완전히 수행했는지 검사
        	// 만약 임시 큐는 비어있지 않은 경우 뒤늦은(비 연속적인) 작업 요청이 존재한다는 의미
        	if(!tmps.isEmpty() && que.isEmpty()) {
        		// 따라서 작업 요청 시간이 가장 빠른 작업 기준으로 경과 시간을 변경
        		cnt = tmps.peek()[0];
        		// 임시 큐에 존재하는 작업들을 메인 큐에 다시 넣어줌
        		for(int[] tmp : tmps) {
        			que.add(tmp);
        		}
        		// 임시 큐 클리어 후 다시 while문 반복
        		tmps.clear();
        	}
        }
        // (총 작업 시간) / (수행한 작업 수) 를 return 
        return answer/jobs.length;
    }
	
	public static void main(String[] args) {
		int[][] jobs = {{0, 3}, {1, 9}, {2, 6}, {3,1}, {40, 2}};
		System.out.println(solution(jobs));
		Arrays.sort(a);
	}

}

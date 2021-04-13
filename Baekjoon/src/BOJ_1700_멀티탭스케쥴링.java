import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class BOJ_1700_멀티탭스케쥴링 {

	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] order = new int[K];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < K; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		
		// 앞으로 가장 오랫동안 사용되지 않을 페이지를 찾아서 교체.
		// optimal page replacement
		
		Queue<Integer> list[] = new LinkedList[K+1]; 
		
		for (int i = 1; i <= K; i++) {
			list[i] = new LinkedList<Integer>();
		}
		
		for (int i = 0; i < K; i++) {
			list[order[i]].add(i);
		}
		
		Set<Integer> plug = new HashSet<Integer>();
		int cnt = 0;
		for (int i = 0; i < K; i++) {
			
			if(plug.size() < N) {
				plug.add(order[i]);
				
			} else if(plug.contains(order[i])) {
				;
			} else {
				
				int mostFar = 0;
				int mostFarIdx = -1;
				for (Integer p : plug) {
					if(list[p].size() == 0) {
						mostFarIdx = p;
						break;
					}
					if(mostFar < list[p].peek()) {
						mostFar = list[p].peek();
						mostFarIdx = p;
					}
				}

				plug.remove(mostFarIdx);
				plug.add(order[i]);
				cnt++;
			}
			list[order[i]].poll();
		}
		
		System.out.println(cnt);
	}
		
	
}

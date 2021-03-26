import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16593_AtoB{
	
	static int[] arr;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		Queue<Integer[]> pq = new PriorityQueue<Integer[]>(new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				// TODO Auto-generated method stub
				return o1[0] - o2[0];
			}
			
		});
		
		Integer[] init = {A, 1};
		pq.add(init);
		
		Integer[] value;
		while(true) {
			value = pq.poll();
			
			if(value[0] == B) {
				System.out.println(value[1]);
				break;
			}
			
			if(value[0] > B) {
				System.out.println(-1);
				break;
			}
			
			Integer[] temp1 = {value[0] * 2, value[1] + 1};
			pq.add(temp1);
			
			if(value[0] < Integer.MAX_VALUE/10) {
				Integer[] temp2 = {value[0] * 10 + 1, value[1] + 1};
				pq.add(temp2);
			}
			
		}
		
	}
	
	
	
	
}




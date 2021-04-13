import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class BOJ_1806_부분합 {
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] num = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		int sum = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			q.offer(num[i]);
			sum += num[i];
			while(sum >= S) {
				min = Math.min(min, q.size());
				sum -= q.poll();
			}
		}
		
		if(min == Integer.MAX_VALUE) {
			min = 0;
		}
		
		System.out.println(min);
		
		
	}

	

	
	
	
	
}

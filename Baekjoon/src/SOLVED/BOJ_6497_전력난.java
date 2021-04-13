package SOLVED;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_6497_전력난{
	
	static int[] arr;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			
			String str =  br.readLine();
			
			if(str.equals("0 0")) {
				break;
			}
			
			st = new StringTokenizer(str);
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int totalCost = 0;
			int leastCost = 0;
			
			arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = i;
			}
			Queue<Line> queue = new PriorityQueue<Line>();
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				queue.offer(new Line(from, to, cost));
				totalCost += cost;
			}

			Line temp;
			for (int i = 0; i < N-1; i++) {
				while(true) {
					temp = queue.poll();
					
					if(find(temp.from) != find(temp.to)) {
						union(temp.from, temp.to);
						leastCost += temp.cost;
						break;
					}
				}
			}
			
			sb.append(totalCost - leastCost).append("\n");
		}
		
		
		System.out.println(sb);
		
		
	}
	
	static int find(int a) {
		if(a == arr[a]) {
			return a;
		} else {
			return arr[a] = find(arr[a]); 
		}
	}
	
	static void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		arr[A] = arr[B] = Math.min(A, B);
	}
	
	static class Line implements Comparable<Line>{
		int from;
		int to;
		int cost;
		public Line(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Line o) {
			
			return this.cost-o.cost;
		}
		
	}
}




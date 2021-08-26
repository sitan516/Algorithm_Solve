package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1197_최소스패닝트리 {
	
	static int V;
	static int E;
	static int parent[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parent = new int[V];
		Queue<Line> q = new PriorityQueue<Line>();
		
		for (int i = 0; i < V; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			
			q.add(new Line(a,b,c));
		}
		int lineCnt = 0;
		int sum = 0;
		while(lineCnt != V-1) {
			
			Line l = q.poll();
			
			if(find(l.from) == find(l.to)) {
				continue;
			} else {
				union(l.from, l.to);
				sum += l.cost;
				lineCnt++;
			}
			
		}

		System.out.println(sum);
		
	}
	
	private static void union(int a, int b) {
		int aP = find(a);
		int bP = find(b);
		parent[aP] = parent[bP] = Math.min(aP, bP);
	}

	private static int find(int i) {
		if(parent[i] == i) return i;
		return parent[i] = find(parent[i]);
	}

	static class Line implements Comparable<Line>{
		int from;
		int to;
		int cost;
		@Override
		public int compareTo(Line o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
		public Line(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	

	
	
	
}

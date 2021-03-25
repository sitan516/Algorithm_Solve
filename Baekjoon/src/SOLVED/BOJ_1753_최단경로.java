package SOLVED;

import java.io.*;
import java.util.*;

// (0,0) 으로부터 시작하여 DFS로 탐색.


public class BOJ_1753_최단경로 {
	
	static int V;
	static int E;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
				
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(br.readLine()) - 1;
		int lastConnected = start;
		int[] shortcut = new int[V];
		
		Map<Integer, Integer> lines[] = new HashMap[V]; 
		for (int i = 0; i < V; i++) {
			lines[i] = new HashMap<Integer, Integer>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			
			lines[from].computeIfPresent(to, (key, value) -> Math.min(value, weight));
			lines[from].putIfAbsent(to, weight);
		}
		
		
		Queue<Line> queue = new PriorityQueue<Line>();
		
		for (int i = 0; i < V; i++) {
			for (Integer to : lines[lastConnected].keySet()) {
				if(shortcut[to] == 0 && to != start) {
					queue.offer(new Line(lastConnected, to, shortcut[lastConnected] + lines[lastConnected].get(to)));					
				}
			}
			
			Line next = null;
			while(!queue.isEmpty()) {
				next = queue.poll();
				if(shortcut[next.to] == 0) {
					break;
				}
				next = null;
			}
			
			if(next == null) {
				break;
			}
			
			shortcut[next.to] = next.d;
			lastConnected = next.to;
		}
		
		for (int i = 0; i < V; i++) {
			if(i != start && shortcut[i] == 0) {
				sb.append("INF").append("\n");
			} else {				
				sb.append(shortcut[i]).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
}
class Line implements Comparable<Line>{
	int from;
	int to;
	int d;
	public Line(int from, int to, int d) {
		super();
		this.from = from;
		this.to = to;
		this.d = d;
	}
	@Override
	public int compareTo(Line o) {
		// TODO Auto-generated method stub
		return d - o.d;
	}
	
}

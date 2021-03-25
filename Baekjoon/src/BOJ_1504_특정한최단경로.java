import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1504_특정한최단경로{
	
	static int answer = Integer.MAX_VALUE;
	static Map<Integer, Integer> path[];
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		path = new HashMap[N];
		
		for (int i = 0; i < N; i++) {
			path[i] = new HashMap<Integer, Integer>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			path[a].put(b, c);
			path[b].put(a, c);
		}
		
		st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken()) - 1;
		int b = Integer.parseInt(st.nextToken()) - 1;

		int[][] shortcut = new int[N][N];
		
		getShortcut(shortcut, 0, a, b);
		getShortcut(shortcut, a, a, b);
		getShortcut(shortcut, b, a, b);
		
		if(shortcut[0][a] == 0 && a!=0) {
			System.out.println(-1);
			return;
		}
		
		if(shortcut[0][b] == 0 && b!=0) {
			System.out.println(-1);
			return;
		}
		
		if(shortcut[0][N-1] == 0 && N-1!=0) {
			System.out.println(-1);
			return;
		}
		
		// start - a - b - end
		// start - b - a - end 두개 경우만 고려하면 됨
		
		int first = shortcut[0][a] + shortcut[a][b] + shortcut[b][N-1];
		int second = shortcut[0][b] + shortcut[b][a] + shortcut[a][N-1];
			
		System.out.println(Math.min(first, second));
	}
	

	// 다익스트라
	// 현재 갈 수 있는 곳 중 가장 가까운 곳으로 가기
	static void getShortcut(int[][] shortcut, int start, int a, int b) {
		
		PriorityQueue<Next> pq = new PriorityQueue<Next>();
		int lastOffer = start;
		
		for (int find = 0; find < shortcut.length-1; find++) {
			// 다음 갈 곳 정보
			int length = Integer.MAX_VALUE;
			int to = -1;
			int cnt = 0;
				
			// 새로 연결된 곳에서 갈 수 있는곳을 pq에 추가
			for (Integer key : path[lastOffer].keySet()) {
				if(key != start && shortcut[start][key] == 0) {
					length = shortcut[start][lastOffer] + path[lastOffer].get(key);	
					pq.add(new Next(key, length));
				}
			}
			
			// pq에서 하나씩 꺼내면서 아직 안가본 애 찾음
			while(!pq.isEmpty()) {
				Next tempNext = pq.poll();
				if(shortcut[start][tempNext.to] == 0) {
					length = tempNext.length;
					to = tempNext.to;
					break;
				}
			}
			
			// 어차피 목적지는 이거 세개 중에 하나임
			if(to == a || to == b || to == N-1) {
				cnt++;
				if(cnt >= 3) {
					return;
				}
			}
			
			if(to == -1) {
				return;
			}
			
			shortcut[start][to] = length;
			lastOffer = to;
		}
		
	}
	
	
}

class Next implements Comparable<Next>{
	int to;
	int length;
	@Override
	public int compareTo(Next o) {
		return length - o.length;
	}
	public Next(int to, int length) {
		super();
		this.to = to;
		this.length = length;
	}
	
}

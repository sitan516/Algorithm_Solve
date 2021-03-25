import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1043_거짓말{
	
	static int parent[];
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// -1은 비밀을 아는 사람
		// 나머지는 같이 파티한 사람 중 번호가 가장 낮은 사람
		
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		int P = Integer.parseInt(st.nextToken()); // 아는 사람 수
		for (int i = 0; i < P; i++) {
			parent[Integer.parseInt(st.nextToken()) - 1] = -1; 
		}
 
		int[] host = new int[M];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int temp[] = new int[Integer.parseInt(st.nextToken())];
			for (int j = 0; j < temp.length; j++) {
				temp[j] = Integer.parseInt(st.nextToken()) - 1;
			}
			union(temp);
			host[i] = temp[0];
		}
		
		int answer = M - count(host);
		
		System.out.println(answer);
	}
	
	static int count(int[] host) {
		int cnt = 0;
		for (int i = 0; i < host.length; i++) {
			if(parent[find(host[i])] == -1) {
				cnt++;
			}
		}
		return cnt;
	}
	
	static void union(int[] temp) {
		Arrays.sort(temp);
		
		int standard = temp[0];
		
		for (int i = 1; i < temp.length; i++) {
			if(find(standard) != find(temp[i])) {
				parent[find(standard)] = parent[find(temp[i])] = Math.min(parent[find(standard)], parent[find(temp[i])]);
			}
		}
		
	}
	
	static int find(int i) {
		if(i == parent[i] || parent[i] == -1) {
			return i;
		} else {
			return parent[i] = find(parent[i]);
		}
		
	}
}


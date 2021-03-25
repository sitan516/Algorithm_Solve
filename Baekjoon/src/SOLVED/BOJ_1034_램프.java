package SOLVED;

// 1 0 0
// 1 0 1
// 1 0 0

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class BOJ_1034_램프 {
	
	static int N;
	static int M;
	static int K;
	static String map[];
	static int max = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new String[N];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine();
		}
		
		K = Integer.parseInt(br.readLine());
		
		// 알고리즘 구현
		// 초기 상태가 다르면 같이 켜질 수 없음
		
		Map<String,Integer> m = new HashMap<String, Integer>();
		
		for (int i = 0; i < N; i++) {
			m.computeIfPresent(map[i], (key, value) -> value+1);
			m.putIfAbsent(map[i], 1);
		}
		
		List<Entry<String,Integer>> list = new ArrayList<>(m.entrySet());
		list.sort(Entry.comparingByValue((o1,o2) -> o2-o1));
		
		for (Entry<String, Integer> entry : list) {
			if(canSwitch(entry.getKey())) {
				System.out.println(entry.getValue());
				return;
			}
		}
		System.out.println(0);
		return;
	}
	
	static boolean canSwitch(String num) {
		int cnt = 0;
		for (int i = 0; i < num.length(); i++) {
			if(num.charAt(i) == '0') cnt++;
		}
		if(cnt <= K && (cnt-K) % 2 == 0) return true;
		return false;
	}
	
	
	
}



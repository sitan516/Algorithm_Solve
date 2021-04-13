import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
	
	static int R;
	static int C;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		int S = Integer.parseInt(br.readLine());
		
		int[] store = new int[S+1];
		/*
		 1  2  3  4  5
		 14		     6
		 13		     7
		 12	11 10 9  8
		*/
		for (int i = 0; i <= S; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			
			if(dir == 1) {
				store[i] = dis;
			} else if(dir == 2) {
				store[i] = C + R + (C - dis);
			} else if(dir == 3) {
				store[i] = 2*C + R + (R - dis);
			} else if(dir == 4) {
				store[i] = C + dis;
			}
		}

		int patrol = store[S];
		
		int sum = 0;
		
		for (int i = 0; i < S; i++) {
			sum += Math.min(getClock(store[i], patrol), getCounterClock(store[i], patrol));
		}
		
		System.out.println(sum);

	}

	private static int getCounterClock(int store, int patrol) {
		int res = patrol - store;
		return res >= 0 ? res : res + 2*R + 2*C;
	}

	private static int getClock(int store, int patrol) {
		int res = store - patrol;
		return res >= 0 ? res : res + 2*R + 2*C;
	}

	

	
	
	
	
}

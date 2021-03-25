package SOLVED;

// BOJ_9935_문자열폭발

import java.io.*;
import java.util.*;

// 하나씩 폭발시키면 시간초과
// bomb하고 비교하다가 bomb의 첫글자랑 같은 애가 나오면 걔가 bomb인지 먼저 검사

public class BOJ_15686_치킨배달 {
	
	static int N;
	static int M;
	static int[][] map;
	
	static int[] chickinR;
	static int[] chickinC;
	static int[] houseR;
	static int[] houseC;
	
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		
		int chickNum = 0;
		int houseNum = 0;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 2) {
					chickNum++;
				} else if(map[r][c] == 1) {
					houseNum++;
				}
			}
		}
		
		
		houseR = new int[houseNum];
		houseC = new int[houseNum];
		chickinR = new int[chickNum];
		chickinC = new int[chickNum];
		int houseIdx = 0;
		int chickIdx = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c] == 1) {
					houseR[houseIdx] = r;
					houseC[houseIdx] = c;
					houseIdx++;
				} else if(map[r][c] == 2) {
					chickinR[chickIdx] = r;
					chickinC[chickIdx] = c;
					chickIdx++;
				}
			}
		}
		
		
		getCombination(0,0,0);
		
		System.out.println(min);
	}
	
	static void getCombination(int cnt, int idx, int bitmask) {
		if(cnt == M) {
			min = Math.min(calculateChicken(bitmask), min);
		} else {		
			for (int i = idx; i < chickinR.length; i++) {	
				getCombination(cnt+1, i+1, bitmask | 1<<i);
			}
		}
	}
	
	static int calculateChicken(int bitmask) {
		
		int sum = 0;
		
		for (int i = 0; i < houseR.length; i++) {
			
			if(sum > min) return Integer.MAX_VALUE;
			
			int localMin = Integer.MAX_VALUE;
			
			for (int j = 0; j < chickinR.length; j++) {
				if((bitmask & 1<<j) != 0) {
					int localSum = 0;
					localSum += Math.abs(chickinR[j] - houseR[i]);
					localSum += Math.abs(chickinC[j] - houseC[i]);
					localMin = Math.min(localSum, localMin);
				}
			}
			
			sum += localMin;
		}
		
		return sum;
	}
	
}



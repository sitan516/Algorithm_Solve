package SOLVED;
// Main_백준_2580_스도쿠_

import java.io.*;
import java.util.*;

public class BOJ_2116_주사위쌓기 {
	
	// 0 <-> 5
	// 1 <-> 3
	// 2 <-> 4
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] dices = new int[N][6];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 6; j++) {
				dices[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		int max = 0;
		
		for (int start = 0; start < 6; start++) {
			
			int topNum = dices[0][start];
			int topIdx;
			int cnt = getMaxByTopIdx(dices, start, 0);
			
			for (int diceIdx = 1; diceIdx < N; diceIdx++) {
				
				topIdx = getTopIdxByBottom(dices, topNum, diceIdx);
				cnt += getMaxByTopIdx(dices, topIdx, diceIdx);
				topNum = dices[diceIdx][topIdx];
				
			}
			max = Math.max(max, cnt);
		}
		
		
		
		
		
		System.out.println(max);

	}
	
	static int getTopIdxByBottom(int[][] dices,int bottomNum,int diceIdx) {
		
		for (int i = 0; i < 6; i++) {
			if(dices[diceIdx][i] == bottomNum) {
				switch(i) {
				case 0: return 5;
				case 1: return 3;
				case 2: return 4;
				case 3: return 1;
				case 4: return 2;
				case 5: return 0;
				}
			}
		}
		return -1;
	}
	
	static int getMaxByTopIdx(int[][] dices, int topIdx, int diceIdx) {	
		if(topIdx == 0 || topIdx == 5) {
			return Math.max(Math.max(dices[diceIdx][1], dices[diceIdx][2]), Math.max(dices[diceIdx][3], dices[diceIdx][4]));
		} else if(topIdx == 1 || topIdx == 3) {
			return Math.max(Math.max(dices[diceIdx][0], dices[diceIdx][2]), Math.max(dices[diceIdx][5], dices[diceIdx][4]));			
		} else {
			return Math.max(Math.max(dices[diceIdx][0], dices[diceIdx][1]), Math.max(dices[diceIdx][5], dices[diceIdx][3]));						
		}
	}
	
}
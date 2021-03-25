package SOLVED;
// Main_백준_2580_스도쿠_조규홍

import java.io.*;
import java.util.*;

public class BOJ_2580_스도쿠 {
	
	// 스도쿠를 저장
	static int[][] board = new int[9][9];
	
	// 각각 가로열, 세로열, 3*3 칸에 있는 숫자를 bit를 이용해 나타냄
	// 만약 가로열에 1,3 ,6 이 있으면 00010001010
	static int[] horizon = new int[9];
	static int[] vertical = new int[9];
	static int[] box = new int[9];
	
	// 정답을 찾으면 true
	static boolean flag = false;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int r = 0; r < 9; r++) {
			st = new StringTokenizer(br.readLine());			
			for (int c = 0; c < 9; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if(board[r][c] != 0) setMemo(r,c,board[r][c]);
			}
		}
		
		solve();

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				sb.append(board[r][c]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	static void solve() {
	
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				
				// 0이면 넣을 수 있는 값을 넣고 DFS진행.
				// 아무것도 안들어가면 뒤로 돌아간다.
				if(board[r][c] == 0) {
					for (int i = 1; i < 10; i++) {					
						if(isOkay(r,c,i)) {
							setMemo(r,c,i);
							board[r][c] = i;
							solve();
							if(flag) return;
							board[r][c] = 0;
							setMemoZero(r,c,i);
						}
					}
					return;
				}
				
			}
		}
		
		flag = true;
		
	}
	
	// bit로 나타낸 배열에서 i를 지움
	static void setMemoZero(int r, int c, int i) {
		horizon[r] -= 1 << i;
		vertical[c] -= 1 << i;
		box[r/3*3 + c/3] -= 1<< i;
	}
	
	// bit로 나타낸 배열에 i를 더함
	static void setMemo(int r, int c, int i) {
		horizon[r] |= 1 << i;
		vertical[c] |= 1 << i;
		box[r/3*3 + c/3] |= 1<< i;
	}
	
	// 넣을수 있는지 확인
	static boolean isOkay(int r,int c,int i) {
		if((horizon[r] & 1 << i) != 0) return false;
		if((vertical[c] & 1 << i) != 0) return false;
		if((box[r/3*3 + c/3] & 1 << i) != 0) return false;
		return true;
	}
}
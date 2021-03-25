package SOLVED;
// Main_백준_10830_행렬제곱_조규홍

import java.io.*;
import java.util.*;

public class BOJ_10830_행렬제곱 {
	
	static int[][] original; // 오리지날이 저장되요. 딱히 필요는 없음
	static int[][] matrix; // 결과값을 저장할 배열
	static int[][][] poweredMatrix; // [i][j][k] 2^i 한 행렬 [j][k]가 저장됨
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		original = new int[N][N];
		poweredMatrix = new int[40][N][N];
		matrix = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				original[i][j] = Integer.parseInt(st.nextToken()) % 1000;
				if(i ==j) matrix[i][j] = 1;
				else matrix[i][j] = 0;                 
				poweredMatrix[0][i][j] = original[i][j];
			}
		}

		// 2^40 번째 값까지 구하기. Math.log2(B) 이런거 쓰면 좀 줄일 수는 있음
		for (int i = 1; i < 40; i++) {
			getPoweredMatrix(i);
		}
		
		// B를 bit연산 해가면서 1이면 곱하고, 아니면 말고
		int idx = 0;
		while(B!=0) {
			if((B & 1) == 1) {
				mulMatrix(idx);
			}
			B = B >> 1;
			idx++;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(matrix[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	// 두 행렬의 곱을 구하는 메서드
	static void mulMatrix(int p){
		int temp[][] = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int i = 0; i < N; i++) {
					temp[r][c] += matrix[r][i] * poweredMatrix[p][i][c];
				}
				temp[r][c] %= 1000;
			}
		}
		matrix = temp;
	}
	
	// 2^n 제곱한 행렬을 구하는 메서드
	static void getPoweredMatrix(int p) {	
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int i = 0; i < N; i++) {
					poweredMatrix[p][r][c] += poweredMatrix[p-1][r][i] * poweredMatrix[p-1][i][c];
				}
				poweredMatrix[p][r][c] %= 1000;
			}
		}
	}
	
	
}
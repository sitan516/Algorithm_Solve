package SOLVED;

// Main_백준_1780_종이의개수

import java.io.*;
import java.util.*;

public class BOJ_1780_종이의개수 {

	static int N;
	static int[][] paper;

	static int minus = 0;
	static int zero = 0;
	static int plus = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		checkSame(0,0,N);
		
		System.out.println(minus);
		System.out.println(zero);
		System.out.println(plus);

	}

	static void checkSame(int r, int c, int len) {

		int temp = paper[r][c];
		boolean same = true;

		checkLoop: for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				if (paper[i][j] != temp) {
					same = false;
					break checkLoop;
				}
			}
		}

		if (same) {
			if (temp == 0)
				zero++;
			else if (temp == -1)
				minus++;
			else if (temp == 1)
				plus++;
		}

		else {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					checkSame(r+i * len/3, c+j * len/3 ,len/3);
				}
			}
		}

	}

}
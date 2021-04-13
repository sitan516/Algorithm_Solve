import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Solution {

	
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
				
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			
			int map[][] = new int[N][N];
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cnt = 0;
			
			// 높이가 바뀌면 낮은쪽 높이가 연속으로 x개 존재해야함
			
			for (int r = 0; r < N; r++) {
				int serial = 1;
				int last = map[r][0];
				int underFlag = 0;
				
				boolean cntFlag = true;
				for (int c = 1; c < N; c++) {
					if(underFlag > 0 && serial == X) {
						serial = 0;
						underFlag--;
					}
					if(map[r][c] == last) {
						serial++;
					} else if(map[r][c] > last) {
						if(serial < X * (map[r][c] - last)) {
							cntFlag = false;
							break;
						}
						serial = 1;
						last = map[r][c];
					} else if(map[r][c] < last) {
						if(underFlag > 0) {
							cntFlag = false;
							break;
						}
						serial = 1;
						underFlag = last - map[r][c];
						last = map[r][c];
					}
				}
				if(underFlag > 0 && serial < X) cntFlag = false;
				if(cntFlag) cnt++;
			}
			
			for (int c = 0; c < N; c++) {
				int serial = 1;
				int last = map[0][c];
				int underFlag = 0;
				boolean cntFlag = true;
				for (int r = 1; r < N; r++) {
					if(underFlag > 0 && serial == X) {
						serial = 0;
						underFlag--;
					}
					if(map[r][c] == last) {
						serial++;
					} else if(map[r][c] > last) {
						if(serial < X * (map[r][c] - last)) {
							cntFlag = false;
							break;
						}
						serial = 1;
						last = map[r][c];
					} else if(map[r][c] < last) {
						if(underFlag > 0) {
							cntFlag = false;
							break;
						}
						serial = 1;
						underFlag = last - map[r][c];
						last = map[r][c];
					}
				}
				if(underFlag > 0 && serial < X) cntFlag = false;
				if(cntFlag) cnt++;
			}
			
			sb.append("#").append(testCase).append(" ").append(cnt).append("\n").append("\n");
			
			
		
		}
			
		System.out.println(sb);
		
		

		
	}
	
}

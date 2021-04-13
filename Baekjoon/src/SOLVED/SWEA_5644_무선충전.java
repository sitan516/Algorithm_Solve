package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class SWEA_5644_무선충전 {

	static int M;
	static int A;
	
	static int[] moveA;
	static int[] moveB;
	
	static BC[] bc;
	
	static int[] dirY = {0, -1, 0, 1, 0};
	static int[] dirX = {0, 0, 1, 0, -1};
	
	static int[][] map;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			bc = new BC[A];
			map = new int[10][10];
			
			moveA = new int[M+1];
			moveB = new int[M+1];
			moveA[0] = moveB[0] = 0;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bc[i] = new BC(y, x, c, p);
			}
			
			//============입력 끝===============
			
			for (int i = 0; i < A; i++) {
				for (int r = bc[i].y - bc[i].C; r <= bc[i].y + bc[i].C ; r++) {
					for (int c = bc[i].x - bc[i].C; c <= bc[i].x + bc[i].C; c++) {
						if(r < 0 || r > 9 || c < 0 || c > 9) {
							continue;
						} 
						if(Math.abs(bc[i].y - r) + Math.abs(bc[i].x - c) <= bc[i].C ) {
							map[r][c] += 1 << i;
						}
					}
				}
			}
			
			
			int[] posA = {0, 0};
			int[] posB = {9, 9};
			int sum = 0;
			// 새로운 위치를 구함
			// 해당되는 BC 값 중 가장 큰 값을 찾는다.
			// 겹치면 두번째 중에 큰 값을 찾는다.
			// 두번째 값이 없으면 없는대로 한다.
			for (int time = 0; time <= M; time++) {
				
				posA[0] += dirY[moveA[time]];
				posA[1] += dirX[moveA[time]];
				posB[0] += dirY[moveB[time]];
				posB[1] += dirX[moveB[time]];
				
				int bit[] = {map[posA[0]][posA[1]], map[posB[0]][posB[1]]};
				
				int max[] = {0, 0};
				int idx[] = {-1, -1};
				int secondMax = 0;
				
				for (int i = 0; i < A; i++) {
					for (int j = 0; j < 2; j++) {
						if((bit[j] & 1 << i) != 0) {
							if (max[j] < bc[i].P) {
								secondMax = Math.max(secondMax, max[j]);
								max[j] = bc[i].P;
								idx[j] = i;							
							} else {
								secondMax = Math.max(secondMax, bc[i].P);							
							}
							
						}
					}				
				}
				
				if(idx[0] != idx[1]) {
					sum += max[0] + max[1];
				} else {
					sum += max[0] + secondMax;
				}
				
				
			}
			sb.append("#").append(testCase).append(" ").append(sum).append("\n");
			
		}
		
		

		System.out.println(sb);
	}
	
}
class BC{
	int y;
	int x;
	int C;
	int P;
	public BC(int y, int x, int c, int p) {
		super();
		this.y = y;
		this.x = x;
		C = c;
		P = p;
	}
	
}
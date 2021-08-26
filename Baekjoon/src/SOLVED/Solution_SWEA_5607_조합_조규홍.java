package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Solution_SWEA_5607_조합_조규홍 {
	static int P = 1234567891;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		
		for (int testCase = 1; testCase <= T; testCase++) {
			
			
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
	         
			// A = N!
			// B = R!(N-R)!
			
			// (A % P) * (B^(P-2) % P)
			
			long A = 1;
			long B = 1;
			
			for (int i = 1; i <= N; i++) {
				A *= i;
				A %= P;
			}
			for (int i = 1; i <= R; i++) {
				B *= i;
				B %= P;
			}
			for (int i = 1; i <= N-R; i++) {
				B *= i;
				B %= P;
			}
			
			long answer = A * power(B, P-2);
	        answer %= P;    
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
			
		}
			
		System.out.println(sb);
	}

	static long power(long a, long b) {
		if(b==0) return 1;
		if(b%2 == 0) {
			long half = power(a,b/2);
			half %= P;
			return half * half % P;
		} else {
			return power(a, b-1) * a % P;
		}
	}
	
}

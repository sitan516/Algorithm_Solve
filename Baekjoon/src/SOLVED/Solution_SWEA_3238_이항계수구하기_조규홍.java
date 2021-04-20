package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Solution_SWEA_3238_이항계수구하기_조규홍 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		
		for (int testCase = 1; testCase <= T; testCase++) {
			
			
			st = new StringTokenizer(br.readLine());
			
			long N = Long.parseLong(st.nextToken());
			long R = Long.parseLong(st.nextToken());
			long P = Long.parseLong(st.nextToken());
		
			long[] fact = new long[(int) P];
			fact[0] = 1;
			
			for (int i = 1; i < P; i++) {
				fact[i] = (fact[i-1] * i) % P;
			}
			
			long res = 1;
			while(N > 0 || R > 0) {
				long n = N % P;
				long r = R % P;
				if(n < r) {
					res = 0;
					break;
				}
				res = (res * fact[(int) n]) % P;
				res = (res * pow((fact[(int) r] * fact[(int) (n - r)]) % P, P - 2, P)) % P;
				N /= P;
				R /= P;
			}
			
			sb.append("#").append(testCase).append(" ").append(res).append("\n");
			
		}
			
		System.out.println(sb);
	}

	static long pow(long a, long b, long p) {
		long answer = 1;
		while(b > 0) {
			if(b % 2 == 1){
				answer *= a;
				answer %= p;
			}
			a *= a;
			a %= p;
			b /= 2;
		}
		return answer;
	}
	
}

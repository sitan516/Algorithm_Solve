package SOLVED;
import java.io.*;
import java.util.*;

public class Solution_SWEA_5604_구간합_조규홍 {
	static long preSum[] = new long[17];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		preSum[0] = 1;
		preSum[1] = 45;

		for (int i = 2; i < preSum.length; i++) {
			preSum[i] = (long) (preSum[1] * ((i) * Math.pow(10, i-1)));
		}

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			long A = Long.parseLong(st.nextToken()) - 1;
			long B = Long.parseLong(st.nextToken());
			
			long a = F(A, (int) Math.floor(Math.log10(A)));
			long b = F(B, (int) Math.floor(Math.log10(B)));
			
			sb.append("#").append(tc).append(" ").append(b-a).append("\n");
			
		}

		System.out.println(sb);

	}

	static long F(long n, int v) {
		
		if(v==0) {
			return n*(n+1)/2;
		}
		
		if(n==0) return 0;
		long l = 0;
		long v10 = (long) Math.pow(10, v);
		long a = (n - n % v10); // 몇자리 수 인지
		long first = (n / v10); // 첫번째 자리 수
		if(first == 0) {
			l+= G(n,v);
		} else {
			l = preSum[(int) Math.floor(Math.log10(a))] * first;
			l += v10 * first * (first-1) / 2;
			l += G(n, v);
		}
		
		return l;
	}

	private static long G(long n, int v) {
		long v10 = (long) Math.pow(10, v);
		return n/v10 * (n%v10+1) + F((n%v10), v-1);				
	}

}

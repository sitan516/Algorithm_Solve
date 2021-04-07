import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class BOJ_11052_카드구매하기 {

	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int cardPack[] = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			cardPack[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];
		
		dp[0] = 0;
		
		
		for (int i = 1; i <= N; i++) {
			for (int j = i; j <= N; j++) {
				dp[j] = Math.max(dp[j], dp[j-i] + cardPack[i]); 					
			}
		}
		System.out.println(dp[N]);
		
	}
		
	
}

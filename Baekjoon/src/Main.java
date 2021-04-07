import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		String a = br.readLine();
		String b = br.readLine();
		
		
		
		int[][] dp = new int[a.length()+1][b.length()+1];
		
		for (int i = 1; i <= a.length(); i++) {
			boolean upFlag = false;
			for (int j = 1; j <= b.length(); j++) {
				if(a.charAt(i-1) == b.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1; 
				} else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		

		
		System.out.println(dp[a.length()][b.length()]);
		
	}
		
	
}

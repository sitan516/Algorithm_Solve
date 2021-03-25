package SOLVED;

import java.io.*;
import java.util.*;

public class Main_백준_1463_1로만들기_조규홍 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int target = Integer.parseInt(br.readLine());
		int[] dp = new int[target + 1];
		
		if(target == 1) {
			System.out.println(0);
			return;
		}
		
		if(target == 2) {
			System.out.println(1);
			return;
		}
		
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;

		for (int i = 4; i < dp.length; i++) {
			dp[i] = dp[i - 1] + 1;
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			}
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
		}

		System.out.println(dp[target]);
	}

}

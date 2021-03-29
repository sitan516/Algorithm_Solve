import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Check {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		long cnt = 0;
		long compare1;
		long compare2;
		long compare3;

		for (int i = 0; i < N - 2; i++) {
			compare1 = num[i];
			if (compare1 % K == 0) {
				cnt += (N - i - 1) * (N - i - 2) / 2;
				continue;
			} else {
				compare1 %= K;
			}

			for (int j = i + 1; j < N - 1; j++) {
				compare2 = compare1 * num[j];
				if (compare2 % K == 0) {
					cnt += (N - j - 1);
					continue;
				} else {
					compare2 %= K;
				}
				for (int k = j + 1; k < N; k++) {
					compare3 = compare2 * num[k];
					if (compare3 % K == 0) {
						cnt++;
					}
				}
			}
		}

		System.out.println(cnt);
	}

}

import java.io.*;
import java.util.*;

public class BOJ_2342_DanceDanceRevolution {

	static int[] step = new int[100001];
	static int[] dp;
	static int[] foot;
	static int[][] cost =
			{
					{0,2,2,2,2},
					{0,1,3,4,3},
					{0,3,1,3,4},
					{0,4,3,1,3},
					{0,3,4,3,1}
			};

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());

		int N = 0;
		while(true){
			step[N] = Integer.parseInt(st.nextToken());
			if(step[N++] == 0) break;
		}

		dp = new int[N];



		for (int i = 0; i < N; i++) {

		}



	}
}



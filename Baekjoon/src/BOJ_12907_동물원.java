import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_12907_동물원{
	
	static int[] arr;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		// 자신 보다 키가 큰 동물이 i만큼 많은 동물의 수
		int[] height = new int[42];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			height[Integer.parseInt(st.nextToken())]++;
		}
		
		// height[i] > 3 이면 불가능
		// height[i] < height[i+1] 이면 불가능
		// height[i] == 2 이면 *2
		// height[i] == 1 이면 *2 하고 더이상 answer는 변화 없음. 이후로는 불가능한지만 확인한다.

		int answer = 1;
		boolean finishFlag = false;
		
		for (int i = 0; i < 41; i++) {
			if(height[i] < height[i+1] || height[i] >= 3) {
				answer = 0;
			}
			if(!finishFlag) {
				if(height[i] == 2) {
					answer *= 2;
				} else if(height[i] == 1) {
					answer *= 2;
					finishFlag = true;
				} 
			}
		}
		System.out.println(answer);
	}
	
	
	
	
}




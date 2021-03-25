package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11054_가장긴바이토닉부분수열{
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 왼쪽에 있는 숫자 중
		// 자신보다 작은 숫자 중
		// 제일 큰 갯수를 저장
		int[] left = new int[N];
		left[0] = 1;
		for (int i = 1; i < N; i++) {
			int max = -1;
			for (int j = 0; j < i; j++) {
				if(nums[j] < nums[i]) {
					max = Math.max(max, left[j]);
				}
			}
			if(max == -1) {
				left[i] = 1;
			} else {
				left[i] = max + 1;				
			}
		}
		
		int[] right = new int[N];
		right[N-1] = 1;
		for (int i = N-1; i >= 0; i--) {
			int max = -1;
			for (int j = N-1; j > i; j--) {
				if(nums[j] < nums[i]) {
					max = Math.max(max, right[j]);
				}
			}
			if(max == -1) {
				right[i] = 1;
			} else {
				right[i] = max + 1;				
			}
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, left[i] + right[i]);
		}
		
		System.out.println(max-1);
	}
	
	
	
	
}


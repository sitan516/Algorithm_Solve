package SOLVED;

// BOJ_1083_소트

import java.io.*;
import java.util.*;

public class BOJ_1083_소트 {
	
	// 사전 순 제일 뒤
	// 높은 수를 앞으로 끌고 와야함
	// 바꿀 수 있는 자리 중에 가장 큰 자리랑 바꾸기
	// 바꾸면 손해 -> 안바꾸면 됨
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] numbers = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		int S = Integer.parseInt(br.readLine());
		
		int idx = 0;
		
		
		// S가 0이면 끝
		while(S > 0) {
			
			// 정렬이 끝나도 끝
			if(idx >= N-1) break;
			
			// 바꿀 수 있는 최대의 수 와 인덱스 찾기
			int maxIdx = idx;
			int maxValue = numbers[idx];
			for (int i = idx+1; i < (S+idx >= N ? N : S+idx+1); i++) {
				if(maxValue < numbers[i]) {
					maxValue = numbers[i];
					maxIdx = i;
				}
			}
			
			// 내가 최고인가...?
			if(maxIdx == idx) {
				idx++;
				continue;
			}
			
			// 최고가 아닐때 옮기기
			else {
				S -= maxIdx - idx;
				System.arraycopy(numbers, idx, numbers, idx+1, maxIdx-idx);
				numbers[idx] = maxValue;
				idx++;
				continue;
			}

		}
		
		for (int i = 0; i < N; i++) {
			sb.append(numbers[i]).append(" ");
		}
		System.out.println(sb);
		
	}
}



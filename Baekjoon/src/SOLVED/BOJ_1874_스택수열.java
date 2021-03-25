package SOLVED;

// BOJ_1874_스택수열

import java.io.*;
import java.util.*;

public class BOJ_1874_스택수열 {
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int order[] = new int[N];
		
		for (int i = 0; i < order.length; i++) {
			order[i] = Integer.parseInt(br.readLine())-1;
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		
		stack.push(0);
		int target = 0;
		int next = 1;
		sb.append("+").append("\n");
		
		while(true) {
			
			if(next > N) {
				System.out.println("NO");
				break;
			}
			
			if(target == N) {
				System.out.println(sb);
				break;
			}
			
			if(!stack.isEmpty() && stack.peek() == order[target]) {
				stack.pop();
				target++;
				sb.append("-").append("\n");
			}
			
			else {
				stack.add(next++);
				sb.append("+").append("\n");
			}
			
		}
		
	}
}



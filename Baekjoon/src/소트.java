import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class 소트 {
	
	
	// 1 2 -> 2 1
	// 1 2 3 -> 1 3 2
	// 1 2 3 4 -> 1 3 2 4
	// 1 2 3 4 5 -> 1 3 2 5 4
	// 132456 - > 132 | 465
	// 123456879 -> 132 | 465 | 798
	
	// 112334  1:2 2:1 3:2 4:1
	// 131324
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		int nums[] = new int[1002];
		
		for (int i = 0; i < N; i++) {
			nums[Integer.parseInt(st.nextToken())]++;
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] != 0) q.add(i);
		}
		
		while(!q.isEmpty()) {
			
			ArrayList<Integer> part = new ArrayList<Integer>();
			part.add(q.poll());
			
			for (int i = 1; i < 3; i++) {
				if(q.isEmpty()) break;
				if(q.peek() - part.get(i-1) == 1) {
					part.add(q.poll());					
				} else {
					break;
				}		
			}
			
			if(part.size() == 3){
				for (int j = 0; j < nums[part.get(0)]; j++) {
					sb.append(part.get(0)).append(" ");
				}
				for (int j = 0; j < nums[part.get(2)]; j++) {
					sb.append(part.get(2)).append(" ");
				}
				for (int j = 0; j < nums[part.get(1)]; j++) {
					sb.append(part.get(1)).append(" ");
				}
			}
			
			else if(part.size() == 2) {
				for (int j = 0; j < nums[part.get(1)]; j++) {
					sb.append(part.get(1)).append(" ");
				}
				for (int j = 0; j < nums[part.get(0)]; j++) {
					sb.append(part.get(0)).append(" ");
				}
				
			}
			
			else if(part.size() == 1) {
				for (int j = 0; j < nums[part.get(0)]; j++) {
					sb.append(part.get(0)).append(" ");
				}
			}
			
		}
		
		
		System.out.println(sb);
	}

	
	

	
	
	
	
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18870_좌표압축{
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int nums[] = new int[N];
		int nums_sorted[] = new int[N];
		
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			nums_sorted[i] = nums[i];
		}
		
		Arrays.sort(nums_sorted);
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int value = 0;
		for (int i = 0; i < nums_sorted.length; i++) {
			if(!map.containsKey(nums_sorted[i])) {
				map.put(nums_sorted[i], value++);				
			}
		}
		
		for (int i = 0; i < nums.length; i++) {
			nums[i] = map.get(nums[i]);
			sb.append(nums[i]).append(" ");
		}
		
		
		
		System.out.println(sb);
	}
	
	
	
	
}


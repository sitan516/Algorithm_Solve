package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1759_암호만들기{
	
	static int L;
	static int C;
	static char nums[];
	static boolean vowel[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		nums = new char[C];
		vowel = new boolean[C];
		for (int i = 0; i < C; i++) {
			nums[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(nums);
		
		for (int i = 0; i < C; i++) {
			if(nums[i] == 'a' || nums[i] == 'e' || nums[i] == 'i' || nums[i] == 'o' || nums[i] == 'u') {
				vowel[i] = true;
			}
		}
		
		comb(0, 0, 0);
		
		System.out.println(sb);
	}
	
	
	static void comb(int cnt, int idx, int bitmask) {
		
		if(cnt == L) {
			int vow = 0; // 모음의 갯수
			StringBuilder s = new StringBuilder();
			
			for (int i = 0; i < C; i++) {
				if((bitmask & 1<<i) != 0) {	
					if(vowel[i]) vow++;
					s.append(nums[i]);
				}
			}
			
			int con = L-vow;
			
			if(vow >= 1 && con >= 2) {
				System.out.println(s);
			}
			
			return;
		}
		
		for (int i = idx; i < C; i++) {
			comb(cnt+1, i+1, bitmask | 1 << i);
		}
		
	}
	
	
	
}





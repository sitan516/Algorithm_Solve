package SOLVED;



// bnb

// Main_백준_1053_팰린드롬공장

// 추가 = 삭제 --> 추가해서 될꺼면 삭제해도 된다. (둘 중 하나만 생각해도 됨)
// 서로 다른 문자 교환은 처음 한 번만 해도 된다.
// 이후에 교환 vs 삭제 무엇이 이득인지 보면 됨.

//asddsxab
//asddxa -> 교환이 이득인 경우
//aaaaaaf -> 삭제가 이득인 경우

//BFS 탐색으로 찾을거임
// 처리 방법
// 1. 이상한 부분 탐색
// 2. 두 문자 중 하나 제거 (2)
// 3. 두 문자 중 하나 교환 (2)
// 4. 1로 돌아가기
import java.io.*;
import java.util.*;

public class BOJ_1053_팰린드롬공장 {
	
	static Set<String> set;
	static Queue<String> qs;
	static Queue<Integer> qn;
	static int N;
	static String str;
	static String p;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//StringTokenizer st;

		str = br.readLine();
		N = str.length();
		qs = new LinkedList<String>();
		qn = new LinkedList<Integer>();
		set = new HashSet<String>();
		
		qs.add(str);
		qn.add(1);
		
		set.add(str);
		getCombination();
		calculate(str);
		
		System.out.println(answer);
	}
	
	static void calculate(String s) {
		
		int[][] dp = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				if(i==j){
					dp[i][j] = 0;
				}else if(j-i == 1) {
					dp[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
				} else {
					dp[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		for (int len = 2; len < N; len++) {
			for (int i = 0; i < N-len; i++) {
				int j = i+len;
				int min = Integer.MAX_VALUE;
				min = Math.min(dp[i+1][j]+1, dp[i][j-1]+1);
				if(s.charAt(i) == s.charAt(j)) {
					min = Math.min(dp[i+1][j-1], min);
				} else {
					min = Math.min(dp[i+1][j-1]+1, min);
				}
				dp[i][j] = min;
			}
		}
		
		int localAnswer = dp[0][N-1];
		if(!str.equals(s))localAnswer++;
		answer = Math.min(localAnswer, answer);
		
	}
	
	static void getCombination() {
		char temp;
		char[] strArr = str.toCharArray();
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				temp = strArr[i];
				strArr[i] = strArr[j];
				strArr[j] = temp;
				String tempStr = toStr(strArr);
				if(!set.contains(tempStr)) {
					set.add(tempStr);
					calculate(tempStr);
				}
				strArr[j] = strArr[i];
				strArr[i] = temp;
			}
		}
		
		
	}
	
	static String toStr(char[] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}
		return sb.toString();
	}

}

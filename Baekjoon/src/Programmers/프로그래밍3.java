package Programmers;
import java.util.*;

public class 프로그래밍3 {

	public static void main(String[] args) {
		
		Solution sol = new Solution();
		int[] g = {70,70,0};
		int[] s = {0,0,500};
		int[] w = {100,100,2};
		int[] t = {4,8,1};
		long answer  = sol.solution(90, 500, g,s,w,t);
		System.out.println(answer);
		

	}
	
	
	
}


//은을 가져올지 금을 가져올지 선택하는 알고리즘
// 그냥 이분탐색?
// T 라는 시간 동안 가져올 수 있는 횟수는 n = (T/t -1) / 2 + 1 
// 가져오는 자원량은  m = w * n 단, 최댓값 고려 해야함
// g+s <= m 이면, m = g+s
// 아니면, 생각해야함

// 일단 다 가져왔는데 a+b 보다 작으면 false

// a+b 보다 크면 생각해야함
// 각자한테 더 적은 것 부터 가져오기


// 은을 다 가져오는데 걸리는 시간 
// 금을 다 가져오는데 걸리는 시간



// Sm - Sg = Ss 

// a와 Sg 비교, b와 Ss 비교
// 



// 전부 나르는데 필요한 시간이 짧은 것 우선?

// 가져올 수 있는 녀석의 최대가 큰거 부터 가져오기?

// 금이 9 은이 2 필요
// 하나는 금이 200개 은이 200개 갔다오는데 10
// 하나는 금이 1개 은이 1개 갔다오는데 1

//0 ≤ a, b ≤ 10^9
//1 ≤ g의 길이 = s의 길이 = w의 길이 = t의 길이 = 도시 개수 ≤ 10^5
//0 ≤ g[i], s[i] ≤ 10^9
//1 ≤ w[i] ≤ 10^2
//1 ≤ t[i] ≤ 10^5
//a ≤ g의 모든 수의 합
//b ≤ s의 모든 수의 합

//각 도시에서 금을 얼마나 가져갈 것인지
// 무조건 금반, 은반 챙기기 ? 다른게 없으면 말고
// 마지막에 은을 위해서 움직였다?
// 금 : 은 = 7.5 : 2.5 로 챙기기
// 반복

// 

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        
        long l = 0;
        long r = (long) 1e17;
        
	    // 은을 다 가져오는데 걸리는 시간 
        while(l<r) {
        	long mid = (l+r) / 2;
        	if(canFetch(mid,b,s,w,t)) {
        		r = mid;
        	}else {
        		l = mid + 1;
        	}
        }
        long S = r;
        
        
        l = 0;
        r = (long) 1e17;
        
	    // 금을 다 가져오는데 걸리는 시간
        while(l<r) {
        	long mid = (l+r) / 2;
        	if(canFetch(mid,a,g,w,t)) {
        		r = mid;
        	}else {
        		l = mid + 1;
        	}
        }
        long G = r;
        
        l = 0;
        r = (long) 1e17;
        // 최대한 가져와보자
        while(l<r) {
        	long mid = (l+r) / 2;
        	if(canFetch(mid,a+b,g,s,w,t)) {
        		r = mid;
        	}else {
        		l = mid + 1;
        	}
        }
        answer = r;
        answer = Math.max(answer, G);
        answer = Math.max(answer, S);

        return answer;
    }
    
    
    boolean canFetch(long T, int need, int[] g, int[] s, int[] w, int[] t) {
    	long sum = 0;
    	for (int i = 0; i < t.length; i++) {
			long n = t[i] > T ? 0 : (T/t[i] - 1) / 2 + 1;
			long m = w[i] * n;
			sum += m >= s[i] + g[i] ? s[i]+g[i] : m;
		}
    	if (sum >= need) return true; 
    	else return false;
    }
    
//   	T 라는 시간 동안 가져올 수 있는 횟수는 n = (T/t -1) / 2 + 1
// 		가져오는 자원량은  m = w * n 단, 최댓값 고려 해야함
// 		g+s <= m 이면, m = g+s
    boolean canFetch(long T, int b, int[] s, int[] w, int[] t) {
    	long sum = 0;
    	for (int i = 0; i < t.length; i++) {
			long n = t[i] > T ? 0 : (T/t[i] - 1) / 2 + 1;
			long m = w[i] * n;
			sum += m >= s[i] ? s[i] : m;
		}
    	if (sum >= b) return true; 
    	else return false;
    }
    
}










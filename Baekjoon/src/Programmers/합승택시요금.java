package Programmers;
import java.util.*;


public class 합승택시요금 {

	public static void main(String[] args) {
		
		Solution1 sol = new Solution1();
		int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		int answer  = sol.solution(6,4,6,2,fares);
		System.out.println(answer);
	}
	
	
	
}


// s -> a + a -> b
// s -> b + b -> a
// s -> a + s -> b

// 4 -> 1 -> 5
// 5 -> 6
// 5 -> 3 -> 2
class Solution1 {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        
        int[][] shortcut = new int[n+1][n+1];
        
        for (int i = 0; i < shortcut.length; i++) {
			Arrays.fill(shortcut[i], 100000000);
		}
        
        for (int i = 0; i < fares.length; i++) {
			shortcut[fares[i][0]][fares[i][1]] = fares[i][2];
			shortcut[fares[i][1]][fares[i][0]] = fares[i][2];
		}
        
        for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if(i==j) {
						shortcut[i][j] = 0;
						continue;
					}
					shortcut[i][j] = Math.min(shortcut[i][k] + shortcut[k][j], shortcut[i][j]);
				}
			}
		}
        
        // s -> bye + bye -> a + bye -> b
        int answer = Integer.MAX_VALUE;
        
        for (int bye = 1; bye <= n; bye++) {
			int sum = shortcut[s][bye] + shortcut[bye][a] + shortcut[bye][b];
			answer = Math.min(answer, sum);
		}
        
        
        return answer;
    }
}
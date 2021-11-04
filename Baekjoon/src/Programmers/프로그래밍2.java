package Programmers;
import java.util.*;

public class 프로그래밍2 {

	public static void main(String[] args) {
		
		Solution2 sol = new Solution2();
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2,3,4};
		String[] answer  = sol.solution(orders, course);
		for (String string : answer) {
			System.out.println(string);
		}

	}
	
	
	
}



class Solution_프로그래밍2 {
	static boolean[][][] checked;
	static int N;
	static int M;
	static Queue<Integer> q = new LinkedList<Integer>();
	static List<Integer> answerList = new ArrayList<Integer>();
    public int[] solution(String[] grid) {
        int[] answer;
        
        N = grid.length;
        M = grid[0].length();
        checked = new boolean[N][M][4];
        for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				for (int dir = 0; dir < 4; dir++) {
					move(r,c,dir,0,grid);
					
					while(!q.isEmpty()) {
						int newR = q.poll();
						int newC = q.poll();
						int newDir = q.poll();
						int newDepth = q.poll();
						move(newR, newC, newDir,newDepth,grid);
					}
					
				}
			}
		}
        
        answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
			answer[i] = answerList.get(i);
		}
        Arrays.sort(answer);
        return answer;
    }
    
    
    
    
    void move(int r, int c, int dir, int depth, String[] grid) {
    	if(checked[r][c][dir]) {
    		if(depth == 0) return;
    		answerList.add(depth);
    		return;
    	}
    	checked[r][c][dir] = true;
    	char oper = getGrid(r,c,grid);
    	int newDir = getNextDir(dir, oper);
    	q.add(getNextR(r,newDir));
    	q.add(getNextC(c,newDir));
    	q.add(newDir);
    	q.add(depth+1);
    	
//    	move(getNextR(r,newDir), getNextC(c,newDir), newDir, depth+1, grid);
    }
    
    int getNextDir(int dir, char oper) {
    	if(oper == 'R') {
    		if(dir == 0) return 3;
    		if(dir == 1) return 2;
    		if(dir == 2) return 0;
    		if(dir == 3) return 1;
    	} else if(oper =='L') {
    		if(dir == 0) return 2;
    		if(dir == 1) return 3;
    		if(dir == 2) return 1;
    		if(dir == 3) return 0;
    	} else {
    		return dir;
    	}
    	return -1;
    }
    
    int getNextR(int r, int dir) {
    	if(dir == 2 || dir == 3) {
    		return r;
    	}else if(dir == 1) {
    		return r == N-1 ? 0 : r+1;    		
    	} else {
    		return r == 0 ? N-1 : r-1;
    	}
    }
    
    int getNextC(int c, int dir) {
    	if(dir == 0 || dir == 1) {
    		return c;
    	}else if(dir == 3) {
    		return c == M-1 ? 0 : c+1;    		
    	} else {
    		return c == 0 ? M-1 : c-1;
    	}
    }
    
    char getGrid(int r, int c, String[] grid) {
    	return grid[r].charAt(c);
    }
}


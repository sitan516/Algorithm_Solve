import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16946_벽부수고이동하기4 {
	
	static int[][] map;
	static int[][] canGo;
	static boolean[][] visited;
	static int n;
	static int m;
	static Map<Integer, Integer> sector = new HashMap<Integer, Integer>();
	static int key = 1;
	static int[] dx = {0, -1, 0 ,1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		canGo = new int[n][m];
		visited = new boolean[n][m];
		
		for (int r = 0; r < n; r++) {
			String line = br.readLine();
			for (int c = 0; c < m; c++) {
				map[r][c] = line.charAt(c) == '0' ? 0 : 1;
			}
		}
		
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if(map[r][c] == 1)continue;
				int v = divide(r,c);
				if(v != 0) {
					sector.put(key++, v);
				}
			}
		}
		
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if(map[r][c] == 0) {
					sb.append(0);
				}else {
					int sum = 1;
					Set<Integer> went = new HashSet<Integer>();
					for (int d = 0; d < 4; d++) {
						int newY = r + dy[d];
						int newX = c + dx[d];
						if(newY < 0 || newY >= n || newX < 0 || newX >= m) {
							continue;
						}
						if(went.contains(canGo[newY][newX]) || canGo[newY][newX] == 0) continue;
						sum += sector.get(canGo[newY][newX]);
						went.add(canGo[newY][newX]);
					}
					sb.append(sum%10);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	
	static int divide(int r, int c) {
		if(visited[r][c]) return 0;
		visited[r][c] = true;
		canGo[r][c] = key;
		int go = 1;
		for (int d = 0; d < 4; d++) {
			int newY = r + dy[d];
			int newX = c + dx[d];
			if(newY < 0 || newY >= n || newX < 0 || newX >= m) {
				continue;
			}
			if(map[newY][newX] == 0) {
				go += divide(newY, newX);								
			}
		}
		
		return go;
	}
	
	
	
}



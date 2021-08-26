package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_4386_별자리만들기 {
	
	static int[] parent;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		double result = 0;
		int n = Integer.parseInt(br.readLine());
		float[][] stars = new float[n][2];
		
		parent = new int[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			parent[i] = i;
			stars[i][0] = Float.parseFloat(st.nextToken()); 
			stars[i][1] = Float.parseFloat(st.nextToken()); 
		}
		
		PriorityQueue<Route> routes = new PriorityQueue<Route>();
		
		for (int i = 0; i < stars.length; i++) {
			for (int j = i+1; j < stars.length; j++) {
				routes.add(new Route(i,j,getDistance(stars[i][0], stars[i][1], stars[j][0], stars[j][1])));
			}
		}
		
		int cnt = 0;
		
		while(!routes.isEmpty()) {
			Route r = routes.poll();
			if(find(r.a) != find(r.b)) {
				union(r.a,r.b);
				result += r.distance;
				cnt++;
			}
			if(cnt == n-1) break;
		}
		
		System.out.println(result);
		
	}

	public static float getDistance(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a != b) {
			parent[a] = a > b ? b : a;
			parent[b] = a > b ? b : a;
		}
	}
	
	public static int find(int a) {
		if(a == parent[a]) {
			return a;
		} else {
			return parent[a] = find(parent[a]);			
		}
	}
	
	
}

class Route implements Comparable<Route>{
	int a;
	int b;
	double distance;
	
	public Route(int a, int b, float distance) {
		super();
		this.a = a;
		this.b = b;
		this.distance = distance;
	}

	@Override
	public int compareTo(Route o) {
		if(this.distance > o.distance) {
			return 1;
		} else if(this.distance == o.distance){
			return 0;
		} else {
			return -1;
		}
	}
	
}

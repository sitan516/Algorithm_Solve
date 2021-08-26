package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class BOJ_2577_ACMcraft {
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int testCase = 0; testCase < T; testCase++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			Task[] tasks = new Task[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				tasks[i] = new Task(Integer.parseInt(st.nextToken()), i);
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int before = Integer.parseInt(st.nextToken()) - 1;
				int after = Integer.parseInt(st.nextToken()) - 1;
				tasks[before].after.add(tasks[after]);
				tasks[after].before.add(tasks[before]);
			}
			
			int W = Integer.parseInt(br.readLine()) - 1;
			
			
			int time = 0;
			Queue<Task> q = new PriorityQueue<Task>();
			Set<Task> notStart = new HashSet<Task>();
			for (Task task : tasks) {
				notStart.add(task);
			}
			// before가 없으면 build할 수 있음.
			// task가 끝나면 after들이 시작할 수 있는지 확인하고 시작할 수 있으면 시작
			// task가 시작하면 queue에 끝나는 시간을 집어넣자.
			
			while(true) {
				// 시작
				Set<Task> toRemove = new HashSet<BOJ_2577_ACMcraft.Task>();
				for (Task task : notStart) {
					if(task.before.isEmpty() && !task.isStart) {
						task.finishTime = time + task.cost;
						task.isStart = true;
						toRemove.add(task);
						q.add(task);
					}
				}
				notStart.removeAll(toRemove);
				
				// 끝
				Task fin = q.poll();
				time = fin.finishTime;
				if(fin.num == W) break;
				for (Task i : fin.after) {
					i.before.remove(fin);
				}
			}
			
			sb.append(time).append("\n");
			
		}
		
		System.out.println(sb);
		
	}
	
	static class Task implements Comparable<Task>{
		int num;
		int cost;
		int finishTime;
		boolean isStart = false;
		List<Task> before = new LinkedList<Task>();
		List<Task> after = new LinkedList<Task>();
		
		public Task(int cost, int num) {
			super();
			this.cost = cost;
			this.num = num;
		}

		@Override
		public int compareTo(Task o) {
			return this.finishTime - o.finishTime;
		}
		
	}
}

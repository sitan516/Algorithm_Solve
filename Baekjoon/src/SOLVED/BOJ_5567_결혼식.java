package SOLVED;

// Main_백준_5567_결혼식

import java.io.*;
import java.util.*;

public class BOJ_5567_결혼식 {
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		Person[] persons = new Person[N];
		
		for (int i = 0; i < N; i++) {
			persons[i] = new Person();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			persons[a].friends.add(persons[b]);
			persons[b].friends.add(persons[a]);
		}
		
		// 여기부터 친구 수 구하기
		Iterator<Person> iterSang = persons[0].friends.iterator();
		int cnt = 0;
		while(iterSang.hasNext()) {
			
			Person friend = iterSang.next();
			
			if(!friend.counted) {
				cnt++;
			}
			friend.counted = true;
			for (int i = 0; i < friend.friends.size(); i++) {
				
				Person friend_friend = friend.friends.get(i);
				
				if(!friend_friend.counted) {
					friend_friend.counted = true;
					cnt++;
				}else {
					continue;
				}
			}
		}
		
		System.out.println(cnt-1);
		
	}
}

class Person{
	List<Person> friends = new LinkedList<Person>();
	boolean counted = false;
}

package Programmers;
import java.util.*;

public class 메뉴리뉴얼 {

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



class Solution2 {
	Map<String, Integer> group = new HashMap<String, Integer>();
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        List<String> answerList = new ArrayList<String>();
        
        // course 정렬
        for (int i = 0; i < orders.length; i++) {
			char[] charArr = orders[i].toCharArray();
			Arrays.sort(charArr);
        	orders[i] = new String(charArr);
		}
        
        // 만들 수 있는 세트메뉴 세기
        for (int c = 0; c < course.length; c++) {
        	for (int i = 0; i < orders.length; i++) {
        		setGroup(course[c], orders[i], 0, 0, 0);
        	}
        }
        
        Set<String> keySet = group.keySet();
        int[] max = new int[11];
        
        // 가장 많은 세트 메뉴의 수
        for (String key : keySet) {
			max[key.length()] = Math.max(max[key.length()], group.get(key));
		}
        
        // 정답 하나씩 추가
        for (String key : keySet) {
        	if(group.get(key) == max[key.length()]) {
        		if(max[key.length()] < 2) continue;
        		answerList.add(key);
        	}
		}
        
        answer = answerList.toArray(new String[0]);
        Arrays.sort(answer);
        return answer;
    }
    
    public void setGroup(int len, String order, int sel, int bitmask, int idx) {
    	if(len == sel) {
    		StringBuilder sb = new StringBuilder();
    		for (int i = 0; i < order.length(); i++) {
				if((bitmask & 1<<i) != 0) sb.append(order.charAt(i));
			}
//    		group.computeIfPresent(sb.toString(), (k,v)->v+1);
//    		group.putIfAbsent(sb.toString(), 1);
    		group.compute(sb.toString(), (k, v) -> v == null ? 1 : v + 1);
    		return;
    	} else {
    		for (int i = idx; i < order.length(); i++) {
    			if((bitmask & 1<<i) != 0) continue;
				setGroup(len, order, sel+1, bitmask | 1<<i, i+1);
			}
    	}
    }
}


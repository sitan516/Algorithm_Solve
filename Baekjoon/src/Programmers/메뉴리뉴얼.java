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
        
        for (int i = 0; i < orders.length; i++) {
			char[] charArr = orders[i].toCharArray();
			Arrays.sort(charArr);
        	orders[i] = new String(charArr);
		}
        
        for (int c = 0; c < course.length; c++) {
        	for (int i = 0; i < orders.length; i++) {
        		setGroup(course[c], orders[i], 0, 0, 0);
        	}
        }
        
        Set<String> keySet = group.keySet();
        int[] max = new int[11];
        
        for (String key : keySet) {
			max[key.length()] = Math.max(max[key.length()], group.get(key));
		}
        
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
    		group.compute(sb.toString(), (key, value) -> value == null ? 1 : value + 1);
    		return;
    	} else {
    		for (int i = idx; i < order.length(); i++) {
    			if((bitmask & 1<<i) != 0) continue;
				setGroup(len, order, sel+1, bitmask | 1<<i, i+1);
			}
    	}
    }
}


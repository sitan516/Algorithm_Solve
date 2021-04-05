import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[] num = new int[N];
			int[] has = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
				has[i] = getGCD(num[i], K);
			}
			
			Map<Integer, Integer> hasMap = new HashMap<Integer, Integer>();
			
			for (int i = 0; i < N; i++) {
				hasMap.computeIfPresent(has[i], (k,v) -> v+1);
				hasMap.putIfAbsent(has[i], 1);
			}
			
			int[] hasNum = new int[hasMap.size()];
			int[] numberOfHas = new int[hasMap.size()];
			int index = 0;
			for (Integer key : hasMap.keySet()) {
				hasNum[index] = key;
				numberOfHas[index++] = hasMap.get(key);
			}
			
			BigInteger cnt = new BigInteger("0");
			BigInteger temp;
			BigInteger l;
			// 같은 3개 숫자를 선택하는 경우
			for (int i = 0; i < hasNum.length; i++) {
				if(numberOfHas[i] >= 3) {
					
					l = new BigInteger("1");
					l = l.multiply(new BigInteger(Integer.toString(hasNum[i])));
					l = l.multiply(new BigInteger(Integer.toString(hasNum[i])));
					l = l.multiply(new BigInteger(Integer.toString(hasNum[i])));
					
					if(l.mod(new BigInteger(Integer.toString(K))).toString().equals("0")) {
						temp = new BigInteger(Integer.toString(numberOfHas[i]));
						temp = temp.multiply(new BigInteger(Integer.toString(numberOfHas[i] - 1)));
						temp = temp.multiply(new BigInteger(Integer.toString(numberOfHas[i] - 2)));
						temp = temp.divide(new BigInteger("6"));
						cnt = cnt.add(temp);
					}
				}
			}
			
			// 서로 다른 2개 숫자를 선택하는 경우
			for (int i = 0; i < hasNum.length-1; i++) {
				for (int j = i+1; j < hasNum.length; j++) {
					
					//i 번째를 두 개 선택하는 경우
					if(numberOfHas[i] >= 2) {
						l = new BigInteger("1");
						l = l.multiply(new BigInteger(Integer.toString(hasNum[i])));
						l = l.multiply(new BigInteger(Integer.toString(hasNum[i])));
						l = l.multiply(new BigInteger(Integer.toString(hasNum[j])));
						if(l.mod(new BigInteger(Integer.toString(K))).toString().equals("0")) {
							temp = new BigInteger(Integer.toString(numberOfHas[i]));
							temp = temp.multiply(new BigInteger(Integer.toString(numberOfHas[i] - 1)));
							temp = temp.multiply(new BigInteger(Integer.toString(numberOfHas[j])));
							temp = temp.divide(new BigInteger("2"));
							cnt = cnt.add(temp);
						}
					}
					
					//j 번째를 두 개 선택하는 경우
					if(numberOfHas[j] >= 2) {
						l = new BigInteger("1");
						l = l.multiply(new BigInteger(Integer.toString(hasNum[i])));
						l = l.multiply(new BigInteger(Integer.toString(hasNum[j])));
						l = l.multiply(new BigInteger(Integer.toString(hasNum[j])));
						if(l.mod(new BigInteger(Integer.toString(K))).toString().equals("0")) {
							temp = new BigInteger(Integer.toString(numberOfHas[i]));
							temp = temp.multiply(new BigInteger(Integer.toString(numberOfHas[j] - 1)));
							temp = temp.multiply(new BigInteger(Integer.toString(numberOfHas[j])));
							temp = temp.divide(new BigInteger("2"));
							cnt = cnt.add(temp);
						}
					}
				}
			}
			
			// 서로 다른 3개 숫자를 선택하는 경우
			for (int i = 0; i < hasNum.length-2; i++) {
				for (int j = i+1; j < hasNum.length-1; j++) {
					for (int k = j+1; k < hasNum.length; k++) {
						l = new BigInteger("1");
						l = l.multiply(new BigInteger(Integer.toString(hasNum[i])));
						l = l.multiply(new BigInteger(Integer.toString(hasNum[j])));
						l = l.multiply(new BigInteger(Integer.toString(hasNum[k])));
						if(l.mod(new BigInteger(Integer.toString(K))).toString().equals("0")) {
							temp = new BigInteger(Integer.toString(numberOfHas[i]));
							temp = temp.multiply(new BigInteger(Integer.toString(numberOfHas[j])));
							temp = temp.multiply(new BigInteger(Integer.toString(numberOfHas[k])));
							cnt = cnt.add(temp);
						}
					}
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(cnt).append("\n");
		}
		
		

		System.out.println(sb);
	}
	
	static int getGCD(int a, int b) {
		int M = Math.max(a,b);
		int N = Math.min(a,b);
		int pre = 1;
		while(true) {
			pre = M % N;
			if(pre == 0) {
				break;
			}else {
				M = N;
				N = pre;
			}
		}
		return N;
	}
	
}

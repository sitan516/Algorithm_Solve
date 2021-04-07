package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class BOJ_13706_제곱근{
	
	static int[] arr;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		String s = br.readLine();
		
		BigInteger num = new BigInteger(s);
		
		BigInteger l = new BigInteger("0");
		BigInteger r = new BigInteger(s);
		
		while(true) {
			BigInteger mid = l.add(r);
			mid = mid.divide(new BigInteger("2"));
			if(mid.multiply(mid).compareTo(num) > 0) {
				r = mid;
			} else if(mid.multiply(mid).compareTo(num) < 0) {
				l = mid.add(new BigInteger("1"));
			} else {
				System.out.println(mid);
				break;
			}
			
		}
		
		
	}
	
	
	
	
}




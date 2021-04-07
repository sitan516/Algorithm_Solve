package SOLVED;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class BOJ_12871_무한문자열{
	
	static int[] arr;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		String s =br.readLine();
		String t =br.readLine();
		StringBuilder ssb = new StringBuilder(); 
		StringBuilder tsb = new StringBuilder(); 
		
		int ls = s.length();
		int lt = t.length();
		
		for (int i = 0; i < ls; i++) {
			tsb.append(t);
		}
		for (int i = 0; i < lt; i++) {
			ssb.append(s);
		}
		
		System.out.println(ssb.toString().equals(tsb.toString()) ? 1 : 0);
		
	}
	
	
	
	
}




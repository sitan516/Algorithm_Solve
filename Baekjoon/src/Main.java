import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int num[] = new int[10];
		int mul = 1;
		for (int i = 0; i < 3; i++) {
			mul *= Integer.parseInt(br.readLine());
		}
		
		while(mul >0) {
			num[mul % 10]++;
			mul /= 10;
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(num[i]);
		}
	}
	
	
	
	
}


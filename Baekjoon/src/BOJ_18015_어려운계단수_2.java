import java.io.IOException;
import java.util.Scanner;

public class BOJ_18015_어려운계단수_2 {

    static long dp[][][][]; //현재 위치, 남은 자릿수, 0도착, B-1 도착
    static long divider = 1000000000;
    static long answer = 0;
    static int B;


    public static void main(String[] args) throws NumberFormatException, IOException {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        B = sc.nextInt();
        dp = new long[B][N][2][2];
        for (int i = 0; i < B; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        dp[i][j][k][l] = -1;
                    }
                }
            }
        }
        for (int i = 1; i < B ; i++) {
            answer = (answer + dfs(i, N-1, 0, i == B-1 ? 1 : 0)) % divider;
        }


        System.out.println(answer);
    }

    public static long dfs(int cur, int left, int afterZero, int afterB){

        if(dp[cur][left][afterZero][afterB] != -1) return dp[cur][left][afterZero][afterB];

        if(left == 0){
            return afterB & afterZero;
        }

        long result = 0;
        if(cur != 0){
            result = (result + dfs(cur-1, left-1, cur == 1 ? 1 : afterZero, afterB)) % divider ;
        }

        if(cur != B-1){
            result = (result + dfs(cur+1, left-1, afterZero, cur == B-2 ? 1 : afterB)) % divider;
        }

        dp[cur][left][afterZero][afterB] = result;

        return result % divider;

    }
}

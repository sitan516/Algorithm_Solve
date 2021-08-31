import java.io.IOException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BOJ_18015_어려운계단수 {

    static long dp[][][]; //현재 위치, 남은 자릿수, bitmask
    static long divider = 1000000000;
    static long answer = 0;
    static int B;
    static BitSet satisfy;
    static Map<Memo, Long> map;
    public static void main(String[] args) throws NumberFormatException, IOException {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        B = sc.nextInt();
        satisfy = new BitSet();
        satisfy.set(0, B);
        map = new HashMap<Memo, Long>();

        for (int i = 1; i < B ; i++) {
            BitSet bitSet = new BitSet();
            bitSet.flip(i);
            answer += dfs(i, N-1, bitSet) % divider;
            answer %= divider;
        }

        System.out.println(answer);
    }

    public static long dfs(int cur, int left, BitSet bitSet){
        Memo memo = new Memo(cur, left, bitSet);
        if(map.containsKey(memo)) return map.get(memo);
        if(left == 0){
            if(equalBitSet(bitSet, satisfy)) return 1;
            else return 0;
        }

        long result = 0;
        if(cur != 0){
            BitSet tempBitSet = (BitSet) bitSet.clone();
            tempBitSet.set(cur-1);
            result += dfs(cur-1, left-1, tempBitSet) % divider ;
        }

        if(cur != B-1){
            BitSet tempBitSet = (BitSet) bitSet.clone();
            tempBitSet.set(cur+1);
            result += dfs(cur+1, left-1, tempBitSet) % divider;
        }

        map.put(memo, result);
        return result % divider;

    }

    static boolean equalBitSet(BitSet lhs, BitSet rhs) {
        if (lhs.equals(rhs)) return true;
        BitSet xor = (BitSet)lhs.clone();
        xor.xor(rhs);
        int firstDifferent = xor.length()-1;
        if(firstDifferent==-1)
            return true;

        return false;
    }


}

class Memo{
    int cur;
    int left;
    BitSet bitSet;

    public Memo(int cur, int left, BitSet bitSet) {
        this.cur = cur;
        this.left = left;
        this.bitSet = bitSet;
    }

    @Override
    public boolean equals(Object obj) {
        Memo temp = (Memo) obj;
        if(this.cur == temp.cur && this.left == temp.left){
            if(equalBitSet(this.bitSet, temp.bitSet)) return true;
        }
        return false;

    }

    boolean equalBitSet(BitSet lhs, BitSet rhs) {
        if (lhs.equals(rhs)) return true;
        BitSet xor = (BitSet)lhs.clone();
        xor.xor(rhs);
        int firstDifferent = xor.length()-1;
        if(firstDifferent==-1)
            return true;

        return false;
    }
}
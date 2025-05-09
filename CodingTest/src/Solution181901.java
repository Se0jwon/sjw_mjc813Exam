import java.util.ArrayList;
import java.util.Arrays;

public class Solution181901 {
    // https://school.programmers.co.kr/learn/courses/30/lessons/181901
    public int[] solution(int n, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        int num = k;
        while( num <= n ) {
            list.add(num);
            num += k;
        }
        int[] answer = Arrays.stream(list.toArray())
                .mapToInt((x) -> (int)x).toArray();
        return answer;
    }
    public static void main(String[] args) {
        Solution181901 sol = new Solution181901();
        System.out.println("10, 3 => "
                + Solution181867.printArray(sol.solution(10, 3)));
        System.out.println("15, 5 => "
                + Solution181867.printArray(sol.solution(15, 5)));
    }
}

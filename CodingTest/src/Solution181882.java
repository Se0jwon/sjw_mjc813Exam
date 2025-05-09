import java.util.Arrays;

public class Solution181882 {
    // https://school.programmers.co.kr/learn/courses/30/lessons/181882
    public int[] solution(int[] arr) {
        int[] answer = Arrays.stream(arr).map((node) -> {
            if( node % 2 == 0 && node >= 50 ) {
                return node / 2;
            } else if ( node % 2 == 1 && node < 50 ){
                return node * 2;
            } else {
                return node;
            }
        }).toArray();

        return answer;
    }
    public static void main(String[] args) {
        Solution181882 sol = new Solution181882();
        System.out.println("[1, 2, 3, 100, 99, 98] => " + Solution181867.printArray(sol.solution(new int[] {1, 2, 3, 100, 99, 98})));
    }
}

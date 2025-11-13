import java.util.ArrayList;
import java.util.Arrays;

public class Solution181895 {
    // https://school.programmers.co.kr/learn/courses/30/lessons/181895
    public int[] solution(int[] arr, int[][] intervals) {
        ArrayList<Integer> list = new ArrayList<>();
        for( int i = 0; i < intervals.length; i++ ) {
            for( int j = intervals[i][0]; j <= intervals[i][1]; j++ ) {
                // 시작 인덱스 ~ 끝 인덱스까지
                list.add(arr[j]);
                // 값을 배열에 추가
            }
        }
        int[] answer = Arrays.stream(list.toArray())
                .mapToInt(x -> (int)x).toArray();
        // ArrayList<Integer> 를 int[] 배열로 데이터형 변환
        return answer;
    }
    public static void main(String[] args) {
        Solution181895 sol = new Solution181895();
        System.out.println("[1, 2, 3, 4, 5], [[1, 3], [0, 4]] => "
                + Solution181867.printArray(sol.solution(new int[] {1, 2, 3, 4, 5}, new int[][]{{1, 3}, {0, 4}})));
    }
}

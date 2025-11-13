public class Solution181884 {
    // https://school.programmers.co.kr/learn/courses/30/lessons/181884
    public int solution(int[] numbers, int n) {
        int nIndex = 0;
        int answer = 0;
        while ( nIndex < numbers.length && answer <= n ) {
            answer += numbers[nIndex];
            nIndex++;
        }
        return answer;
    }
    public static void main(String[] args) {
        Solution181884 sol = new Solution181884();
        System.out.println("[34, 5, 71, 29, 100, 34], 123 => " + sol.solution(new int[] {34, 5, 71, 29, 100, 34}, 123));
        System.out.println("[58, 44, 27, 10, 100], 139 => " + sol.solution(new int[] {58, 44, 27, 10, 100}, 139));
    }
}

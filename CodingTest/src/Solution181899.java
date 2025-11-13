public class Solution181899 {
    // https://school.programmers.co.kr/learn/courses/30/lessons/181899
    public int[] solution(int start_num, int end_num) {
        int[] answer = new int[start_num - end_num + 1];
        int num = start_num;
        for ( int i = 0; i < answer.length; i++ ) {
            answer[i] = num--;
        }
        return answer;
    }
    public static void main(String[] args) {
        Solution181899 sol = new Solution181899();
        System.out.println("10, 3 => "
                + Solution181867.printArray(sol.solution(10, 3)));
    }
}

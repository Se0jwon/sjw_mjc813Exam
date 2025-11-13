public class Solution120892 {
    // https://school.programmers.co.kr/learn/courses/30/lessons/120892
    public String solution(String cipher, int code) {
        String answer = "";
        int index = code;   // 배수 위치 기억
        while ( cipher.length() >= index ) {
            // 문자열길이 >= 배수번째 위치
            answer += cipher.charAt(index - 1); // 배수번째 위치 - 1, 인덱스
            index += code; // 배수를 더한다.
        }
        return answer;
    }
    public static void main(String[] args) {
        Solution120892 sol = new Solution120892();
        System.out.println("\"dfjardstddetckdaccccdegk\", 4 => " + sol.solution("dfjardstddetckdaccccdegk", 4));
        System.out.println("\"pfqallllabwaoclk\", 2 => " + sol.solution("pfqallllabwaoclk", 2));
    }
}

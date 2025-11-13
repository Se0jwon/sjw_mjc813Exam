import java.util.ArrayList;
import java.util.Arrays;

public class Solution181867 {
    private int number = 10;
    // https://school.programmers.co.kr/learn/courses/30/lessons/181867
    public int[] solution(String myString) {
        String[] arrString = myString.splitWithDelimiters("x", -1);
        // "x" 를 구분자로 하여 배열 원소를 나눔, 그런데 x 로 배열원소에 참여됨
        ArrayList<Integer> arrAnswer = new ArrayList<>();
        for ( int i = 0; i < arrString.length; i++ ) {
            if( !"x".equals( arrString[i] ) ) { // "x" 인 값은 배열 원소가 되면 안된다.
                arrAnswer.add(arrString[i].length());
                // 가변길이 배열에 원소를 추가했다.
            }
        };
        int[] answer = Arrays.stream(arrAnswer.toArray())
                .mapToInt((x) -> (int)x).toArray();
        // 가변길이 배열 ArrayList<Integer> 를 int[] 로 데이터 형변환
        return answer;
    }
    public static String printArray(int[] array) {
        String result = "";
        for( int i = 0; i < array.length; i++ ) {
            result += String.valueOf(array[i]) + ", ";
        }
        return result.substring(0, result.length() - 2);
    }
    public static void main(String[] args) {
        Solution181867 sol = new Solution181867();
        System.out.println("\"oxooxoxxox\" => " + sol.printArray(sol.solution("oxooxoxxox")));
        System.out.println("\"xabcxdefxghi\" => " + sol.printArray(sol.solution("xabcxdefxghi")));
        switch ((true?"aa":"bb")) {
            case "aa":
                System.out.println("aa");
                break;
            case "bb":
                System.out.println("bb");
            case "1, 2, 3":
                System.out.println("1, 2, 3");
                break;
            default :
                System.out.println("default");
        }
    }
}

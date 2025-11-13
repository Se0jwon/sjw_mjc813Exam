import java.util.ArrayList;

public class Solution181909 {
    // https://school.programmers.co.kr/learn/courses/30/lessons/181909
    public String[] solution(String my_string) {
        ArrayList<String> list = new ArrayList<>();
        for( int i = 0; i < my_string.length(); i++ ) {
            list.add(my_string.substring(i, my_string.length()));
        }
        list.sort((o1, o2) -> o1.compareTo(o2));
        String[] answer = list.toArray(new String[0]);
        return answer;
    }
    public static String printArray(String[] array) {
        String result = "";
        for( int i = 0; i < array.length; i++ ) {
            result += String.valueOf(array[i]) + ", ";
        }
        return result.substring(0, result.length() - 2);
    }
    public static void main(String[] args) {
        Solution181909 sol = new Solution181909();
        System.out.println("banana => "
                + Solution181909.printArray(sol.solution("banana")));
        System.out.println("programmers => "
                + Solution181909.printArray(sol.solution("programmers")));
    }
}

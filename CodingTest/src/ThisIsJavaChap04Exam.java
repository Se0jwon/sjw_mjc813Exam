import java.util.ArrayList;

public class ThisIsJavaChap04Exam {
    public static void main(String[] args) {
        ThisIsJavaChap04Exam tij = new ThisIsJavaChap04Exam();
        System.out.println(tij.solve04());
    }

    /**
     * 주사위 2개 던져서 합 5 만들기 : (1,4), (4,1), (2,3), (3,2) => {{}, {}, {}, {}}
     * @return
     */
    public String solve04() {
        String result = "";
        while( "".equals(result) ) {
            int dice01 = this.getRandomDice();
            int dice02 = this.getRandomDice();
//            if ( dice01 과 dice02 를 이용하여 합이 5인지 ) {
//                  result = "(x,y)";출력하면 끝
//            }
        }
        return result;
    }

    /**
     * 1~6까지의 랜덤한 숫자를 리턴한다.
     * @return
     */
    private int getRandomDice() {
        return 0;
    }

    /**
     * 4x + 5y = 60 이라는 x 값과 y 값이 해당하는 모든 x, y 항을 구해야 한다.
     * x, y 는 단 10 이하의 자연수입니다.
     * @return
     */
    public String solve05() {
        ArrayList<int[]> result = new ArrayList<int[]>();
        // {0.5, 6}, {0.5, 6}, {0.5, 6}, {0.5, 6}, ......
        // for 2번 중첩해서 4x + 5y = 60 이 되는 모든 x, y 를 출력한다.
        for ( int x = 1; x <= 10; x++ ) {
            for ( int y = 1; y <= 10; y++ ) {
                // 4x + 5y = 60 이 되는지 검사한다.
                // 맞으면 {x, y} 배열을 result 에 추가한다.
                result.add(new int[] {x, y});
            }
        }
        String answer = "";
        for ( int i = 0; i < result.size(); i++ ) {
            answer += this.getResultString(result.get(i));
        }
        return answer;
    }
    private String getResultString(int[] result) {
        // for 2번 중첩해서 모든 원소를 문자열로 표시해야 한다. (3,2), (1,4), ...
        return "";
    }
}

# Java 강의

## JDK : 컴파일용,실행용               JRE : 실행용
- LTS 버전 위주로 다운로드 하고, 설치파일보다는 압축파일을 다운로드 하여서 압축을 풀고
  IDE(IntelliJ, Eclipse) 에서 JDK 경로를 설정하는게 좋다.
  장점 : 여러버전의 JDK 소스를 컴파일/실행
  단점 : path 경로가 없어서 javac 를 임의 폴더에서 실행 불가능
- 소스파일 : 개발자가 개발한다. *.java	C: *.c => *.obj => link => *.exe 실행
  소스파일을 컴파일(빌드:maven, gradle) (javac.exe) : *.class, 빌드 : *.jar, *.war 파일로 결과 생성
  실행 : java 실행프로그램에서 실행한다. JVM 에서 바이트코드(*.class, *.jar, *.war)를 실행한다.
- Ch01Exam08
  다음과 같이 출력되로록 Example.java 를 패키지 ch01.verify 에서 작성해 보세요.
  개발자가 되기 위한 필수 개발 언어 Java

## 통합개발환경도구
- IntelliJ : .idea 폴더에 프로젝트 정보들이 들어있다. .idea 폴더가 프로젝트 하나입니다.
	JDK 설정은 Project Structure => SDKs 에서 설정
- Eclise : workspace 공간 안에 프로젝트들이 들어있다. .metadata 폴더가 workspace 하나이며,
		이 안에는 프로젝트들과 서버가 들어있다. 한프로젝트에는 .project 파일이 하나.
	JDK 설정은 프로젝트만들때 설정, Project => properties 에서 설정

## src 폴더, 패키지, java 소스, Class
- Java 는 src 폴더에 소스들이 존재합니다.
- java package 는 src 폴더 아래에 하위 폴더의 개념으로 존재 합니다.
- java source 파일은 패키지 폴더 안에 *.java 파일로 존재 합니다.
- Class 이름으로 *.java 파일을 만들어야 합니다. 즉 MyCar 클래스는 MyCar.java 파일로 존재.

# 변수와 타입
- 기본형 데이터 타입
	1. 정수 데이터 타입 (byte:2진수:0b1010, 8진수:017, 16진수:0x44, short, char:'', int, long:4L )
	2. 문자 데이터 타입 (char) 'q'
	3. 실수 데이터 타입 (float:123.23F, double:123.23, 12323E-2)
	4. 논리 데이터 타입 (boolean: true/false, 0아닌것/0)
	5. 진수 정수 표현 : 2진수로 표현했을때 맨 앞 1bit 는 0이면 양수, 1이면 음수, 
		10진수 : 4093 -> 4*10^3 + 0*10^2 + 9*10^1 + 3*10^0
		16진수 : 6b7 -> 6*16^2 + b*16^1 + 7*16^0
		2진수 : 0b11011 -> 1*2^4 + 1*2^3 + 0*2^2 + 1*2^1 + 1*2^0
		2진수 4자리 => 16진수 1자리로 변환 0b10111011110011 => 0x2ef3
		16진수 1자리 => 2진수 4자리로 변환 (a=>1010, b=1011, c=1100, d=1101, e=1110, f=1111)
				0x5d7a => 0101110101111010
		0 2^4    3406383 => 3.406383E+6 => 3406383E-0 => 340.6383E+4
- 클래스형 데이터 타입 (참조타입)
	1. 문자열 (String)
	2. 숫자 (BigDecimal)
- 데이터 형 변환
	1. byte 저장공간 길이가 같은 데이터형을 계산 할때는 overflow, underflow 만 조심
	2. byte 저장공간 길이가 다른 데이터형을 계산 하면 길이가 큰 데이터형으로 자동 형변환
	3. 정수, 실수를 계산할때 결과값을 정수만 저장하느냐 실수와 같이 저장하느냐에 따라서 다름.
	4. 암시적 형변환 (자동형변환)은 보통 길이가 작은 데이터가 길이가 큰 데이터로 자동 변환
	5. 명시적 형변환 (데이터타입)(연산) : (int)값, (long)변수, (char), (short)
	6. String => 기본형 데이터로 변환 : Long.parseLong(문자열);  Integer.parseInt(문자열)
	6.1 Boxing => 기본형데이터를 대문자가 있는 클래스형 데이터로 변환, int => Integer, byte => Byte, char => Char, short => Short
	6.2 UnBoxing => 클래스형 데이터를 소문자가 있는 기본형 데이터로 변환, Integer => int, Byte => byte, Char => char, Short => short
	7. 기본데이터 => String : String.valueOf(값)
- 변수 LifeCycle
	1. { ~~ }블록이 시작하고 끝나는 사이에서 선언한 변수는 활동하고 블록이 끝나면 변수는 사망한다.
# 연산자
- 연산자의 종류와 우선순위
	0. 괄호연산자 : ()가장 깊은 곳의 괄호부터 연산우선순위를 가장 높게 처리한다.
	1. 증감연산자 전위증가연산자 : ++변수 => 변수의 값을 1 더한 후 에 처리한다.
				후위증가연산자 : 변수++ => 변수의 값을 먼저 처리하고 이후에 1을 더한다.
				감소연산자 : --변수, 변수-- => 먼저 1감소후 처리, 먼저 처리하고 1 감소한다.
	2. 산술연산자 : 연산자 우선 순위 => */% > +-, 데이터형 변환, 데이터형결과가 숫자,문자
	3. 비트이동연산자 : 비트왼쪽 => * 2^n, 비트오른쪽 => / 2^n
			3 << 6 => 3 * 2^6,   20 >>> 3 => 20 / 2^3
			11 => 11000000		10100 => 10
	3. 오버플로우 : 값을 증가하면 -로 바뀌는 경우, 값을 감소하면 +로 바뀌는 경우
	4. 비교연산자 : 최종값은 boolean, 연산순위가 낮다. 이항연산자.
		String strA = "abc", strB = "abc";
		if ( strA == strB ) //	클래스형데이터 변수는 번지를 저장하므로 번지수가 같은지 비교
		if ( strA.equals(strB) )	// 변수의 값이 같은지 비교
		if ( 리터럴.equals(변수) )	// 변수가 null 일때 런타임에러를 방지한다.
	5. 비트논리연산자 : 비트로 처리해서 논리연산을 실행하고 최종값은 비트데이터 0/1, 연산순위가 비교연산자 다음
	6. 논리연산자 : 최종값은 boolean, 일항연산자, 이항연산자, 연산순위가 비트논리연산자 다음
	7. 할당연산자 : 연산순위가 가장 낮다. 이항연산자, 왼쪽 = 오른쪽, +=, -=, *=, ...
		연산부호= : 왼쪽 연산 오른쪽 그 결과를 왼쪽에 저장
	8. 삼항연산자 : ((조건1) ? ((조건2 ? 참일때 : 거짓일때) : 거짓일때)
	9. instanceof : if ( 객체 instanceof 클래스 ) 결과가 boolean, 객체가 클래스 데이터형 맞는지
```
5월7일 문제 (계산기 클래스를 만들어 본다.)
com.mjc.studyjava.MyMathMathic 클래스 생성
	산술연산 하는 멤버메소드 5개 생성
		int add(int a, int b) : 두수를 더한 결과 리턴
		int sub(int a, int b) : 두수를 뺀 결과 리턴
		long mul(int a, int b) : 두수를 곱한 결과 리턴
		int divM(int a, int b) : 두수를 나눈 몫 결과 리턴
		int divN(int a, int b) : 두수를 나눈 나머지 결과 리턴
	비교연산 하는 멤버메소드 4개 생성
		boolean same(int a, int b) : 두수가 같으면 true 리턴, 아니면 false
		boolean same(String a, String b) : 두 문자열이 같으면 true 리턴, 아니면 false
		boolean notsame(int a, int b) : 두수가 다르면 true 리턴, 아니면 false
		boolean big(int a, int b) : a가 b보다 크면 true 리턴, 아니면 false
		boolean small(int a, int b) : a가 b보다 작으면 true 리턴, 아니면 false
	기타연산 하는 멤버메소드
		int shiftLeft(int a, byte b) : a를 b 만큼 왼쪽 시프트 결과 값을 리턴
		int shiftRight(int a, byte b) : a를 b 만큼 오른쪽 시프트(부호유지) 결과 값을 리턴
		int shiftRight0(int a, byte b) : a를 b 만큼 오른쪽 시프트(부호를0으로) 결과 값을 리턴
		long square(int a, byte b) : a를 b만큼 제곱한 결과 값을 리턴, *= 이용하여 코딩하세요
		int absolute(int a) : a의 절대값을 리턴
		String toHex(int a) : a 값을 16진수 문자열로 리턴
		String toBin(int a) : a 값을 2진수 문자열로 리턴
		String toOct(int a) : a 값을 8진수 문자열로 리턴
		int toDec(String a) : a 문자열을 10진수 정수로 리턴
```
- JavaProject(Maven build 프로젝트) 에 JUnit 라이브러리를 추가
	1. 선생의 git 에 있는 mavenJUnit 폴더를 본인 폴더에 복사하세요.
	2. 복사 붙여넣기 한 폴더 mavenJUnit(pom.xml 파일이 있어야한다.) 에서 Open IntelliJ 를 하세요.
	3. com.mjc.studyjava 패키지의 MyMathMathic.java 파일에 본인이 작성한 MyMathMathic 클래스를 붙여넣기 하세요.
	4. /src/test/java/com/mjc/studyjava/TestMyMathMathic.java 클래스에 마우스 오른쪽버튼 클릭해서 Debug 실행하세요.

# 조건문과 반복문
- 문장을 위에서부터 아래로 차례대로 실행한다.
- goto 문 형식으로 레이블로 제어를 분기하지 말자
- if
	1. 조건식이 참 일때만 처리
		if (조건식) {
			참일때 실행
		}
	2. 조건식이 참 일때와 거짓 일때 분기 처리
		if (조건식) {
			참일때 실행
		} else {
			거짓일때 실행
		}
	3. 조건식을 여러개 처리하여서 각 조건식이 참일때 한 분기만 처리만 처리
		if (조건식1) {
			조건식1 참일때 실행
		} else if (조건식2) {
			조건식2 참일때 실행
		} else if (조건식3) {
			조건식3 참일때 실행
		} else if (조건식4) {
			조건식4 참일때 실행
		} else {
			모든 조건식1,2,3,4 가 거짓일때 실행
		}
- switch : if ~ else if ~ else if ~ else if ~ else 형태의 제어 구조이다.
	1. switch (변수,객체,메소드결과값) {
		case 리터럴1:
			switch(상태) 결과값이 리터럴1 일때 실행하는 명령어들
			break;
		case 리터럴2:
			switch(상태) 결과값이 리터럴2 일때 실행하는 명령어들
			break;
		case 리터럴3:
			switch(상태) 결과값이 리터럴3 일때 실행하는 명령어들
		case 리터럴4:
			switch(상태) 결과값이 리터럴4 일때 실행하는 명령어들
			break;
		default:
			switch(상태) 결과값이 case 에 없을때 실행하는 명령어들
			break;
		}
- for : 1.초기화, 2.조건문, 3.반복문, 4.반복실행블록
	for ( 1.초기화; 2.조건문; 3.반복문 ) {
		4.반복실행블록
	}
	=> 1.초기화 실행
	   2.조건문 참인지 판별 => 참이면 4.반복실행블록 실행한다.
					=> 거짓이면 for 블록실행 빠져나감
	   3.반복문 실행 => 2번으로 올라간다.
	   1 => 2 => 4 => 3 => 2 => 4 => 3 => 2 => 4 => 3 => 2가 거짓이면 빠져나간다.
- continue : 루브(for, while, do~while) 블록의 맨 위 부분으로 분기한다.
- break : 블록을 빠져나간다.
- while : 1.조건문, 2번.반복실행블록
	while ( 1.조건문 ) {
		2.반복실행블록
	}
	=> 1.조건문이 참인지 판별 => 참이면 2.반복실행블록 실행한다.
					=> 거짓이면 while 블록실행 빠져나감
	1 => 2 => 1 => 2 => 1 => 2 => 1이 거짓이면 빠져나간다.
- do ~ while : 1.반복실행블록, 2.조건문
	do {
		1.반복실행블록
	} while (2.조건문)
	=> 1.반복실행블록 실행 => 2.조건문이 참이면 반복실행블록을 실행한다.
								=> 거짓이면 do~while 블록실행 빠져나감
	1 => 2 => 1 => 2 => 1 => 2이 거짓이면 빠져나간다.
- 예제
	while(a > 20) {
		System.out.println("while1");
		System.out.println("while2");
		break; => 블록을 빠져나간다.
		System.out.println("while3");
	}
	while(a > 20) {
		System.out.println("while1");
		System.out.println("while2");
		continue; => 아래문장 실행하지 않고 맨위(조건문비교)로 분기한다.
		System.out.println("while3");
	}
```
5월9일 문제 (계산기 클래스를 업그레이드 한다.)
com.mjc.studyjava.MyMathMathic 클래스
	int avg(int [] array) : int 배열 array 의 평균값을 리턴한다.
	int sum(int [] array) : int 배열 array 의 총합계값을 리턴한다.
	int sumOfNumber(int [] array, int number) : int 배열 array 의 원소중에서
			n의 배수인 값의 총합계값을 리턴한다.
	int sumOfLoop(int a, int b); a 부터 b 까지의 총합계값을 리턴한다. 루프제어를 사용한다.
	int sumOfFav(int a, int b); a 부터 b 까지의 총합계값을 리턴한다. 재귀호출을 사용한다.
```
# 객체지향프로그램
# 참조타입
- String, 배열, 열거형, 클래스객체, 인터페이스객체 => 데이터형 이름이 대문자로 시작
- 기본타입의 변수에 100이 저장되어 있다면 이것은 100이라는 값입니다.
- 참조타입의 변수에 100이 저장되어 있다면 이것은 주소 100번지라는 것입니다.
	참조타입은 대용량메모리가 필요하므로 힙메모리에 생성한다.
	그리고 길이는 컴파일러가 계산해서 몇바이트 공간인지 알고 있다.
- 스택메모리에 저장되는 것들 : 함수호출시 복귀주소, 함수호출시 매개변수
	, 함수실행시 지역변수, 블록내의 지역변수, 함수실행후 리턴값
- 힙메모리에 저장되는 것들 : 클래스가 new 로 생성되는 객체, 용량이 큰 데이터 들
- 명령어코드가 저장되는 곳 : 메소드영역, 정적변수, 정적필드, 메소드코드들, 생성자코드들, 정적리터럴(상수)
- 참조타입 변수의 비교연산은 주소가 비교된다. 값을 비교할려면 메소드를 사용한다.
- null 은 오로지 참조타입 변수에서만 사용가능하다. null 일경우에는 NullExecption 이 발생할수 있으므로
	참조타입 변수를 사용하려면 null 인 상태에서는 사용 못한다. null 체크를 잘해야 합니다.
	public 메소드의 참조타입 매개변수는 반드시 null 체크를 잘해야 합니다.
- String 클래스의 비교는 equals 메소드 사용
	boolean equals(String s);	// 나의 문자열과 s 문자열을 비교하고 같으면 true 리턴
		hobby.equals("") ===> "".equals(hobby)
	int length();	// 나의 문자열의 길이를 리턴한다.
	char charAt(int n); // 나의 문자열에서 n 번째 인덱스 문자 하나를 리턴한다.
	String[] split(String s);	// 나의 문자열에서 s 문자열로 분리되는 문자들을 배열로 리턴
	String replace(String s, String t);	// 나의문자열에서 s 문자를 t 문자로 바꿔서 리턴
	String replaceAll(String s, String t);	// 나의문자열에서 s 문자를 t 문자로 계속 바꿔서 리턴
	String substring(int start, int end);	// 나의문자열에서 인덱스번호가 start ~ end-1 까지 문자열을 리턴
	int indexOf(String s);	// 나의문자열에서 s문자열이 몇번째 인덱스에서 찾았는지 리턴, -1 없다. 0부터시작
- 배열
	new 데이터형[] // 생성
	int length	// 프로퍼티, 필드, 멤버변수, 속성
	new 클래스[]
- ArrayList<데이터형A> 변수명 = new ArrayList<데이터형A>();
- List<데이터형BB> 변수명 = new ArrayList<데이터형BB>();
```
5월12일 문제
com.mjc.studyjava.MyMathMathic 클래스
	BigDecimal getBigDecimal(int n, int s);	// 정수 n 값과 소수 s 값인 BigDecimal 데이터형을 리턴하세요
		예를 들어 getBigDecimal(3643, 5630932);	// BigDecimal 3643.5630932 를 리턴
	Integer[] getIntegerArray(int[] array);	// int[] 배열을 Integer[] 로 변환해서 리턴
	int[] makeRandomIntArray(int n); n 개의 랜덤한 int 값을 가지는 int[] 배열을 리턴
	String makeEncryptString(String text, int n);	// text 문자열의 각 한 문자를 n 번째 배수 인덱스에 배치하는 랜덤 문자열을 리턴하세요.
		랜덤문자열은 영문대문자, 영문소문자, 숫자 가능하다.
		n 은 5이상 이어야 한다.
		예를 들어 makeEncryptString("mjc813", 5);	=> A8dkIm8TkdjKLi9cu4Rs893sw1ksNm3
	String makeDecryptString(String code, int n);	// code 문자열에서 n번째 배수 인덱스 문자를 뽑아서 하나의 문자열로 리턴하세요.
		예를 들어 makeDecryptString("A8dkIm8TkdjKLi9cu4Rs893sw1ksNm3", 5);	=> mjc813
	지금까지 만든 모든 메소드들에서 참조타입 매개변수의 값이 null 이면 기본데이터 값 0 또는 "" 또는 null 을 리턴하도록 수정하세요.
```
- 할당연산자 : =, +=, -=, ...
	오른쪽항의 값과 처리하고 왼쪽항에 복사한다.
	기본타입 데이터(int, char, byte, long, float, double, short)는 값을 복사합니다.	: 깊은복사
	참조타입 데이터(배열, 클래스객체, 열거타입, 인터페이스객체)는 주소를 복사합니다. : 얕은복사,
		참조타입데이터를 깊은복사를 하려면 참조타입데이터의 원소,값,멤버변수를 모두 복사해야 한다. Clone
```
// 예제 얕은복사와 깊은복사
char[] chArray01 = new char[]{'1', 'c', '2', 'b'};    // 배열을 힙에 생성
char[] chArray02 = chArray01;     // 얕은복사 (참조타입은 주소를 복사)
chArray01[02] = '3';
System.out.println(sol.printArray(chArray01));	//  1, c, 2=>3, b
System.out.println(sol.printArray(chArray02));  //  1, c, 2=>3, b

char[] chArray03 = new char[4]; // 배열을 힙에 생성
System.out.println(sol.printArray(chArray03));  //  0, 0, 0, 0
for ( int i = 0; i < chArray01.length; i++ ) {
	chArray03[i] = chArray01[i];  // 배열의 원소를 하나하나씩 깊은 복사
}
System.out.println(sol.printArray(chArray03));  //  1, c, 3, b
chArray01[03] = '4';
System.out.println(sol.printArray(chArray01));  //  1, c, 3, b=>4
System.out.println(sol.printArray(chArray03));  //  1, c, 3, b
```
- 향상된 for 문
	for ( 데이터타입 변수1 : 배열변수이름 ) {
		변수1
	}
	장점 : 간단 코딩
	단점 : 배열 인덱스를 도저히 알 수 없음
- 열거타입
	enum 예약어를 이용하여 특정한 값들만 저장할 수 있는 데이터타입을 만들수 있다.
	public enum MyEnumData {
		명지대(4),
		명지전문대(5),
		고려대(6);
		private int value;
		MyEnumData(int val) {
			this.value = val;
		}
		public int getValue() {
			return this.value;
		}
	}
	MyEnumData med = MyEnumData.명지전문대;
	상수, 또는 변하지 않지만 특정한 상태를 나타낼때 고급 프로그래머들이 자주 사용한다.
	"명지대", "명지전문대", "고려대"
	String MED = "명지전문대";	==> 이런 방법으로 특정한 값을 저장한다면 나중에 오류가 날 확률이 매우 높다.
# 클래스
- 클래스란 설계도, 빵틀, 속성과 동작을 선언한 집합
- 객체란 클래스 설계도를 이용하여 실제 메모리에 존재하는 인스턴스된 것
- c의 Structure 가 업그레이드 된 느낌, c 언어의 구조체는 데이터형 변수들만 안에 존재합니다.
	struct 구조체이름 {
		int age;
		char name[20];
	};
	구조체이름 c;
	구조체이름 *a;
	a = malloc(sizeof(구조체이름));
	free(a);
- c++ Class 는 structure(구조체)에 메소드를 추가할수 있게 됩니다. => Java, C#, PHP, Python, JS, ...
	class 클래스명 {
	};
	클래스명 cp;
	클래스명 *cpp = new 클래스명();
	delete cpp;
- Java Class 는 클래스명과 *.java 소스파일명이 이름이 같아야 한다.
- 클래스안에는 데이터형 변수들과 함수(메소드)들이 같이 존재하는 집합이다. 이것을 개발자가 개발하므로 사용자데이터타입
- 클래스 선언
	접근제한자(private/protected/default/public) class 클래스이름(대문자로시작) {
		접근제한자 데이터타입 변수명; => 멤버 변수,필드,프로퍼티,속성 => (Getter/Setter)
		접근제한자 데이터타입 메소드(함수)명 (매개변수를 열거) {	=> 멤버 메소드
			메소드를 구현합니다.
			super.멤버변수 사용할수있다 => 부모의 멤버변수를 사용한다.
			this.멤버변수 사용할수있다 => 자기자신객체의 멤버변수를 사용한다.
			super.멤버메소드(매개변수); => 부모의멤버메소드 호출가능
			this.멤버메소드(매개변수); => 멤버메소드 호출가능
			// 생성자는 호출 불가능 하다.
			return 데이터타입;	// void 이면 return 문장 없어도 된다. return;
		}
		클래스이름() {	=> 기본 생성자 (컴파일러가 자동으로 만들어준다.) => 1개
			super()/super(매개변수);	부모클래스의 기본생성자, 비기본생성자 호출가능 1번만
			this.name = "";
			this.멤버메소드(); => 멤버메소드 호출가능
			this(매개변수 형태); => 비기본생성자 호출가능
		}
		클래스이름(매개변수 형태) {	=> 비기본 생성자 (프로그래머가 구현한다) => n개, 생성자 오버로딩
			super()/super(매개변수);	부모클래스의 기본생성자, 비기본생성자 호출가능 1번만
			this(); 기본생송자 호출가능,	생성자 호출 여러개 하면 안된다.
			this(매개변수 형태); => 비기본생성자 호출가능,	생성자 호출 여러개 하면 안된다.
			this.name = "";
			this.멤버메소드(); => 멤버메소드 호출가능
		}
		String name;
		public void printArray(int[] array) {
			this.멤버메소드(매개변수); => 멤버메소드 호출가능
			// 생성자는 호출 불가능 하다.
		}
	}
- 클래스 사용 (인스턴스 객체로 사용하는 방법, 정적으로 사용하는 방법)
- 인스턴스 객체로 사용하는 방법
	클래스명 변수명1 = new 클래스명(); => 기본 생성자
	클래스명 변수명2 = new 클래스명(매개변수들); => 비기본 생성자
- 정적으로 사용하는 방법
- 클래스 정적변수와 클래스 정적 메소드를 선언한다.
	public class 클래스명 {
		접근제한자 static 데이터타입 변수명; => 클래스 정적 변수
		접근제한자 static 데이터타입 메소드명(매개변수들) { => 클래스 정적 메소드
			메소드구현;
			지역변수 사용 가능;
			정적변수 사용 가능;
			정적메소드 사용 가능;
			new 하지 않은 멤버변수 사용 불가능;
			new 하지 않은 멤버메소드 사용 불가능;
		}
	}
- 클래스 정적변수, 클래스 정적메소드를 사용하는 방법
	클래스명.변수명 => 클래스를 new 하지 않고 클래스 정적 변수로 사용 가능하다.
	클래스명.메소드명 => 클래스를 new 하지 않고 클래스 정적 메소드로 사용 가능하다. System.out.println();
## 메소드
- 메소드의 prototype
	[접그제한자] 리턴데이터형 메소드명(매개변수,...)
- 메소드의 리턴데이터형
	메소드 몸체를 실행시 return 문장 다음에 값/변수/객체를 리턴할 수 있다. 이때 리턴되는 값의 데이터형
- 메소드의 이름
	메소드를 구별할 수 있는 이름 (lower camelCase)
- 메소드의 매개변수
	형식매개변수 : 메소드의 prototype 부분에서 선언된다. 즉 (데이터형 변수명, ..)
	실매개변수 : 메소드를 실제로 호출하여 사용할때 값이 매개변수로 전달되는 값. 즉 메소드명(값1, 값2, 변수);
	Call by Value 매개변수 : 형식매개변수가 기본형 데이터형, Boxing,
		메소드안에서 매개변수의 값을 변경해도 메소드가 종료되면 원래 값으로 돌아온다.
	Call by Address : 형식매개변수가 포인터, 실매개변수가 주소값,
		메소드 안에서 매개변수 주소가 가르치는 공간의 값을 변경했으므로
		메소드가 종료되면 변경된 값이 유지된다.
	Call by Reference : 형식매개변수가 클래스타입, 인터페이스타입, 배열, 실매개변수가 참조타입,
		메소드 안에서 매개변수가 가르치는 공간의 값을 변경했으므로
		메소드가 종료되면 변경된 값이 유지된다. 다만 자바는 매개변수 자체 값을 변경 할 수 없다.
## 객체관계
- Has a 관계 : 클래스 안에 클래스가 객체 멤버변수로 존재하는 경우
	public class 클래스 {
		private String name;
		private MyMathMathic mmm;
	}
	신발클래스 안에 신발끈 클래스가 2개 객체로 있고, 밑창 클래스의 객체가 있다.
- Is a 관계 : 클래스가 부모클래스를 상속 하는 경우
	원숭이클래스는 포유류 클래스로부터 상속 받는다.
	신발클래스는 운동화클래스의 부모가 될 수 있다.
- 자식은 부모이다. 부모는 자식이 아니다. 그러나 자식이 부모가 된 후에 부모는 자식이 될수 있다.
- 예를 들면 원숭이클래스로 객체가 된 경우에는 이 객체는 포유류 클래스가 될수 있다. 포유류 클래스 객체는 원숭이 클래스로 될수 있다.
- Is a 관계에서는 부모클래스의 모든(protected, public) 속성과
	모든(protected, public) 동작을 자식클래스가 사용 할 수 있다.
- 다형성은 오버로딩과 오버라이딩으로 구현할 수 있습니다.
- 오버로딩은 클래스 안의 메소드 이름이 같고 매개변수의 형태가 다른 메소드들을 만들고 사용 할 수 있다.
- 오버라이딩은 부모클래스에서 상속받은 메소드를 자식클래스에서 다시 구현하여 사용 할 수 있다.
## 접근제한자
- private : 나만의 클래스에서 사용 가능하다. 접근가능하다. (멤버변수들, 내부에서 사용할 메소드들)
- default(C# internal) : 나와 같은패키지(C# 네임스페이스)에서 사용 가능하다.
- protected : 나와 자식클래스, 같은패키지에서 사용 가능하다.	(멤버변수들, 나와 자식들에서 사용할 메소드들)
- public : 모든 곳에서 사용 가능하다. (외부에 오픈할 멤버메소드들)
```
5월15일 문제
com.mjc813.studyjava.Computer 클래스를 구현한다.
한글로 표현하는 문제를 Java 소스로 구현하세요.
1. Computer 에는 각각 이름이 존재한다.
2. Computer 를 부팅하면 Console 에 이름을 출력하면서 "가 켜졌습니다." 출력한다.
	예를 들면 "mjc813Com 켜졌습니다."
3. Computer 에는 CPU(Gen9_i5, Gen9_i7, Gen9_i9), RAM 크기 (4,8,16,32GB)
	, 저장장치는 SSD, HDD 종류로 용량은 500GB, 1000GB, 2000GB 가 존재한다.
	, 그래픽카드는 GTX_1660, RTX_4080, Intel_A770 가 존재한다.
4. 컴퓨터를 5대의 스펙은 자유롭게 생성 하고 5대를 부팅하세요.
5. 컴퓨터의 스펙을 Console 에 출력하는 메소드를 구현 하세요.
	예를 들면
		CPU : Gen9_i7
		RAM : 16GB
		Storage : HDD 500GB
		Graphic : GTX_1660
6. 컴퓨터 5대 스펙을 출력하세요. 
7. 컴퓨터를 끄면 "가 꺼집니다." 출력한다.
8. 컴퓨터 5대 Power 를 끄세요.
```
- Upper CamelCase (Large CamelCase) : 첫글자 대문자 시작, 중간에 단어의 첫글자는 대문자
	클래스명, 인터페이스명, 열거형이름
- Lower CamelCase (Small CamelCase) : 첫글자 소문자 시작, 중간에 단어의 첫글자는 대문자
	멤버변수, 멤버메소드, 지역변수
```
5월16일 문제
com.mjc813.studyjava.Computer 클래스를 업그레이드 한다.
1. 기본생성자에서는 이름은 "Computer", CPU는null, RAM:null, 저장장치:null, 그래픽:null 초기화
2. 첫번째 비기본생성자는 이름을 매개변수로 받는다. 나머지 필드는 null 로 초기화
3. 두번째 비기본생성자는 이름,CPU를 매개변수로 받는다. 나머지 필드는 null 로 초기화
4. 세번째 비기본생성자는 이름,CPU,RAM를 매개변수로 받는다. 나머지 필드는 null 로 초기화
5. 네번째 비기본생성자는 이름,CPU,RAM,저장장치를 매개변수로 받는다. 나머지 필드는 null 로 초기화
6. 다섯번째 비기본생성자는 이름,CPU,RAM,저장장치,그래픽를 매개변수로 받는다.
7. 정보를 출력할때 멤버변수의 값이 null 이 아닌 변수만 출력한다. null 이면 "조립중입니다" 출력한다.
	예를 들면
		이름 : Samsung NT530
		CPU : Gen9_i7
		RAM : 32GB
		Storage : 조립중입니다
		Graphic : 조립중입니다
8. com.mjc813.studyjava.Mouse 클래스를 생성한다.
9. Mouse 클래스에는 정수 pointX, 정수 pointY 멤버변수를 생성한다.
10. Mouse 클래스에는 왼쪽버튼클릭, 오른쪽버튼클릭 2개 메소드를 생성한다.
	clickLeftButton
	clickRightButton
11. Mouse 클래스에는 moveMouse(int x, int y) 메소드를 생성한다.
	x, y 값이 멤버변수 pointX, pointY 에 저장된다.
12. Computer 클래스는 Mouse 객체를 하나 가진다.
13. Computer 클래스에서 Mouse 를 200, 400 좌표로 움직여서
	왼쪽 버튼을 클릭하면 아래와 같이 출력되는 메소드를 생성한다.
	예를들면 clickMouseLeftButton(200, 400);
	"마우스(200, 400) 왼쪽버튼 클릭됨"
14. Computer 클래스에서 Mouse 를 600, 800 좌표로 움직여서
	오른쪽 버튼을 클릭하면 아래와 같이 출력된다.
	예를들면 clickMouseRightButton(600, 800);
	"마우스(600, 800) 오른쪽버튼 클릭됨"
```
## 가변길이 매개변수
- 매개변수를 선언할때 맨 마지막에 선언한다. 두개 이상 사용 금지
- 매개변수들의 데이터형이 같고 갯수가 다르게 호출될때 선언한다.
- int add(String str, boolean bool, int ... item ) {}
	int result = add( "", false, 2, 4, 5, 6, 6, 9 );
- int[] 처럼 처리 된다. item[0]
## this 와 super
- this : 클래스 선언할때 멤버변수와 멤버메소드들을 클래스 내부에서 사용하려면 객체명.멤버변수/멤버메소드
	this 라는 자기자신을 의미하는 예약어를 사용한다. this 는 자기자신 객체를 말한다.
	this.멤버변수;	// 멤버변수를 접근한다.
	this.멤버메소드(매개변수);	// 멤버메소드를 실행한다.
	this(매개변수);	// 생성자를 실행한다.
	return this;
- super : 클래스를 상속받아서 선언할때 클래스 내부에서 부모 클래스의 멤버변수/멤버메소드/생성자를 사용하려면
	super 라는 부모를 의미하는 예약어를 사용한다. super 는 부모를 의미한다.
	super.부모의 멤버변수;	// 부모클래스의 멤버변수를 접근한다.
	super.부모의 멤버메소드(매개변수);	// 부모클래스의 멤버메소드를 실행한다.
	super(매개변수)	// 부모클래스의 생성자를 실행한다.
## static
- 정적클래스변수, 정적클래스메소드 를 만들때 static 이라는 예약어를 사용한다.
- 정적변수와 정적메소드는 프로그램이 실행될때 프로그램 명령어들이 있는 메모리영역에 탄생한다.
- 선언방법
	static 이라는 단어를 사용한다.
- 사용방법
	new 를 사용 하면 안된다.
	클래스명.정적클래스변수;
	클래스명.정적클래스메소드(매개변수);
- 정적메소드내에서는 멤버변수/메소드(인스턴스변수/메소드)를 사용하지 못한다.
- 정적메소드는 프로그램명령어가 저장되는 메모리영역에 존재한다. 그러나 인스턴스멤버는 힙메모리에 탄생을 시켜야지 존재한다.
	인스턴스메소드는 프로그램명령어가 저장되는 메모리영역에 존재하나 이 메소드 시작번지를 모른다.
## final
- 값을 초기화는 가능하나 수정을 못 하게 한다.
- final 멤버변수를 선언과 동시에 초기화하는 방법
- final 멤버변수를 생성자에서 초기화 하는 방법
- 클래스에 final 이 있으면 상속을 못하는 클래스, 즉 부모클래스가 될 수 없다.
- 멤버메소드에 final 이 있으면 오버라이딩을 못하는 메소드가 된다.
## getter/setter
- 멤버변수를 private 접근제한자로 선언한다.
- public 접근제한자 getter 메소드로 값을 읽는다. get멤버변수();
- public 접근제한자 setter 메소드로 값을 수정한다. set멤버변수(매개변수);
- Java Bean, Java VO, Java DTO
## 싱글톤
- 프로그램내에서 클래스로 인스턴스 되는 객체가 오직 한개인 클래스를 생성한다.
- 생성자를 private 접근제한자를 사용하여 외부에서 new 를 못하게 한다.
```
public class SingleClass {
    private static SingleClass sc = new SingleClass();
    private SingleClass() {
    }
    public static SingleClass getInstance() {
        return sc;
    }
}
5월19일 문제
13, 14, 15 번 문제
16, 17 번 문제
18번 문제
19번, 20번 문제
19번문제에서
Account : 속성{ 계좌번호(accountNumber), 계좌주(accountName), 금액(money) }
			동작{ 생성자(계좌번호,계좌주), addMoney(금액), subMoney(금액) }
20번문제에서
BankApplication : 동작 { 계좌생성(addAccount), 계좌목록(printAccounts)
	, 예금(deposit), 출금(draw), 종료(exit) }
Scanner scan = new Scanner();
String input = scan.nextLine(); // input 에 키보드 입력된 문자열이 저장됩니다.
```
# 상속 Inheritance
## 상속 개념
- 부모 클래스를 자식 클래스가 상속 한다.
- 부모 클래스에 있는 멤버변수, 멤버메소드 즉 인스턴스 되는 객체의 속성과 동작을 자식 클래스는 모두 가짐.
- 자식 클래스는 추가로 멤버변수, 멤버메소드를 가질 수 있다.
- 상속은 객체지향의 관계에서 Is a 관계 입니다. 자식 클래스는 부모 클래스 이다.
- 부모 클래스는 자식 클래스가 아니다. (부모가 자식이 될려면 처음부터 new 인스턴스된 자식객체이어야 한다.)
- 구현방법
	[접근제한자] class 클래스명 extends 부모클래스 (1개만 가능) implements 인터페이스들 (n개) {
		클래스를 구현한다.
	}
- tip : 인터페이스는 멤버변수는 없고 멤버메소드의 껍데기만 있는 데이터형
- 부모클래스 객체변수명 = new 부모클래스(); => 자식클래스로 형변환이 안됨
- 자식클래스 객체변수명 = new 자식클래스(); => 부모클래스로 자동 형변환이 된다.
- 오버라이딩 : 자식클래스가 부모클래스의 멤버메소드를 재정의 하는것 (부모클래스 멤버메소드의 이름과 매개변수형태와 리턴형이 같아야 한다)
	부모 멤버메소드의 접근제한자보다 더 적은권한은 안된다. 부모 멤버메소드에서 발생하는 예외이외에 추가 안됨.
- final : 변수에 있을때는 초기화만 가능 중간에 값을 수정 안됨
	클래스에 있을때는 상속을 못하는 클래스로 만듬, 부모클래스가 안된다.
	메소드에 있을때는 오버라이딩을 못하는 멤버메소드가 된다.
- 자동타입변환 : Is a 관계에 대한 설명, 자식은 부모이다.
	부모클래스 객체변수명 = new 부모클래스();
	부모클래스 객체변수명 = 자식객체;
	부모클래스 객체변수명 = new 자식클래스();
- 강제타입변환 : Is a 관계에 대한 설명, 부모는 자식이 될수 없는데, 되는 경우에 대해서 설명
	자식클래스 객체변수명 = new 자식클래스();
	부모클래스 부모객체변수 = 객체변수명;
	자식클래스 변수명 = (자식클래스)부모객체변수;
	즉 new 로 인스턴스되는 객체의 자식클래스는 잠시 부모클래스에서 다시 자식클래스로 형변환을 캐스팅 할 수 있다.
- 다형성
	필드 다형성 : 멤버변수를 자식클래스로 인스턴스하고 부모클래스로 형변환하여 사용할 수 있다.
	매개변수 다형성 : 형식매개변수의 데이터형을 부모클래스로 선언하고,
		메소드를 호출할때 실매개변수 데이터형을 자식객체를 사용할 수 있다.
	리턴데이터형 다형성 : 메소드의 리턴데이터형을 부모클래스로 선언하고,
		메소드를 리턴할때 자식객체를 리턴할 수 있다.
- instanceof
	객체가 해당 데이터형이 맞으면 true 를 리턴한다.
	if ( 객체 instanceof 데이터형 ) ==> true/false
	자식은 부모이므로 이때는 사용 안하고, 부모를 자식으로 변환할경우에 미리 데이터형이 맞는지 검사
## 추상 클래스
- abstract 라는 단어로 class 를 정의한다.
- 추상메소드가 필요하다. 추상메소드는 메소드의 몸체가 없다.
	[접근제한자] abstract 리턴형 멤버메소드명 (매개변수형);
- 추상메소드가 있는 클래스가 곧 추상클래스 이다.
- 추상클래스는 직접 인스턴스가 될 수 없다. new 를 못합니다. (추상메소드가 없는 추상클래스는 new 된다)
- 자식클래스는 추상클래스를 상속받아서 추상클래스의 추상메소드를 반드시 재정의 해야한다.
- 개발자가 추상메소드를 재정의하므로 부모클래스에서 자식클래스의 메소드를 호출할 수 있다. (역전주입과 비슷한 개념, 인터페이스 동일)

```
5월21일 문제
com.mjc813.studyjava.Animal 클래스
	멤버변수 : String name, int age
	멤버메소드 : void eat(Object feed) {출력 "{이름}은 {feed}를 먹는다"}
			void sleep() {출력 "{이름} 잔다"}
			void move() {출력 "{이름} 이동한다"}
			Animal reproduce(String newName) {Animal 객체의 이름을 정해서 생성하고 리턴한다}

com.mjc813.studyjava.Bird 클래스 는 Animal 클래스를 상속받는다.
	멤버변수 : String wings ("날개")
	멤버메소드 : void flap() {출력 "{이름} {날개} 날개짓한다."}
			void fly() {출력 "{이름} {날개} 난다."}
			부모의 reproduce 를 재정의 해서 return Bird 객체가 되도록 한다.
			
com.mjc813.studyjava.Parrot 클래스 는 Bird 클래스를 상속받는다.
	멤버메소드 : fly 메소드를 재정의 하여서 {출력 "{이름} {날개} 조금만 난다."}
		eat 메소드를 재정의 하여서 매개변수에는 String 데이터형태 일때만 "{이름}은 {곡식이름}를 먹는다"로 출력하도록 재정의한다.
		부모의 reproduce 를 재정의 해서 return Parrot 객체가 되도록 한다.
		
com.mjc813.studyjava.Eagle 클래스 는 Bird 클래스를 상속받는다.
	멤버메소드 : fly 메소드를 재정의 하여서 {출력 "{이름} {날개} 높이 난다."}
		eat 메소드를 재정의 하여서 매개변수에는 Animal 데이터형태 일때만 "{이름}은 {동물이름}를 먹는다"로 출력하도록 재정의한다.
		부모의 reproduce 를 재정의 해서 return Eagle 객체가 되도록 한다.
Eagle eg = new Eagle("독수리",12);
eg.eat("생쥐");	=> String 형태는 실행할 필요 없다.
eg.eat(new Animal("생쥐",0));	=> 매개변수가 Animal 데이터형태만 입력받아서 출력한다.

com.mjc813.studyjava.Mammalia 클래스 는 Animal 클래스를 상속받는다.
	멤버메소드 : void feedingMilk() {출력 "{이름} 젖을 먹인다."}
			eat 메소드를 재정의 하여서 매개변수에는 String 데이터형태 일때만 "{이름}은 {음식이름}를 먹는다"로 출력하도록 재정의한다.
			부모의 reproduce 를 재정의 해서 return Mammalia 객체가 되도록 한다.
Mammalia ml = new Mammalia("젖소",7);
ml.eat("생쥐");	=> 매개변수가 String 데이터형태만 입력받아서 출력한다.
ml.eat(new Animal("생쥐",0));	=> 매개변수가 String 형이 아니므로 무시한다.
Mammalia ml4 = ml.reproduce("젖소아기");
ml4.eat("우유");
			
com.mjc813.studyjava.Herbivore 클래스 는 Mammalia 클래스를 상속받는다.
	멤버메소드 : 부모의 reproduce 를 재정의 해서 return Herbivore 객체가 되도록 한다.
			
com.mjc813.studyjava.Carnivore 클래스 는 Mammalia 클래스를 상속받는다.
	멤버메소드 : eat 메소드를 재정의 하여서 매개변수에는 Herbivore 데이터형태 일때만 "{이름}은 {초식동물이름}를 먹는다"로 출력하도록 재정의한다.
		부모의 reproduce 를 재정의 해서 return Carnivore 객체가 되도록 한다.
Carnivore cv = new Carnivore("사자 2",3);
cv.eat("염소");	=> String 형태는 실행할 필요 없다.
cv.eat(new Herbivore("염소",0));	=> 매개변수가 Herbivore 데이터형태만 입력받아서 출력한다.
Carnivore cv3 = cv.reproduce("사자 3");

Main 에서 Animal, Bird, Parrot, Eagle, Mammalia, Herbivore, Carnivore 객체를 하나 이상씩 만들어서
	각각의 메소드를 실행하고 출력 해 보세요 (정답이 없습니다.)
	특히 reproduce 메소드를 실행하여 객체를 직접 낳는 실행을 해보고, 그 객체가 인스턴스됐는지 아무 메소드나 실행해 보세요.
```
# 인터페이스 (객체지향 추상화의 꽃)
- interface 라는 단어로 데이터형을 선언한다. (참조타입)
- interface 는 절대로 new 를 할 수 없다. 
- 클래스는 implements 로 interface 를 구현한다. Is a 관계
- 인터페이스 객체 = new 클래스();
- 인터페이스구현한클래스 객체Object = new 인터페이스구현한클래스();
	인터페이스 객체 = 객체Object;
- 인터페이스를 구현한클래스 한다. (구현클래스)
- 인터페이스 선언 방법 (인터페이스명.java)
	public interface 인터페이스명 extends 부모인터페이스 n개 {
		추상메소드
		public 리턴데이터형 메소드명 (매개변수 형태);	=> 메소드의 껍데기(prototype)
	}
- 클래스에 인터페이스를 구현하는 방법 (구현클래스)
	public class 클래스명 extends 부모클래스1개 implements 인터페이스 n개 {
	}
- 구현클래스와 구현되는 인터페이스는 Is a 관계이므로 구현클래스가 인스턴스된 객체는 인터페이스가 자동으로 된다.
- 인터페이스 a = new 구현클래스();
  구현클래스 b = (구현클래스)a;
```
5월22일 문제
1. 추상클래스를 활용
	com.mjc813.studyjava.abstractj.Vehicle 추상클래스를 만든다.
		멤버변수 : int wheelCount;  => 바퀴갯수
		추상메소드 : void move();
				void stop();
	com.mjc813.studyjava.abstractj.Truck 클래스를 Vehicle 추상클래스를 상속받는다.
		멤버변수 : Sting name, int ton;
	com.mjc813.studyjava.abstractj.Bicycle 클래스를 Vehicle 추상클래스를 상속받는다.
		멤버변수 : String name;
	com.mjc813.studyjava.abstractj.Driver 운전수 클래스를 만든다.
		멤버변수 : String name, int age, Vehicle vehicle
		생성자 : 이름, 나이, Vehicle클래스 인스턴스객체
		멤버메소드 : void drive(); => Vehicle클래스데이터형 이 Truck 을 운전할때에는
					"운전수{이름}은 {wheelCount}개의 바퀴의 트럭{이름}을 운전한다." 출력한다.
								Vehicle클래스데이터형 이 Bicycle 을 운전할때에는 
					"운전수{이름}은 {wheelCount}개의 바퀴의 자전거{이름}을 이동한다." 출력한다.
				void breakk(); => "운전수{이름}은 트럭/자전거{이름}을 정지한다." 출력한다.


2. 인터페이스를 활용
	com.mjc813.studyjava.interfacej.IVehicle 인터페이스를 만든다.
		추상메소드 : String drive();
				String breakk();

	com.mjc813.studyjava.interfacej.ITruck 인터페이스는 IVehicle 인터페이스를 상속받는다.
	com.mjc813.studyjava.interfacej.Truck 클래스는 ITruck 인터페이스를 구현 한다.
		멤버변수 : String name, int wheelCount;
		생성자 : 필요하다
		멤버메소드 : String drive();	=> "{wheelCount}개의 바퀴의 트럭{이름}을 운전한다." 문자열 리턴
			String breakk();	=> "트럭{이름}을 정지한다." 문자열 리턴

	com.mjc813.studyjava.interfacej.IBicycle 인터페이스는 IVehicle 인터페이스를 상속받는다.
	com.mjc813.studyjava.interfacej.Bicycle 클래스는 IBicycle 인터페이스를 구현 한다.
		멤버변수 : String name, int wheelCount;
		생성자 : 필요하다
		멤버메소드 : String drive();	=> "{wheelCount}개의 바퀴의 자전거{이름}을 이동한다." 문자열 리턴
			String breakk();	=> "자전거{이름}을 정지한다." 문자열 리턴
		
	com.mjc813.studyjava.interfacej.Driver 운전수 클래스를 만든다.
		멤버변수 : String name, int age, IVehicle ivehicle
		생성자 : 이름, 나이, IVehicle구현클래스 인스턴스객체
		멤버메소드 : void drive(); => Vehicle클래스데이터형 이 Truck 을 운전할때에는
					"운전수{이름}은 {wheelCount}개의 바퀴의 트럭{이름}을 운전한다." 출력한다.
								Vehicle클래스데이터형 이 Bicycle 을 운전할때에는 
					"운전수{이름}은 {wheelCount}개의 바퀴의 자전거{이름}을 이동한다." 출력한다.
				void breakk(); => "운전수{이름}은 트럭/자전거{이름}을 정지한다." 출력한다.
3.
- /doc/mjc813.zip 파일을 본인 컴퓨터의 C:\Users\사용자명\.m2\repository\com 에 압축 풀으세요
- 압축풀면 "C:\Users\사용자명\.m2\repository\com\mjc813\mjc_library\1.0-SNAPSHOT\mjc_library-1.0-SNAPSHOT.jar" 파일이 있어야 합니다.
- Java 프로젝트를 Maven 프로젝트인지 확인하세요. 즉 프로젝트의 루트 디렉토리에 pom.xml 파일이 있어야 합니다.
- pom.xml 의 <dependencies> ~ </dependencies> 안에 아래의 문장을 붙여넣기 하세요
	
	<dependencies>
		...
		<dependency>
		  <groupId>com.mjc813</groupId>
		  <artifactId>mjc_library</artifactId>
		  <version>1.0-SNAPSHOT</version>
		</dependency>
	</dependencies>
- com.mjc813.mjc_library.IMarketInfo 인터페이스를 구현하는 클래스를 만드세요.
- com.mjc813.mjc_library.MjcPoint 클래스를 public static void main 안에서 인스턴스하고
	addPoint 와 subPoint 메소드를 실행하세요.
- 
```
Animal
Bird extends Animal
Parrot extends Bird
Eagle extends Bird

Mammalia extends Animal
Herbivore extends Mammalia
Carnivore extends Mammalia

Car c = new Car();
- 인터페이스는 추상화의 최극강 개념으로써 객체가 다양한 형태의 데이터형으로 형변환을 할 수 있도록 만든다.
- 인터페이스는 라이브러리가 여러분 개발자들의 메소드를 호출하는 역전주입, 역전호출을 할 수 있도록 만든다.

# jar, war 라이브러리 만드는 방법
- 빌드툴을 이용해서 만들어 보자 (maven, gragle, ant, ..)
- 빌드툴의 장점은 모든 개발자가 동일한 설정파일(pom.xml, build.gradle)로 같은 라이브러리를 참조하여 개발할 수 있다.
- 빌드툴로 라이브러리를 만드는 방법도 매우 쉽고 설정파일로 만들 수 있다. 만들때는 설정파일의 build 정보를 설정
## maven(pom.xml)
- xml 문법에 맞아야 한다.
- groupId : 본인이 만드는 프로젝트의 패키지와 그룹아이디를 뜻한다. (패키지명은 groupId 하위레벨로 만드는게 좋다)
- artifactId : 패키지 이름 하위에 만들 프로젝트 이름이다.
- version : 본인이 만드는 프로젝트의 버전을 숫자와 점으로 표시하며 항상 다름 버전은 숫자가 높아야 한다.
	예를 들어 : 1.0.0.0 => 1.0.0.1
- name : 보통 파일이름으로 사용하거나 프로제트의 이름으로 사용한다.
- dependencies : dependency 항목으로 사용할 라이브러리들을 열거한다.
 (로컬리포지토리에 없으면 중앙리포지토리나 기타리포지토리에서 로컬리포지토리로 다운로드 한다.
- build : 프로젝트를 빌드하면 발생하는 결과물에 대한 정보를 열거한다. (jar, war 파일 생성 가능)
- 로컬리포지토리 설정은 intellij 는 build 툴 > maven 설정, eclipse 는 이클립스설정 > maven 
## gradle (build.gradle)
- groovy, kotlin 문법에 맞아야 한다.
- group : 본인이 만드는 프로젝트의 패키지와 그룹아이디를 뜻한다.
- settings.gradle : rootProject.name 항목에 프로젝트 이름과 결과 파일이름이 설정
- version : 본인이 만드는 프로젝트의 버전을 숫자와 점으로 표시하며 항상 다름 버전은 숫자가 높아야 한다.
	예를 들어 : 1.0.0.0 => 1.0.0.1
- dependencies : implementation 항목으로 사용할 라이브러리들을 열거한다.
	group,   name,    version
 (로컬리포지토리에 없으면 중앙리포지토리나 기타리포지토리에서 로컬리포지토리로 다운로드 한다.
- build : 프로젝트를 빌드하면 발생하는 결과물에 대한 정보를 열거한다. (jar, war 파일 생성 가능)
- 로컬리포지토리 설정은 intellij 는 build 툴 > maven 설정, eclipse 는 이클립스설정 > maven 
## IntelliJ 에서 maven 프로젝트 만들기
- New => Project => maven quick start
- New => Project => SpringBoot (빌드옵션을 maven 으로 설정)
- New => Project => JavaProject (maven 선택)
- groupId, artifactId, version 을 전세계 유일한 값을 설정한다.
- 루트폴더의 pom.xml 에 groupId, artifactId, version 이 맞는지 확인한다.
- 사용하고자 하는 라이브러리를 <dependencies> ~ </dependencies> 안에 추가 한다.
	예를 들면
```
	JUnit 5.0 버전 추가
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.8.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>3.22.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```
- src 폴더에는 만들고자 하는 라이브러리의 클래스들을 패키지명(groupId 보다 같거나 하위레벨로)아래에 만든다.
- test 폴더에는 src 폴더에서 만든 라이브러리 클래스들을 JUnit 단위테스트 한다.
```
package com.mjc813.lib;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestCalculator {
    @Test
    public void TestAdd() {
        Calculator cal = new Calculator();
        assertThat(cal.add(1, 3)).isEqualTo(4);
        assertThat(cal.add(-4, 4)).isEqualTo(0);
    }
}
```
- 테스트 통과 되면 인텔리제이의 maven 도구창에서 package 를 실행하면 target 폴더에 jar 파일이 생성된다.
- maven 도구창에서 install 을 실행하면 C:\Users\사용자명\.me\repository\{groupId}\{artifactId}\{version}\*.jar 로 생성된다.
- 해당 artifactId 폴더를 zip 파일로 압축하여 친구에게 라이브러리 전달 하면 된다.

## Eclipse 에서 maven 프로젝트 만들기
- New => Maven Project
```
5월26일 문제
- 계산기 클래스를 설계하여 라이브러리를 만들어서 옆사람에게 전달해주세요 (pom.xml 사용)
- 사칙연산 가능 (+, -, *, /, %)
- 제곱연산 기능 (n^m)
- 배열의 합을 구하는 기능 (int[] 배열값을 모두 더한 합을 리턴하는 메소드)
- 라이브러리(pom.xml 사용)를 받은 옆사람은 라이브러리에 있는 계산기 클래스의 객체를 생성하고
  모든 메소드를 사용해 보세요
```

## 외부라이브러리 찾아서 사용하기
- maven repository 에서 사용할 라이브러리를 검색하고 사용자수가 많거나 검증된 라이브러리를 선택한다.
- pom.xml 에 추가하려면 maven 탭의 문자열을 복사해서 본인 프로젝트의 pom.xml 에 <dependencies> ~ </dependencies> 붙여넣기 한다. 그리고 maven update, maven sync all 한다.
- build.gradle 추가하려면 gradle 탭의 문자열을 복사해서 본인 프로젝트의 build.gradle 에 dependencies{~~} 붙여넣기 한다. 그리고 gradle sync 한다.
- 빌드툴이 없는 경우에는 각 IDE 앱의 java build path 에 jar 파일들을 추가한다.

### 빌드툴이 없는 Eclipse 에서 외부라이브러리 사용하기
- 일반프로젝트에서는 외부 라이브러리 파일 (*.jar)을 추가 (Java build Path 의 Add Extenal Jar)

### 빌드툴이 없는 IntelliJ 에서 외부라이브러리 사용하기
- 일반프로제트에서는 Project Structure 메뉴 클릭 후 Libraries 에서 (*.jar)을 추가

```
5월27일 문제
- Java New 일반프로젝트를 만든다.
- Java 에서 email 을 전송할 수 있는 라이브러리를 찾아서 추가한다.
- 자기 자신의 google, hanmail, naver 등의 이메일 SMTP 설정을 참고해야 한다.
	(참고 https://m.blog.naver.com/ghldbssla1997/222328725188)
	<dependency>
		<groupId>com.sun.mail</groupId>
		<artifactId>javax.mail</artifactId>
		<version>1.6.2</version>
	</dependency>
- 참고 URL 에 있는 개발 소스를 보고 옆사람의 이메일로 간단한 제목과 간단한 내용으로 전송하는 기능을 구현한다.
- 위의 프로젝트를 New maven 프로젝트인 pom.xml 로 만들어 본다.
	<dependency>
		<groupId>com.sun.mail</groupId>
		<artifactId>javax.mail</artifactId>
		<version>1.6.2</version>
	</dependency>
- 위의 프로젝트를 New gradle 프로젝트인 build.gradle 로 만들어 본다.
- 인텔리제이에서 gradle JDK JVM 에러 발생하면 Setting 메뉴의 Build > Build Tools > Gradle > Gradle JVM (프로젝트 JDK  설정)
- build.gradle 파일이 프로젝트 루트 폴더에 생긴다.
- dependencies {..} 블록안에 사용할 라이브러리의 groovy, kotlin 문법으로 추가한다.
	implementation group: 'com.sun.mail', name: 'javax.mail', version: '1.6.2'
```
## GIT 루트폴더와 인텔리제이 프로제트폴더의 구조를
GIT 루트폴더 예를 들어 (C:\DevJava\Git\)
인텔리제이 Abc 프로젝트 (C:\DevJava\Git\Abc\.idea 폴더, src 폴더)
인텔리제이 Def 프로젝트 (C:\DevJava\Git\Def\.idea 폴더, src 폴더)
인텔리제이 Hij 프로젝트 (C:\DevJava\Git\Hij\.idea 폴더, src 폴더)
## 다시 GIT와 인텔리제이 프로젝트 폴더를 설정하는 법
- 기존의 폴더(C:\DevJava\Git\Abc), (C:\DevJava\Git\Abc\Def), (C:\DevJava\Git\src)는 그대로 놔눈다.
- 새로운 폴더를 생성하여 GIT clone 한다. (D:\DevMyGit\Abc, D:\DevMyGit\Abc\Def, D:\DevMyGit\src)
- D:\DevMyGit 안의 .git 폴더만 놔두고 다른것들은 모조리 삭제한다.
- C:\DevJava\Git\Abc 폴더를		D:\DevMyGit\Abc 폴더로 복사/붙여넣기 한다.
- C:\DevJava\Git\Abc\Def 폴더를	D:\DevMyGit\Def 폴더로 복사/붙여넣기 한다.
- C:\DevJava\Git\src 폴더, pom.xml 파일을		D:\DevMyGit\Hij\src 폴더로 복사/붙여넣기 한다.
- D:\DevMyGit 를 commit, push 한다.

```
5월28일 문제
- 사용자들이 문자열로 입력한 String 값이 유효한지 검증하는 클래스를 라이브러리로 만들 계획입니다.
- maven 프로젝트나 gradle 프로젝트를 새로 생성합니다.
- 여러분들이 임의로 group, name, version 을 결정 합니다.
- maven 프로젝트인 사람은 pom.xml 에 추가한다.
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-api</artifactId>
      <version>5.8.2</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.assertj</groupId>
      <artifactId>assertj-core</artifactId>
      <version>3.22.0</version>
      <scope>test</scope>
    </dependency>
- gradle 프로젝트인 사람은 build.gradle 에 추가한다.
    testImplementation group:'org.junit.jupiter', name:'junit-jupiter-api', version:'5.8.2'
    testImplementation group:'org.assertj', name:'assertj-core', version:'3.22.0'
- 여러분들이 임의로 정하는 패키지명.MjcValidCheck 으로 클래스를 생성합니다.
- MjcValidCheck 의 아래 메소드를 개발 합니다.
- boolean isValidPhoneNumber(String text);
 => text 문자열이 스마트폰번호(010-NNNN-NNNN)를 유효하게 갖고 있는 문자열인지 검증해서 맞으면 true를 리턴한다.
- boolean isValidZipNumber(String text);
 => text 문자열이 우편번호(NNNNN)를 유효하게 갖고 있는 문자열인지 검증해서 맞으면 true를 리턴한다.
	앞의 숫자가 정확한지 검사한다. 0은 없다.
- boolean isValidEmail(String text);
 => text 문자열이 이메일주소(~~~@***.com, ~~~@***.***.net 등등)를 유효하게 갖고 있는 문자열인지 검증해서 맞으면 true를 리턴한다.
- 위의 3개 메소드를 제대로 구현했는지 JUnit5 라이브러리를 사용하여 메소드를 테스트 해야 합니다.
- 단위 테스트 하는 클래스에서 아래의 import 추가해야 한다.

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Test클래스명 {
    @Test
    public void test메소드명() {
        MjcValidCheck mvc = new MjcValidCheck();
        assertThat(mvc.isValidPhoneNumber(null)).isEqualTo(false);
        assertThat(mvc.isValidPhoneNumber("")).isEqualTo(false);
        assertThat(mvc.isValidPhoneNumber("010-3992")).isEqualTo(false);
        assertThat(mvc.isValidPhoneNumber("02-7487-0203")).isEqualTo(false);
        assertThat(mvc.isValidPhoneNumber("02-d35a-1s32")).isEqualTo(false);
        assertThat(mvc.isValidPhoneNumber("010-1111-2222")).isEqualTo(true);
	}
}
- 테스트 성공을 하면 maven install 또는 gradle build/jar 로 *.jar 파일을 생성하여 옆사람에게 전달한다.
- 옆사람에게 받은 라이브러리 *.jar 파일을 본인의 프로젝트에서 JUnit 으로 단위 테스트를 해서 모두 성공 시켜야 합니다.
```
## gradle 프로젝트에서 로컬리포지토리 .m2\repository 로 인스톨하는 설정
- build.gradle 에 아래의 부분을 추가하세요.
- gradle publishing > publish
```
plugins {
	id 'java'
	id 'maven-publish'
}

repositories {
	mavenCentral()
	mavenLocal()
}

publishing {
	publications {
		maven(MavenPublication) {
			groupId = group
			artifactId = rootProject.name
			version = version

			artifact "./build/libs/" + rootProject.name + "-" + version + ".jar"
		}
	}
	repositories {
		mavenLocal()
	}
}
```

- 라이브러이에 인터페이스가 있는 경우 활용하기
```
5월29일 문제
- 선생 mjc813 git 하위 doc 폴더의 mjc_exam_lib.zip 을 본인의 PC 로 복사하세요.
- mjc_exam_lib.zip 파일을 C:\Users\user\.m2\repository\com\mjc813\mjc_exam_lib\1.0.0.1 경로에 맞도록 압축을 풀으세요
- 즉 C:\사용자폴더\.m2\repository\com\mjc813\mjc_exam_lib\1.0.0.1\ 경로에 mjc_exam_lib-1.0.0.1.jar 파일이 있어야 합니다.
- 새로 프로젝트를 만드는데 maven, gradle 둘 중에서 하나로 생성하세요.
- 본인의 프로젝트에 mjc_exam_lib-1.0.0.1.jar 라이브러리를 추가하세요
- 각조의 1번, 2번 사람으로 나누어서 문제를 해결해야 합니다.
# 1번 사람의 문제
- com.mjc813.mjc_exam_lib.IContact 인터페이스를 구현하는 클래스를 만들어야 한다.
- IContact 인터페이스 에 있는 모든 메소드를 구현하세요
- setPhoneNumber(String) 메소드에 폰번호를 저장할때는 폰번호가 유효한지 isValidPhoneNumber 메소드가 true 일때만 저장하도록 처리하세요.
- setZipNumber(String) 메소드에 우편번호를 저장할때는 우편번호가 유효한지 isValidZipNumber 메소드가 true 일때만 저장하도록 처리하세요.
- setEmail(String) 메소드에 이메일를 저장할때는 이메일이 유효한지 isValidEmail 메소드가 true 일때만 저장하도록 처리하세요.
- getContactFullInfo() 메소드는 이름,폰번호,우편번호,이메일을 예쁘게 문자열로 리턴하는 기능의 메소드 입니다.
- toString() 메소드를 오버라이드 하여서 getContactFullInfo 메소드의 문자열을 리턴하세요
# 2번 사람의 문제
- com.mjc813.mjc_exam_lib.IContactCUD 인터페이스와 IContactList 인터페이스를 구현하는 클래스를 만들어야 한다.
- 해당 클래스의 멤버변수 중 에는 new ArrayList<IContact>() 배열객체를 갖고 있는 멤버변수가 반드시 있어야 합니다.
- IContactCUD 인터페이스에 있는 모든 메소드를 구현하세요
    void add(IContact contact) => 매개변수 contact 를 배열객체에 추가한다.
    IContact getContactFromName(String name) => 배열객체에서 매개변수 name 이랑 똑같은 이름의 IContact 를 찾아서 리턴한다. 없으면 null 을 리턴한다.
    void update(IContact contact) => 매개변수 contact 의 name 이랑 같은 배열객체를 찾아서 폰번호,우편번호,이메일을 수정한다.
    void delete(IContact contact) => 매개변수 contact 의 name 이랑 같은 배열객체를 찾아서 삭제한다.
- IContactList 인터페이스에 있는 모든 메소드를 구현하세요
    int length() => 배열객체의 길이를 리턴한다.
    List<IContact> getListAll() => 배열객체를 리턴한다.
- IContactSearch 인터페이스에 있는 모든 메소드를 구현하세요
    List<IContact> getListFromContainsName(String word) => 매개변수 word 라는 단어가 포함된 이름으로 찾은 결과를 List 형태로 리턴한다.
```
public class PhoneBook implements IContactCUD, IContactList, IContactSearch {
	private ArrayList<IContact> list = null;
	
	public PhoneBook() {
		this.list = new ArrayList<IContact>();
	}
	
	@Override
	public void add(IContact contact) {
		if ( contact == null ) {
			return;
		}
		this.list.add(contact);
	}
	
	
	
	
	
	// 3번문제 해결중
	@Override
	public List<IContact> getListFromContainsName(String word) {
	}
}

## github.com 에서 원격리포지토리 생성하고 친구 추가하기
- github 로그인해서 New Repository 를 만든다.
- private, readme.md, gitignore 설정하고 create repository 한다.
- settings 설정에서 Collaborators 메뉴의 Add People 을 클릭한다.
- 같이 개발할 개발자의 github 로그인되는 이메일 계정을 추가한다. (glone, commit, push, pull down, merge 된다.)
- 상대방 이메일 계정으로 이메일이 송신된다. 상대방은 초대를 수락해야 한다.

## 예외처리
- 컴파일중에 또는 프로그램 실행 중에 로직에서 발생하는 데이터 에러 또는 데이터형 에러, null 에러 등등을
	예외 클래스에서 자동으로 new 인스턴스를 만들어서 처리하게 된다.
	또는 개발자가 수동으로 new 예외클래스를 생성하여 처리할 수 있다.
	또는 현재 메소드에서 처리 하지 않고 상위 호출한 메소드로 예외 인스턴스를 던질 수 있다.
- 현재 메소드에서 처리하는 경우
	try {
		~~
		예외가 발생할 수 있는 실행 블록
		예외클래스를 자동으로 new 인스턴스 하여서 catch 블록으로 제어를 넘긴다.
		예외클래스에 정확하게 맞는 데이터형이 있는 catch 블록으로 제어를 넘긴다.
		~~
	//} catch (부모예외클래스 객체명a) {
	//	// try 블록에서 자동으로 new 생성된 인스턴스 예외객체는 이 부모클래스로 자동 형 변환 되므로
	//	// 이곳의 catch 블록을 실행하고 아래의 자식예외클래스 catch 블록은 실행 안 한다.
	} catch (예외클래스_자식 객체명1) {
		~~
		catch 중괄호 안에 있는 예외클래스의 예외가 try 에서 발생 되면 이 블록을 처리한다.
		객체명1 으로 예외클래스가 자동으로 new 인스턴스되서 객체명1을 사용할 수 있다.
	} catch (예외클래스_조카 객체명1) {
		~~
		catch 중괄호 안에 있는 예외클래스의 예외가 try 에서 발생 되면 이 블록을 처리한다.
		객체명1 으로 예외클래스가 자동으로 new 인스턴스되서 객체명1을 사용할 수 있다.
	} catch (예외클래스_부모 객체명2) { // Exception, RuntimeException, Throwable
		~~
		catch 중괄호 안에 있는 예외클래스의 예외가 try 에서 발생 되면 이 블록을 처리한다.
		객체명2 으로 예외클래스가 자동으로 new 인스턴스되서 객체명1을 사용할 수 있다.
	} finally {
		~~
		try 블록 실행 하고 자동으로 finally 블록을 실행하여 마감한다.
		try 블록 실행 하다가 예외가 발생하면 catch 블록 실행하고 자동으로 finally 블록을 실행하여 마감한다.
	}
- 현재 메소드에서 나를 호출한 상위 메소드로 예외를 던지는 경우
	* 자동으로 던지는 경우
		[접근제한자] 리턴데이터형 메소드명(매개변수들...) throws 예외클래스 열거 {
			~~
			명령어들
			예외클래스 자동/수동으로 발생
			~~
		}
		예외클래스를 자동으로 new 인스턴스 하여서 나를 호출한 상위 메소드로 예외 제어를 넘긴다.
	* 수동으로 던지는 경우
		[접근제한자] 리턴데이터형 메소드명(매개변수들...) throws 예외클래스 열거 {
			try {
			} catch (예외클래스 e1) {
				~~
				throw e1;
			}
			throw new 예외클래스(...);
		}
		예외클래스를 자동으로 new 인스턴스 하여서 나를 호출한 상위 메소드로 예외 제어를 넘긴다.
- 예외에는
	Runtime예외가 있고 => 실행 중에서 메소드에서 예외를 모두 처리해야 한다. DB에서 트랜잭션 Rollback 이 가능하다.
	일반예외가 있음. => 컴파일 하다가 예외가 발생 하면 그때 예외를 모두 처리해야 한다. DB에서 Rollback 을 못한다.
- 호출 예를 들어
	상위1메소드 호출 실행 (필수로 try ~ catch ~ finally)
	상위2메소드 호출 실행 (선택으로 try ~ catch, 필수로 throws 있어야 한다.)
	상위3메소드 호출 실행 (선택으로 try ~ catch, 필수로 throws 있어야 한다.)
	현재메소드 호출 실행 (선택으로 try ~ catch, 선택으로 throw new 예외클래스, 필수로 throws 가 있어야 한다.)
- 프로그램이 실행할때 어떠한 예외를 만나더라도 개발자에게는 상세한 예외정보(라인수,파일명,클래스명,메소드명)를 보여야 한다.
	그러나 실제 실행하는 고객들에게는 절대로 예외정보를 보이면 안되고 예외에 대한 메세지와 어떻게 처리해야 하는지를 보여야 한다.

```
6월2일 문제
5월29일 문제에서 예외처리 기능을 추가해야 합니다.
- 1번 사람은 RuntimeException 을 상속받아서 NotValidDataException 예외클래스를 만든다.
	폰번호, 우편번호, 이메일이 유효하지 않는 데이터로 저장하려고 할때 NotValidDataException 예외 클래스를
	예외객체로 생성하여 던져야 합니다. 특히 생성자에서도 예외처리를 해야 합니다.
	예를들어 폰번호 입력시 유효하지 않는 경우에 보여줄 메세지는
		"[{입력된번호}]This is not valid phone number. [010-NNNN-NNNN] please input this type"
	예를들어 우편번호 입력시 유효하지 않는 경우에 보여줄 메세지는
		"[{입력된번호}]Wrong zip number. [NNNNN] please input this, first letter must not '0'."
	예를들어 이메일 입력시 유효하지 않는 경우에 보여줄 메세지는
		"[{입력된이메일}]는 유효한 이메일 아닙니다. [이메일아이디@인터넷도메인] 형식으로 입력하세요"
- 2번 사람은 NullPointException 을 상속받아서 ParameterNullPointException 예외 클래스를 만든다.
    add(IContact contact), update(IContact contact), delete(IContact contact)
	위 3개 메소드의 매개변수가 null 일 경우에는 ParameterNullPointException 예외 클래스를
	예외 객체로 생성하여 던져야 합니다.
	예를들어 add, update, delete 메소드의 매개변수가 null 일때
		"[{add/update/delete 3개 메소드 중 하나 출력}] real parameter need not null data."
- 모든 클래스의 메소드에서 try 블록, throw, throws 등을 이용하여 예외처리를 구현해 보세요.
- JUnit 단위테스트 실패하면 \doc\TestContactProject.java 소스파일을 단위테스트에 적용하세요
- 아래처럼 변경해야 한다.
        assertThatThrownBy(() -> contact.setPhoneNumber(null))
                .isInstanceOf(NotValidDataException.class);
				
        assertThatThrownBy(() -> contactCud.add(null))
                .isInstanceOf(ParameterNullPointException.class);

		// Junit 단위테스트에서 예외클래스가 발생했을때 예외클래스 객체를 리턴하여 얻는 방법
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            contact.setPhoneNumber("010-3ss3-33wa");
        });
        System.out.println(exception.getMessage());
```
이씨가문
이순임
이강순		이강자
이순신		이강말

try {
	이순신 예외 발생
} catch (이씨가문 e) {
	이씨가문 예외객체 처리
} catch (이강순 e) {
	이강순 예외객체 처리
} catch (이강말 e) {
	이강말 예외객체 처리
} catch (이강자 e) {
	이강자 예외객체 처리
} catch (이순임 e) {
	이순임 예외객체 처리
} catch (이순신 e) {
	이순신 예외객체 처리
}
## java 기타 정보
- Object 는 자바의 기본 클래스이다. 가장 선조 클래스이다. 가장 최상위 부모이다.
- Object 의 toString, equals, hashcode, clone 등의 메소드를 자식클래스에서 Override 재정의 하여 사용할 수 있다.
- Java DTO 는 자바 객체중에서 순수하게 getter, setter 로 표현되는 멤버변수들이 있는 데이터형을 말한다.
	Java Bean, Java VO, Java DTO, Java Entity
	사람정보 : id, 이름, 생년월일, 성별, 주소, ...
	자동차정보 : id, 일련번호, 모델명, CC, ...
	토끼정보 : id, 무늬형태, 눈색깔특징, 털모양, 털색깔, ...
	객체명.getName(), 객체명.setName(값)
- Java Record
	사람정보 : id, 이름, 생년월일, 성별, 주소, ...
	객체명.name(), 객체명.name(값)
- Lombok 은 getter, setter, 생성자, 빌더 등등의 소스를 자동으로 generate 생성한다.
- Lombok 설치는 IDE 에 따라 다른다. 인텔리제이는 Setting => plugins 에서 lombok 찾아서 추가
- System.out, err, in 은 시스템의 기본출력, 기본에러, 기본입력
- System.exit 는 프로그램종료, currentTimeMills 는 밀리초를 Long 으로 리턴한다.
- System.getProperty 는 운영체제의 정보를 제공한다.
- String 을 여러개 연결하여 새로운 String 을 만들때는 StringBuilder 를 사용하자.
- Wrapper 클래스 (Integer, Long, Character, Double, Boolean, Byte, ...)
- SQL 에서 테이블 생성시 create table 테이블이름 { age int, name varchar(20) }
	public class 테이블 { private int age; private String name; }
	포장클래스(Boxing/UnBoxing 은 DB 와 데이터를 연동할때 DB 의 null 을 에러없이 처리하기 위함)
- Boxing : 기본타입 데이터를 참조타입 데이터로 변환 (int => Integer)
- UnBoxing : 참조타입 데이터를 기본타입 데이터로 변환 (Integer => int)
- DB에 있는 date, time, datetim 을 Java 와 연결하려면 LocalDateTime 클래스를 사용하세요.
- 정규표현식은 유닉스에서 시작된 표현식 (https://namu.wiki/w/%EC%A0%95%EA%B7%9C%20%ED%91%9C%ED%98%84%EC%8B%9D)
- 교재 소스에서 중요한 것들
	p524 (StringBuilder), p536 (Calender), p540/p542 (LocalDateTime), 
	p544 (천단위 , 찍는경우 (소숫점 몇째자리) NumberFormat), p546 (SimpleDateFormat)
- 리플렉션 클래스,인터페이스 등이 클래스타입, 메소드, 필드, 생성자의 정보를 읽거나 사용하는 방법
	* 클래스이름.clss => 라이브러리가 참조되어 있는 클래스를 Class 라는 데이터형으로 가져올수있다.
	* 객체명.getClass(); => 인스턴스된 객체의 클래스를 Class 라는 데이터형으로 가져올수있다.
	* Class.forName("패키지명.클래스명"); => 패키지의 클래스를 Class 라는 데이터형으로 가져올수 있다.
		(주의할점 패키지명과 클래스명을 문자열로 검색하므로 없으면 null 예외가 발생한다.)
	p553 (Contact 클래스를 이용하세요, ReflectionExample.java 의 7번 라인을 Class clazz = Contact.class;)
- 리소스 : 자원 (웹서버 : 그림파일, css파일, js파일, html파일, 아이콘파일, 음악, 동영상...)
	Class 의 멤버메소드인 getResource 사용한다.
- 어노테이션 : 컴파일러, 빌드툴, JVM 또는 특정상황에 대해서 메타정보 등을 표현하는 방법이다.
	@어노테이션이름이 어디에 표현되느냐 (Target)
	클래스명 앞에 표현되는 어노테이션 => ElementType.TYPE
	필드명 앞에	=> ElementType.FIELD
	메소드명 앞에 => ElementType.METHOD
	생성자 앞에 => ElementType.CONSTRUCTOR+
	어노테이션 앞에 => ElementType.ANNOTATION_TYPE
	
	@어노테이션이름이 언제까지 동작하느냐 (Retention)
	컴파일할때 동작 ~ 컴파일종료시 사라짐	=> RetentionPolicy.SOURCE
	메모리에 로딩될때 동작 ~ 로딩이 끝나면 사라짐	=> RetentionPolicy.CLASS
	실행할때 동작 ~ 실행끝나면 사라짐	=> RetentionPolicy.RUNTIME
- 어노테이션 만드는 문법 : public @interface 어노테이션이름 {}
## 제네릭(Generic)
- 어떤 데이터형이라도 특정 알파벳으로 표현하는 기술
- 데이터형을 일반화 시켜서 클래스나 메소드를 정의하고 사용하는 곳에서 사용할 데이터형을 선언하고 초기화하며 사용한다.
- Java Bean, VO, DTO, Entity 등의 객체 데이터를 이용하여 일반적인 기능을 정의하는 클래스/메소드 용도
- <임이의 알파벳>
- ArrayList<Contact> 객체명 = new ArrayList<>();
- public class MyData extends ... implements ... {
		Object temp;
		void setTemp(Object obj) {
			this.temp = obj;
		}
		Object getTemp() {
			return this.temp;
		}
	}

	다른클래스 멤버메소드() {
		MyData 객체명 = new MyData();
		객체명.setTemp("문자열");
		Object o = 객체명.getTemp();
		if( o instanceof String ) {
			String s = (String)o;
		} else if ( o instanceof MyClass ) {
			MyClass mc = (MyClass)o;
		} else if ( o instanceof ,,, ) {
		} else if ( o instanceof .... ) {
		}
		객체명.setTemp(new MyClass());
		Object o = 객체명.getTemp();
		if( o instanceof String ) {
		} else if ( o instanceof MyClass ) {
		} else if ( o instanceof ,,, ) {
		} else if ( o instanceof .... ) {
		}
	}
- public class MyData<AAA, BBB> extends ... implements ... {
		AAA temp;
		BBB temp2;
		void setTemp(AAA obj) {
			this.temp = obj;
		}
		AAA getTemp() {
			return this.temp;
		}
	}
	다른클래스 멤버메소드() {
		MyData<String> 객체명 = new MyData<>();
		객체명.setTemp("문자열");
		String o = 객체명.getTemp();
		
		MyData<MyClass> 객체명2 = new MyData<>();
		객체명2.setTemp(new MyClass());
		MyClass o = 객체명.getTemp();
	}
- <알파벳 extends 클래스명> : 클래스명이 부모인 모든 클래스는 사용 가능하다.
이씨가문
이순임
이강순		이강자
이순신		이강말
	예를 들어서 <T extends 이씨가문> => 6가지 데이터형을 사용 가능하다.
	<T extends 이강순> => 이강순, 이순신 데이터형을 사용 가능하다.
	<T extends 이순임> => 이강순, 이순신, 이강자, 이강말 데이터형을 사용 가능하다.
- <알파벳 super 클래스명> : 클래스명이 자식 클래스인 경우 사용 가능하다.
	예를 들어서 <T super 이순임> => 이순임, 이씨가문 데이터형을 사용 가능하다.
	예를 들어서 <T super 이순신> => 이순신, 이강순, 이순임, 이씨가문 데이터형을 사용 가능하다.
## Thread
- 프로그램이 시작 실행하면 main 스레드가 탄생합니다.
- 메소드 하나가 하나의 스레드로 탄생이 가능하다.
- Runnable 인터페이스를 구현하는 클래스를 만들어서 run() 메소드 안에 코딩하는 방법 (p597)
- public class 스레드클래스명 implements Runnable {
		@Override
		public void run() {
			스레드에서 실행할 명령어들
		}
	}
	Thread t = new Thread(new 스레드클래스명());
	t.start();	// 실행대기상태로 진입
	t.stop();	// 실행종료 (deprecated)
- Thread 상속받아서 스레드 전용의 클래스를 만들어서 run() 메소드 안에 코딩하는 방법 (p600)
- public class 스레드클래스명 extends Thread {
		@Override
		public void run() {
			스레드에서 실행할 명령어들
		}
	}
	Thread t = new 스레드클래스명();
	t.start();	// 실행대기상태로 진입
	t.stop();	// 실행종료 (deprecated)
- 위에서 선언한 스레드클래스를 사용 하는 방법
	 스레드클래스 스레드객체명 = new 스레드클래스();
	 스레드객체명.start();	// 실행대기상태로 진입한다.
- 스레드마다 이름이 존재 합니다. 이 이름을 프로그래머가 변경이 가능하다. t.setName("이름");
- 스레드의 상태 (스레드가 탄생하고 실행하고 일시정지하고 다시 실행하고 일시정지 마지막에 종료하는 주기)
	* 실행중에서 일시정지로 만드는 메소드
	 Thread.sleep(밀리초);		// 밀리초 동안 현재 스레드 잠시 대기 (일시정지상태로 진입)
	 synchronized {wait()};	// 동기화 함수/블록에서만 사용 가능, 나의 스레드를 일시정지 시킨다.
	 스레드객체명.join();		// 현재 스레드를 일시정지상태로 진입(현재 스레드를 동기상태,블로킹상태로 만든다.)하고
					객체명스레드가 종료될때까지 기다린다.
	* 일시정지에서 실행대기로 만드는 메소드
	 synchronized {notify()};		// 다른 스레드를 실행대기상태로 진입한다.
	 synchronized {notifyAll()};	// 다른 스레드를 실행대기상태로 진입한다.
	 객체명.interrupt();	// 실행대기상태로 진입한다. (InterruptedException 발생됨)
	* 실행에서 실행대기로 만드는 메소드
	 Thread.yield();	// 실행하는 스레드를 실행대기상태로 진입시킨다.

- 실행대기상태 => 실행상태 => 일지정지상태 => 실행대기상태
					=> 실행대기상태
- synchronized 단어는 메소드나 블록의 현재 스레드에서 실행중인 데이터가 다른 스레드에서 사용 못하도록 잠그는 역할을 한다.
	다른 스레드 입장에서는 동기상태, 블로킹상태가 되는 현상이 발생한다.
- 스레드 종료하는 방법 (p620) 스레드가 실행되는 메소드를 정상 종료 해야 한다.
- 스레드가 실행중인 상황에서 외부에서 interrupt() 메소드를 호출해서 종료 가 가능하다.
- 스레드의 속성을 데몬으로 해서 실행하면 종료가 가능하다. (p626)
	t.setDaemon(true);
	t.start();
- 동기 (synchronized) : 자바에서 한라인 한라인 명령어가 실행되는데 한라인이 실행 종료되어야지 다음 라인이 실행하는 경우
	1 while
	2 함수 (함수가 끝나야지 다음 라인을 실행한다.)
	3 for
- 블로킹상태 : 어떤 명령어의 종료가 될때까지 기다리는 현상 (Server Socker 에서 accept, Client Socket 에서 connect)
- 비동기 (asynchronized) : 한 라인이 실행 종료되지 않았어도 다음라인의 명령어를 실행할 수 있는 
	1. while
	2. 비동기함수 (함수가 끝나지 않아도 다음 라인을 실행한다.)
	3. for
- 스레드풀 : 스레드를 최대 갯수만큼 실행할 수 있는 예비 자원을 만들어서 최대갯수만큼 스레드를 실행한다.
	이미 종료된 스레드의 자원을 다시 재 활용 할 수 있다.
	최대갯수내에서 활용 가능한 스레드를 재사용하든지 새로 만들어서 사용한다.
```
6월5일 문제
- 교재에 있는 예제를 모두 작성하고 직접 디버그로 실행하세요 (브레이크포인트 사용)
- 6월2일 문제의 프로젝트에 PrintThread 라는 스레드클래스를 생성하세요
	세가지 방법중에 하나 선택 (Runnable 구현, Thread 상속, 스레드풀 사용)
- PrintThread 에서는 연락처 ArrayList 에 있는 모든 연락처를 콘솔에 출력하는 run() 를 만드세요.
- JUnit5 단위테스트에서 PrintThread 를 이용하여 콘솔에 출력하는 기능 start() 을 실행하세요.
```

int sum = 0;
for 1~1000 까지의 합을 구해서 sum 에 저장
sum 출력

스레드에 값을 전달할때는 비기본 생성자를 사용하여 멤버변수에 값을 전달
스레드의 결과 값을 받을때까지 기다리는 것은 동기방식인데 그러면 중지 상태가 된다. 스레드의 원래 비동기 식으로 실행하기 위하여
인터페이스를 사용할 수 있다.

스레드(인터페이스구현클래스);
스레드가 실행하는 결과를 기다리지 않고 그 결과가 처리가 끝나면 스레드에서 자동 실행하도록 만들수 있다.

## Collection
- List 인터페이스를 구현한 ArrayList, LinkedList, Vector
- ArrayList : 같은 데이터형을 배열처럼 선형자료구조 로 구현한 자료구조 클래스 이다. (추가, 수정, 삭제, 전체삭제,...)
	synchronized 가 아니므로 여러 스레드에서 자료를 공유할 수 없다.
	배열 중간에 데이터를 추가, 삭제 하면 다시 일렬로 재 정렬하므로 추가,삭제 가 잦은 데이터형에는 안 맞다.
- LinkedList : ArrayList 처럼 선형자료구조 이지만 연결형 자료구조 클래스 이다. (추가, 수정, 삭제, 전체삭제, 등등)
	synchronized 가 아니다. 데이터와 연결데이터의 주소를 갖고 있으므로 중간에 추가, 삭제시 속도가 ArrayList 보다 빠르다.
- Vector : synchronized 메소드로 구성되어 있으므로 멀티 스레드에서 자료 공유시 자료의 정합성을 보장한다.
- Set : 중복 데이터를 추가 할 수 없다. 순서가 없다. 인덱스도 없다. Iterator 로 다음객체를 루프로 처리할수 있다.
- Iterator : 반복자 인터페이스이다. 자료구조에서 다음 객체를 가져와서 처리할 수 있다. hasNext(); true 이면 next(); 로 다음객체를 처리할수있다. 
- Map : 인터페이스이다. 키와 값의 쌍으로 된 객체가 저장되는 자료구조를 처리하는 인터페이스이다.
- HashMap : 클래스이다. Map 인터페이스의 모든 추상 메소드를 구현해 놓은 자바 java.util 기본클래스이다.
- Hashtable : 클래스이다. HashMap 과 모든 메소드는 같은 동작을 한다. 그러나 synchronized 메소드이다. 멀티스레드 상황에서 공유객체로 사용 가능하다.
- Properties : Hashtable 의 자식클래스, 키와 값이 문자열 String (키 = 값)입니다. 사용자정의환경설정파일, 환경설정 파일을 읽어서 값을 가져올수있다.
- TreeSet : Set 인터페이스를 상속했다. Tree 구조이다. 자료구조의 이진검색트리 처럼 삽입이 될때 자동 정렬한다. 검색할때 빠르다. 범위 검색도 빠르다. ~ 이상, ~ 초과, ~ 이하, ~ 미만, ~ 부터 ~ 까지
- TreeMap : TreeSet 과 동일한데 Map 인터페이스를 상속했다. 키와 값의 쌍으로 된 entry 구조를 저장한다.
- Comparable : 인터페이스이다. int compareTo(X item); 을 구현해야 한다.
	자기객체와 매개변수객체를 비교해서 같으면 0, 자기객체가 작으면 -1, 자기객체가 크면 1을 리턴하도록 개발해야 한다.
- Comparator : 인터페이스이다. int compare(X x1, X x2); 를 구현해야 한다.
	매개변수1번객체와 매개변수2번객체를 비교해서 같으면 0, 매개변수1번객체가 작으면 -1, 매개변수1번객체가 크면 1을 리턴하도록 개발해야 한다.
- Stack : 클래스이다. LIFO 구조이다. push, pop
- Queue : 인터페이스이다. LinkedList 는 Queue 를 구현한 클래스이다.
- Collections.synchronizedList : List 의 메소드를 동기화 메소드로 처리하는 List 를 얻을 수 있다.
- Collections.synchronizedSet : Set 의 메소드를 동기화 메소드로 처리하는 Set 를 얻을 수 있다.
- Collections.synchronizedMap : Map 의 메소드를 동기화 메소드로 처리하는 Map 를 얻을 수 있다.
- List.copyOf, Set.copyOf, Map.copyOf : 정적메소드 .copyOf 는 똑같은 컬렉션을 만들어서 리턴해준다. 그러나 리턴된 객체를 추가,삭제,수정할 수 없다.
- List.of, Set.of, Map.of : 정적메소드 .of 는 컬렉션을 만들어준다. 그러나 만든 객체를 추가,삭제,수정할 수 없다.

## 네크워크
- 서버 모드와 클라이언트 모드로 구분되어서 프로그램을 개발해야 한다.
- 서버모드에서는 클라이언트 소켓이 접속이 되도록 받아 들이는 accept 동작을 해서 생성된 소켓과 클라이언트 소켓이 통신한다.
	즉, 클라이언트 접속을 기다리는 소켓이 필수로 존재한다. 블로킹상태
	또한 클라이언트마다 접속이 되면 그 클라이언트와 통신을 소켓을 생성해서 사용 한다.
- 서버모드에서는 클라이언트 접속을 기다리는 부가 정보가 필요하다.
	어떤 네트워크(ip대역)를 기다릴건지, 어떤 포트(557~65535)에서 기다릴건지
	클라이언트의 접속을 받아들이면 클라이언트와 통신할 수 있는 통신 소켓을 리턴해준다.
	클라이언트와 통신하는 통신 소켓으로 데이터를 송신, 수신 할 수 있다.
	서버는 보통 수신동작을 먼저 실행한다.
	정상종료를 위한 클라이언트와의 약속을 실행해서 스레드를 정상종료 해야 한다.
	"@#die9)93dii%%^ldlsi"
- 클라이언트모드에서는 서버에 접속하기 위한 통신 소켓 한개를 가져야 한다. connect 동작
- 블로킹 상태 :
	* 서버모드에서는 클라이언트 접속을 기다리는 동작에서 블로킹상태가 된다.
	* 서버모드에서는 클라이언트 통신 소켓과 통신할때 데이터를 수신하는 부분에서 기다리는 블로킹 상태가 된다.
	* 클라이언트모드에서는 서버소켓과 통신할때 데이터를 수신하는 부분에서 기다리는 블로킹 상태가 된다.
- 클라이언트모드에서는 서버에 접속하기 위한 부가 정보가 필요하다.
	어디 서버 주소(IPv4 주소)에 접속할건지, 어떤 포트(접속할 서버에서 대기중인 포트번호)로 접속할지
	서버와 접속이 되면 데이터를 송신, 수신 할 수 있다.
	클라이언트는 보통 서버에게 나 접속했다는 부가정보를 송신하는 것을 먼저 실행한다.
	데이터를 클라이언트와 서버와 수신,송신을 잘 처리해야 한다.
	정상종료를 위한 서버와의 약속을 실행해서 스레드를 정상종료 해야 한다.
	"@#die9)93dii%%^ldlsi"
	
```
6월9일의 문제
- ServerApp, ClientApp 에 아래의 기능을 1차로 추가하세요
- ServerApp 에서는 통신소켓으로 입력되는 문자열을 계속 받아서 화면에 출력하는 메소드를 스레드로 만들어서 실행 하세요.
- ServerApp 은 "exit!@#$app" 이라는 문자열을 받으면 서버 앱를 중지할 수 있습니다
- ClientApp 에서는 Scanner 키보드로 문자열을 입력 받아서 서버로 전송하는 기능을 만들어서 실행 하세요.
- ClientApp 에서 "quit" 이라는 문자열이 키보드로 입력되면 "exit!@#$app" 이라는 문자열로
	바꾸어서 서버에 전송하고 ClientApp 도 종료하세요

- 2차로 추가할 것들
- ServerApp 에도 Scanner 키보드로 문자열을 입력 받아서 ClientApp 에 전송하는 기능을 만드세요.
- ServerApp 에서 "quit" 라는 문자열이 입력되면 "exit!@#$app" 이라는 문자열로
	바꾸어서 클라이언트에 전송하고 ServerApp 도 종료하세요
- ClientApp 에서는 통신소켓으로 입력되는 문자열을 계속 받아서 화면에 출력하는 메소드를 스레드로 만들어서 실행 하세요.
- ClientApp 은 "exit!@#$app" 이라는 문자열을 받으면 Client 앱를 중지할 수 있습니다
```

```
6월10일 문제
- 3차로 추가할 것들
- ServerApp 에는 이제 여러개의 ClientApp를 접속할 수 있는 기능을 만들어야 한다.
	Socket sck = this.ss(ServerSocket).accept();
	sck 를 배열에 추가해야 한다.
- 어떤 ClientApp 에서 데이터를 서버로 전송하면 서버는 다른 모든 ClientApp 으로 데이터를 broadcasting 해야 한다.
	ClientApp 에서 받은 데이터를 sck 배열의 모든 소켓으로 전송해야 한다.
- 접속중인 ClientApp 들 중에서 하나가 접속이 해제되면 그 소켓을 삭제해야 한다.
	배열에 있는 ClientApp Socket 들 중에서 해당 ClientApp 소켓을 삭제해야 한다.
- ServerApp 은 특별한 상황이 아니면 종료되어서는 안된다. 특별한 상황에서 종료할때에는 모든 ClientApp 연결을 끊어버린다.
	배열에 있는 모든 ClientApp Socket 을 연결해제 시킨다.
```

ServerApp
(ServerSocket accept 루프를 실행한다. => 접속 대기 스레드 한개)
(모든 배열의 Socket 들에게 데이터 전송하는 메소드 필요)
						- ClientApp
(ClientApp 연결되면 Socket 생성 => 배열에 추가 => 데이터 수신전용 스레드 한개 할당)
(ClientApp 연결해제시 특정 종료 문자열 "exit!@#$app" 이면 수신 스레드의 루프 종료한다. 배열의 Socket 삭제)
						- ClientApp
(ClientApp 연결되면 Socket 생성 => 배열에 추가 => 데이터 수신전용 스레드 한개 할당)
						- ClientApp
(ClientApp 연결되면 Socket 생성 => 배열에 추가 => 데이터 수신전용 스레드 한개 할당)
```
6월11일 문제
6월10일까지 해결한 상황에서
ServerApp 에서 키보드 입력을 하면 모든 클라이언트로 데이터를 송신하는 기능을 추가한다.
ServerApp 에서 종료하는 문자열 "exit!@#$app" 전송하면 모든 클라이언트는 앱을 종료해야 한다.
ServerApp 이 강제종료되면 모든 클라이언트 앱도 종료해야 한다.
```

## 6월13일 보충 강의 (12장, 13장, 14장)
- Object 는 자바 모든 클래스의 부모이다. 오버라이딩을 해서 Object 클래스의 toString(), equals(Object o), hashCode(), clone()
	Calculator cal1 = new Calculator();
	Calculator cal2 = new Calculator();
	Object o = cal1;
	Calculator c1 = (Calculator)o;
	Object o2 = new Computer();
	Computer c2 = (Computer)o2;
	public v1(Object o) {
		if ( o instanceof Calculator ) {
			Calculator c1 = (Calculator)o;
		}
	}
- System.exit 는 자바프로그램 프로세서를 종료한다.
- 기본데이터형을 참조데이터형으로 형변환 가능하다. (byte = Byte, int = Integer)
	Boxing : 참조데이터형으로 변환 (char => Character, long => Long)
	UnBoxing : 기본데이터형으로 변환 (Integer => int, Float => float)
	parseInt
	parseBoolean
- Math.정적메소드 : 반올림, 올림, 짜름, ...
- Math.random() : 0 ~ 1 사이의 실수를 리턴하므로 0.02920, 0.00000292
	1~100 까지의 랜덤 수 : (int)(Math.random() * 100) => 0~99 + 1 => 1~100
- 자바의 날짜 데이터는 Calender, Date, LocalDateTime(SQL type : date, datetime, time)
- 날짜 데이터를 문자열로 변환할때 변환 포맷 SimpleDateFormat, DateTimeFormatter
- 숫자 데이터를 문자열로 변환할때 (, 콤마, 소숫점 자리수) DecimalFormat
- 어노테이션 : 컴파일러에게 알려주는 정보, 빌드툴에게 알려주는 정보, 실행시 객체에게 알려주는 정보
- 제네릭(Generic) : 데이터형을 일반화 시킨것입니다.
	클래스를 선언할때에 데이터형을 대문자로 일반화 시켜서 클래스를 구현합니다.
	그러나 실제 객체로 생성될때에는 대문자를 사용할 데이터형과 일치 시켜서 new 를 해야 합니다.
	public class Calculator<P super Number> {
		//리턴데이터형 메소드(매개변수데이터형 변수명, 매개변수데이터형 변수명, ...) {
		//}
		BigDecimal add(P a, P b) {
			P plocal = null;
			BigDecimal bda = new BigDecimal(a.toString());
			BigDecimal bdb = new BigDecimal(b.toString());
			BigDecimal bd = bda.add(bdb);
			return bd;
		}
		BigDecimal sub(P a, P b) {
			BigDecimal bda = new BigDecimal(a.toString());
			BigDecimal bdb = new BigDecimal(b.toString());
			BigDecimal bd = bda.subtract(bdb);
			return bd;
		}

		public static void main(String[] args) {
			Calculator<Integer> cal = new Calculator<>();
			BigDecimal i = cal.add(10, 20);
			BigDecimal j = cal.sub(30, 5);
			System.out.printf("%s, %s\n", i.toString(), j.toString());

			Calculator<Double> cal2 = new Calculator<>();
			BigDecimal bd1 = cal2.add(53.2, 392.1);
			BigDecimal bd2 = cal2.sub(933.1, 33.1);
			System.out.printf("%s, %s\n", bd1.toString(), bd2.toString());
		}
	}
- 스레드 : 프로세스에서 실행하는 프로세스보다 작은 단위 작업
	일반적으로는 프로세스 하나에 스레드 하나가 실행됩니다.
	java 프로그램이 프로세스 하나 실행되면 main 스레드 하나 실행된다.
	그런데 main 스레드에서 다른 스레드를 생성하여 동시 작업을 처리 할 수 있다.
	어떤 경우에 스레드를 사용하는가 ? :
		1. 블로킹 상태가 발생하는 문장
		2. 메소드 또는 루프가 실행되는데 시간이 오래 걸려서 단일 스레드에서는 마치 중지된 느낌이 발생할 경우
- 스레드 생성하는 방법
	1. public class 클래스명 extends Thread {
			@Override
			public void run() {
				// 스레드에서 실행할 명령어들
				// new, loop, 다른객체 메소드 호출
				// 멀티스레드 상황에서는 공유객체의 값이 다른 스레드에서 변경되지 않도록 동기화메소드/블록을 사용하세요
			}
		}
		클래스명 객체명 = new 클래스명();
		객체명.start();	// 스레드가 실행대기 상태로 된다.
	2. public class 클래스명 extends 부모클래스 implements Runnable {
			@Override
			public void run() {
				// 스레드에서 실행할 명령어들
				// new, loop, 다른객체 메소드 호출
				// 멀티스레드 상황에서는 공유객체의 값이 다른 스레드에서 변경되지 않도록 동기화메소드/블록을 사용하세요
			}
		}
		클래스명 객체명 = new Thread(new 클래스명());
		객체명.start();	// 스레드가 실행대기 상태로 된다.
	3. 스레드풀을 사용하는 방법 p627~633
		ExecutorService 객체명1 = Executors.newCachedThreadPool();	60초 동안 스레드가 동작을 안하면 풀에서 제거한다.
		ExecutorService 객체명2 = Executors.newFixedThreadPool(최대개수);	자동 제거는 없으므로 프로그래머가 제거한다.
		객체명2.shutdown(), 객체명2.shutdownNow()
		객체명.execute();
```
6월13일 문제 1
- 기존에 만들었던 Computer 클래스에 equals 메소드를 오버라이드 하여 만든다.
	아래의 4개 멤버변수의 값이 같으면 true 를 리턴한다.
    private ECpu cpu;
    private ERam ram;
    private EStorage storage;
    private EGraphicCard graphicCard;
- Computer 클래스에 toString 을 오버라이드하여서 이름과 4개의 멤버변수의 값을 예쁘게 출력할 수 있는 문자열을 리턴한다.
- 교재p575~580의 Generic타입 직접 소스 코딩 하고 디버깅 분석하기
- 교제 p604~p610 공부하고 직접 소스 코딩하고 디버깅 분석하기 (제대로 종료 되도록 소스를 고쳐보세요)
- 기존에 만들었던 Computer 클래스에 Comparable 과 Comparator 인터페이스를 구현한다.
	compareTo 와 compare 메소드를 오버라이드 재정의 한다.
	compareTo 메소드는 cpu 멤버변수의 성능이 작다, 같다, 크다로 구분하는 기능을 구현한다.
	compare 메소드는 ram 멤버변수의 크기가 작다, 같다, 크다로 구분하는 기능을 구현한다.
```

## 6월16일 보충강의 (15장)
- 컴퓨터 자료구조 : 선형구조, 트리구조, 원형구조, 그래프구조.. : 데이터를 효과적으로 추가,수정,삭제,검색 하는 알고리즘에 의한 자료구조
- 선형구조 : 문자열 [][][][], 배열 [][][][], 링크드리스트 [] -> [] -> [], 키/값 구조
- 트리구조 : 이진트리로  []
				[]   []
			   [][] [][]
- ArrayList : List 인터페이스를 구현한 클래스이다. (맨뒤에 추가, 몇번째 위치 수정, 몇번째 삭제 ..) index 있다.
	단점 은 몇번째 위치에 추가, 몇번째 위치에 삭제 할 경우에 모든 배열리스트 원소를 재 조정합니다.
	그러므로 2가지 일이 빈번하면 속도가 느려진다.
- LinkedList : List 인터페이스를 구현한 클래스이다, ArrayList 처럼 동작한다. index 있다.
	장점은 2가지 일에 대해서 원소들을 재 조정하지 않는다. 다만 다음 원소의 주소를 갱신한다.
- Vector : List 인터페이스를 구현한 클래스이다. 다만 모든 메소드가 synchronized 메소드이다.
	즉 멀티스레드에서 공유객체로 사용하면 데이터 정합성을 보장한다.
- Set 은 인터페이스이다. => HashSet 이라는 클래스에서 구현했다. 중복된 원소는 추가 안된다.
	index 가 없다. 추가한 순서로 출력 보장 안됨
	원소가 같은것을 판별하는 방법은 내부적으로 hashCode() 결과값이 같고, equals(객체) 이 true 이면 같은 원소로 처리된다.
	HashSet<E> set객체명 = new HashSet<E>();
	set객체명.add(new Computer());
	set객체명.add(new Computer());
	for( E 원소객체명 : set객체명 ) {
		원소객체명.메소드명();
	}
	Iterator iter객체명 = set객체명.iterator();
	while( iter객체명.hasNext() ) {
		E 객체명 = iter객체명.next();
		객체명.메소드명();
	}
	iter객체명.hasNext() -> true 이면 다음 원소 사용 가능하다.
	E 객체명 = iter객체명.next();
- Iterator : 반복자 인터페이스이다. Collection객체의 .iterator() 메소드로 인터페이스 사용가능
	hasNext() 와 next() 메소드로 다음 원소객체를 계속 사용 가능하다. 추가한 순서로 출력 보장 안됨
	while 문과 같이 사용된다.
- Map : 인터페이스이다. 키와 값 의 쌍인 entry 객체로 구성된 값을 추가,수정,삭제,검색 하는 구조이다.
	HashMap : Map 인터페이스를 구현한 클래스이다.
	옛날 JSP 시절부터 웹서버 구현할때 GET, POST 방식의 브라우저 값을 서버에서 받을때 사용했었다.
	HashMap<키, 값> 객체명 = new HashMap<>();
	Map<키, 값> 객체명 = new HashMap<>();
	객체명.put(키, 값) 키의 데이터가 문자열일때 매우 큰 문제가 발생한다. 대소문자를 구분한다. "Tiny", "Tlny", "NAME", "Name", "name"
	getter/setter
	웹컨트롤러(..., HashMap<String, ?> m) {
		타입 변수 = m.get("XXXx");
	}
	웹컨트롤러(..., Book m) {
		타입 변수 = m.getXxx()
	}
- Hashtable : 클래스이다. HashMap 과 동일한 구조 이지만 모든 메소드가 synchronized 메소드 이다.
	멀티스레드에서 공유데이터로 HashMap 을 사용하면 데이터가 정합성(정확성)이 없다. 그러므로 Hashtable 을 사용해야한다.
	Hashtable 은 모든 메소드가 synchronized 이므로 하나의 메소드가 동작할때 다른 스레드에서는 기다린다.
- Properties : 클래스이다. HashMap 과 동일한 키,값 구조 인데, 키가 문자열 이고, 값도 문자열인 구조 입니다.
	주로 환경설정 파일을 읽어서 값을 가져올때 사용한다.
- TreeSet : 클래스이다. 이진트리 이며 중복이 없는 데이터 저장 구조이다.
	객체를 추가할때 자동으로 정렬이 이루어진다. TreeSet 에 저장되는 객체는 비교가 가능한 메소드가 있어야 된다.
	TreeSet<Computer> ts = new TreeSet<>();
	NavigableSet<Computer> ns1 = ts.descendingSet();
	NavigableSet<Computer> ns3 = ts.headSet(값아래로, 포함boolean);
	NavigableSet<Computer> ns2 = ts.tailSet(값위로, 포함 boolean);
	NavigableSet<Computer> ns3 = ts.subSet(값아래로, 포함 boolean, 값위로, 포함boolean);
- TreeMap : 클래스이다. 이진트리 이며, 키/값 의 쌍인 entry 가 저장되는 구조이다.
	TreeMap<키, 값> tm = new TreeMap<>();
	NavigableMap<키, 값> tm1 = ts.descendingMap();
	NavigableMap<키, 값> tm3 = ts.headMap(키값아래로, 포함boolean);
	NavigableMap<키, 값> tm2 = ts.tailMap(키값위로, 포함 boolean);
	NavigableMap<키, 값> tm3 = ts.subMap(키값아래로, 포함 boolean, 키값위로, 포함boolean);
- Comparable<일반화> : 인터페이스이다. Java VO, Java Bean, Java Dto, Entity 클래스에서 구현한다.
	int compareTo(일반화 매개변수);
		자기객체와 매개변수객체로 비교한 결과를 int 형으로 리턴 하는 메소드를 구현해야 합니다.
		자기객체 == 매개변수객체 => 0 을 return
		자기객체 > 매개변수객체 => 양수 을 return
		자기객체 < 매개변수객체 => 음수 을 return
- Comparator<일반화> : 인터페이스이다. 별도의 클래스에서 Comparator 인터페이스를 구현한다.
	int compare(일반화 매개변수1, 일반화 매개변수2);
		매개변수1과 매개변수2를 비교한 결과를 int 형으로 리턴 하는 메소드를 구현해야 합니다.
		매개변수1 == 매개변수2 => 0 을 return
		매개변수1 > 매개변수2 => 양수 을 return
		매개변수1 < 매개변수2 => 음수 을 return
- Stack : 클래스이다. FILO 먼저 push 된 객체가 나중에 결과가 pop 됨
			LIFO 나중에 push 된 객체가 먼저 결과로 pop 됨
- Queue : 인터페이스이다. FIFO 구조의 자료형태
		FIFO : 먼저 offer (추가 메소드) 된 객체가 먼저 결과로 poll (빼내는 메소드) 된다.
		LinkedList 가 Queue 인터페이스를 구현한 클래스이다.
- 동기화 컬렉션 : 멀티스레드에서 공유객체로 사용 할때 데이터 정확성을 위해서 synchronizedList/Map/Set 메소드로 자료를 리턴한다.
- .of(), .copyOf(), .asList() : 데이터를 수정하지 못하고 출력이나 계산만 하는 값으로 사용하는 자료구조를 리턴한다.
```
6월16일문제
15장의 예제들을 본인이 직접 소스 개발 해보세요. (클래스를 별도로 만들필요 없이 Chapter15 클래스의 메소드로 추가해도된다.)
15장의 확인문제 소스를 직접 풀어보세요.
6월13일 문제의 Computer 클래스를 HashSet 에 저장할때 키를 무엇으로 할지 결정하고 저장해보세요
6월13일 문제의 Computer 클래스를 TreeMap 에 5월16일 자료를 저장할때 자동으로 정렬되는것을 확인해보세요.
```
## 람다식 (이름없는 메소드, 화살표 함수)
- 메소드를 사용할려면 메소드이름으로 먼저 선언하고 그 메소드이름과 매개변수로 호출하고 리턴값을 받았었다.
- 이름없는 메소드를 만들어서 그 메소드의 매개변수와 몸체와 리턴값을 사용할 수 있는 방법을 말한다.
- 사용하는경우 : (매개변수) -> {메소드의 몸체}
- 선언하는 경우 : 인터페이스가 필수이다. 인터페이스에 추상메소드가 1개 존재 (@FunctionalInterface 어노테이션)
- 데이터만 선언하고 실행하는 메소드는 나중에 처리하는 경우
- 사용하는경우 : (매개변수) -> 한문장
	() -> {
		문장1;
		문장2;
		return 값;
	}
	(매개변수,..) -> 한문장 ::: 한문장의 결과가 리턴값으로 리턴된다.
- 원래는 메소드(타입형, 변수) {
	} 선언하는 부분
	메소드(매개변수); 실제로 사용하는 호출
	
	메소드(인터페이스(추상메소드1개)); 선언하는 부분 추상메소드(Boolean 리턴, 매개변수가 Double, String, ArrayList)
	메소드(() -> {}); 사용하는 부분
클래스::정적메소드 -> (Boolean 리턴, 매개변수가 Double, String, ArrayList)
	메소드(클래스::정적메소드명);
클래스::멤버메소드 -> (Boolean 리턴, 매개변수가 Double, String, ArrayList)
	클래스명 객체명 = new 클래스();
	메소드(객체명::멤버메소드명);
클래스::new -> 리턴은 인스턴스된객체, 매개변수가 Double, String, ArrayList)
	메소드(클래스::new);
```
문제1
	public interface INumberProc {
		public void process(String str, Integer age);
	}
	proces 를 람다식을 만들어서 호출하는
	str 변수와 age 변수의 내용을 아래와 같이 출력하는 메소드
	"{str} 님의 나이는 {age} 입니다."
	printHuman(INumberProc inp) {
		String a, Integer b
		inp.process( .... );
	}
	printHuman(.......);
문제2
	str 변수와 age 변수의 내용을 아래와 같이 출력하는 메소드
	"자동자 {str} 의 년식은 {age} 입니다."
	printCar(INumberProc inp) {
		inp.process( .... );
	}
	printCar(.......);
문제3
	List<Integer> items = new LinkedList<>();
	items.add(10);
	items.add(20);
	items.add(30);
	items.add(40);
	items.add(70);
	
	public interface IArrayProc {
		public Long process(List<Integer> list);
	}
	list 배열의 합을 리턴하는 기능
	void sum() {
		Long sum = 0;
		....
		System.out.printf("합은 {%d} 입니다.", sum);
	}
문제4
	list 배열의 합을 리턴하는 기능
	void avg() {
		Long avg = 0;
		....
		System.out.printf("평균은 {%d} 입니다.", avg);
	}
```

## 스트림 (배열처리, 콜렉션처리, 루프처리)
- 배열이나 콜렉션 자료형을 일률적으로 순서적으로 처리하는 방법 stream
- 객체명.stream() / List 의 stream() : 내부반복자로 처리 된다.
- 메소드 체이닝 : 메소드명(...).메소드명().메소드명(...).메소드명() ===> 최종적으로는 마지막 메소드의 리턴값이 결과 값이다.

```
6월17일 문제
- 6월16일 문제 풀은것을 계속 활용한다
- Computer 클래스에 Integer price, Integer qty 멤버변수를 추가한다.
- Computer 클래스 객체 배열을 생성하고 배열 원소 5개 이상 추가한다. 비기본생성자를 통하여 price 와 qty 는 임의의 정수를 저장하세요
- 객체배열의 stream() 을 이용하여 Computer 객체의 재고 총 금액을 계산하세요. 모든 배열 원소들의 price * qty 의 합계금액이다.
- 객체배열의 stream() 을 이용하여 cpu 가 가장 낮은 Computer 객체의 재고 수량이 몇개인지 계산하세요
- 6월2일 문제 풀은것을 계속 활용한다.
- Contact 클래스에 Integer age 멤버변수를 추가한다.
- Contact 클래스 객체배열을 생성하고 배열 원소 5개 이상 추가한다. age 값도 생성자에서 저장되도록 추가한다.
- 객체배열의 stream() 을 이용하여 연락처 사람의 평균 나이를 계산한다.
```
## 6월18일 보충강의
- 12장 Object ~ 
- 13장 Generic
	개념 : 클래스를 만들때 클래스의 메소드, 멤버변수, 생성자 등에서 사용할 데이터형을 직접 지정하지 않고 대문자를 이용하여 일반화를 시킨다.
		클래스를 객체로 인스턴스하여 사용할때 그때에 비로서 일반화 된 데이터형을 실제 데이터타입으로 선언한다.
	사용방법 : ArrayList<Market> 객체명 = new ArrayList<>();
			객체명.add(Market 변수);
			Market s = 객체명.get(0);
	선언방법 : public interface 인터페이스명<대문자단어, 대문자 extends Number> {
				public 대문자단어 getInfo();
				public Integer getCountFromInfo(대문자단어 o1, Integer i2);
			}
			public class MyGeneric<AAA, B, EEE> {
				private AAA item;
				public MyGeneric(AAA item) {
					this.item = item;
				}
				public AAA getInfo() {
					return this.item;
				}
				public void setInfo(BBB, EEE) {
					...
				}
			}
			MyGeneric<String, Integer, Market> 객체명 = new 클래스명(String);
			MyGeneric<Computer, Boolean, Long> 객체명 = new 클래스명(Computer);
- 14장 Thread
	동기모드 : 명령어들이 순차적으로 차례대로 실행되는데 현재 명령어가 종료되어야지 다음명령어가 실행되는 모드
		(블로킹상태) : 현재 명령어가 실행이 끝나지 않아서 기다리는 상태
	비동기모드 : 명령어들이 순차적으로 차례대로 실행되는데 현재 명령어가 종료되지 않아도 다음 명령어를 실행하는 모드
		(난블로킹상태) : 현재 명령어가 실행 끝나는것을 기다리지 않고 다음명령어로 제어를 넘긴다.
	멀티태스킹 : 동시에 작업을 처리하는 개념
	멀티프로세싱 : 동시에 여러 프로세서에서 멀티태스킹하는 개념
	멀티프로그램 : 동시에 여러 프로그램이 실행되는 개념
	스레드 : 자바프로그램에서 main 함수가 실행되는 작업 단위 (main스레드)
		(운영체제에서 실행되는 가장 작은 작업 단위, 스레드마다 스택메모리가 존재함)
	멀티스레드 : main 함수에서 새로운 스레드들을 생성하여 시작할 수 있다.
	멀티스레드효과 : main 스레드에서 실행되는 문장들과 새로운 스레드에서 실행되는
			메소드(함수)들이 동시에 실행되는 효과가 발생된다.
	멀티스레드로 만드는 방법 : 
		1. public 클래스 extends Thread {
				@Override
				public void run() {
					실행할 명령어들.
					..if
					... while
					.. for
				}
			}
			클래스 객체명 = new 클래스명();
			객체명.run(); => 동기모드
			객체명.start(); ==> 새로운 스레드에서 run 메소드를 실행한다. 비동기모드
				(이 문장은 블로킹이 아니고 난블로킹이다) (동기가 아니라 비동기로 처리한다)
			다음문장
		2. public 클래스 implements Runnable {
				@Override
				public void run() {
					실행할 명령어들.
					..if
					... while
					.. for
				}
			}
			Thread 객체명 = new Thread(new 클래스());
			객체명.start();
		3. 스레드풀을 사용하는 방법 p627~633
		ExecutorService 객체명1 = Executors.newCachedThreadPool();
			60초 동안 스레드가 동작을 안하면 스레드풀에서 제거한다.
		ExecutorService 객체명2 = Executors.newFixedThreadPool(최대개수);
			자동 제거는 없으므로 프로그래머가 제거한다.
		객체명.execute();	=> 스레드를 실행한다.
		객체명2.shutdown(), 객체명2.shutdownNow() => 스레드를 종료한다.
- 왜 스레드를 사용합니까 ? : 블로킹상태가 발생하는 문장을 다른스레드를 생성해서 실행하면 main스레드 입장에서는 다음문장을 바로 실행할수 있다.
	. 프로그램이 멈춰있는 현상을 고객에게 보여주면 안되는 상황일 경우에
	. 일괄적인 동작을 하는데에 있어서 동시에 처리해야 하는 경우에
	. 빠른 속도가 필요한 경우이면서 (synchronized 메소드를 사용하면 속도가 느려지게 된다)
- 스레드마다 이름이 존재한다.
	기본값은 JVM 이 알아서 이름을 정합니다. 그 이름을 변경할려면 객체명.setName("새로운이름")
	Thread.currentThread() => 현재 스레드, .getName() 스레드의 이름을 가져온다.
- 스레드 마다 상태가 존재한다.
	start() -> 실행준비상태 -> 실행상태 -> 실행대기/일시정지
		일시정지 -> 실행대기
		실행대기 -> 실행상태
- 멀티스레드 상황에서 공유데이터의 값을 저장하거나 읽을때 다른 스레드에서 값을 바꿔버릴수 있다.
  그러므로 데이터 정합성을 위하여 synchronized 메소드나 블록을 이용해야 한다.
- 스레드는 실행하는 것보다 정상 종료 하는게 매우 중요합니다.
	정상종료 방법은
	1. void run() 메소드가 정상적으로 종료되는 알고리즘을 구현해야 한다.
	2. interrupt() 실행해서 void run() 메소드 안에서 InterruptedException 이 발생하여 종료되도록 한다.
	3. Thread 를 상속한 스레드객체.setDaemon(true) 하고 스레드객체.start() 실행하면
		스레드가 정상 종료된다.
	4. System.exit() 라도 사용해서 모든 스레드를 종료해야 한다.
```
6월18일 문제
- p633 의 소스를 직접 개발해서 실행해보시고 나서 14줄을 i <= 100000; 까지로 바꿔서 실행해보면
	main 스레드는 점점 속도가 느려질 겁니다.
	int -> long : Future<Long>, Callable<Long>, Long sum, Long result
- main 스레드는 30번 줄에서 다른 스레드가 계산을 끝날때까지 기다리기 때문입니다.
- public interface ISumLoop {
		public void print(Long num);
	}
	ISumLoop 인터페이스 print 메소드를 구현하여 main 스레드가 좀더 빨리 실행 되도록 개선해 보세요
	즉 main 스레드는 다른 스레드 계산이 끝날때까지 기다리지 않고 계속 실행되도록 해 보세요.
	Callable 을 사용하지 말고 p632 의 Runnable 인터페이스를 구현한 클래스를 구현해서 사용하세요.
```
## 6월19일 보충강의
- Collection Interface
	* List :
		index 가 있다. 순서가 있다.
		선형 자료형에 원소를 추가,수정,삭제,전체삭제 등등을 한다. 검색은 원소의수
	* Set :
		index 가 없다. 순서가 없다.
		List 의 자료 제어(추가,수정,삭제,전체삭제 등등)를 지원한다.
		그러나 원소가 중복된 값은 추가가 안된다.
		중복된 값을 판별하는 방법은 hashCode() 가 true 이고, equals(Object o) 가 true 이면
		중복객체이다라고 판별한다.
	* Map :
		키 / 값 의 쌍으로 이루어진 entry 들을 추가,수정,삭제,전체삭제 등등을 한다.
		키가 index 역할을 한다. 키가 같은 원소는 중복이므로 추가 할 수 없다.
	* Queue :
		FIFO, LILO 구조의 자료 저장 처리를 한다.
		offer, poll
	* Iterator :
		다음 원소를 가져올수 있는 자료구조에 사용 하는 인터페이스
		boolean hashNext(); 다음원소가 있으면 true 리턴
		<A> next(); 다음원소를 가져온다.
		
		List<Computer> list = new LinkedList<>();
		Iterator iter = list.iterator();
		while ( iter.hasNext() ) {
			Computer s = iter.next();
		}

- Collection Class
	* ArrayList implements List
		배열처럼 선형 구조 이다.
		중간에 데이터를 추가,삭제 할경우에는 전체 원소 배열의 위치를 재조정하므로 속도가 느려지게 된다.
	* LinkedList implements List
		다음원소의 주소를 가지고 있는 링크형 선형 구조 이다.
	* Vector implements List
		모든 메소드가 synchronized 인 동기모드 메소드이므로 멀티스레드에서 공유객체로 사용할 수 있다.

	* HashSet implements Set
		중복 데이터를 추가 하지 않는다.
	* TreeSet implements Set
		이진검색트리 구조인 자료형인데 중복데이터를 추가하지 않는다.
		자료를 검색하는데 좋다.

	* HashMap implements Map
		키/값 구조로 된 원소들을 저장하는 자료형
		HashMap 의 키를 문자열로 할때 키가 오타가 발생하거나 수정이 발생하면 매우 유지보수가 어려운 소스를 만들어내는 악성코드
		HashMap<Integer, String> map = new HashMap<>();
		Map<Integer, String> map = new HashMap<>();
	* Hashtable implements Map
		HashMap 같은 동작을 하는데 모든 메소드가 synchronized 이므로 멀티스레드에서 공유객체로 사용하면 데이터 정확하다.
	* TreeMap implements Map
		이진검색트리 구조인 자료형인데 키가 같은 중복데이터를 추가하지 않는다.
		자료를 검색하는데 좋다.
	* Properties implements Map<String, String>
		키도 문자열, 값도 문자열인 원소를 저장하는 자료형인데, 주로 환경설정값들을 읽을때 사용한다.

	* Stack extends Vector
		LIFO, FILO 구조의 자료저장 형태, push, pop
- 1555라인의 ## 6월16일 보충강의 (15장) 를 참조하세요
	TreeMap<Integer, String> tm = new TreeMap<>();
	tm.add.........
	...
	NavigableMap<Integer, String> nm1 = tm.headMap(3, true);
- 람다식 (선언하는 경우는 많이 없지만, 사용은 많이 합니다.)
	javascript 화살표함수
	이름없는 메소드
	메소드 선언시에는 항상 public 리턴값 메소드명(매개변수....) { .... }
	(매개변수...) -> {
		명령어들; 명령어들2;
	}
	() -> 한문장 (결과가 자동으로 리턴된다)
	() -> {return 한문장;}
	
- a. 선언하는 방법
	@FunctionalInterface
	a.1 람다인터페이스를 만들고 추상메소드 1개만 있어야 한다.
		public inteface ISample {
			public String sample(Integer a, Long b);
		}
	a.2 다른 클래스에서 정적/멤버메소드를 만들때 람다인터페이스를 매개변수로 선언해야 한다.
		public class SampleClass implements ISample<String, Integer, Long>{
			private Integer c;
			private Long d;
			public String sample(Integer a, Long b, ISample inter) {
				return inter.sample(a, b);
			}
		}

- b. 사용하는 방법
	b.1 람다식에서 사용할 값(지역변수,멤버변수,static변수)이 있어야 한다.
		SampleClass sc = new SampleClass();
	b.2 이 값을 기준으로 어떤 동작을 할건지 람다인터페이스의 추상메소드를 구현하면 된다.
		sc.sample(4, 60L, (m, n) -> {return (m + n).toString();});
		sc.sample(100, 3000L, (x, y) -> (x * y / 10).toString());
- 람다식 표현의 형태
	() -> 한문장    : 한문장의 자동 리턴된다.
	() -> { 여러문장들;..;..;.; }    : 여러문장이 실행되며 리턴할려면 return 값; 문장이 꼭 필요하다.
	(String a, Integer b) -> {}
	(a, b) -> {}
	(var a, var b) -> {}
- 스트림 stream
	개념 : 일률적으로 구성된 자료형 구조에서 원소마다 동일한 동작(계산,검색,변환)을 하는경우에 stream()
	List<String> alist = new ...();
	for ( String s : alist ) {
		print(s);
	}
	
	alist.parallelStream()
		.forEach( (m) -> {print(m);} );
	체이닝 문법
	
	브레이크포인트 사용하거 디버깅이 편하다.
	Stream<String> sss = alist.parallelStream();
	sss.forEach( (m) -> {print(m);} );

	Optional 은 값이 null 일지도 모르는 그런 객체를 사용할때 NullExecption 방지하는 역할을 한다.
	Optional<Result> result = 함수.함수.();
	result.orElse(new Result(33));
```
6월19일
12장부터 17장까지 예제를 직접 소스 개발해보세요.
	Java VO 클래스는 Lombok 사용하세요
	***Example.java 는 main 에서 별도의 함수로 만들어도 됩니다.
```
## 입출력 스트림 (java.io)
- InputStream, OutputStream : byte 단위로 한바이트씩 입력장치로 byte 를 읽거나 출력장치로 byte 를 보낸다.
- Reader, Writer : 문자단위로 읽거나 문자단위로 보낸다.
- 바이트단위 스트림 : 파일을 읽거나 쓰거나, 소켓으로 읽거나 쓰거나
- 바이트 출력 스트림 :
	void write(byte[]); 보통 파일인경우 1024, 4096, 8192 길이의 배열 루프로 전송하고
	void flush(); 버퍼를 비우고 루프를 실행한다.
	void close(); 출력스트림을 닫는다.
- 바이트 입력 스트림 :
	int read(byte[]); 몇바이트 읽었는지 리턴을 하고 읽은 데이터는 매개변수에 저장된다. 루프로 처리
		-1 이 리턴되면 더이상 읽은 데이터 가 없습니다.
	void close(); 입력스트림을 닫는다.
- 문자단위 스트림 : 문자를 읽거나 쓰거나
- 문자 출력 스트림 : Writer, FileWriter, BufferedWriter, ....
	void write(char[]);
	void write(String); 문자열의 CharacterSet(UTF-8, UTF-16, ANSI,..) 정보를 보낼수 없다.
	void flush(); 버퍼를 비우고 루프를 실행한다.
	void close(); 출력스트림을 닫는다.
- 문자 입력 스트림 : Reader, FileReader, BufferedReader, ..
	int read(char[]);  읽은 데이터를 매개변수에 저장하고, 읽은 갯수를 리턴한다.
		리턴값이 -1이면 더이상 읽을 데이터가 없다.
	void close();
- 객체 직렬화/역직렬화 : public Animal implements Serializable {
		private static final long serialVersionUID = -223925009698727272L;
		
		private String name;
		private Integer age;
		private Integer leg;
		private Computer com;
	}
	직렬화 : 객체를 byte[] 로 변환을 해서 파일이나 네트워크 전송을 하여 보관이나 전달이 가능하다.
	역직렬화 : byte[] 로 보관되어 있는 데이터를 Java 객체로 변환한다. Class 선언이 소스/라이브러리 안에 있어야 한다.
		serialVersionUID 값이 같은 클래스의 객체로 만든다.
- 요즘에는 JSON 으로 객체를 전송하거나 저장하는 경우가 많다.
	{"name":"dkdisk", "age":52, "leg":4, "com":{"cpu":"I3", "ram":"4GB",..}}
- File, Files 클래스로 파일과 디렉토리 정보를 얻어오거나 복사,이동,삭제 등등을 할 수 있다.
## 19장 Java 네트워크
- N개의 호스트들이 TCP/IP, UDP/IP 프로토콜 이용하여 데이터(byte[], 문자들, ..) 전송하는 기능
- Java 에서 Server 역할을 하는 호스트가 필요하고, Client 역할을 하는 호스트가 필요하다.
- Client 끼리, Server 끼리는 통신을 안한다.
- Server 를 만드는 방법
	1. ServerSocket 클래스 객체가 필요하다.
	2. 클라이언트를 받아들이는 포트를 지정해야 한다. 클라이언트의 IP 대역폭을 지정해야 한다.(기본값 모든IP)
	3. .bind(정보) 를 해야 한다. (포트, IP 대역폭)
	4. Socket socket(클라이언트소켓객체) = ServerSocket객체.accept();		// 클라이언트 접속을 기다린다. 접속이 될때까지 블로킹 상태
	5. 4번에서 접속이 되면 Socket 객체가 리턴 된다.
	6. 리턴된 Socket 객체의 InputStream, OutputStream 를 이용하여 Client 와 통신을 할 수 있다.
	6-1. 일반적으로 서버는 클라이언트소켓객체 로부터 읽는 동작 (수신) 으로 시작을 한다.
	7. Client 와 통신이 끝나면 클라이언트소켓객체.close();
	8. accpet() 도 끝내려면 ServerSocket객체.close(); 한다.
- Client 를 만드는 방법
	1. Socket 객체 = new Socket();
	2. 객체.connect(주소와 포트가 있는 정보); 정상접속이 되면 다음 문장 실행, 예외 발생
	3. 소켓객체의 InputStream, OutputStream 을 이용하여 서버와 통신을 한다.
	3-1. 클라이언트 일반적으로 서버에 첫 접속시에 정보(나의 주소, 명령어,..)를 보내는 걸(송신)로 시작한다.
	4. 소켓객체의 close() 해서 끝낸다.
- p844 까지 예제를 직접 개발하여 디버그모드로 실행 하세요
- 블로킹 상태 : 본 md 파일의 1328라인의 ## 네크워크 참조
	* 서버모드에서는 클라이언트 접속을 기다리는 동작에서 블로킹상태가 된다.
	* 서버모드에서는 클라이언트 통신 소켓과 통신할때 데이터를 수신하는 부분에서 기다리는 블로킹 상태가 된다.
	* 클라이언트모드에서는 서버소켓과 통신할때 데이터를 수신하는 부분에서 기다리는 블로킹 상태가 된다.
- Socket 에서는 수신하는 read 를 먼저 동작해야지만 상대편 Socket 에서 보내는 데이터를 받을 수 있다.
## 6월23일 보충 강의
- Generic : 클래스를 만들때 명시적으로 데이터를 선언하여 사용하지 않고, 여러 데이터형을 사용해야 할경우에
	그 데이터형을 Generic (일반화) 하여 선언합니다.
	public class Computer {
		private String name;
		private Integer price;
	}
	Computer com = new Computer();
	
	Generic 사용할 경우
	public class, interface Computer<대문자1, 대문자2 extends Number> {
		private <대문자1> name;
		private <대문자2> price;
	}
	Computer<String, Integer> com1 = new Computer();
	Computer<MyName, BigDecimal> com2 = new Computer();
- Java 에 Collection 개념이 없다면 모든 개발자들이 선형자료형, 가변길이배열, 트리자료형, 키/값 자료형을 스스로 만들어야 한다.
	컬렉션이 없다면 무조건 배열을 사용해야 된다. 또는 관련 라이브러리를 검색해서 사용해야 한다.
- Collection : 인터페이스
	List<Computer> extends Collection : 인터페이스
		가변길이 배열 : 길이가 변합니다. 어떤 데이터형이 든지 같은 데이터형으로 배열 처럼 사용 가능하다.
			인덱스가 존재한다. O(n)
			.add(String, Integer), remove(인덱스), 객체변수명.get(인덱스), ...
- ArrayList, LinkedList, Vector : List 인터페이스를 구현한 클래스이다.
	Set extends Collection : 인터페이스 
		가변길이 배열 : 인덱스가 없다. 중복데이터를 추가 하지 못한다. O(n)
			.add()
- HashSet : Set 인터페이스를 구현한 클래스
- Map : 인터페이스
		가변길이, 키/값의 쌍으로 된 자료형을 저장한다. 키가 중복되면 안된다. 중복 키는 추가 안된다.
			.put(키, 값)
- HashMap : Map 인터페이스를 구현한 클래스
- Properties : 키도 문자열, 값도 문자열, 환경설정 파일 읽을때 사용한다.
- TreeSet : 클래스, 이진검색트리 구조 이며, 데이터를 추가 하면 자동 정렬되서 저장된다. O(n/2)
- TreeMap : 클래스, 키/값 쌍으로 된 자료형 저장한다. 이진검색트리 구조, O(n/2)
- Iterator : 인터페이스, 반복자 hasNext() 가 참이면 다음 원소가 존재한다. next() 다음원소를 가져온다.
- Stack : 클래스, LIFO 스택구조 자료형, push(매개변수) 저장, 데이터형 n = pop() 가져오면서 삭제한다.
- Queue : 인터페이스, LILO/FIFO 큐 자료형, offer(매개변수) 저장, 데이터형 n = poll() 가져온다 삭제한다.
- .of(), copyOf(), Arrays.list(매개변수) => 원소를 추가, 수정 못하는 복사된 배열을 가져온다.
- 멀티스레드 상황에서 컬렉션을 공유된 객체로 사용할려면 데이터 정합성이 필요합니다.
	synchronized 메소드들을 사용해야 한다. 다만 속도가 느릴수 있다.
	Vector(List), synchronizedList, synchronizedSet(Set 객체 매개변수), synchronizedMap
	Hashtable(Map)
- Json 데이터형은 HashMap(런타임에러)을 사용하지 마시고 json 라이브러리를 사용하세요 getter/setter(컴파일에러)

## 네트워크
- OSI 7 Layer
 (네트워크 하드웨어와 소프트웨어 개발시 국제적으로 표준으로 삼아서 어떤 회사 어떤 국가든 개발해도 서로 네트워크 가능하도록 표준)
 (Physical, Data, Network, Transport, Session, Presentaion, Application)
- TCP/IP (검증함, 데이터 누실 없음), UDP/IP (검증안함, 1byte 누실해도 별 상관없는 데이터 통신, 영상, 그림, 음악)
- 프로그램 개발자가 개발한 명령어가 실행되는 모드
	동기모드 : 거의 90%, 개발자가 명령어를 코딩한다, 현재 명령어가 실행 끝나면 다음 명령어를 실행한다.
		현재 명령어가 끝날때까지 기다린다. 현재 명령어가 끝나야지 다음 명령어를 실행한다.
		while(true) {
			동기명령어1 블로킹상태가 생김 (실행문장, 메소드호출, 제어문, 연산문, 할당문, 객체생성)
			동기명령어2 블로킹상태가 생김
			동기명령어3 블로킹상태가 생김
			동기명령어4 블로킹상태가 생김
		}
	비동기모드 : 개발자 코딩한다. 현재 명령어가 끝나지 않아도 실행하라고 하면 다음 명령어를 실행한다.
		현재 명령어가 끝나지 않아도 실행이되면 다음 명령어를 실행한다. loop 에서는 위로 올라가서 실행한다.
		while(true) {
			동기명령어1 블로킹상태가 생김 (실행문장, 메소드호출, 제어문, 연산문, 할당문, 객체생성)
			비동기명령어1 (블로킹이 발생안함) (메소드호출)
			동기명령어3 블로킹상태가 생김
			동기명령어4 블로킹상태가 생김
		}
- Java 네트워크 프로그램 개발 :
	Server 모드 : 클라이어트와 통신한다. 클라이언트는 여러개 가능하다
		받아들일수 있는 IP 대역, 포트번호를 이용하여 bind(생성자에서 처리가능)하고 클라이언트 접속을 기다리고
		, 접속이 되면 클라이언트와 통신 가능한 소켓이 새로 생긴다. 이 소켓으로 데이터를 통신한다.
		, 접속을 기다리는 소켓 1개, 클라이언트와 통신할 소켓 1개, 만약에 클라이어트 100개 = 통신 소켓 100개
	Client 모드 : 클라이언트끼리는 통신 못함, 서버 1개와 통신 가능
		접속할 IP 와 포트번호를 이용하여 소켓을 만들어서 서버에 접속하고
		, 접속이 되면 해당 소켓으로 데이터를 통신한다. 소켓이 1개만 필요한다.
	정상적으로 종료할때 접속을 끊어야 합니다. :
		Server 는 클라이언트통신소켓을 닫는다. 모두 닫으면 기다리는 소켓을 닫는다.
		Client 는 서버통신소켓을 닫는다.
		상대방이 끊으면 자기는 예외가 발생하므로 예외 처리를 해야 합니다.
	너무 오랫동안 블로킹상태가 발생되는 문장이 있음 :
		1. 서버에서는 클라이언트 접속을 기다리는 문장 Socket sock = accept(); 메소드
		서버에서는 클라이언트통신소켓에서 데이터를 읽기 위하여 기다리는 문장
		
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		BufferedReader br = new BufferedReader(
			new InputStreamReader(socket.getInputStream())
		);
		
		2. br.read...();
		Strin str = br.read...();	=> 데이터를 읽기 위하여 상대방에서 보낼때까지 기다리는 문장
		서버나 클라이언트 동일하게 데이터를 읽기위하여 기다리는 문장은 블로킹상태가 오래 걸린다.
- Json 데이터형식 : 객체를 문자열로 표현하는 전세계적인 방법 RestFull API 는 필수이다.
	{} : 집합기호는 객체를 의미한다.
	"" : 문자열을 의미한다.
	[] : 배열을 의미한다.
	숫자 : 숫자를 의미한다.
	true/false : Boolean 을 의미한다.
	키/값의 쌍으로 이루어진 데이터 표현방식
	키에는 Java, C#, Python 이나 인스턴스 객체의 멤버변수 이름을 키로 하고 값을 값으로 표현한다.
	public class Human {
		private Long id;
		private String name;
		private String ssn;
		private Telephone tel;
		private int age;
	}
	public class Telephone {
		private int vendor;
		private int first;
		private int last;
	}
	Human hong = new Human(2, "홍길동"
		, "333333-3333333"
		, new Telephone(10, 65, 3021)
		, 34);
	Json 표현 문자열 => {"hong":{"id":2, "name":"홍길동"
		, "ssn":"333333-3333333",
		"tel":{"vendor":10, "first":65, "last":3021}
		, "age":34}}
	google 에서 json beautify
## Java 네트워크 프로그램 개발
- 서버 클래스 구현
	멤버변수 : 
		accept 용 ServerSocket (접속을 허용할 IP 대역폭(10/0/0/0, *), 포트번호)
		accept 한 클라이언트소켓을 담을 객체(N개 일 경우에는 배열, 콜렉션, ...)
		클라이언트소켓과 통신할 InputStream, OutputStream 이것을 편리하게 사용할 보조 스트림들
	스레드 :
		accept(); 블로킹 발생되므로 스레드로 표현해야 한다.
		클라이언트소켓과 데이터 통신시 읽기동작에서 블로킹 발생되므로 스레드로 표현해야 한다.
	메소드 : SRP 규칙, ... (Single Responsible Recipe)
		함수는 하나의 일만 제대로 수행하는 책임이 있다. (JUnit 단위 테스트 활용하세요)
	
- 클라이언트 클래스 구현
	멤버변수 : 
		connect 할 Socket (서버모드에 접속할 IP, 포트번호)
		소켓과 통신할 InputStream, OutputStream 이것을 편리하게 사용할 보조 스트림들
	스레드 : 
		서버와 연결된 소켓에서 데이터 통신시 읽기동작에서 블로킹 발생되므로 스레드로 표현해야 한다.
	메소드 :
```
6월23일 문제
19장까지 모르는 예제 꼭 직접 코딩하고 디버깅 해야 합니다.
연습문제도 해보셔야 합니다.
선생의 가르친 소스 com.mjc813.network.*; 네트워크 소스 분석하고 자기도 개발해야 합니다.











최종문제
ServerApp 은 대화방목록을 가지고 있다. ServerApp 종료시 모든 클라이언트 종료시킴
ClientApp 은 명령어들로 구성해야 한다.
======================================================
1.대화명변경, 2.방목록보기, 3.방 생성, 4.방 입장, q.종료
======================================================
1 엔터
대화명변경 : 새로운대화명 입력 엔터

결과 :
======================================================
1.대화명변경(현재대화명출력), 2.방목록보기, 3.방 생성, 4.방 입장, q.종료
======================================================
{"command":"CHNAME", "data":"새로운대화명"}
제약조건 : 클라이언트는 대화방 바깥에서 입력한 문자는 서버에 전송하지 않는다.
제약조건 : 대화명이 없으면 방 생성, 방 입장 못함


2. 방목록보기 : 서버의 대화방목록을 출력한다.
2 엔터

결과 :
---------------------------------------------------
1	대화방이름(3)
2	대화방이름(2)
6	대화방이름(4)
11	대화방이름(1)
---------------------------------------------------
{"command":"ROOMLIST"}

3. 대화방 생성
3 엔터
새로운 대화방명을 입력 : 대화방 명 입력 엔터
{"command":"CREATEROOM", "data":"여름방학 뭐할래"}
---------------------------------------
[대화방이름]	(exit 입력시 퇴장합니다.)
---------------------------------------
(대화명) : 입장 했습니다



4. 대화방 입장
======================================================
1.대화명변경(현재대화명출력), 2.방목록보기, 3.방 생성, 4.방 입장, q.종료
======================================================
4 엔터
대화방번호를 입력 : 대화방 번호를 입력 엔터

결과 :
------------------------------------------------------------------
[대화방명] (exit 입력시 퇴장합니다.)
------------------------------------------------------------------
(대화명) : 입장 했습니다.
- 방입장하기 전까지 있었던 대화는 출력할 필요 없다.
(대화명2) : 대화글
(대화명3) : 대화글
안녕하세요 엔터
(대화명) : 안녕하세요
(대화명4) : 대화글
(대화명3) : 퇴장 했습니다.
(대화명2) : 대화글
exit 엔터
(대화명) : 퇴장 했습니다.

대화 작성 JSON 명령어 : {"command":"CHAT", "room":2, "data":"전송할 문자열"}
대화방 퇴장 JSON 명령어 : {"command":"EXITROOM", "room":2}


5. 종료 : 프로그램 종료
======================================================
1.대화명변경(현재대화명출력), 2.방목록보기, 3.방 생성, 4.방 입장, q.종료
======================================================
q 엔터

결과 :
프로그램 종료합니다.
C:\xxxxxx\xxx\xxxx
데스크탑사용자 서버모드 : 10.11.83.61
노트북사용자 서버모드 : 172.111.114.116
```
# Docker
- 서버용 프로그램들을 설치하고 설정하는 일련의 과정들을 각 장비마다 할려면 많은 수고와 시간이 소모된다.
- 이런 동작들을 이미지(레이어들로 구성되어있다.) 빌드해서 해당 이미지를 다운로드하여 실행하면 설치와 셋팅이 쉽게 완료된다.
- 서버용프로그램들을 별도로 설치하지 않고 도커이미지를 pull 다운로드하여 container 로 실행할 수 있다.
- docker-compose 를 이용하면 도커이미지 여러개를 컨터네이들로 실행하고 그 여러개를 하나의 네트워크로 묶을 수 있다.
- k8s (쿠버네티스) 가 실행되는 작은 프로그램을 실행 할 수 있습니다.
- 도커가 container 로 실행되는데 실행이 끝나면 실행중에 추가,수정,삭제등으로 사용했던 폴더와 파일은 모두 없어집니다.
- 도커에서 한개의 이미지로 한개의 컨테이너를 실행합니다.
  컨테이너 실행이 끝나면 별도의 볼륨을 본인의 호스트에 연결하지 않으면
  컨테이너 실행시 볼륨에 있던 데이터는 다시 시작 하면 사라집니다.

## DB 서버 DB 클라이언트
- DB 서버는 무거운 Oracle 보다는 mysql 8.4.5 LTS 설치
- DB 클라이언트 프로그램을 설치 (Dbeaver zip, mysql work-bench, oracle orange, sql developer, ...)

### docker-compose
- docker 에서 1개씩 실행되는 컨테이너들을 묶어서 종합 프로그램처럼 동시에 실행하는 방법으로 사용된다.
- 원하는 폴더에 docker-compose.yml 파일에 yml 형식대로 작성해야 한다.
```
version: '3.8'	// 도커컴포즈 형식 버전
services: // 도커컴포즈에서 실행할 컨테이너 모음, 서비스 형태로 실행됨
  mysql:	// 컨테이너가 실행될 docker-compose 설정파일에서 사용될 이름
    container_name: "mysql8"	// docker container ls 명령으로 컨테이너 목록
    image: "mysql:8.4.5"	// image 는 도커의 이미지를 이미지명:버전, 버전 생략시 latest 버전
#    restart: "always"	// 도커프로그램이 실행되면 자동으로 실행한다.
    command:	// 도커이미지 실행명령어의 옵션 mysqld --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
      - --character-set-server=utf8mb4
      - --collation-server=utf8mb4_unicode_ci
    ports:	// 도커이미지실행(=컨테이너) 가 실행되는 포트는 호스트포트와 redirect
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: // 루트암호설정
      TZ: Asia/Seoul	// mysql 데이터의 date, datetime, time 형태의 데이터의 시간존 설정
    volumes:	// 호스트폴더: 도커컨테이너실행시 폴더 를 연결한다.
      - ./mysql-data: /var/lib/mysql
```
- 원하는 폴더에서 시작 : docker-compose up -d (데몬)
- 원하는 폴더에서 종료 : docker-compose down
- cd 명령어
```
명령프롬프트> cd [엔터]
명령프롬프트> 현재 경로가 출력된다.
명령프롬프트> cd 절대경로 [엔터]
명령프롬프트(절대경로로 변경)>_
명령프롬프트> cd 상대경로 [엔터]
명령프롬프트(현재디렉토리를기준으로 상대경로로 이동하여 변경)>_
상대경로 :
	. (현재디렉토리)
	.. (상위디렉토리)
	cd abc [엔터] => 현재디렉토리에서 하위의 abc 디렉토리로 변경한다. (cd ./abc)
	cd ../../../Temp => 현재디렉토리에서 상위로 3번 이동하고 Temp 디렉토리로 변경한다.
명령프롬프트(c드라이브상태현재경로)>d:[엔터]
명령프롬프트(d드라이브상태현재경로)>e:[엔터]
명령프롬프트(e드라이브상태현재경로)>
```
- 디비이버에서 데이터베이스생성 -> 테이블생성 (대소문자 구분해서 소문자와 "_") -> 컬럼명 (소문자, _)
- DB의 테이블 생성시 컬럼에는 무조건 id bigint/int unsigned not null auto_increment primary_key 해당 컬럼이 존재해야 한다. (Spring boot)
- DB의 컬럼명 : first_name => getFirstName() setFirstName(""), 2글자 이하로 만들지 마세요.
- 
CREATE TABLE test_tbl (
	id INT UNSIGNED auto_increment NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name varchar(10) NOT NULL,
	CONSTRAINT test_tbl_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;


ALTER TABLE testdb.test_tbl COMMENT='테스트 테이블';
ALTER TABLE testdb.test_tbl MODIFY COLUMN id int unsigned auto_increment NOT NULL COMMENT '고유값 기본키 자동증가';
ALTER TABLE testdb.test_tbl MODIFY COLUMN first_name varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '이름';
ALTER TABLE testdb.test_tbl MODIFY COLUMN last_name varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '성';
- 디비이버에서 연결된 데이터베이스의 SQL편집기를 실행하면 SQL 언어를 편집하고 실행하는 화면
```
SELECT * FROM test_tbl;
# SELECT 출력될컬럼명1, 출력될컬럼명2, 출력될컬럼명3, ... FROM 테이블명;
# SELECT *(FROM의모든컬럼을출력) FROM 테이블명/뷰명/JOIN테이블구문;
# SELECT *.. FROM table WHERE 조건식; 조건식이 참인 행을 출력한다.

INSERT INTO test_tbl(first_name, last_name) VALUES('원철😇', '최');
INSERT INTO test_tbl(last_name) VALUES ('김'); #에러발생
INSERT INTO test_tbl(first_name, last_name) VALUES('😄순신', '이');
#SQL 쿼리의 추가문장
#INSERT INTO abc테이블이름(컬럼명1, 컬럼명2, 컬럼명들,...)
	VALUES(컬럼명1에추가될값1, 컬럼명2에추가될값2, ....);
#컬럼명의 갯수와 값의 갯수가 다르면 에러가 발생한다.
#테이블의 제약 조건(기본키제약, 외래키제약, 컬럼특성제약,..)에 맞아야지만 추가가 정상 동작한다. 그외는 에러발생
#SQL 은 테이블의 행 단위로 추가를 동작하게 된다.

# UPDATE 테이블명 SET 컬럼명1=수정할값, 컬럼명2=수정할값,...
	WHERE 행단위로 검색해서 참을 만드는 조건식;
# WHERE 조건식을 항상 참으로 만들어버리면 큰일난다. 왜냐하면 모든 행의 데이터가 수정됩니다.
UPDATE test_tbl SET last_name = '손' WHERE 1 = 1;

# DELETE FROM 테이블명 WHERE 조건식;
# 테이블명에서 조건식이 참인 행을 찾아서 그 행을 삭제합니다.
DELETE FROM test_tbl WHERE id=3;
# WHERE 조건식을 항상 참으로 만들어버리면 큰일난다. 왜냐하면 모든 행의 데이터가 삭제됩니다.
DELETE FROM test_tbl WHERE 1=1;
```

- 각각의 DB 벤더마다 지원하는 데이터 타입
	정수, 소숫점숫자 : Number(정수자리,소수자리), Decimal(정수자리,소수자리)
	문자의 갯수 : char(고정길이 개수), varchar(가변길이 max개수), nvarchar, varchar2
	날짜 : date, timestamp, datetime, ..
	불린 : bool
	
	DB벤더의 데이터타입 <===> Java 타입
	정수 <===> Integer, Long
	소숫점 <===> BigDecimal
	문자 <===> String
	날짜 <===> LocalDateTime
	불린 <===> Boolean
	? <===> Java 의 타입 변환이 되어야지 사용 가능하다.
- 전화번호부, Animal, Human, 클래스타입의 객체 컬렉션을 Java 의 메모리에만 저장하면 java 가 종료되면 사라집니다.
	파일이나 DB 에 저장해야 합니다.
	파일/DB 는 CRUD 의 개념으로 데이터를 조작합니다.
	C = Create, 파일(파일의 라인을 추가), DB(INSERT 쿼리문장, 행 추가)
	R = Read, 파일(파일의 라인을 읽는다), DB(SELECT 쿼리문장, 행 조회)
	U = Update, 파일 (파일의 라인을 수정), DB(UPDATE 쿼리문장, 행의 컬럼값 수정)
	D = Delete, 파일 (파일의 라인을 삭제), DB(DELETE 쿼리문장, 행을 삭제)
- SQL 의 프로시저와 함수를 이용하여 비지니스로직을 실행하게 되면
	Java 는 SQL 에서 실행할때 매개변수 전달, SQL 에서 실행하고 결과는 Java 가 받을 수 있다.
- SQL에서 프로그램을 하게 되는 효과가 발생한다. Java 가 형상관리나 프로그램을 개발하는 의미가 줄어든다.
- DBA 자격자가 SQL의 특정상황에서만 가끔 사용하는 SQL함수나, SQL프로시져를 개발하여 사용한다.
- 트랜잭션 : 작은단위의 작업들을 그룹으로 묶어서 그룹의 작업 시작을 했으면 끝까지 실행될 수 있도록 처리한다.
	시작을 했지만 중간에 에러가 발생, 중간에서 일이 틀어진경우에는 지금까지 했던것들을 다 취소하고 처음 상태로 돌아간다.
	작업들이 시작을 해서 끝까지 완전히 실행이 완료되든지, 아니면 원상복구 처리 되든지 둘 중 하나로 처리되는 작업단위
	작업1
	작업2
	작업3
	작업4 ==== 작업A (작업1,2,3,4까지 완전히 실행되는 작업단위)
	작업1 실행
	작업2 실행
	작업3 실행
	작업4 실패 ==== 작업A (작업1,2,3,4까지 실행 완료 못했으면 실행 안한것과 같다. 원상복구)
- BEGIN TRANSACTION; 작업시작
	SQL 쿼리 문장으로 CRUD 동작들을 할 수 있다.
		BEGIN TRANSACTION;
		SQL 쿼리 문장으로 CRUD 동작들을 할 수 있다.
		COMMIT; 작업모두 실행 완료 상태			ROLLBACK; 모든 작업 취소, 원상복구
	SQL 쿼리 문장으로 CRUD 동작들을 할 수 있다.
  COMMIT; 작업모두 실행 완료 상태			ROLLBACK; 모든 작업 취소, 원상복구
- SpringBoot
	@Transaction 을 메소드에서 선언한다.
- Connection 풀은 SpringBoot 에서 내장되어 있다.
```
20장 예제들을 직접 개발해 보십시오.
오라클 접속시 암호
system/manager
system/oracle
```
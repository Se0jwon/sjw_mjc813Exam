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
- 클래스형 데이터 타입
	1. 문자열 (String)
	2. 숫자 (BigDecimal)
- 데이터 형 변환
	1. byte 저장공간 길이가 같은 데이터형을 계산 할때는 overflow, underflow 만 조심
	2. byte 저장공간 길이가 다른 데이터형을 계산 하면 길이가 큰 데이터형으로 자동 형변환
	3. 정수, 실수를 계산할때 결과값을 정수만 저장하느냐 실수와 같이 저장하느냐에 따라서 다름.
	4. 암시적 형변환 (자동형변환)은 보통 길이가 작은 데이터가 길이가 큰 데이터로 자동 변환
	5. 명시적 형변환 (데이터타입)(연산) : (int)값, (long)변수, (char), (short)
	6. String => 기본형 데이터로 변환 : Long.parseLong(문자열);  Integer.parseInt(문자열)
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
- 기본타입의 변수에 100이 저장되어 있다면 이것은 100이라는 값입니다.
- 참조타입의 변수에 100이 저장되어 있다면 이것은 주소 100번지라는 것입니다.
  참조타입은 대용량메모리가 필요하므로 힙메모리에 생성한다.
  그리고 길이는 컴파일러가 계산해서 몇바이트 공간인지 알고 있다.
- 스택메모리에 저장되는 것들 : 함수호출시 복귀주소, 함수호출시 매개변수
  , 함수실행시 지역변수, 블록내의 지역변수, 함수실행후 리턴값
- 힙메모리에 저장되는 것들 : 클래스가 new 로 생성되는 객체, 용량이 큰 데이터 들
- 명령어코드가 저장되는 곳 : 메소드영역, 정적변수, 정적필드, 메소드코드들, 생성자코드들, 정적리터럴(상수)
- 참조타입 변수의 비교연산은 주소가 비교된다. 값을 비교할려면 메소드를 사용한다.
- null 은 오로지 참조타입 변수에서만 사용가능하다. null 일경우에는 NullExecption 이 발생하므로
  변수에 null 이 아닌 값으로 초기화 하는게 좋다. null 체크를 잘해야 합니다.
  public 메소드의 매개변수를 null 체크를 하세요.
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
  기본타입 데이터는 값을 복사합니다.	: 깊은복사
  참조타입 데이터는 주소를 복사합니다. : 얕은복사,
  참조타입데이터를 깊은복사를 하려면 참조타입데이터의 원소,값,멤버변수를 모두 복사해야 한다. Clone
# RDBMS 강의

## 용어
- 데이터베이스 : 데이터가 저장되는 가장 큰 모임, 접속/관리되는 주체, Table들의모임(3차원, 육면체), View들의모임, Procedure, Function, Trigger, Index, 들의 모임, 액셀의 sheet의 모임
- 스키마 : 오라클의 접속계정이름으로 관리되는 주체, 데이터베이스라고도 볼 수 있다.
- 테이블 : 2차원적인 데이터를 저장할 수 있는 구조, 면, 1차원데이터구조인 행들의 모임, 액셀의 sheet
- ROW 행 : 1차원적인 데이터를 저장할 수 있는 구조, 선, 컬럼들의 모임, 레코드, Row, 튜플, 액셀의 행
- 컬럼 : 제일작은 데이터 저장 단위, 데이터타입이 같아야 한다. 액셀의 열
- 키 : 데이터를 대표하는 값(열쇠), PK, FK, UK

## oracle 에서 사용자(스키마) 만들기
- 관리자권한으로 접속하세요.
- 관리자권한에서 스크립트 실행해야 한다.
- CREATE USER 사용자명(스키마명) IDENTIFIED BY "암호"; => 사용자계정(스키마) 만들기
- GRANT CONNECT, RESOURCE, DBA TO 사용자명(스키마명); => 접속권한, 자원권한, 슈퍼쿼리문권한
- COMMIT; 확정한다.
- DB 클라이언트 툴(dbeaver)에서 만든 사용자(스키마)에 접속할 수 있는 접속객체를 생성하세요
- 생성된 접속객체로 접속을 하세요.

## DDL 언어
- Data Definition language : 데이터 구조를 정의하는 명령
- CREATE TABLE <테이블명> ( 생성할 컬럼들 명세 ); : 테이블 생성
	생성할 컬럼들 명세 : 컬럼명 데이터형 제약(길이수, not null, DB벤더에 따라 다른 제약옵션)
- DROP TABLE <테이블명>; :
- TRUNCATE TABLE <테이블명>; : 테이블의 모든 행을 삭제한다. 되돌릴 수 없다.
- ALTER TABLE <테이블> 수정할 컬럼 명세;

## DML 언어 (CRUD)
- Data Manipulation Language : 데이터를 조작하는 명령
- C (create) : INSERT 쿼리문장
	- 첫번재(주로사용하세요)
	INSERT INTO <테이블명>
		(값이 추가될 컬럼을 서술 컬럼명1, 컬럼명2, ...)
	VALUE
		(서술되어있는 순서대로 컬럼명1값, 컬럼명2값, ...);
	- 두번째(테이블의 컬럼순서가 바뀌거나 추가되면 오류)
	INSERT INTO <테이블명>
	VALUE
		(컬럼의 만들어져있는 순서대로 컬럼명1값, 컬럼명2값, 모든나머지컬럼명n값);
	- 세번째(백업용, 임시테이블, 복사용)
	INSERT INTO <테이블명>
	SELECT 절; -> SELECT 절의 데이터 결과를 INSERT 시킨다. (인덱스,PK,FK 정보 없음)
- R (read) : SELECT 쿼리문장 (가장 난위도가 높다)
	- 첫번째 (모든컬럼출력하는 용도, 단순 테스트용도)
	SELECT * FROM <테이블명>;
	- 두번째 (출력할 컬럼을 서술한다.)
	SELECT
		값이 출력될 컬럼을 서술 컬럼명1, 컬럼명2, ...
	FROM <테이블명> AS 별명;
	- 세번째 (WHERE 절, ORDER BY 절, GROUP BY 절, HAVING 절, SubQuery 절)
- U (update) : UPDATE 쿼리문장
	UPDATE <테이블명> SET
		컬럼명1 = 컬럼명1의 수정할값
		, 컬럼명2 = 컬럼명2의 수정할값
		, 컬럼명3 = 컬럼명3의 수정할값
		, 컬럼명4 = 컬럼명4의 수정할값
	WHERE <조건식>;
	참이 되는 행을 찾아서 그 행의 컬럼의 값들을 수정한다.
	WHERE 절의 조건식은 항상 참이 되면 안된다. 이러면 모든 행이 수정된다.
	WHERE 절이 없으면 안된다. 이러면 모든 행이 수정된다.
	PRIMARY KEY 컬럼의 값으로 찾아서 1행의 컬럼 값을 수정한다.
	PRIMARY KEY 컬럼을 찾을때 IN 단어로 OR 처리를 한다. WHERE id IN (1, 4, 5, 70)
- D (delete) : DELETE 쿼리문장
	DELETE FROM <테이블명>
	WHERE <조건식>;
	조건식 참이 되는 행을 찾아서 그 행을 삭제한다.
	조건식은 항상 참이 되면 안된다. 이러면 모든 행이 삭제 된다.
	WHERE 절이 없으면 안된다. 이러면 모든 행이 삭제 된다.
	PRIMARY KEY 컬럼의 값으로 찾아서 1행의 컬럼 값을 삭제 한다.
	PRIMARY KEY 컬럼을 찾을때 IN 단어로 OR 처리를 한다. WHERE id IN (4, 6, 51, 76)
## TRANSACTION
- 작업들을 묶어서 그 작업들이 전체 실행 성공 하든지, 하나라도 실패하면 모두 취소하든지 결정한다.
- 쿼리문장들을 묶어서 그 전체 쿼리문장들이 실행 성공 하든지, 하나라도 실패하면 모두 취소하든지 결정한다.
- BEGIN TRANSACTION; BEGIN TRAN; BEGIN;
	트랜잭션 시작 명령어, try 시작할때 실행하면 좋다.
- COMMIT;
	트랜잭션 전체 성공 처리 명령어, try 마지막문장에 실행하면 좋다.
- ROLLBACK;
	트랙잭션 실행 취소 명령어, cath 부분에서 처리하면 좋다.

## 이상현상
- 데이터 정합성(오류가 없이 보관되어 있음)이 너무 형편없다.
- 데이터 추가할때 이상한 현상 : 삽입이상, 데이터를 행단위로 추가할때 다른필드(컬럼)에 null 이거나 없는경우
	번호, 구매날짜, 소비자, 구매물품, 가격, 상점이름, 상점관리자
	1, 2025-07-08, 홍길동, 우유, 2000, 명지편의점, 최원철
	2, 2025-07-08, 이순신, 빵, 1500, 골목편의점, 김호중
	NULL, NULL, NULL, NULL, NULL, 삼거리편의점, NULL
- 데이터 수정할때 이상한 현상 : 수정이상, 행단위로 특정필드(컬럼) 수정할때 다른 데이터와 정합성이 맞지 않는다.
	번호, 구매날짜, 소비자, 구매물품, 가격, 상점이름, 상점관리자
	1, 2025-07-08, 홍길동, 우유, 2000, 명지편의점, 최원철
	2, 2025-07-08, 이순신, 빵, 1500, 골목편의점, 김호중
	NULL, NULL, NULL, NULL, NULL, 삼거리편의점, NULL
	3, 2025-07-09, 홍길동, 초코파이, 3000, 명지대편의점, 이승민
- 데이터 삭제할때 이상한 현상 : 삭제이상, 행단위로 삭제할때 다른 데이터와 정합성이 맞지 않는다.
	번호, 구매날짜, 소비자, 구매물품, 가격, 상점이름, 상점관리자
	1, 2025-07-08, NULL, 우유, 2000, 명지편의점, 최원철
	2, 2025-07-08, 이순신, 빵, 1500, 골목편의점, 김호중
	NULL, NULL, NULL, NULL, NULL, 삼거리편의점, NULL
	3, 2025-07-09, 홍길동, 초코파이, 3000, 명지대편의점, 이승민
## 정규화
- 장점
	- 데이터 정합성이 좋다.
	- 데이터 중복이 거의 없어진다.
	- 저장공간을 절약할 수 있다.
- 단점
	- SELECT 조회할때 여러 테이블을 JOIN 으로 조합해서 조회하므로 속도가 떨어질 수 있다.
		해결법: JOIN 을 PK, FK, INDEX 컬럼을 사용해야 한다.
- 제1정규화
	ATOM 에 데이터가 여러개 있을 수 없다.
	하나의 컬럼에 데이터를 여러개 있는경우에 이것을 제1정규화를 시켜야 한다.
	예) 번호, 구매날짜, 소비자, 구매물품, 가격, 상점이름, 상점관리자
	1, 2025-07-08, NULL, 우유, 2000, 명지편의점, 최원철
	2, 2025-07-08, 이순신, 빵, 1500, 골목편의점, 김호중
	NULL, NULL, NULL, NULL, NULL, 삼거리편의점, NULL
	3, 2025-07-09, 홍길동, 초코파이, 3000, 명지대편의점, 이승민
	4, 2025-07-09, 이순신, 딸기우유|바나나우유|김밥, 2000|2500|5000, 명지편의점, 최원철
=>	
	4, 2025-07-09, 이순신, 딸기우유, 2000, 명지편의점, 최원철
	5, 2025-07-09, 이순신, 바나나우유, 2500, 명지편의점, 최원철
	245, 2025-07-09, 이순신, 김밥, 5000, 명지편의점, 최원철
- 제2정규화
	키의 부분값이 데이터를 종속시키는 상황을 해결해야 한다.
	키:245|2025-07-09 => 이순신, 김밥, 5000, 명지편의점, 최원철
		245 => 2025-07-09, 이순신, 김밥, 5000, 명지편의점, 최원철
		코드1, 코드2, 이름,  값, 부가정보
		"abcd" => "가나다라", 8372, "ㄴㅇㄴㅁㅁ"
		"ab" => "가나다라", 8372, "ㄴㅇㄴㅁㅁ"
		"cd" => "가나다라", 8372, "ㄴㅇㄴㅁㅁ"
	컬럼이 여러개로 키가 만들어진 경우에는 한개의 컬럼이 키가 되도록 제2정규화
		코드2, 이름, 값, 부가정보
		"cd" => "가나다라", 8372, "ㄴㅇㄴㅁㅁ"
- 제3정규화
	이행종속은 A -> B, B -> C : A -> C
	이행종속인 상황을 해결해야한다.
	홍길동 -> GOLD등급 -> 할인율20%
	이순신 -> VIP등급 -> 할인율30%
	홍미애 -> BRONZE등급 -> 할인율10%
	김유신 -> VIP등급 -> 할인율30%
	신사임당 -> GOLD등급 -> 할인율20%
	X -> Y, Y -> Z
	
	홍길동 -> 할인율20%
	이순신 -> 할인율30%
	X -> Z
	
	Y를 없애지 않고 사용할려면 별도의 테이블로 제3정규화 시켜야 한다.
	고객등급 테이블
	홍길동 -> GOLD등급
	이순신 -> VIP등급
	홍미애 -> BRONZE등급
	김유신 -> VIP등급
	신사임당 -> GOLD등급
	
	등급할인율 테이블
	GOLD등급 -> 할인율20%
	VIP등급 -> 할인율30%
	BRONZE등급 -> 할인율10%
- BCNF, 제3.5 정규화
- 제4정규화
- 제5정규화
- 결론적으로 테이블을 설계할때 항상 숫자값이 있는 컬럼 1개를 primary key 로 지정해야 한다.
- SpringBoot, Python Django, Php Laravel 웹서버 프레임워크들은
	id 라는 숫자값 컬럼을 primary key 로 자동 생성하는 방법을 지원한다.
	다른테이블에서 해당테이블의 id 컬럼을 외래키로 지정해서 참조 관계 해야 한다.
- RDBMS (관계형 데이터베이스 관리 시스템)
- 주테이블(마스터 테이블)
	CREATE TABLE grade_tbl (   
		id INT UNSIGNED NOT NULL auto_increment
		, name VARCHAR(20) NOT NULL
		, discount_rate INT UNSIGNED NOT NULL
		, CONSTRAINT grade_tbl_pk PRIMARY KEY (id)
	);
	id,	name,	discount_rate
	1,	A등급,	10
	2,	GOLD급,	15
	3,	VIP급,	20
	
	외래/업무테이블
	CREATE TABLE user_tbl (
		id INT UNSIGNED NOT NULL auto_increment
		, first_name VARCHAR(20) NOT NULL
		, last_name VARCHAR(20) NOT NULL
		, grade_id INT UNSIGNED NOT NULL,
		, CONSTRAINT user_tbl_pk PRIMARY KEY (id)
		, CONSTRAINT user_tbl_fk FOREIGN KEY (grade_id) REFERENCES grade_tbl (id)
	);
	id,	first_name, last_name, grade_id
	1,	길동,		홍,			2
	SELECT
		ut.first_name
		, ut.last_name
		, gt.name
		, gt.discount_rate
	FROM
		user_tbl AS ut
	INNER JOIN grade_tbl AS gt ON
		ut.grade_id = gt.id;
		
- 마스터키의 값이 수정하거나 삭제될때 외래테이블의 값도 수정되던지 삭제되던지 금지하든지 설정
ON DELETE RESTRICT(제일많이사용) : 마스터키가 삭제될때 외래키에 값이 사용되어있다면 마스터키 삭제금지
ON DELETE CASECADE(무서운옵션) : 마스터키가 삭제될때 외래키에 값이 사용되어있다면 마스터키 삭제되고, 외래키의 행도 일괄적으로 삭제된다.
ON DELETE SET DEFAULT : 마스터키가 삭제될때 외래키에 값이 사용되어있다면 마스터키 삭제되고, 외래키의 값은 DEFAULT 값
ON DELETE SET NULL : 마스터키가 삭제될때 외래키에 값이 사용되어있다면 마스터키 삭제되고, 외래키의 값은 NULL

ON UPDATE RESTRICT : 마스터키가 수정될때 외래키에 값이 사용되어있다면 마스터키 수정금지
ON UPDATE CASECADE(제일많이사용) : 마스터키가 수정될때 외래키에 값이 사용되어있다면 마스터키 수정되고, 외래키 값도 일괄적으로 수정된다.
```
7월2일 문제
SQL 쿼리 문장으로
contact_tbl 테이블에는 아래와 같이 있다.
CREATE TABLE contact_tbl (
	id INT UNSIGNED auto_increment NOT NULL,
	name varchar(20) NULL,
	phoneNumber varchar(20) NULL,
	zipNumber varchar(5) NULL,
	email varchar(100) NULL,
	CONSTRAINT contact_pk PRIMARY KEY (id)
);
이 Contact 정보는 고객용도나 학생데이터 용도로도 사용가능하다
1. 고객용도로 사용할경우에 판매된 정보를 저장해야 한다. 판매정보는 아래와 같다
판매월일, 판매금액, 판매개수, 판매물품, 배송장소, 할인금액, 판매자, 판매자주소가 저장되도록 구성해 보세요.
새로운 테이블을 생성하거나 contact_tbl 의 컬럼을 수정해도 무방하지만 될수 있으면 정규화를 시켜서 데이터 중복을 피하고 정합성을 유지하세요.

2. 학생데이터 용도로 사용할 경우에는 수강정보를 저장해야 합니다. 수강정보는 아래와 같다.
수강과목, 수강교수, 강의실번호, 수강시작일, 수강종료일, 점수(숫자로 0~100사이), 수강학생 가 저장되도록 구성해보세요
새로운 테이블을 생성하거나 contact_tbl 의 컬럼을 수정해도 무방하지만 될수 있으면 정규화를 시켜서 데이터 중복을 피하고 정합성을 유지하세요.
```

## mysql 에서 사용자 추가하고 권한을 할당하여 해당 database 에만 접속
CREATE DATABASE <만들데이터베이스이름> CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE USER '<만들사용자계정명>'@'%' IDENTIFIED BY '<만들사용자암호>';
GRANT ALL PRIVILEGES ON <데이터베이스이름>.* TO '<사용자계정명>'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

## SELECT 쿼리
- UNION 합치는 경우 : 데이터 행의 갯수로 추가 됩니다. 컬럼의 갯수가 동일해야 한다.
	SELECT 문장 2개 이상 합쳐지는 경우, 컬럼의 데이터형식을 상관하지 않더라
		SELECT id, b, c FROM table1
		UNION
		SELECT t1_id, b, c FROM table2;
- JOIN 합치는 경우 : 컬럼의 갯수로 추가 될 수 있습니다.
	SELECT 문장은 1개 이지만 FROM ~ JOIN 절에서 테이블의 갯수가 N개 될 수 있습니다.
		SELECT id, table1.b, table1.c, t1_id, t2.b, t2.c
		FROM table1 INNER JOIN table2 AS t2 ON table1.id = t2.t1_id
		;

## JOIN
- 마스터테이블의 기본키와 업무테이블의 외래키의 값이 같은 값으로 조인하는게 제일 좋습니다.
- INNER JOIN : A 테이블와 B 테이블의 교집합을 만들어 냅니다.
- LEFT OUTER JOIN : JOIN 단어 기준으로 왼쪽 테이블의 모든 행은 출력되며 오른쪽테이블은 ON 조건에 맞는 행만 표시 된다.
	왼쪽 테이블의 행 중에서 조건에 맞지 않는 행은 오른쪽 테이블의 출력할 값이 없으므로 NULL 이 출력 됩니다.
- RIGHT OUTER JOIN : JOIN 단어 기준으로 오른쪽 테이블의 모든 행은 출력되며 왼쪽 테이블은 ON 조건에 맞는 행만 표시 된다.
	오른쪽 테이블의 행 중에서 조건에 맞지 않는 행은 왼쪽 테이블의 출력할 값이 없으므로 NULL 이 출력 됩니다.
- FULL OUTER JOIN : A 테이블 행수 * B 테이블 행수 (곱한 결과)
- select a, b, c, d, e, ...
	from businessTable as bt
	inner join masterTable as mt1 on bt.외래키컬럼 = mt1.기본키컬럼
	left join masterTable2 as mt2 on bt.외래키컬럼 = mt2.기본키커럼

## SELECT 문장에서 조건과 정렬과 DISTINCT
- SELECT 컬럼1, 컬럼2, *(모든컬럼) FROM (조회할 데이터들) WHERE 조회조건
	조회조건은 컬럼이름 과 비교연산들을 이용하여 참이면 해당 행에 대한 컬럼 값들을 출력합니다.
- WHERE 절은 조회조건만 서술해야한다. 그런데 Oracle에서는 WHERE 에서 JOIN 조건과 같이 사용합니다.
	그러나 Oracle 은 WHERE 절에는 JOIN 조건과 조회 조건이 공존하고 있다.
- ORDER BY <컬럼1 [ASC/DESC]>, 컬럼2, 절은 행들의 출력할때 1행 2행 우선적으로 출력되는 정렬 절입니다.
	ORDER BY 는 SELECT 쿼리문장의 마지막에 서술한다.
	컬럼 ASC : (ascending)컬럼 값으로 행의 출력 오름차순 시킨다. 작은값 -> 큰값순으로 정렬, 생략단어
	컬럼 DESC : (descending)컬럼 값으로 행의 출력 내림차순 시킨다.
	예를들어 ORDER BY price, qty DESC, amount; price 값이 오름차순, qty 값이 내림차순
	price, qty, amount
	1,	2,	10
	2,  3,  600
	2,  1,  200
	2,  1,  600
- DISTINCT : SELECT 컬럼1, 컬럼2, 컬럼3, .. FROM (조회할데이터들) W.. O..;
	1, 2, 3
	1, 2, 4
	1, 2, 5 => 행의 값이 중복 출력된다
	1, 2, 5	=> 행의 값이 중복 출력된다
	1, 2, 5	=> 행의 값이 중복 출력된다
	1, 2, 6
	SELECT DISTINCT ALL 컬럼1, 컬럼2, 컬럼3, .. FROM (조회할데이터들) WH... O..;
	1, 2, 3
	1, 2, 4
	1, 2, 5	=> 행의 값이 중복 출력되지 않는다
	1, 2, 6
```
7월3일 오후 문제
mysql 에서 사용자 추가하고 권한을 할당하는 부분을 보고 database 를 하나 만들고, 사용자계정을 만들고 권한을 주세요
새로 만든 사용자계정으로 새로운 database 에 dbeaver 클라이언트 툴을 이용하여 접속합니다.
mjc_db명세서.xlsx 파일의 최종명세 Sheet 에 있는대로 SQL DDL 언어로 만들어 보세요.
dbeaver 에서 테이블 개체관계도 (ERD) 를 보고 테이블 들의 관계도를 이해하세요.
데이터들을 수동으로 insert 하여서 데이터를 추가 하세요.
수동으로 추가된 데이터들을 select 쿼리 문장을 활용하여 조회 하는 쿼리를 만들어 보세요
첫번째는 판매정보를 출력 하는 SELECT 쿼리이며, 두번째는 수강정보를 출력하는 SELECT 쿼리 입니다.
지금까지 배웠던 INNER JOIN 을 활용하면 될것이며, 그외의 JOIN 도 실습 해 보세요.
```

-7월4일 강의
- 위의 문제를 해결해서 init_mysql_data.sql 로 저장하는 과정
- dbeaver 에서 복사붙여넣기로 데이터를 추가, 수정
- dbeaver 에서 dump 메뉴로 데이터베이스와 특정테이블의 DDL 과 insert 문장으로 데이터를 백업할 수 있다.
- 외래키를 만들고나서
	- 외래키 값 데이터를 추가할 경우에는 마스터테이블에 존재하는 id 값이 저장되어야 합니다.
	- 외래키 값 데이터를 수정할 경우에도 마스터테이블에 존재하는 id 값으로 변경 가능 합니다.
	- 외래키 값 데이터를 삭제할 경우에는 마스터테이블과 무관하게 삭제 가능하다.
	- 마스터테이블의 기본키 값을 추가할 경우 외래키테이블과 무관하게 추가 가능하다.
	- 마스터테이블의 기본키 값을 수정할 경우 RESTRICT 모드 외래키에 값이 이미 사용되어 있다면 수정 불가
	- 마스터테이블의 기본키 값을 수정할 경우 CASCADE 모드 외래키에 값이 이미 사용되어 있다면 그 값을 수정한다.
	- 마스터테이블의 기본키 값을 삭제할 경우 RESTRICT 모드 외래키에 값이 이미 사용되어 있다면 삭제 불가
	- 마스터테이블의 기본키 값을 삭제할 경우 CASCADE 모드 외래키에 값이 이미 사용되어 있다면 그 데이터 행을 삭제한다.
	- 마스터테이블의 기본키 값을 삭제할 경우 Set Default 모드 외래키에 값이 이미 사용되어 있다면 기본값으로 변경
	- 마스터테이블의 기본키 값을 삭제할 경우 Set Null 모드 외래키에 값이 이미 사용되어 있다면 Null 값으로 변경
- JOIN 부가 설명
	마스터테이블의 기본키와 참조테이블(외래키테이블)의 외래키를 같은 값을 JOIN 을 하는게 중요하다.
	왼쪽데이터 INNER JOIN 오른쪽데이터 ON 마스터테이블의 기본키 = 외래테이블의 외래키
		=> ON 의 조건이 참인 데이터만 출력
	왼쪽데이터 LEFT OUTER JOIN 오른쪽데이터 ON 마스터테이블의 기본키 = 외래테이블의 외래키
		=> 왼쪽데이터는 무조건 출력 + 오른쪽데이터는 ON 의 조건이 참인 데이터만 출력
	왼쪽데이터 RIGHT OUTER JOIN 오른쪽데이터 ON 마스터테이블의 기본키 = 외래테이블의 외래키
		=> 오른쪽데이터는 무조건 출력 + 왼쪽데이터는 ON 의 조건이 참인 데이터만 출력
	왼쪽데이터 FULL OUTER JOIN 오른쪽데이터 ON 마스터테이블의 기본키 = 외래테이블의 외래키
		=> 왼쪽데이터는 무조건 출력 X 오른쪽데이터는 무조건 출력 (행의수는 곱한수)
- UNION 은 출력할 컬럼의 갯수가 같은 데이터테이블 2개 이상을 데이터행에 이어서 출력한다.
	SELECT a, b, c (출력할 컬럼의 갯수가 동일해야 한다.)
	FROM (데이터1)
	UNION ALL
	SELECT r, a, b (출력할 컬럼의 갯수가 동일해야 한다.)
	FROM (데이터2)
	UNION ALL
	SELECT e, f, j (출력할 컬럼의 갯수가 동일해야 한다.)
	FROM (데이터3)
- 마스터 테이블이란
	기본키가 있고 해당 데이터 다른 테이블의 참조값으로 사용된다.
	마스터테이블의 Primary Key (고유한 값, 중복값X), 정수형이 값이 다른 테이블의 참조형태로 값만 저장되어 있다.
	마스터테이블 (기본정보)
	Key	 정보	맛의등급	원산지
	1	간장		보통		강원도
	2	고추장	강함		경상남도
	3	된장		보통		제주도

	업무테이블(외래테이블,참조테이블) (기본정보를 활용합니다.)
	Key 요리재료1	요리재료2	...
	1	1		배추
	2	1		무우
	3	2		부추

	select 정보,맛의등급,원산지 FROM 마스터테이블;
	select 요리재료1,요리재료2 FROM 업무테이블;
	CRUD, Insert, Select, Update, Delete
- 집합연산, 집합데이터 : 개별(데이터 여러행) 데이터형에 있는 값을 집합연산(합계, 평균, 최고값, 최저값,...)을 처리하여 한개의 행 출력
- SELECT 컬럼1, 컬럼2, ...
	FROM (조회할데이터) [JOIN 마스터테이블 ON PKEY = FKEY]
	WHERE 조회할데이터를 검색하는 조건절이 참인 행만 조회된다. PKEY를 사용하면 1개의 행만 조회된다.
	GROUP BY (컬럼1, 컬럼2, 컬럼3, ...) => 컬럼1의 값이 같은 데이터행을 1개의 행으로 집계할수있다.
	HAVING (집합한 데이터에 대해서 조건절이 참인 행만 출력한다. 집합데이터에 대한 WHERE 조건과 같은의미)
	ORDER BY 컬럼기준정렬(오름차순 ASC/내림차순 DESC)
- GROUP BY 에 등장할 수 있는 컬럼은 집합연산(합계, 평균, 최고값, 최저값,...)을 할때 기준이 되는 컬럼
	GROUP BY 컬럼의 값이 같은 행들을 묶어서 집합시킬수 있다.
	SELECT 컬럼들은 집합연산으로 출력 시켜야 한다. 집합연산함수를 사용한다.
	SELECT 일반컬럼(집합이되는컬럼), SUM(price), 집합연산(qty) FROM (조회할데이터)
	WHERE ...
	GROUP BY price, qty
	HAVING 집합연산결과로 조건을 설정
	ORDER BY 컬럼1,
- VIEW 뷰 : 일종의 SELECT 문장을 뷰라는 것으로 대체시키는 효과가 있다.
	SELECT a, b, c, d,...
	FROM (t, join s ....)
	WHERE ...
	GROUP BY
	
	CREATE VIEW [OR REPLACE] <뷰이름>
	AS
		SELECT 쿼리 문장
		FROM
		WHERE
		GROUP BY
		HAVING
	;
	
	select * from <뷰이름>;

```
7월4일 오후 문제
7월3일의 문제
조건 : score 점수를 임의로 저장하세요, 더 많은 insert 문장으로 contact, lecture, location, learn, saled 데이터를 추가하세요
첫번째는 판매정보를 출력 하는 SELECT 쿼리문장과 두번째는 수강정보를 출력하는 SELECT 쿼리문장을
본인만의 뷰이름을 이용하여 생성해 보세요.

JOIN, GROUP BY, VIEW 등을 활용할 수 있습니다.
수강정보에 대한 쿼리
수강학생별로 수강중인 과목의 가장빠른 시작일자시간과 가장늦은 끝일자시간을 출력하세요
각과목별로 수강하는 학생 인원수를 출력하세요.
각과목별로 최우수점수 학생 이름과 score 가 맨위로 정렬되도록 출력하세요.
각과목별로 최하위점수 학생 이름과 score 가 맨위로 정렬되도록 출력하세요.

판매정보에 대한 쿼리
판매정보마다 판매금액은 (price * qty - discount) 이와 같은 수식이다.
각고객별로 판매총금액을 내림차순으로 출력하세요.
각상품별로 판매총금액을 내림차순으로 출력하세요.
```

## 7월7일 강의
- view 를 사용하여 group by 를 편하게 할 수 있다.
- 같은 과목에 동일 학생이 수강하는것은 실생활에서도 존재하지 않으므로 수강테이블(learn_tbl)의
	과목_id, 학생_id 2개의 컬럼에 Uniqe 키 제약을 추가했다.
- 테이블의 primary key 는 한개의 컬럼에 숫자 값으로 중복되지 않는 유일한 값을 사용해야 한다.
	Spring Boot, Django, Laravel, 최신 프레임워크들은 이렇게 DB 테이블을 구성한다.
	id 라는 이름의 컬럼으로 자동증가 값을 만든다.
	mysql 는 auto_increment 예약어를 사용한다.
	oracle 은 create sequence <시퀀스명> 라는 명령으로 사용한다.
- PRIMARY KEY : 마스터 테이블의 어떤 행의 값을 대표하는 키값, 보통은 자동증가 숫자
	1개의 숫자데이터 컬럼으로 구성해야 한다. 절대로 중복하면 안됨, 자동으로 INDEX 생성해줌
- FOREIGN KEY : 업무테이블에서 사용하는 마스터정보의 PK 값을 참조로 가지고 있다.
	PK값을 사용하는 입장이므로 중복발생O, 자동으로 INDEX 생성해줌
- UNIQUE KEY : 1개이상의 컬럼의 값을 중복되지 않도록 만드는 Key 입니다. 자동으로 INDEX 생성해줌
	예를들어 아래와같은 테이블,컬럼,데이터행이 있을경우에
	컬럼A, 	컬럼B, 컬럼C
	1,		2,		ab
	2,		2,		cc
	3,		2,		DDL
	2,		2,		aa
	UNIQUE KEY (컬럼A) : 컬럼A 에는 중복된 값을 허용 안한다. (2 번 값 중복)
	UNIQUE KEY (컬럼A, 컬럼B) : 컬럼A와 컬럼B를 연결한값이 중복된 값을 허용 안한다.
		(2, 2 값이 중복)
	UNIQUE KEY (컬럼A, 컬럼C) : 컬럼A와 컬럼C를 연결한값이 중복된 값을 허용 안한다.
		(중복된 값이 없으므로 UNIQUE key 생성 가능)
- INDEX : 테이블의 어떤 컬럼 1개 이상에 Index 를 생성할 수 있다.
	장점 : 검색 속도가 매우 빠르다 (이진 검색 트리 index 생성) BTREE
	단점 : 데이터를 추가,삭제 하면 index 재조정을 해야 하므로 속도가 느리다.
	활용법 : 아래와 같은 문장의 등장하는 컬럼들은 index 를 생성하면 속도가 빨라진다.
		ON 마스터.id = 외래.참조_id
		WHERE 컬럼A 비교연산 값 AND 컬럼B 비교연산
		GROUP BY 컬럼, ...
		HAVING 조건절 컬럼
		ORDER BY 컬럼, ..
	문법 : CREATE INDEX <인덱스명> 컬럼들, ...;
- 관계형데이터베이스 : PK(마스터테이블)와 FK(외래/업무테이블) 구조로 데이터를 표현한다.
```
7월4일 오후문제를 강사 방법으로 풀은 해결은 contact_web\docs\select_query.sql

7월7일 오후 문제
1. w3schools 홈페이지 아래 주소를 찾아서 각자 공부하세요
	https://www.w3schools.com/sql/sql_insert_into_select.asp
	https://www.w3schools.com/sql/sql_case.asp
	https://www.w3schools.com/sql/sql_isnull.asp
	https://www.w3schools.com/mysql/mysql_limit.asp
2. programmers 의 sql 문제를 공부하세요.
	https://school.programmers.co.kr/learn/courses/30/lessons/59406
	https://school.programmers.co.kr/learn/courses/30/lessons/59036
	https://school.programmers.co.kr/learn/courses/30/lessons/59038
	https://school.programmers.co.kr/learn/courses/30/lessons/59039
	https://school.programmers.co.kr/learn/courses/30/lessons/59041
	https://school.programmers.co.kr/learn/courses/30/lessons/59047
3. 구구단 1단 부터 9단까지 출력 하도록 테이블을 설계하고 데이터를 추가하는 등
	데이터베이스와 테이블을 사용해서 sql 쿼리 문장으로 구구단의 1단 부터 9단까지 출력 해보세요.
4. contact_web\docs\damedata.xlsx 파일의 데이터열거를 보고
	팀원 또는 조원의 협의로 정규화를 하여 테이블 설계를 해보고 데이터 시뮬을 해보세요.
```
## 7월8일 강의
```
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: mjc813_db
-- ------------------------------------------------------
-- Server version	8.4.5

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `train_tbl`
--

DROP TABLE IF EXISTS `train_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `train_tbl` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '기본키 자동증가',
  `name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '기차이름',
  `qty` int NOT NULL COMMENT '기차의 차량수',
  `seat_count` int NOT NULL COMMENT '기차의 의자수',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train_tbl`
--

LOCK TABLES `train_tbl` WRITE;
/*!40000 ALTER TABLE `train_tbl` DISABLE KEYS */;
INSERT INTO `train_tbl` VALUES (1,'무궁화1호',12,1180);
INSERT INTO `train_tbl` VALUES (2,'무궁화2호',13,1290);
INSERT INTO `train_tbl` VALUES (3,'새마을1호',11,1010);
INSERT INTO `train_tbl` VALUES (4,'KTX1호',10,990);
INSERT INTO `train_tbl` VALUES (5,'KTX2호',11,1050);
INSERT INTO `train_tbl` VALUES (6,'새마을2호',12,1150);
INSERT INTO `train_tbl` VALUES (7,'ITX1호',6,590);
INSERT INTO `train_tbl` VALUES (8,'ITX2호',7,640);
INSERT INTO `train_tbl` VALUES (9,'비둘기1호',14,1330);
INSERT INTO `train_tbl` VALUES (10,'비둘기2호',13,1260);
/*!40000 ALTER TABLE `train_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train_way_tbl`
--

DROP TABLE IF EXISTS `train_way_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `train_way_tbl` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '기본키 자동증가',
  `name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '노선이름',
  `train_id` int unsigned DEFAULT NULL,
  `start_station_id` int unsigned DEFAULT NULL,
  `end_station_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `train_way_fk_train` (`train_id`),
  KEY `train_way_fk_start` (`start_station_id`),
  KEY `train_way_fk_end` (`end_station_id`),
  CONSTRAINT `train_way_fk_end` FOREIGN KEY (`end_station_id`) REFERENCES `station_tbl` (`id`),
  CONSTRAINT `train_way_fk_start` FOREIGN KEY (`start_station_id`) REFERENCES `station_tbl` (`id`),
  CONSTRAINT `train_way_fk_train` FOREIGN KEY (`train_id`) REFERENCES `train_tbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train_way_tbl`
--

LOCK TABLES `train_way_tbl` WRITE;
/*!40000 ALTER TABLE `train_way_tbl` DISABLE KEYS */;
INSERT INTO `train_way_tbl` VALUES (1,'여름여행길',9,1,2);
INSERT INTO `train_way_tbl` VALUES (2,'바다여행길',10,9,3);
INSERT INTO `train_way_tbl` VALUES (3,'출퇴근급행',4,6,1);
INSERT INTO `train_way_tbl` VALUES (4,'출퇴근서행',3,5,1);
/*!40000 ALTER TABLE `train_way_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station_tbl`
--

DROP TABLE IF EXISTS `station_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station_tbl` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '기본키 자동증가',
  `name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '역이름',
  `lat` decimal(12,8) DEFAULT NULL COMMENT '위도',
  `lng` decimal(12,8) DEFAULT NULL COMMENT '경도',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station_tbl`
--

LOCK TABLES `station_tbl` WRITE;
/*!40000 ALTER TABLE `station_tbl` DISABLE KEYS */;
INSERT INTO `station_tbl` VALUES (1,'서울',38.66222000,127.54656000);
INSERT INTO `station_tbl` VALUES (2,'춘천',38.43455000,127.33444000);
INSERT INTO `station_tbl` VALUES (3,'부산',38.65454000,127.12321000);
INSERT INTO `station_tbl` VALUES (4,'광주',38.76554000,127.75444000);
INSERT INTO `station_tbl` VALUES (5,'대전',38.33331000,127.12322000);
INSERT INTO `station_tbl` VALUES (6,'인천',38.74444000,127.32432000);
INSERT INTO `station_tbl` VALUES (7,'대구',38.54643000,127.34555000);
INSERT INTO `station_tbl` VALUES (8,'목포',38.24322000,127.34433000);
INSERT INTO `station_tbl` VALUES (9,'강릉',38.75443000,127.45442000);
/*!40000 ALTER TABLE `station_tbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-08 12:42:34
```
- 위의 dump 쿼리를 본인의 데이터베이스서 실행하고 아래의 문제를 해결해본다.
```
select
	*
from train_way_tbl as twt
inner join train_tbl as tt on twt.train_id = tt.id
;


select
	twt.name
	, tt.name
	, tt.qty
	, tt.seat_count
from train_way_tbl as twt
inner join train_tbl as tt on twt.train_id = tt.id
;

select
	twt.name as '여행제목'
	, tt.name as '기차명'
	, tt.qty as '차량갯수'
	, tt.seat_count as '객석수'
	, sta.name as '출발역'
	, sta.lat as '출발역 위도'
	, sta.lng as '출발역 경도'
	, stb.name as '도착역'
	, stb.lat as '도착역 위도'
	, stb.lng as '도착역 경도'
from train_way_tbl as twt
inner join train_tbl as tt on twt.train_id = tt.id
... join ... on ...
... join ... on ...
```
## mysql select 연산자
- WHERE 절, HAVING 절에서 조건비교연산자
	컬럼명 = 값 : 컬럼명의 값이 같은 행을 조회
	컬럼명 != 값 : 컬럼명의 값이 다른 행을 조회
	컬럼명 >= 값 : 컬럼명의 값이 크거나 같은 행을 조회
	컬럼명 LIKE 'AB%' : 컬럼명의값이 AB 문자로 시작하는 행을 조회
	컬럼명 LIKE '%AB' : 컬럼명의값이 AB 문자로 끝나는 행을 조회
	컬럼명 LIKE '%AB%' : 컬럼명의값이 AB 문자가 포함된 행을 조회
	AND : 왼쪽도 참이고 오른쪽도 참이면 참이된다.
	OR : 왼쪽이 참이거나 오른쪽이 참이거나 둘중 하나가 참이면 참이된다.
	컬럼명1 = 3 OR 컬럼명1 = -1 OR 컬럼명1 = 10 ===>
	컬럼명1 IN (3, -1, 10)
	컬럼명1 IS NULL : 컬럼명1의 값이 null 인 행을 조회
	컬럼명 IS NOT NULL : 컬럼명의 값이 null 이 아닌 행을 조회
## programmers 사이트 문제 풀이
- 동명 동물 수 찾기 (https://school.programmers.co.kr/learn/courses/30/lessons/59041)
```
SELECT
    NAME
    , COUNT(NAME)
FROM ANIMAL_INS
WHERE NAME IS NOT NULL
GROUP BY NAME
HAVING COUNT(*) >= 2
ORDER BY NAME
;
```
- 카테고리 별 상품 개수 구하기 (https://school.programmers.co.kr/learn/courses/30/lessons/131529)
```
SELECT
    LEFT(PRODUCT_CODE, 2) AS CATEGORY
    , COUNT(*) AS PRODUCTS
FROM PRODUCT
GROUP BY LEFT(PRODUCT_CODE, 2)
ORDER BY LEFT(PRODUCT_CODE, 2)
;
```
```
7월9일 오후 문제
아래 프로그래머 사이트의 mysql 문제 1페이지 를 전부 해결해 보세요
https://school.programmers.co.kr/learn/challenges?tab=all_challenges&order=acceptance_desc&languages=mysql&page=1
```
- 12세 이하인 여자 환자 목록 출력하기 (https://school.programmers.co.kr/learn/courses/30/lessons/132201)
```
SELECT
    PT_NAME
    , PT_NO
    , GEND_CD
    , AGE
    , IFNULL(TLNO, 'NONE')
FROM PATIENT
WHERE AGE <= 12 AND GEND_CD = 'W'
ORDER BY AGE DESC, PT_NAME ASC
;
```
## 7월10일
- VIEW 를 만들려면 권한이 있는 사용자로 로그인하고 CREATE VIEW <뷰이름> AS SELECT...;
- CTE (공통 테이블 표현식) 는 VIEW 와 99% 비슷한데 차이점은 CREATE 문장을 사용하지 않으며
	SELECT 문장의 권한이 있는 사용자가 해당 SELECT 문장 1개에만 영향을 미치는 VIEW 를 만든다고 생각할 수 있다.
- CTE 문법 :
		WITH <cte이름> [(가상의컬럼명1, 컬럼명2,...)] AS (SELECT 컬럼1, 컬럼2, ... 쿼리문장)
		SELECT 가상의컬럼명1, 컬럼명2 FROM <cte이름>;
```
7월10일 문제
각자가 CTE 를 활용하여 SELECT 를 몇번 실행해보세요.
programmers 사이트에서 mysql, 정답률 높은 문제의 약 70~80% 대 까지는 문제를 해결해보시기 바랍니다.
```
- 집계함수 중 count(매개변수: *, 컬럼명) 컬럼명의 값이 NULL 이면 count 에서 제외됩니다.
	정확한 데이터 행의 갯수를 출력하려면 count(*) 를 사용하세요
- GROUP BY 컬럼A : 컬럼A의 값이 같은 데이터행들을 하나의 데이터행으로 출력한다.
	다른 컬럼들은 여러행으로 출력되므로 SELECT 절에 출력 불가능 하다.
	한개의 행으로 출력되는 것들만 SELECT 절에 출력 가능하다.

- 식품분류별 가장 비싼 식품의 정보 조회하기 (https://school.programmers.co.kr/learn/courses/30/lessons/131116)
```
SELECT 
    T2.CATEGORY
    , T1.MAX_PRICE
    , T2.PRODUCT_NAME
FROM (
    SELECT
        CATEGORY
        , MAX(PRICE) AS MAX_PRICE
    FROM FOOD_PRODUCT
    GROUP BY CATEGORY
    HAVING CATEGORY IN ('과자', '국', '김치', '식용유')
) AS T1
INNER JOIN FOOD_PRODUCT AS T2
    ON T2.PRICE = T1.MAX_PRICE
    AND T2.CATEGORY = T1.CATEGORY
ORDER BY MAX_PRICE DESC
;


WITH CTE AS (
    SELECT
        CATEGORY
        , MAX(PRICE) AS MAX_PRICE
    FROM FOOD_PRODUCT
    GROUP BY CATEGORY
    HAVING CATEGORY IN ('과자', '국', '김치', '식용유')
)

SELECT 
    T2.CATEGORY
    , T1.MAX_PRICE
    , T2.PRODUCT_NAME
FROM CTE AS T1
INNER JOIN FOOD_PRODUCT AS T2
    ON T2.PRICE = T1.MAX_PRICE
    AND T2.CATEGORY = T1.CATEGORY
ORDER BY MAX_PRICE DESC
;

SELECT
	*
FROM (
	SELECT
		* 
	FROM (
		SELECT
			CATEGORY
			, PRICE AS MAX_PRICE
			, PRODUCT_NAME
		FROM FOOD_PRODUCT
		WHERE CATEGORY = '과자'
		ORDER BY PRICE DESC
		LIMIT 1 OFFSET 0
	) A1
	UNION
	SELECT
		* 
	FROM (
		SELECT
			CATEGORY
			, PRICE AS MAX_PRICE
			, PRODUCT_NAME
		FROM FOOD_PRODUCT
		WHERE CATEGORY = '국'
		ORDER BY PRICE DESC
		LIMIT 1 OFFSET 0
	) A2
	UNION
	SELECT
		* 
	FROM (
		SELECT
			CATEGORY
			, PRICE AS MAX_PRICE
			, PRODUCT_NAME
		FROM FOOD_PRODUCT
		WHERE CATEGORY = '김치'
		ORDER BY PRICE DESC
		LIMIT 1 OFFSET 0
	) A3
	UNION
	SELECT
		* 
	FROM (
		SELECT
			CATEGORY
			, PRICE AS MAX_PRICE
			, PRODUCT_NAME
		FROM FOOD_PRODUCT
		WHERE CATEGORY = '식용유'
		ORDER BY PRICE DESC
		LIMIT 1 OFFSET 0
	) A4
) TT
ORDER BY TT.MAX_PRICE DESC
;
```
## 🚀 SJW/MJC813 교육 과정 학습 저장소

### 💡 개요 (Overview)

본 저장소는 **클라우드 기반 웹 개발자 양성 과정**에서 진행된 다양한 기술 스택에 대한 학습 및 실습 코드를 모아둔 모노레포(Monorepo) 스타일의 학습 아카이브입니다. Java, Spring Boot를 중심으로 프론트엔드, 데이터베이스, 네트워크 프로그래밍, 도커(Docker) 등 광범위한 실습 예제가 포함되어 있습니다.

### 🛠️ 주요 기술 스택 (Tech Stack)

| 구분 | 기술 스택 | 주요 목적 및 사용 예제 |
| :--- | :--- | :--- |
| **백엔드** | **Java**, **Spring Boot**, Gradle, Maven | Java 기본 문법, 객체 지향 프로그래밍(OOP), 쓰레드, 네트워크 프로그래밍, 스프링 웹 애플리케이션 개발. |
| **웹/프론트엔드** | **HTML5**, **CSS3**, **JavaScript**, Vue.js, Bootstrap | 웹 UI/UX 구현, 동적 클라이언트 로직, Vue.js 기반 프론트엔드 프로젝트 (`Front_Daewoo`). |
| **데이터베이스** | **MySQL**, MyBatis, **Spring Data JPA** | 관계형 데이터베이스 관리(RDBMS), MyBatis를 활용한 CRUD 구현, JPA Entity 기반 데이터 처리. |
| **기타** | **Docker**, Git | 애플리케이션 컨테이너화 및 배포 환경 구성, 버전 관리 및 협업 실습 문서. |

### 📂 폴더 구조 및 프로젝트 설명 (Project Structure)

주요 하위 폴더는 다음과 같이 구성되어 있으며, 각 폴더는 독립적인 프로젝트 또는 특정 주제에 대한 학습 내용을 담고 있습니다.

| 폴더명 | 설명 | 사용 기술/주요 내용 |
| :--- | :--- | :--- |
| `Food_web` | 음식 레시피/재료 관리 웹 애플리케이션 (REST API + Thymeleaf/JSP) | Spring Boot, JPA, MyBatis, Spring Security, 파일 업로드/다운로드, Email 전송 |
| `Front_Daewoo` | Vue.js 기반 프론트엔드 프로젝트 (호텔/숙박 예약 사이트) | Vue.js, Vue Router, SCSS, Axios (백엔드 연동), Dockerfile/Nginx 설정 포함 |
| `cinema_crud` | 영화 및 장르 관리 CRUD 백엔드 (MyBatis 기반) | Spring Boot, MyBatis, RESTful API, MySQL |
| `cinema_jpa` | 영화 및 장르 관리 CRUD 백엔드 (JPA 기반) | Spring Boot, Spring Data JPA, RESTful API, MySQL |
| `contact_web` | 연락처 관리 웹 프로젝트 (Spring Boot + Mustache 템플릿) | Spring Boot, MyBatis, Mustache, Web CRUD 구현 |
| `gugudan_mustache` | 구구단 출력 예제 (Mustache 템플릿 학습) | Spring Boot, Mustache |
| `gugudan_thymeleaf`| 구구단 출력 예제 (Thymeleaf 템플릿 학습) | Spring Boot, Thymeleaf |
| `swimpool_app` | 수영장 정보 관리 시스템 (보안 및 인증/인가 실습 포함) | Spring Boot Security, REST API, AES256 암복호화, 로그인/회원가입 기능 |
| `mavenJUnit` | Java 네트워크 프로그래밍 및 JUnit 테스트 코드 실습 | Java Socket, Multi-Client Chat Program, JUnit 5 |
| `CodingTest` | Java 코딩 테스트 문제 풀이 및 기초 자료구조/알고리즘 연습 | Java, 프로그래머스/백준 문제 풀이 (예상) |
| `doc` | 강의 자료, DB 명세서, 시간표 등 참고 문서 및 이미지 파일 | Markdown, CSV, PPTX, PDF |

-----

### ⚙️ 빌드 및 실행 방법 (How to Build and Run)

대부분의 백엔드 프로젝트는 **Gradle** 기반의 Spring Boot 프로젝트입니다. `Front_Daewoo`는 Vue.js 프로젝트로 별도의 빌드 과정이 필요합니다.

#### 1\. 환경 설정 (Prerequisites)

* **Java 17+** (JDK)
* **Gradle** (혹은 Maven, 프로젝트 설정에 따라 다름)
* **MySQL 8.0+** (데이터베이스 연결이 필요한 프로젝트의 경우)
* **Docker/Docker Compose** (Docker 환경으로 실행할 경우)

#### 2\. 백엔드 프로젝트 (Java/Spring Boot)

* **DB 설정:**
    * 각 프로젝트의 `src/main/resources/application.yml` 파일에서 데이터베이스 접속 정보(URL, username, password)를 본인 환경에 맞게 수정합니다.
    * 필요한 경우, `natural_sevlet_jsp/doc/init_mysql.sql` 또는 유사한 SQL 파일을 사용하여 DB 스키마 및 초기 데이터를 생성해야 합니다.
* **빌드 및 실행 (Gradle 기준):**
  ```bash
  # 원하는 프로젝트 폴더로 이동 (예: cinema_crud)
  cd cinema_crud

  # 프로젝트 빌드 (의존성 다운로드 및 컴파일)
  ./gradlew clean build -x test

  # JAR 파일로 실행
  java -jar build/libs/*.jar
  ```
* **IDE (IntelliJ IDEA, Eclipse)에서 실행:**
    * IDE에서 해당 폴더를 프로젝트로 열고, 메인 애플리케이션 클래스(예: `CinemaCrudApplication.java`)를 찾아 실행합니다.

#### 3\. 프론트엔드 프로젝트 (`Front_Daewoo`)

`Front_Daewoo` 폴더는 Vue CLI로 생성된 프로젝트로 보입니다.

* **의존성 설치:**
  ```bash
  cd Front_Daewoo
  npm install  # 또는 yarn install
  ```
* **개발 서버 실행:**
  ```bash
  npm run serve
  # 브라우저에서 http://localhost:8080 등으로 접속
  ```
* **배포용 빌드:**
  ```bash
  npm run build
  # 빌드된 파일은 dist 폴더에 생성됩니다.
  ```

#### 4\. Docker를 이용한 실행 (Docker Execution)

`Front_Daewoo` 및 일부 폴더에는 Docker 관련 파일이 포함되어 있습니다.

* **Docker Compose를 이용한 실행 (예시: Front\_Daewoo/docker-backend):**
  ```bash
  # Docker Compose 파일이 있는 폴더로 이동
  cd Front_Daewoo/docker-backend

  # 서비스 빌드 및 실행
  docker-compose up --build -d
  ```

-----

### 📝 코드 규약 및 문서 (Code Convention & Documentation)

* **Prettier & ESLint:** `Front_Daewoo` 폴더에는 `.prettierrc`, `.eslintrc.js` 파일이 존재하여 코드 포맷팅 및 품질 관리를 위한 설정이 되어 있습니다. (참고 문서: `Front_Daewoo/md/prettier/PrettierGuide.md`)
* **주요 문서:** `doc` 폴더에 DB 설계, 강의 자료 등 학습에 필요한 핵심 문서가 포함되어 있습니다.

### 👥 작성자 및 기여 (Author & Contribution)

* **작성자:** Se0jwon
* **기여:** 본 저장소는 개인 학습 기록을 목적으로 하며, 외부 기여는 받지 않습니다. (혹은 조직 내 규약에 따라 기여 방식을 명시)

### 📄 라이선스 (License)

* MIT License (또는 프로젝트의 성격에 맞는 라이선스 명시)
    * **"본 프로젝트는 학습 및 실습 목적으로 생성되었으며, 상업적 이용을 위해서는 별도의 검토가 필요합니다."** 와 같은 문구를 추가할 수 있습니다.
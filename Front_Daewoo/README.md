# vue\_project

## 1\. 프로젝트 개요 (Project Overview)

이 프로젝트는 **Vue.js 3** 기반으로 개발된 프론트엔드 애플리케이션입니다. 사용자 인터페이스 개발 및 비즈니스 로직 처리를 담당하며, 백엔드 서버와 RESTful API 통신을 통해 데이터를 주고받습니다.

* **프로젝트 명**: `vue_project`
* **버전**: `0.1.0`
* **주요 기능**: 숙소 목록 조회/검색, 상세 페이지, 사용자 프로필 관리, 결제 기능 등으로 구성되어 있습니다.

## 2\. 기술 스택 (Tech Stack)

| 구분 | 기술 스택 | 버전/설명 |
| :--- | :--- | :--- |
| **Front-end Core** | Vue.js | `^3.2.13` (Composition API 활용) |
| **Routing** | Vue Router | `^4.5.1` (SPA 라우팅 관리) |
| **HTTP 통신** | Axios | `^1.13.0` (비동기 API 요청 처리) |
| **스타일링** | SASS/SCSS | CSS 전처리기 사용 |
| **코드 품질** | ESLint, Babel | 코드 일관성 유지 및 문법 검사 |
| **UI 컴포넌트** | vue-slider-component | `^4.1.0-beta.7` (슬라이더 기능 구현) |

## 3\. 개발 환경 설정 (Development Setup)

### 3.1. Prerequisites (필수 요구사항)

* Node.js (LTS 버전 권장)
* npm (Node Package Manager)

### 3.2. Install Dependencies (의존성 설치)

프로젝트 루트 디렉토리에서 아래 명령어를 실행하여 필요한 패키지를 설치합니다.

```bash
npm install
```

### 3.3. Run Development Server (개발 서버 실행)

개발 모드로 애플리케이션을 실행합니다. 소스 코드 변경 시 자동으로 리로드됩니다.

```bash
npm run serve
```

> 💡 **NOTE:** 개발 서버는 \*\*`http://localhost:8080`\*\*에서 실행됩니다.

## 4\. 백엔드 연동 및 프록시 설정 (Backend Proxy Configuration)

로컬 개발 환경에서 CORS 문제없이 백엔드 API를 호출하기 위해 개발 서버(DevServer) 프록시가 설정되어 있습니다.

`vue.config.js` 설정에 따라, 프론트엔드 서버(`http://localhost:8080`)로 들어오는 다음 경로의 요청은 백엔드 서버 \*\*`http://localhost:18888`\*\*로 전달(Proxy)됩니다.

| 요청 경로 (Frontend) | 대상 서버 (Backend) |
| :--- | :--- |
| `/api/**` | `http://localhost:18888` |
| `/images/**` | `http://localhost:18888` |
| `/hotel-images/**` | `http://localhost:18888` |
| `/user-images/**` | `http://localhost:18888` |

## 5\. 빌드 및 테스트 (Build & Lint)

### 5.1. Production Build (배포용 빌드)

배포를 위해 코드를 컴파일하고 최소화합니다.

```bash
npm run build
```

### 5.2. Code Linter (코드 품질 검사 및 수정)

ESLint와 Prettier 규칙에 따라 코드를 검사하고 자동으로 수정합니다.

```bash
npm run lint
```

-----

### 기타 정보

* **Configuration Reference**: Vue CLI 설정을 커스터마이징하려면 [Configuration Reference](https://cli.vuejs.org/config/)를 참고하세요.
* **프로젝트 구성**: 컴포넌트 및 뷰 파일은 `src/components/`, `src/views/` 폴더에 구조화되어 있습니다.
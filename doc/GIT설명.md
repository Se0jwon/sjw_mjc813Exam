# GIT
- 리누스토르발즈가 형상관리 사용하다가 GIT 에 대한 아이디어를 냈고 뜻을 맞는 사람들이 모여서 오픈소스로 진행
- SVN 이후에 매우 기능이 뛰어난 형상관리 툴입니다.
- GIT 서버 : GIT 클라이언트, HTTP 서버 : HTTP CLient, FTP 서버 : FTP 클라이언트
	WEB서버 : WEB클라이언트 => 서버와 클라이언트의 프로토콜이 국제표준으로 정해져있다.
- GIT 로컬리포지토리와 원격리포지토리의 History 는 언제나 항상 일치한다.

## Remote Repository
- GIT 서버에서 관리하는 원격 리포지토리라고 생각할 수 있다.
- 전세계의 유명한 GIT 서버 : github.com, bitbucket.org, gitlab.com, ...
- 원격리포지토리는 로컬리포지토리와 연결되어 있으면서 파일을 download(pull), upload(push) 할수있다.
- 원격리포지토리를 함부로 지우면 다시 복구 절대로 못한다.

## Local Repository
- 전세계의 유명한 GIT 클라이언트 : github desktop, sourcetree, tortoiseGit, fork, gitclient, ...
- 나의컴퓨터, window, linux, mac 에서 사용하는 로컬리포지토리 라고 생각할 수 있다.
- 원격리포지토리에 있는 폴더를 로컬의 어떤 특정 폴더에 clone 명령어로 원격과 연결된 로컬리포지토리를 만들 수 있다.
- .git 폴더가 자동으로 생긴다. .git 숨김폴더는 원격리포지토리와 연결된 정보가 있다. 이것을 지우면 연결이 끊어진다.
- 로컬리포지토리에 어떤 파일이나 디렉토리의 변경(추가,수정,삭제) 사항이 생기면 stage 라는 곳에 등장한다.
- stage 에 등장된 어떤 파일이나 디렉토리를 저장하려면 주석을 쓰고 commit 를 해야 한다. (history 추가)
- stage 에 등장된 어떤 파일이나 디렉토리를 취소하려면 Revert, Discard 를 실행 해야 한다.
- 로컬리포지토리에 commit 된 파일이나 디렉토리를 원격리포지토리에 push 로 업로드 할 수 있다.
- 같은 로컬리포지토리를 가르키고 있는 GIT 클라이언트들 중에서 한개의 클라이언트에서 commit 이나 pull, push 하면 모든 클라이언트 적용된다. 
- 로컬리포지토리에서 fetch 하면 원격리포지토리에 있는 변경된 정보만 가져온다. pull 을 해야지 파일을 가져온다.
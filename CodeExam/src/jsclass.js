class 클래스이름 {
    멤버변수이름1 = 초기값;
    멤버변수이름2 = 초기값;
    constructor(매개변수1, 매개변수2, ..) {
        this.멤버변수이름1 = 매개변수1;
        this.멤버변수이름2 = 매개변수2;
    }
    멤버메소드(매개변수1, 매개변수2, ..) {
        메소드 명령어들;
        this.멤버메소드2(...);
    }
    멤버메소드2(매개변수1, 매개변수2, ..) {
        메소드 명령어들;
    }
}

let 객체이름 = new 클래스이름(); // 기본생성자
let 객체이름 = new 클래스이름(매개변수1, 매개변수2); // 비기본생성자
객체이름.멤버메소드(...);
객체이름.멤버메소드2(...);
let x = 6 + 7;
console.log("안녕하세요 지금 시각은 " + new Date()
    + ", x 의 값은 " + x);
console.log(`안녕하세요 지금 시각은 ${new Date()}, x 의 값은 ${x}`);

let text = "The rain in SPAIN stays mainly in the plain";
const myArr = text.match(/ain/);
console.log(myArr.length + " " + myArr);

let array01 = ["a", "b", "c", "d", "e"];
for( let i = 0; i < array01.length; i++ ) {
    // 배열의 모든 원소를 처리하기 위하여
    // for (시작;조건;증감) {...} i 라는 인덱스 사용
    console.log(`오래된 for 블록 : ${array01[i]}`);
}

// 배열변수이름.forEach(......)
array01.forEach(function(item) {
    // forEach 는 배열의 모든 원소를 차례대로 실행하는 이름없는 함수를 선언한다.
    console.log(`forEach 1 블록 : ${item}`);
});

// 배열변수이름.forEach(......)
array01.forEach((item) => {
    // 이름없는 함수는 (매개변수) => { ... } 로 선언 가능하다.
    console.log(`forEach 2 블록 : ${item + 1}`)
});

// 배열변수이름.forEach(......)
array01.forEach(myForEach);
// forEach 는 배열의 모든 원소를 차례대로 myForEach 함수를 이용하여 실행한다.
function myForEach(item) {
    console.log(`forEach 3 블록 : ${item}`);
}



function fMul2(n) {
    return n * 2;
}

function fAdd(a, b) {
    return a + b;
}

function fAddFnc(aFunc, bFunc) {
    return aFunc(10) + bFunc(5);
}
// 함수를 호출하여 매개변수로 전달할때
console.log(fAdd(fMul2(10), fMul2(5)));
console.log(fAddFnc(fMul2, fMul2));
//1. fMul2(10) 실행하고 그 결과 20
//console.log(fAdd(20, fMul2(5)));
//2. fMul2(5) 실행하고 그 결과 10
//console.log(fAdd(20, 10));
//3. fAdd(20, 10) 실행하고 그 결과 30
//console.log(30);


function fAddFunc(a, b) {
    return a(10) + b(5);
}
// 함수의 이름을 매개변수로 전달할때
console.log(fAddFunc(fMul2, fMul2));
//1. fAddFunc 를 실행한다.
//2. fAddFunc 의 내용이 return fMul2(10) + fMul2(5); 이렇게 실행된다.
//3. fMul2(10) 를 실행한 결과 20, return 20 + fMul2(5);
//4. fMul2(5) 를 실행한 결과 10, return 20 + 10;
//5. console.log(30);


let array02 = ["A", "B", "C", "D"];
let resultArray02 = array02.map(function(x, i) {
    return x + i;
    // array02 배열 모든 원소마다 실행한 결과를
    // 원소로 하는 새로운 배열을 만든다. resultArray02 =
    // 원래 배열 array02 는 수정하지 않는다.
});
console.log(resultArray02);

let resultArray03 = array02.filter((v) => {
    return v > "A" && v < "D";    // 참/거짓 값만 리턴해야 한다.
    // array02 배열 모든 원소마다 조건문장이 참인 결과의 원소만
    // 구성 하는 새로운 배열을 만든다. resultArray03 =
    // 원래 배열 array02 는 수정하지 않는다.
});
console.log(resultArray03);

let resultArray04 = array02.reduce((t, m) => {
    // array02 배열 모든 원소마다 실행한 결과가 한개의 값으로 리턴된다.
    // 원래 배열 array02 는 수정하지 않는다.
    return t + m;
});
console.log(resultArray04);


let leftNumber = "";
let rightNumber = "";
let operator = "";
let result = "";

function checkWhere(number) {
    if( operator === "" ) {
        leftNumber += number.toString();
    } else {
        rightNumber += number.toString();
    }
    document.getElementById("outDiv").innerText = leftNumber + " " + operator + " " + rightNumber;
}

function buttonNum(number) {
    checkWhere(number);
}

function buttonAdd() {
    operator = "+";
}

function buttonSub() {
    operator = "-";
}

function buttonMul() {
    operator = "*";
}

function buttonDiv() {
    operator = "/";
}

function buttonEqu() {
    try {
        let res = eval(leftNumber);
    } catch(ex) {
        console.log(ex.message);
        leftNumber = leftNumber.replaceAll('A','');
    }
    let a = typeof leftNumber;
    console.log(a);
    console.log(a instanceof Number);
    try {
        let res = eval(rightNumber);
    } catch(ex) {
        console.log(ex.message);
        rightNumber = rightNumber.replaceAll('A','');
    }
    switch(operator) {
        case "+":
            result = (Number(leftNumber) + Number(rightNumber)).toString();
            break;
        case "-":
            result = (Number(leftNumber) - Number(rightNumber)).toString();
            break;
        case "*":
            result = (Number(leftNumber) * Number(rightNumber)).toString();
            break;
        case "/":
            result = (Number(leftNumber) / Number(rightNumber)).toString();
            break;
    }
    document.getElementById("outDiv").innerText = leftNumber + " " + operator
        + " " + rightNumber + " = " + result;
}

function buttonCE() {
    leftNumber = "";
    rightNumber = "";
    operator = "";
    result = "0";
    document.getElementById("outDiv").innerText = result;
}

class Car1 {
	name = "";
	construct(a) {
	    this.name = a;
	}
	start() {
	    console.log(this.name + " start");
	}
}

let car1 = new Car1("Fiat");
car1.start();

function Person(first, last, age, eye) {
  this.firstName = first;
  this.lastName = last;
  this.age = age;
  this.eyeColor = eye;
}

let p1 = new Person("first", "last", 20, "black");
console.log(p1);
console.log(JSON.stringify(p1));


// JavaScript 에서 클래스 선언하고 객체 탄생하는 방법으로 사용한다.
class Human {
	height = 180; // 인스턴스 변수, 멤버변수
	name = "";
	age = 0;

	constructor(name, age) {	// 비기본 생성자
		// this는 클래스가 생성할 인스턴스를 가리킨다.
		this.name = name;
		this.age = age;
	}

	print() {
//		console.log("이름은 " + this.name + ", 나이는 " + this.age
//		 + ", 키는 " + this.height);
		console.log(`이름은 ${this.name}, 나이는 ${this.age}, 키는 ${this.height}`);
	}

	start() {
	    console.log(this.name + " Start !!");
	}
}

let human1 = new Human("이순신", 30);
human1.print();
let human2 = new Human("홍길동", 25);
human2.print();
human2.start();

class Car {
    name = "";
    model = "";
    weight = "";
    color = "";

//    constructor() {   // 매개변수가 없는 생성자 => 기본생성자
//      javascript 는 생성자를 만드는 constructor 가 두개면 에러 발생함
//      java, javascript 는 기본생성자를 자동으로 만들어 준다.
//    }

//      매개변수가 있는 생성자 => 비기본 생성자
    constructor(name, m, w, color) {
        this.name = name;
        this.model = m;
        this.weight = w;
        this.color = color;
        // this는 클래스를 설계할때는 어떤 이름으로 인스턴스될지 모른다.
        // 인스턴스 된 자기 객체를 뜻할때 this 라는 단어를 사용한다.
    }

    start() {
        //console.log(JSON.stringify(this) + " 출발합니다.");
        // JSON 문자열 형식 {이름:값, 이름:값, ...}
        // 생성된 객체의 멤버변수이름과 멤버변수 값을 출력한다.
        console.log(`${JSON.stringify(this)} 출발합니다.`);
        // ` 문자열 ${자바스크립트객체,수식} 문자열`
    }

    drive() {
        console.log(this.name + " 운전합니다.");
    }

    brake() {
        console.log(this.name + " 브레이크 페달 밟아요.");
    }

    stop() {
        //console.log(this.name + " 정지합니다.");
        console.log(`${this.name} 정지합니다.`);
    }

    showRedSign() {
        console.log(this.name + " 빨간불을 봤습니다.");
        this.brake();
        this.stop();
    }
}

let fiat500 = new Car();
fiat500.name = "Fiat";  // 객체를 만든후에 멤버변수를 할당하는 방법인데
fiat500.model = "500";  // 이 방법은 별로 추천하지 않습니다.
fiat500.weight = "850kg";
fiat500.color = "white";
fiat500.start();
fiat500.drive();
fiat500.stop();

let accentMD = new Car("Accent", "MD", "700kg", "gray");
// 객체를 만들때 생성자로 객체를 생성하면서 멤버변수의 값을 할당하는 방법을 추천합니다.
accentMD.start();
accentMD.drive();
accentMD.showRedSign();

class Zeep extends Car {
    // Car 클래스를 상속받아서 Zeep 클래스를 선언한다.
    // Car 클래스에 존재하는 멤버변수와 멤버메소드를 모두 사용할 수 있다.
    engine = "";
    constructor(name, m, w, color, engine) {
        super(name, m, w, color);   // super 라는 단어는 객체지향에서 부모를 뜻한다
        // base : C# 에서 사용하는 부모를 뜻한다.
        this.engine = engine;
        this.name += " : 자식에서 만든 이름";
    }
    fourWd() {
        console.log(this.name + ", " + this.engine + " 4휠 입니다.");
    }
}

let zeep1 = new Zeep("코란도4", "코란도", "1200kg", "brown", "120마력");
zeep1.start();
zeep1.drive();
zeep1.fourWd();
zeep1.stop();
// 자식클래스 객체는 부모클래스의 속성(멤버변수)와 메소드(멤버메소드)를 사용할 수 있다.
// 그리고 자식클래스의 속성과 메소드를 사용할 수 있다.


let morning1 = new Car("모닝", "M500", "700kg", "red");
morning1.start();
morning1.drive();
// morning1.fourWd();  // 에러가 발생합니다.
// 부모클래스 객체는 부모클래스만 사용할 수 있다.
// 부모클래스 객체는 자식클래스를 모른다.
morning1.stop();

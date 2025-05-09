let strToDay = new Date();
console.log(`오늘 날짜는 ${strToDay} 입니다.`);
console.log(`오늘 년도는 ${strToDay.getFullYear()} 입니다.`);
console.log(`오늘 월는 ${strToDay.getMonth()} 입니다.`);
console.log(`오늘 일는 ${strToDay.getDate()} 입니다.`);
console.log(`오늘 요일는 ${strToDay.getDay()} 입니다.`);
console.log(`지금 시간는 ${strToDay.getHours()} 입니다.`);
console.log(`지금 분는 ${strToDay.getMinutes()} 입니다.`);
console.log(`지금 초는 ${strToDay.getSeconds()} 입니다.`);

strToDay.setDate(strToDay.getDate() + 50);
console.log(`50일을 더한 날짜는 ${strToDay} 입니다.`);
strToDay.setMinutes(strToDay.getMinutes() + 60);
console.log(`60분을 더한 날짜는 ${strToDay} 입니다.`);

console.log(`Math.round(6.67) = ${Math.round(6.67)}`);
console.log(`Math.ceil(6.67)  = ${Math.ceil(6.67) }`);
console.log(`Math.floor(6.67) = ${Math.floor(6.67)}`);
console.log(`Math.trunc(6.67) = ${Math.trunc(6.67)}`);

console.log(`Math.round(10.17) = ${Math.round(10.17)}`);
console.log(`Math.ceil(10.17)  = ${Math.ceil(10.17) }`);
console.log(`Math.floor(10.17) = ${Math.floor(10.17)}`);
console.log(`Math.trunc(10.17) = ${Math.trunc(10.17)}`);

console.log(`Math.random() = ${Math.random()}`);

const fruits = new Map([
  ["apples", 500],
  ["bananas", 300],
  ["oranges", 200]
]);
fruits.forEach(function(item) { // (item) => {
    console.log(`forEach fruits : ${item}`);
});
for( let x of fruits ) {
    console.log(`for of fruits : ${x}`);
}
for( let x in fruits ) {    // Map 형태는 for..in 안됨
    console.log(`for in fruits : ${x}`);
}

try {
    let x = 0;
    let y = 4;
    let z = x / y;
    const arr = new Array(99999999999999);
    console.log(`try 블록임 1`);
    throw 555;
    console.log(`try 블록임 2`);
} catch(e) {
    console.log(`catch 블록임 ${e}`);
} finally {
    console.log(`try~finally 실행끝`);
}

// 이름이 있는 함수 3가지 방법으로 선언했다.
let myFunction1 = (a, b) => a + b;
myFunction1 = (a, b) => {
  return a / b;
}

function myFunction2(a, b) {
	return a * b;
}

let myFunction3 = function(a, b) {
	return a - b;
}
console.log(`myFunction1 : ${myFunction1(4, 6)}`);
console.log(`myFunction2 : ${myFunction2(4, 6)}`);
console.log(`myFunction3 : ${myFunction3(4, 6)}`);


let _age = 3;
//let &age = 1;
//let let = 2;
//let 1age = 4;
//let 1age = 4;

class Exam0414 {
    makeLotto() {
        // 난수로 6개의 숫자를 발행하되, 중복 안됨, 오름차순 정렬
        //Math.random(); // 0.00000 ~ 0.9999999 * 45
        // => 0.45050 ~ 44.7546 + 1 => 1 ~ 45
        let arrLotto1 = [];
        for ( let i = 0; i < 6; i++ ) {
            let nRd = Math.floor( Math.random() * 45 ) + 1;
            // nRd 에는 1~45 까지의 난수가 생성된다.
            if ( !this.existNumber(nRd, arrLotto1) ) {
                // 중복된 값이 없을 경우
                arrLotto1.push(nRd);
            } else {
                // 중복된 값이 있을 경우
                i--;    // loop 를 한번 더 실행해야 한다.
            }
        }
        arrLotto1.sort( (a,b) => { return a - b });  // 숫자배열을 오름차순으로 정렬
        console.log(`makeLotto1 : ${arrLotto1}`);

        let arrLotto2 = [];
        while( arrLotto2.length < 6 ) { // 배열의 길이가 6보다 작으면 실행
            let nRd = Math.floor( Math.random() * 45 ) + 1; // 난수 발생
            if ( !this.existNumber(nRd, arrLotto2) ) {
                // 중복된 값이 없을 경우
                arrLotto2.push(nRd);
            }
        }
        arrLotto2.sort( (a,b) => { return a - b });  // 숫자배열을 오름차순으로 정렬
        console.log(`makeLotto2 : ${arrLotto2}`);
    }

    existNumber(number, array) {
//        let result = array.some( function(item) {   // 배열 모든 원소를 각각 함수 문장으로 비교한다. 하나라도 true 이면 true 를 리턴한다.
//            return item === number;
//        }); // array 배열에서 number === item 이 같은 원소 있으면 true 리턴
//        return result;

        for( let i = 0; i < array.length; i++ ) {
            // 배열 모든 원소를 if 절로 비교한다.
            if ( number === array[i] ) {
                // 같은 값이 존재하면 바로 return true 시킨다.
                return true;
            }
        }
        return false;   // 모든 원소를 비교했을때 같은 값이 없으면 return false
    }

    pdfExam10() {
        console.log("    *");       // 공백 4, 별 1
        console.log("   ***");      // 공백 3, 별 3
        console.log("  *****");     // 공백 2, 별 5
        console.log(" *******");    // 공백 1, 별 7
        console.log("*********");   // 공백 0, 별 9
        let nSpace = 4;
        let nStar = 1;
        for( let nLine = 0; nLine < 5; nLine++ ) {  // 5줄 루프
            let strLine = "";   // 한줄을 만드는 문자열 변수
            for( let nSp = 0; nSp < nSpace; nSp++ ) {   // 공백을 만드는 루프
                strLine += " ";
            }
            for( let nSt = 0; nSt < nStar; nSt++ ) {    // 별을 만드는 루프
                strLine += "*";
            }
            console.log(strLine);
            nSpace--;   // 다음줄은 공백을 1개씩 줄인다.
            nStar += 2; // 다음줄은 별을 2개씩 늘인다.
        }
    }

    pdfExam12() {
        const x = new Wizard(545, 210, 10);
        console.log(x.health, x.mana, x.armor);
        x.attack();
    }

    pdfExam13() {
        let arrStar = ["수성", "금성", "지구", "화성"
            , "목성", "토성", "천왕성", "해왕성"];
        let sInput = prompt("숫자를 1~8 사이에서 입력 하세요.");
        let number = Number(sInput);
        if ( Number.isNaN(number) ) {
            console.log(`숫자를 1~8 사이에서 입력 하세요.`);
        } else {
            console.log(`선택한 별은 ${arrStar[number-1]} 입니다.`);
        }


    }

    pdfExam16() {
        let sInput = prompt("문자열을 입력 하세요.");
//        let arrOutput = sInput.split("");
//        let arrReverse = arrOutput.reverse();
//        let sOutput = arrReverse.reduce(function(result, item) {
//            return result + item;
//        }, "");
//        console.log(sOutput);
        let output = "";
        for( let i = sInput.length - 1; i >= 0; i-- ) {
            output += sInput.charAt(i);
        }
        console.log(output);
    }
}

class Wizard {  // Wizard 클래스 선언
    health = 0; // 멤버변수 선언
    mana = 0;
    armor = 0;
    constructor(h, m, a) {  // 비기본생성자(매개변수 3개) ==> new Wizard(1,2,3);
        this.health = h;
        this.mana = m;
        this.armor = a;
    }
    attack() {  // 멤버메소드
        console.log("파이어볼");
    }
}

let exam0414 = new Exam0414();
exam0414.makeLotto();
exam0414.pdfExam10();
exam0414.pdfExam12();
exam0414.pdfExam13();
exam0414.pdfExam16();

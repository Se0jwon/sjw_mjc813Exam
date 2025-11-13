class Question0410 {
    strExam = "Javascript strings are primitive and immutable: All string methods produce a new string without altering the original string.";

    exam1() {
//- 위의 문장을 각 단어가 원소인 배열로 변환하기
//    => ["Javascript", "strings", "are", ....]
        let arrExam1 = this.strExam.split(" ");
        document.getElementById("result1").innerText = arrExam1;
    }

    exam2() {
//- 대문자 -> 소문자, 소문자는 -> 대문자로 바꾸어서 문장으로 만들기
//	=> jAVASCRIPT STRINGS ARE ....
        let result2 = "";
        for( let i = 0; i < this.strExam.length; i++ ) {
            let ch = this.strExam.charAt(i);
            if ( ch >= 'A' && ch <= 'Z' ) {
                result2 += ch.toLowerCase();
            } else if ( ch >= 'a' && ch <= 'z' ) {
                result2 += ch.toUpperCase();
            } else {
                result2 += ch;
            }
        }
        document.getElementById("result2").innerText = result2;
    }

    exam3() {
//- 위의 문장에서 [string] 이라는 단어를 [문자열] 으로 변환하기
//	=> Javascript 문자열s are ....
        let result3 = this.strExam.replaceAll("string", "문자열");
        document.getElementById("result3").innerText = result3;
        //document.getElementById("result4").innerText = this.strExam.substring(11);
        document.getElementById("result4").innerText = this.strExam.substring(11,12);
    }
}

let question = new Question0410();
question.exam1();
question.exam2();
question.exam3();


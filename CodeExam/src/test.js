
alert('외부js 파일');

let sum = 0;
for ( let i = 0, j = 1; i < 5; i++, j+=2 ) {
    sum += i + j;
    console.log("i : " + i + ", j : " + j + " = sum : " + sum );
}
console.log("sum : " + sum );

let x = 5;
x = 11;
console.log("x : " + x );

const PI = 3.141592435654;
console.log("PI : " + PI );

const MYCOMPANY = "(주)명지전문대";
console.log("MYCOMPANY : " + MYCOMPANY );

sum = "합계";
console.log("sum : " + sum );


// You can create a constant array:
const cars = ["Saab", "Volvo", "BMW"];
console.log("cars : " + cars );

// You can change an element:
cars[0] = "Toyota";
console.log("cars : " + cars[0] );

// You can add an element:
cars.push("Audi");
console.log("cars : " + cars.length );

//cars = [1, 2, 3, 4];
//console.log("cars : " + cars[0] );

console.log(cars instanceof String);
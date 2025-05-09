
class Car2 {
  constructor(name) {
    this.name = name;
  }
  static hello(x) {
    return "Hello " + x.name;
  }
}
const myCar = new Car2("Ford");
console.log( Car2.hello(myCar) );
console.log( Car2.hello(fiat500) );
console.log( Car2.hello("ss") );

function printTime1() {
    console.log(`setTimeout : ${new Date()}`);
}

let handle1 = setTimeout(printTime1, 1000);

function printTime2() {
    console.log(`setInterval : ${new Date()}`);
}
let handle2 = setInterval(printTime2, 1000);

let isDone: boolean = true;
let num: number = 10;
let str: string = "hello";

let arr: number[] = [1, 2, 3];
let tuple: [string, number] = ["kim", 20];

let anyValue: any = "anything"; // 비추천
let unknownValue: unknown = "safe any";

function greeting(name : string) : string {
    return `HI ~, ${name}`;
}

// typescript 객체 형태
const user : {name : string, age : number} = {
    name : "jslim",
    age : 20
}
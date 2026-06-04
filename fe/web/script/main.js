// import {} : 가져오고자 하는 함수가 named export 형태일 때
// default export일 경우, 중괄호 제외
import {add} from './sub.js'


console.log(`debug >>>> module import ` , add(4, 5))
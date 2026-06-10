package features.exception;

import features.exception.util.InspireException;

/*
예외(예기치 못한 상황)처리방법
- compile

- runtime

처리방법
- case 01.
try{
    예외발생 코드
} catch(발생된 예외 객체 정의){
    예외발생시 수행되는 블럭
} finally{
    예외발생여부와 상관없이 무조건 수행되는 블럭
}

- case 02.
throws XXXXException {

}

강제로 예외를 발생시키는 상황
throw new xxxException()

사용자 정의 예외 클래스 작성방법

*/
public class ExceptionDemo {
    
    public void first(int x) throws Exception{
        System.out.println(">>>> first start");
        try {
            if(x<0) {
                throw new InspireException("양의 정수만 입력하세요.");
            }
        } finally {
            System.out.println(">>>> first end");
        }
    }
}

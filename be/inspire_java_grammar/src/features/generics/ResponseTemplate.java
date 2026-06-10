package features.generics;

/* 
Generics
- 원래 타입이 적용되어야 하는 곳에 Generics 적용
- 다양한 타입을 한 클래스에 적용 가능
*/ 
public class ResponseTemplate<T> {
    private T code;

    public void setCode(T code) {
        this.code = code;
    }

    public T getCode() {
        return this.code;
    }
}


// react 전용 bootstrap을 사용 하고싶다면?
// npm install react-bootstrap bootstrap
import Button from 'react-bootstrap/Button';
// 상태관리를 위한 Hook
import { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css'

const EventPage = () => {
    // 변수란 스코프 개념으로 봤을 때 전역, 지역변수가 있음.
    // 양방향 상태관리가 안되는 코드
    // const data = {
    //     id : 'asdf', password : "qwer"
    // }
    // let id=data.id, password=data.password;

    // 사용자의 입력값과 스크립트의 변수를 동기화하기 위해서는 state 선언
    const [id, setId] = useState('');
    const [password, setPassword] = useState('');
    const handler = (id, password) => {
        console.log(`debug >>>> ${id}, ${password}`);
    }
    const idHandler = (e) => {
        setId(e.target.value);
        console.log(`debug >>>>> id value ${id}`);
    }
    return (
        <div className='App'>
            <div>
                <label>아이디</label>
                <input type="text"
                    value={id}
                    onChange={(e) => idHandler(e)} ></input>
            </div>
            <div>
                <label>패스워드</label>
                <input type="passwd"
                    value={password}
                    onChange={(e) => {
                        setPassword(e.target.value);
                    }}></input>
            </div>
            <Button
                variant='primary'
                onClick={(e) => handler(id, password)}>로그인</Button>
        </div>
    );
}

export default EventPage;
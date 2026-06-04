import { useState } from 'react'
import api from "../../api/axios"

const SignUp = () => {
    // script
    const [name, setName] = useState('');
    const [gender, setGender] = useState('남자');

    const nameHandler = (e) => {
        setName(e.target.value);
    }
    const genderHandler = (e) => {
        setGender(e.target.value);
    }
    const buttonHander = async (e) => {
        // event bubbling : 하위 컴포넌트의 이벤트가 상위 컴포넌트의 이벤트에도 영향을 미침
        e.preventDefault();
        console.log(`debug >>>> input data(name, gender) : ${name}, ${gender}`);
        // axiods 이용해서 데이터 통신
        // post : 데이터 입력, get : 데이터를 가져올 때, put : 데이터 수정, delete : 데이터 삭제
        const data = {
            name,
            gender
        }
        await api.post('signUp' , data)
            .then(response => {
                console.log('debug >>>> post response');
                console.log(response)
            })
            .catch(err => {
                console.log(err);
            })
    }
    // ui
    return (
        <form id='form'>
            <label>
                이름 :
                <input type="text"
                    value={name}
                    onChange={(e) => nameHandler(e)} />
            </label>
            <br />
            <label>
                성별 :
                <select value={gender}
                    onChange={(e) => genderHandler(e)}>
                    <option value='남자'>남자</option>
                    <option value='여자'>여자</option>
                </select>
            </label>
            <br />
            <button type="submit" onClick={buttonHander}>회원가입</button>
        </form>
    );
}

export default SignUp;
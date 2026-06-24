import styled from "styled-components"
import { Link } from "react-router-dom";
import api from "../../../api/axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";


const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f2f2f2;
`;

const FormWrapper = styled.div`
  background-color: white;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0px 8px 16px rgba(0,0,0,0.1);
  width: 400px;
`;

const Title = styled.h2`
  text-align: center;
  margin-bottom: 20px;
  color: #333;
`;

const Input = styled.input`
  width: 93%;
  padding: 12px;
  margin-bottom: 15px;
  border-radius: 6px;
  border: 1px solid #ccc;
  font-size: 16px;

  &:focus {
    outline: none;
    border-color: #007bff;
    box-shadow: 0 0 5px rgba(0,123,255,0.3);
  }
`;

const Button = styled.button`
  width: 100%;
  padding: 12px;
  background-color: #007bff;
  color: white;
  border: none;
  font-size: 16px;
  border-radius: 6px;
  cursor: pointer;
  margin-top: 10px;

  &:hover {
    background-color: #0056b3;
  }

  &:disabled {
    background-color: #aaa;
    cursor: not-allowed;
  }
`;

const TextLink = styled(Link)`
  display: block;
  text-align: center;
  margin-top: 15px;
  font-size: 14px;
  color: #007bff;
  text-decoration: none;
  cursor: pointer;

  &:hover {
    text-decoration: underline;
  }
`;

const SignUpPage = () => {
    const [form, setForm] = useState({
        name: '',
        email: '',
        password: ''
    });

    const changeHandler = (e) => {
        //기존값을 유지하면서 현재 입력된 필드에 대한 상태변화를 처리
        //e.target.name, e.target.value
        const {name, value} = e.target;
        setForm({ ...form, [name]: value });
    };

    const submitHandler = async (e) => {
        e.preventDefault();
        const data = {
            name : form.name,
            email : form.email,
            password : form.password
        }
        /*
        Quiz
        - 통신을 통해서 json-server 데이터 입력
        - 통신이 성공했을 때 화면을 SignInPage 이동
        - SignInPage는 기존 SignUpPage에서 이름 입력부분을 제외하면 되고
        - 가입된 계정으로 로그인 시도했을 때 계정이 존재하면
        - BlogMainPage 이동
        */
        // json-server를 활용한 통신
        // await api.post('/users', data)
        //     .then(response => {
        //         console.log('debug >>>> post response');
        //         console.log(response)
        //         moveUrl('/signin');
        //     })
        //     .catch(err => {
        //         console.log(err);
        //     })
        // spring boot backend 서버를 활용한 통신
        await api.post('/user/signUp', data)
            .then(response => {
                console.log('debug >>>> post response');
                console.log(response)
                moveUrl('/signin');
            })
            .catch(err => {
                console.log(err);
            })
    }
    const moveUrl = useNavigate();

    return (
        <Container>
            <FormWrapper>
                <Title>회원가입</Title>
                <form onSubmit={submitHandler}>
                    <Input type='text'
                        name='name'
                        value={form.name}
                        onChange={changeHandler}
                        placeholder="이름을 입력하세요" />
                    <Input type='email'
                        name='email'
                        value={form.email}
                        onChange={changeHandler}
                        placeholder="이메일을 입력하세요" />
                    <Input type='password'
                        name='password'
                        value={form.password}
                        onChange={changeHandler}
                        placeholder="비밀번호를 입력하세요" />
                    <Button type='submit'>가입하기</Button>
                </form>
                <TextLink to='/signin'>이미 회원이시면 로그인하러 가기</TextLink>
            </FormWrapper>
        </Container>
    )
}

export default SignUpPage;
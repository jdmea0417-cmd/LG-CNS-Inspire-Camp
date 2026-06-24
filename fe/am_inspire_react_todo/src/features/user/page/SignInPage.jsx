import styled from "styled-components"
import { Link, useNavigate } from "react-router-dom";
import api from "../../../api/axios";
import { useState } from "react";


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

const ErrorMessage = styled.div`
  color: #dc3545;
  text-align: center;
  margin-bottom: 15px;
  font-size: 14px;
`;


const SignInPage = () => {

    const [form, setForm] = useState({
        email: '',
        password: ''
    });
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const changeHandler = (e) => {
        const {name, value} = e.target;
        setForm({ ...form, [name]: value });
    }

    /*
    CRUD -> insert, read, update, delete
    axios
    - get(), post(), put() | patch(), delete()
    
    QueryString(url 뒤에 직접 데이터 바인딩 ?key=value&key=value)
    api.get('users?email={}&password=${}')
    권장방식)
    api.get('url, {
        params : {
            email : xxxxx,
            password : xxxxx
        }
    })
    DB : SQL

    */

    const submitHandler = async (e) => {
        e.preventDefault();
        setError('');
        setLoading(true);

        // try {
        //     const response = await api.get('/users', {
        //         params: {
        //             email: form.email,
        //             password: form.password
        //         }
        //     });

        //     // 매칭된 사용자 존재 여부 확인
        //     if (response.data.length > 0) {
        //         // 토큰에 실제 이메일 저장
        //         localStorage.setItem("token", form.email);
        //         moveUrl('/blog/index');
        //     } else {
        //         setError('이메일 또는 비밀번호가 올바르지 않습니다.');
        //     }   
        // }
        
        // json-server를 활용한 통신
        // await api.get(`users?email=${form.email}&password=${form.password}`)
        // .then(response => {
        //     if(response.status === 200) {
        //         // 인증된 사용자 정보(token - session)를 유지할 수 있어야 함.
        //         // 현재 기준으로 sessionStorage, localStorage 사용하여
        //         // 인증된 사용자 정보를 저장하고 공유할 수 있다.
        //         // token - Authorization(인증)
        //         // response.headers.get('Authorization')
        //         // 이러한 인증정보를 요청시마다 헤더에 포함해서 전달을 해야 또다시 인증을 요청하지 않는다.
        //         const user = response.data[0];
        //         localStorage.setItem("token", user.email);
        //         moveUrl('/blog/index')
        //     }
        // })
        // .catch (err => {
        //     console.log('로그인 오류:', err);
        //     setError('로그인 중 오류가 발생했습니다.');
        // })

        const data = {
          email : form.email,
          password : form.password
        }
        await api.post(`user/signIn`, data)
        .then(response => {
          console.log('debug >>>> axios request success');
          console.log(response);
            if(response.status === 200) {
                // 인증된 사용자 정보(token - session)를 유지할 수 있어야 함.
                // 현재 기준으로 sessionStorage, localStorage 사용하여
                // 인증된 사용자 정보를 저장하고 공유할 수 있다.
                // token - Authorization(인증)
                // response.headers.get('Authorization')
                // 이러한 인증정보를 요청시마다 헤더에 포함해서 전달을 해야 또다시 인증을 요청하지 않는다.
              const user = response.headers.get('Authorization');
              user = response.data;
              localStorage.setItem("token", user.email);

              //moveUrl('/blog/index');
            }
        })
        .catch (err => {
            console.log('로그인 오류:', err);
            setError('로그인 중 오류가 발생했습니다.');
        })
    }

    const moveUrl = useNavigate();

    return (
        <Container>
            <FormWrapper>
                <Title>로그인</Title>
                {error && <ErrorMessage>{error}</ErrorMessage>}
                <form onSubmit={submitHandler}>
                    <Input type='email'
                        name='email'
                        value={form.email}
                        onChange={changeHandler}
                        placeholder="이메일을 입력하세요"
                        disabled={loading} />
                    <Input type='password'
                        name='password'
                        value={form.password}
                        onChange={changeHandler}
                        placeholder="비밀번호를 입력하세요"
                        disabled={loading} />
                    <Button type='submit' disabled={loading}>
                        {loading ? '로그인 중...' : '로그인'}
                    </Button>
                </form>
                <TextLink to='/'>회원이 아니라면 가입하러 가기</TextLink>
            </FormWrapper>
        </Container>
    )
}

export default SignInPage;
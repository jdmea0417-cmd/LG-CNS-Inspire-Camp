import styled from "styled-components";
import Button from "../../../components/styled/Button";
import { useNavigate } from "react-router-dom";
import BlogList from "../list/BlogList";
import api from "../../../api/axios";
import { useEffect, useState } from "react";

const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
`;

const Container = styled.div`
    width: 100%;
    max-width: 720px;

    & > * {
        :not(:last-child) {
            margin-bottom: 16px;
        }
    }
`;

const WelcomeMessage = styled.div`
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 16px;
    color: #333;
`;

const LogoutButton = styled(Button)`
    background-color: #f44336;
    color: white;

    &:hover {
        background-color: #d32f2f;
    }
`;

// 인증, 인가

const BlogMainPage = () => {
    console.log(`debug >>>> BlogMainPage mount`)
    const [ary, setAry] = useState([]);
    const loadData = async () => {
        // json-server와 통신(get)을 통해서 응답된 데이터를 ary 변수에 바인딩하는 구현
        await api.get('/blogs')
            .then(response => {
                console.log(response);
                setAry(response.data);
            })
            .catch(err => {
                console.log(`debug >>>> axios request error ${err}`);
            })
    }
    useEffect(() => {
        loadData();
    }, []);
    
    // localStorage 를 이용하여 사용자의 인증 정보를 심고, 공유할 수 있다.
    const token = localStorage.getItem('token');
    const moveUrl = useNavigate();
    console.log(ary);

    const logoutHandler = (e) => {
        console.log(`debug >>>> BlogMainPage logout button click`);
        localStorage.removeItem('token');
        moveUrl('/')
    }   
    return (
        <Wrapper>
            <Container>
                {token && <WelcomeMessage>{token} 님 환영합니다.</WelcomeMessage>}
                <Button title='글 작성하기'
                    onClick={() => { moveUrl('/blog/write'); }} />
                &nbsp;&nbsp;&nbsp;
                <Button title='로그아웃'
                    onClick={logoutHandler} />
                <BlogList blogs={ary} />
            </Container>
        </Wrapper>
    )
}

export default BlogMainPage;
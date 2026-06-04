import styled from "styled-components";
import TextInput from "../../../components/styled/TextInput";
import Button from "../../../components/styled/Button";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import api from "../../../api/axios";

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

const BlogWritePage = () => {

    //state
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const email = localStorage.getItem('token');

    //hook
    const moveUrl = useNavigate();
    //handler
    const saveHandler = async () => {
        //이벤트 발생 시 json-server에 (title,content,email)정보를 저장
        //정상적으로 입력완료된 status === 201 이면 페이지 전환(blog/index)
        //만약, status 201 아니면 UI 부분에 메시지를 전달할 수 있는 태그를 만들어서 에러메시지 출력
        console.log(`debug >>>> save button click -> data : ${title}, ${content}, ${email}`);
        await api.post('/blogs', {title, content, email})
            .then( response => {
                console.log(`debug >>>> post response`)
                if( response.status === 201)
                    moveUrl('/blog/index');
            })
            .catch( err => {
                console.log(`debug >>>> post err`, err)
            })
    }
    return(
        <Wrapper>
            <Container>
                {email && <WelcomeMessage>{email} 님 환영합니다.</WelcomeMessage>}
                <TextInput height={20}
                    value={title}
                    changeHandler={ (e) => {
                        setTitle(e.target.value);
                    }}/>
                <TextInput height={280}
                    value={content}
                    changeHandler={ (e) => {
                        setContent(e.target.value);
                    }}/>
                <Button title="글 작성하기"
                    onClick={saveHandler}/>
                    &nbsp;&nbsp;&nbsp;
                <Button title="이전"
                    onClick={ () => {moveUrl('/blog/index')}}/>
            </Container>
        </Wrapper>
    )
}

export default BlogWritePage;
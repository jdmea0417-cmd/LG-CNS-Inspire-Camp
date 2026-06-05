import styled from "styled-components";
import Button from "../../../components/styled/Button";
import TextInput from "../../../components/styled/TextInput";
import { useEffect, useState } from "react";
import api from "../../../api/axios";

const Wrapper = styled.div`
    width: calc(100% - 32px);
    padding: 8px 16px;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: center;
    border: 1px solid grey;
    border-radius: 8px;
    cursor: pointer;
    background: white;
    :hover {
        background: lightgrey;
    }
`;

const ContentText = styled.p`
    font-size: 16px;
    white-space: pre-wrap;
`;

const BlogCommentItem = ({ comments, onClick, updateClick }) => {
    console.log(`debug >>>> blog comment item page load`);
    const email = localStorage.getItem('token');
    console.log(`debug >>>> token === comment , ${email} === ${comments.email}`)

    const [mention, setMention] = useState(comments.comment)
    const [isEdit, setIsEdit] = useState(false);
    // useEffect(() => {
    //     setMention(comments.comment)
    // },[]);

    const updateHandler = async (e) => {
        if (!isEdit) {
            //수정모드 ON
            setIsEdit(true);
        } else {
            //수정완료모드
            /*
            - axios put()
            update comments
            set comment = ?
            where id =1;
            - Quiz
            - 선택된 특정 댓글의 기본키값을 가지고 사용자가 입력한 값으로 수정하는 구현
            - 댓글 목록만 re-rendering 하도록 구현
            - 당연히 json-server 쪽에서도 수정이 되어야 함
            */
            // await api.put(`comments/${comments.id}`, { comment: mention })
            //     .then(response => {
            //         console.log(`debug >>>> comment response`);
            //         console.log(response)
            //         if (response.status === 200 || response.status === 204) {
            //             const editComment = response.data;
            //         }
            //     })
            //     .catch(err => {
            //         console.log(err);
            //     })
            updateClick(comments.id, mention);
            setIsEdit(false);
        }
    }

    return (
        <Wrapper>
            <TextInput height={8}
                value={mention}
                changeHandler={(e) => setMention(e.target.value)}
                disabled={!isEdit} />

            {email === comments.email &&
                <div>
                    <Button title='삭제'
                        onClick={() => onClick(comments.id)}></Button>
                    &nbsp;&nbsp;&nbsp;
                    <Button title={isEdit ? '수정완료' : '수정'}
                        onClick={(e) => updateHandler(e)}></Button>
                </div>
            }
        </Wrapper>
    )
}

export default BlogCommentItem;
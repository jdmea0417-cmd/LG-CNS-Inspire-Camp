import styled from "styled-components";
import Button from "../../../components/styled/Button";

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

const BlogCommentItem = ({comments}) => {
    console.log(comments)
    return (
        <Wrapper>
            <ContentText>{comments.comment}</ContentText>
            <Button title='삭제'></Button>
        </Wrapper>
    )
}

export default BlogCommentItem;
import styled from "styled-components";
import BlogCommentItem from "../item/BlogCommentItem";

const Wrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: center;
    margin-top: 16px;
    & > * {
        :not(:last-child) {
            margin-bottom: 16px;
        }
    }
`;

const BlogCommentList = ({comments}) => {
    return (
        <Wrapper>
            {
                comments.map((comment, index) => {
                    return <BlogCommentItem key={index} comments={comment}/>
                })
            }
        </Wrapper>
    );
}

export default BlogCommentList;
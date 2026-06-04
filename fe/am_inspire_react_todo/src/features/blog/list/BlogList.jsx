import styled from "styled-components";
import BlogItem from "../item/BlogItem";

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

const BlogList = ({blogs}) =>
{
    return(
        <Wrapper>
            {/* 
            - props로 전단된 배열의 길이만큼 BlogItem 객체를 생성해서 데이터를 바인딩
            - map()
            - BlogItem props로 반복구문을 이용해서 추출한 객체를 다시 props 전달
            */}
            {
                blogs.map((blog, index) => {
                    console.log(`debug >>>> BlogList called`)
                    return <BlogItem key={index} 
                        blog={blog}/>
                })
            }
        </Wrapper>
    )
}

export default BlogList;
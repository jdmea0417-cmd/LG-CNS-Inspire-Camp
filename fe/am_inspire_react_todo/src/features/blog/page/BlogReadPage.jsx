import { useNavigate, useParams } from "react-router-dom";
import styled, { keyframes } from "styled-components";
import Button from "../../../components/styled/Button";
import api from "../../../api/axios";
import { useEffect, useState } from "react";
import BlogCommentList from "../list/BlogCommentList";
import TextInput from "../../../components/styled/TextInput";

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

const PostContainer = styled.div`
    padding: 8px 16px;
    border: 1px solid grey;
    border-radius: 8px;
`;

const TitleText = styled.p`
    font-size: 28px;
    font-weight: 500;
`;

const ContentText = styled.p`
    font-size: 20px;
    line-height: 32px;
    white-space: pre-wrap;
`;

const CommentLabel = styled.p`
    font-size: 16px;
    font-weight: 500;
`;

const spin = keyframes`
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
`;

const Spinner = styled.div`
  border: 6px solid #f3f3f3;
  border-top: 6px solid #3498db;
  border-radius: 50%;
  width: 48px;
  height: 48px;
  animation: ${spin} 1s linear infinite;
  margin: 100px auto;
`;

const WelcomeMessage = styled.div`
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 16px;
    color: #333;
`;


const BlogReadPage = () => {
    // url 뒤에 붙어서 넘어오는 QueryString, PathVariable 값을 받기위해서는 useParams()
    const { id } = useParams();
    console.log(`debug >>>> BlogReadPage mount params value : ${id}`)
    const email = localStorage.getItem('token')

    //hook
    const moveUrl = useNavigate();

    //state
    // params 해당하는 블로그
    const [blog, setBlog] = useState({});
    // 특정 블로그에 달린 댓글 목록
    const [comments, setComments] = useState([]);
    // 댓글 입력 문자열
    const [comment, setComment] = useState('');


    //handler
    const commentHandler = async (e) => {
        // comment, token value(email), blog primary key(id)
        // axios post
        // status === 201
        console.log(`debug >>>> comment button click`);
        let blogId = blog.id;
        // json server version
        // await api.post('/comments', { comment, email, blogId })
        //     .then(response => {
        //         console.log(`debug >>>> comment response`);
        //         console.log(response)
        //         // 부분 리렌더링을 위한 작업(배열)
        //         // if(response.status === 201){
        //         //     const newComments = response.data[response.data.length - 1];
        //         //     setComments( ary => {
        //         //         return[...ary, newComments]
        //         //     })
        //         //     setComment('');
        //         // }
        //         //부분 리렌더링을 위한 작업(객체)
        //         if (response.status === 201) {
        //             const newComment = response.data;
        //             setComments((ary) => {
        //                 return [...ary, newComment];
        //             })
        //             //setComments(response.data);
        //             setComment('');
        //         }
        //     })
        //     .catch(err => {
        //         console.log(err);
        //     })

        // 
        await api.post('/blog/comment/write', { comment, email, blogId })
            .then(response => {
                console.log(`debug >>>> comment response`);
                console.log(response)
                // 부분 리렌더링을 위한 작업(배열)
                // if(response.status === 201){
                //     const newComments = response.data[response.data.length - 1];
                //     setComments( ary => {
                //         return[...ary, newComments]
                //     })
                //     setComment('');
                // }
                //부분 리렌더링을 위한 작업(객체)
                if (response.status === 201) {
                    const newComment = response.data;
                    setComments((ary) => {
                        return [...ary, newComment];
                    })
                    //setComments(response.data);
                    setComment('');
                }
            })
            .catch(err => {
                console.log(err);
            })
    }
    const commentDeleteHandler = async (id) => {
        console.log(`debug >>>> comment delete button click`);
        console.log(`debug >>>> comment key : ${id}`);

        // 댓글 삭제 후 해당 댓글만 삭제하는 re-rendering 작업 구현
        /*
        - 전달받은 식별값을 이용해서 해당 댓글을 삭제할 예정
        - axios - delete() :  status 204
        - 프론트쪽에서는 어떤 작업을 수행해야 하는지?
        - 댓글 UI re-rendering
        */

        // json-server version
        // await api.delete(`comments/${id}`)
        //     .then(response => {
        //         console.log(`debug >>>> axios request success`);
        //         console.log(response)
        //         if (response.status === 200) {
        //             setComments(comments.filter((c) => c.id !== id));
        //         }

        //     })
        //     .catch(err => {
        //         console.log(err)
        //     })

        await api.delete(`blog/comment/delete/${id}`)
            .then(response => {
                console.log(`debug >>>> axios request success`);
                console.log(response)
                if (response.status === 200) {
                    setComments(comments.filter((c) => c.id !== id));
                }

            })
            .catch(err => {
                console.log(err)
            })
    }

    const commentUpdateHandler = async (id, comment) => {
        console.log('debug >>>> comment update button click')
        console.log(`debug >>>> comment update data : ${id}, ${comment}`)
        /*
        -axios put()
        comment update
        update comments
        set comment =?
        where id = 1;
        */

        // json-server version
        // await api.patch(`comments/${id}`, {
        //     comment
        // })
        //     .then(response => {
        //         console.log(`debug >>>> axios request success`);
        //         console.log(response)
        //         if (response.status === 200) {
        //             setComments( ary => {
        //                 return ary.map( comment => {
        //                     return comment.id === id ? {...comment, comment: comment} : comment
        //                 })
        //             })
        //         }
        //     })
        //     .catch(err => {
        //         console.log(err)
        //     })

        //
        await api.patch(`/blog/comment/patch`, {
            comment
        })
            .then(response => {
                console.log(`debug >>>> axios request success`);
                console.log(response)
                if (response.status === 200) {
                    setComments( ary => {
                        return ary.map( comment => {
                            return comment.id === id ? {...comment, comment: comment} : comment
                        })
                    })
                }
            })
            .catch(err => {
                console.log(err)
            })
    }

    const getBlog = async () => {
        /*
        - axios get() 파라미터에 해당하는 데이터를 로드하고
        - sql : select * from table where id = ?
        api.get('blogs?id={id}')
        api.get('blogs' , {
            params : {
                id : id    
            }
        })
        실무권장방식인 pathvariable 방식을 사용해서
        api.get('blogs/${id}/${}/${}')
        */

        //댓글이 없는 상황
        //await api.get(`blogs/${id}`)
        //1:N 관계가 있을 때
        //embed 이용해서 특정블로그의 comments 함께 가져올 수 있음
        //Json-server version
        // await api.get(`blogs/${id}?_embed=comments`)
        //     .then(response => {
        //         console.log(`debug >>>> blog response`);
        //         console.log(response.data)
        //         setBlog({
        //             id: response.data.id,
        //             title: response.data.title,
        //             content: response.data.content,
        //             email: response.data.email
        //         })
        //         setComments(response.data.comments);
        //     })
        //     .catch(err => {
        //         console.log(err);
        //     })

        // spring-version
        await api.get(`blog/read/${id}`)
            .then(response => {
                console.log(`debug >>>> blog response`);
                console.log(response.data)
                setBlog({
                    id: response.data.id,
                    title: response.data.title,
                    content: response.data.content,
                    email: response.data.email
                })
                setComments(response.data.comments);
            })
            .catch(err => {
                console.log(err);
            })
    }

    useEffect(() => {
        getBlog();
    }, [id])

    return (
        <Wrapper>
            {!blog.id && <Spinner />}
            {blog.id &&
                <Container>
                    {email && <WelcomeMessage>{email} 님 환영합니다.</WelcomeMessage>}
                    <Button title='메인페이지'
                        onClick={() => {
                            moveUrl(('/blog/index'));
                        }} />
                    <PostContainer>
                        <TitleText>{blog.title}</TitleText>
                        <ContentText>{blog.content}</ContentText>
                    </PostContainer>
                    {/* 블로그 댓글 설계 */}
                    <CommentLabel>작성된 댓글 목록</CommentLabel>
                    <BlogCommentList comments={comments || []}
                        onClick={commentDeleteHandler}
                        updateClick={commentUpdateHandler} />

                    {/* 블로그 댓글 작성 */}
                    <TextInput height={24}
                        value={comment}
                        changeHandler={(e) => {
                            setComment(e.target.value);
                        }} />
                    <Button title='댓글 작성'
                        onClick={commentHandler} />
                </Container>
            }
        </Wrapper>
    )
}
export default BlogReadPage;
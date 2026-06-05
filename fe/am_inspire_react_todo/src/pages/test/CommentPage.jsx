
import Comment from '../../components/UI/Comment';
const CommentPage = () => {
    const comments = [
        {
            writer: "섭섭해",
            comment: "임섭순강사님과 함께하는 재미있는 리액트"
        },
        {
            writer: "손주희",
            comment: "임섭순강사님과 함께하는 재미없는 리액트"
        },
        {
            writer: "양성민",
            comment: "임섭순강사님과 함께하는 즐거운 리액트"
        },
        {
            writer: "최정민",
            comment: "임섭순강사님과 함께하는 즐겁지아니한 리액트"
        }

    ];
    /* script : {} */
    return(
        <div>
            {
                comments.map( (comment, idx) => {
                    return <Comment key={idx}
                                    data={comment}/>
                })
            }
        </div>
    );
}

export default CommentPage;
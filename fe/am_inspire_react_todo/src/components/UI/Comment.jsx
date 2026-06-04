
import '../../styles/comment.css';
import commentImage from '../../img/images.png';

const Comment = ({data}) => {
    return (
        <div className='wrapper'>
            <div>
                <img src={commentImage}
                    className='image'/>
            </div>
            <div>
                <span>{data.writer}</span><p/>
                <span>{data.comment}</span>
            </div>
        </div>
    )

};

export default Comment ;
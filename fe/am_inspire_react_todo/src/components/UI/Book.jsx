
import '../../styles/comment.css';
import commentImage from '../../img/images.png';

const Book = ({bookName, price}) => {
    return (
        <div className='wrapper'>
            <div>
                <img src={commentImage}
                    className='image'/>
            </div>
            <div>
                <span>책 이름 : {bookName}</span><p/>
                <span>{price}</span>
            </div>
        </div>
    )
}

export default Book;
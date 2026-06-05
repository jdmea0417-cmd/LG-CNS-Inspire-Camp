
import Book from '../../components/UI/Book';

const LibraryPage = () => {

    // script + html
    // script
    const books = [
        { category: 'it', bookName: 'java', price: '10000원' },
        { category: 'it', bookName: 'python', price: '10000원' },
        { category: 'lang', bookName: 'veit', price: '10000원' },
        { category: 'lang', bookName: 'eng', price: '10000원' },
        { category: 'essay', bookName: 'xxxx', price: '10000원' },
        { category: 'essay', bookName: 'yyyy', price: '10000원' },
    ];

    // html rendering
    // 전달받은 모든 데이터를 Book 컴포넌트에 전달하는게 아니라
    // 필터링(category=lang)을 통해서 화면에 렌더링되도록 구현해 본다면?
    // 스타일은 기존 comment.css 사용해서
    return (
        <div>
            {
                books.filter(book => book.category === 'lang').map((book, idx) => {
                    return <Book key={idx}
                        bookName={book.bookName}
                        price={book.price} />
                })
            }
        </div>
    );
}

export default LibraryPage;
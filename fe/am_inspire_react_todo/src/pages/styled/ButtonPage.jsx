
import Button from '../../components/styled/Button';

const ButtonPage = () => {
    const saveHandler = () => {
        window.alert(`글 작성 페이지로 이동합니다.`);
    };
    const listHandler = () => {
        window.alert(`글 목록페이지로 이동합니다.`);
    };
    return (
        <div>
            <Button title="글 작성하기"
                onClick={(e) => saveHandler()} />
            <Button title="글 목록보기"
                onClick={(e) => listHandler()} />
        </div>
    );
}

export default ButtonPage
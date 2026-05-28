import { useNavigate } from "react-router-dom";
import Button from "../../components/styled/Button"

const MainPage = () => {
    const writeHandler = () => {
        console.log(`debug >>>> WriterPage로 이동합니다.`);
        moveUrl('/write');
    }
    const readHandler = () => {
        console.log(`debug >>>> ReadPage로 이동합니다.`);
        moveUrl('/read');
    }

    // 라우터를 이용한 컴포넌트 트랜지션을 위한 Hook(useNavigate) 필요
    const moveUrl = useNavigate();
    return (
        <div>
            <Button title="작성하기"
                onClick={writeHandler} />
            &nbsp;&nbsp;
            <Button title="목록보기"
                onClick={readHandler} />
        </div>
    )
}

export default MainPage;
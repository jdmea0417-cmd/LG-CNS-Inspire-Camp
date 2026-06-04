import { useContext } from "react"
import ctx from "../../utils/context/context"


const ContextHeader = () => {
    //useContext를 통해 consumer 역할을 할 수 있다.
    const { isMode } = useContext(ctx);

    return(
        <>
            <header style ={{
                backgroundColor : isMode ? 'black' : 'white',
                color : isMode ? 'white' : 'black'
            }}>
                <h1>오늘은 프론트 마지막 수업입니다.</h1>
            </header>
        </>
    )
}
export default ContextHeader ;
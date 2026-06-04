import { useContext } from "react";
import ctx from "../../utils/context/context";

const ContextBody = () => {

    const {isMode, setIsMode} = useContext(ctx);
    return(
        <>
            <header style ={{
                backgroundColor : isMode ? 'black' : 'white',
                color : isMode ? 'white' : 'black'
            }}>
                <h1>오늘도 화이팅입니다.</h1>
            </header>
        </>
    );
}

export default ContextBody
import { useContext } from "react";
import ctx from "../../utils/context/context";

const ContextFooter = () => {
    
    const {isMode, setIsMode} = useContext(ctx);
    const modeHandler = () =>{
        if(isMode == true)
            setIsMode(false)
        else
            setIsMode(true)
    }

    return(
        <>
            <footer>
                <button type='button' onClick={modeHandler}>
                    모드변경
                </button>
            </footer>
        </>
    )
}

export default ContextFooter
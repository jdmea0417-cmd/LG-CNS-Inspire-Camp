import { useState } from "react";
import ContextPage from "./pages/context/ContextPage";
import ctx from "./utils/context/context";


const ContextApp = () => {
    //isMode State 값을 Context API 이용해서 page, body, footer, header 전달해 본다면?
    const [isMode, setIsMode] = useState(false);

    return(
        <ctx.Provider value={ {isMode, setIsMode} }>
            <ContextPage />
        </ctx.Provider>
    );
}

export default ContextApp;
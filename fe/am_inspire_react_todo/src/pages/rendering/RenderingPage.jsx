import LogoutButton from "../../components/rendering/LogoutButton";
import LoginButton from "../../components/rendering/LoginButton";
import Greeting from "../../components/rendering/Greeting";

import {useState} from 'react'


const RenderingPage = () => {
    
    // script
    const [flag, setFlag] = useState(false);

    // template UI
    return (
        <div>
            <Greeting flag = {flag} />
            {
                flag ? 
                <LogoutButton isLogin={setFlag}/> 
                : 
                <LoginButton isLogin={setFlag}/>
            }

        </div>
    );
}

export default RenderingPage;
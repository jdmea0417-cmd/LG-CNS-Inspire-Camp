import GuestGreeting from "../../components/rendering/GuestGreeting";
import UserGreeting from "../../components/rendering/UserGreeting";

const Greeting = (props) => {
    return(
        props.flag ? <UserGreeting /> : <GuestGreeting />
    );
}

export default Greeting;
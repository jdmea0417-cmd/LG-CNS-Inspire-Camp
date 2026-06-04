import Button from "../styled/Button"

const LoginButton = (props) => {

    const loginHandler = (setFlag) => {
        setFlag(true);
    }

    return(
        <Button title="로그인"
            onClick={(e) => loginHandler(props.isLogin)}/>
    )
}

export default LoginButton;
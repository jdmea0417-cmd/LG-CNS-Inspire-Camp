import Button from "../styled/Button"

const LogoutButton = (props) => {

    const logoutHandler = (setFlag) => {
        setFlag(false);
    }

    return(
        <Button title="로그아웃"
            onClick={(e) => logoutHandler(props.isLogin)}/>
    )
}

export default LogoutButton;
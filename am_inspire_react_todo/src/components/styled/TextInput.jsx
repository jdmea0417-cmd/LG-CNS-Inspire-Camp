import styled from "styled-components";

const SstyledTextArea = styled.textarea`
    width: calc(100% - 32px);
    ${(props) =>
        props.height &&
        `
        height: ${props.height}px;
    `}
    padding: 16px;
    font-size: 16px;
    line-height: 20px;
    margin-down : 16px ;
    margin-top : 16px ;
`;

const TextInput = ({ height, value, changeHandler }) => {

    return (
        <SstyledTextArea height={height}
            value={value}
            onChange={changeHandler} />
    );
}

export default TextInput;
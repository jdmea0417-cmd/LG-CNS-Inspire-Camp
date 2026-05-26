
/*
MUI
npm install @mui/material @emotion/react @emotion/styled
*/

import Button from '@mui/material/Button';

const InspireButton = ({onClick, title}) => {
    return (
        <Button onClick={onClick}>{title}</Button>
    );
}

export default InspireButton;
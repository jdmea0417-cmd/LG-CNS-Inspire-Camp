import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';

import api from '../../api/axios'

const HttpPage = () => {
    //script
    const DataHandler = async () => {
        console.log(`debug >>>> axios 이용한 데이터 통신`);
        // axios 객체를 이용한 데이터 통신 get, post, put, delete
        await api.get('blogs')
            .then(response => {
                console.log(`debug axios response`);
                console.log(response);
            })
            .catch( err => {
                console.log(`debug axios error : ${err}`);
            });
    }
    //template UI
    return (
        <div>
            <Button
                variant='primary'
                onClick={DataHandler}>json-server</Button>
        </div>
    );
}

export default HttpPage;
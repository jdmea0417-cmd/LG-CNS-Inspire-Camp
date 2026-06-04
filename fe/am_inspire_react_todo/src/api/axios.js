import axios from "axios";

const server_url = process.env.REACT_APP_BE_ENDPOINT;
console.log(`debug >>>> axios.js .env - ${server_url}`)
console.log('process.env:', process.env);
console.log('REACT_APP_BE_ENDPOINT:', process.env.REACT_APP_BE_ENDPOINT);
const api = axios.create({
    baseURL : server_url,
    headers : {
        "Content-Type" : "application/json"
    }
});

export default api;
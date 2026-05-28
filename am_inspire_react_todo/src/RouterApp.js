import { BrowserRouter, Route, Routes } from "react-router-dom";
import MainPage from "./pages/router/MainPage";
import ReadPage from "./pages/router/ReadPage";
import WritePage from "./pages/router/WritePage";

const RouterApp =() => {
    return(
        <BrowserRouter>
            <Routes>
                <Route path='/' element={ <MainPage /> }/>
                <Route path='/read' element={ <ReadPage /> }/>
                <Route path='/write' element={ <WritePage /> }/>
            </Routes>
        </BrowserRouter>
    );
}

export default RouterApp;
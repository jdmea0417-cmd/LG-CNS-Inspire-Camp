import { BrowserRouter, Route, Routes } from "react-router-dom";
import SignUpPage from "./features/user/page/SignUpPage";
import SignInPage from "./features/user/page/SignInPage";
import BlogMainPage from "./features/blog/page/BlogMainPage";
import BlogWritePage from "./features/blog/page/BlogWritePage";

const ToyApp =() => {
    return(
        <BrowserRouter>
            <Routes>
                <Route path='/' element={ <SignUpPage /> }/>
                <Route path='/signin' element={ <SignInPage /> }/>
                <Route path='/blog/index' element={ <BlogMainPage /> }/>
                <Route path='/blog/write' element={ <BlogWritePage />}/>
            </Routes>
        </BrowserRouter>
    );
}

export default ToyApp;
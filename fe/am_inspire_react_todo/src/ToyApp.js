import { BrowserRouter, Route, Routes } from "react-router-dom";
import SignUpPage from "./features/user/page/SignUpPage";
import SignInPage from "./features/user/page/SignInPage";
import BlogMainPage from "./features/blog/page/BlogMainPage";
import BlogWritePage from "./features/blog/page/BlogWritePage";
import BlogReadPage from "./features/blog/page/BlogReadPage";

const ToyApp =() => {
    return(
        <BrowserRouter>
            <Routes>
                <Route path='/' element={ <SignUpPage /> }/>
                <Route path='/signin' element={ <SignInPage /> }/>
                <Route path='/blog/index' element={ <BlogMainPage /> }/>
                <Route path='/blog/write' element={ <BlogWritePage />}/>
                <Route path='/blog/read/:id' element={ <BlogReadPage /> }/>
            </Routes>
        </BrowserRouter>
    );
}

export default ToyApp;
import React, { Component } from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import SignIn from "./components/signin/SignIn";
import NewsPage from "./components/newspage/NewsPage";

class App extends Component {
    render() {
        return (
            <div>
                <BrowserRouter>
                    <Routes>
                        <Route exact path="/" element={<SignIn/>} />
                        <Route path="/news" element={<NewsPage/>} />
                    </Routes>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;
//import logo from './logo.svg';
import { Fragment } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import './App.css';

import Main from "./components/main/Main";
import Login from "./components/login/Login";
import Register from "./components/register/Register";
import Done from "./components/done/Done";
import Chat from "./components/chat/Chat";
import List from "./components/list/List";
import Mypage from "./components/mypage/Mypage";
import Detail from "./components/detail/Detail";
import Splash from "./components/splash/Splash";
import Catalog from "./components/catalog/Catalog"
import Compare from "./components/compare/Compare"

function App() {
  return (
    <div id='app'>
      <Fragment>
        <BrowserRouter>
          <Routes>
            <Route path="/main" element={<Main />} />
            <Route path="/login" element={<Login />}></Route>
            <Route path="/register" element={<Register />}></Route>
            <Route path="/done" element={<Done />}></Route>
            <Route path="/chat" element={<Chat />}></Route>
            <Route path="/list" element={<List />}></Route>
            <Route path="/mypage" element={<Mypage />}></Route>
            <Route path="/detail/:id" element={<Detail />}></Route>
            <Route path="/" element={<Splash />}></Route>
            <Route path="/catalog" element={<Catalog />}></Route>
            <Route path="/compare" element={<Compare />}></Route>
          </Routes>
        </BrowserRouter>
      </Fragment>
    </div>
  );
}

export default App;

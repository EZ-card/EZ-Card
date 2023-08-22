import React from "react";
import axios from 'axios'; // axios import

import { Link } from 'react-router-dom';

import profileImage from '../../assets/images/profile.png';
import profileImage2 from '../../assets/images/profile2.png';
import './Nav.css';

// 메뉴 열기 
function openMenuFun() {
    const sectionCL = document.querySelector('.sectionCL');
    const footerCL = document.querySelector('.footer-dark');
    const showMenu = document.querySelector('.openMenu');
    sectionCL.classList.add('showNone');
    footerCL.classList.add('showNone');
    showMenu.classList.add('show');
    // 메뉴 열릴 시 높이 조절
    const appHeight = document.getElementById('app').clientHeight;
    showMenu.style.height = appHeight-"400"+"px";
}
// 상세메뉴 열기 
function openMenuDetailFun() {
    const menuDetail = document.querySelector('.menuDetail'); 
    menuDetail.classList.toggle('clicked');
}
// 메뉴 닫기 
function closeMenuFun() {
    const sectionCL = document.querySelector('.sectionCL');
    const footerCL = document.querySelector('.footer-dark');
    const showMenu = document.querySelector('.openMenu');
    sectionCL.classList.remove('showNone');
    footerCL.classList.remove('showNone');
    showMenu.classList.remove('show');
}

const onClickLoginOut = () => {
    axios.get("/users/logout")
    .then((res) => {
        if(res.status === 200){
            console.log('======================',res.status);
            alert("로그아웃 되었습니다");
            document.location.href = '/';
        }else{
            console.log('======================',res.status);
            alert('로그아웃 실패');
        }
    });
}


const Nav = () => (
        <nav>
            {/* if(!login_state) ? */}
            {/* 로그인 전 */}
            {/* <ul className="loginYet">
                <li>
                    <img src={profileImage2} alt="Profile2"></img>
                </li>
                <li className="loginYet"><p>맞춤 카드 추천</p></li>
                <li className="menuBar">
                    <span onClick={openMenuFun}><ion-icon name="menu-outline"></ion-icon></span>
                </li>
                <li className="openMenu">
                    <div className="sideMenu">
                        <p><strong>EZ:card</strong> <span onClick={closeMenuFun}><ion-icon name="close-outline"></ion-icon></span></p>
                        <ul>
                            <li><Link to="/main">HOME</Link></li>
                            <li><Link to="/chat">EZ:BOT 카드상담</Link></li>
                            <li><Link to="/detail">카드 목록</Link></li>
                            <li><Link to="/login">로그인</Link></li>
                            <Routes>
                                <Route path="/main" element={<Main />} />
                                <Route path="/chat" element={<Chat />} />
                                <Route path="/login" element={<Login />} />
                                <Route path="/detail" element={<Detail />} />
                            </Routes>
                        </ul>
                    </div>
                </li>
            </ul> */}

            {/* : */}

            {/* 로그인 후 */}
            <ul className="loginSuccess">
                <li>
                    <img src={profileImage} alt="Profile"></img>
                </li>
               
                <li><p><strong className="userName">명준</strong>님의 맞춤 카드</p></li>

                <li className="menuBar">
                    <span onClick={openMenuFun}><ion-icon name="menu-outline"></ion-icon></span>
                </li>
                 <li className="openMenu">
                    <div className="sideMenu">
                        <p><strong>EZ:card</strong> <span onClick={closeMenuFun}><ion-icon name="close-outline"></ion-icon></span></p>
                        <ul>
                            <li><Link to="/">HOME</Link></li>
                            <li><Link to="/chat">EZ:BOT 카드상담</Link></li>
                            <li><Link to="/detail">카드 목록</Link></li>
                            <li className="menuDetail">
                                <p>MY PAGE <span onClick={openMenuDetailFun}><ion-icon name="chevron-down-outline"></ion-icon></span></p>
                                <div id="detailDiv1">
                                    <ul>
                                        <li><Link to="/list">찜 목록</Link></li>
                                        <li><Link to="/mypage">회원정보 수정</Link></li>
                                    </ul>
                                </div>
                            </li>
                            <li className="logout" onClick={onClickLoginOut}>로그아웃</li>
                        </ul>
                    </div>
                </li>
            </ul>
        </nav>

)

// if(element.classList.contains('className')){

// }



export default Nav;
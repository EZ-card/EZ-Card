import React, { useEffect, useState } from "react";
import { Route, Routes, Link, useNavigate } from 'react-router-dom';

import profileImage from '../../assets/images/profile.png';
import profileImage2 from '../../assets/images/profile2.png';

import './Nav.css';

import Main from '../../components/main/Main'; // Main 컴포넌트를 가져옴
import Chat from '../../components/chat/Chat'; // Chat 컴포넌트를 가져옴
import Login from '../../components/login/Login'; // Login 컴포넌트를 가져옴
import Catalog from '../../components/catalog/Catalog'; // Catalog 컴포넌트를 가져옴

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
    showMenu.style.height = appHeight-"250"+"px";
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

const Nav = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [nickname, setNickname] = useState('이지카드'); // nickname 상태 추가
    const navigate = useNavigate(); // navigate 함수 생성

    useEffect(() => {
        // 백엔드로부터 로그인 상태와 카드 데이터를 가져오는 함수
        fetch('/home', {
            method: 'GET',
            credentials: 'include' // 이 부분은 세션 정보를 백엔드로 전달하기 위한 설정입니다.
        })
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else if (response.status === 204) {
                    setIsLoggedIn(false);
                }
            })
            .then(data => {
                if (data) {
                    setIsLoggedIn(true);
                    setNickname(data.nickname); // 닉네임 설정
                }
            });
    }, []);

    // 로그아웃 함수
    const handleLogout = () => {
        fetch('/users/logout', {
            method: 'GET',
            credentials: 'include'
        })
            .then(response => {
                if (response.status === 200) {
                    setIsLoggedIn(false);
                    navigate('/'); // navigate 함수로 경로 이동
                    window.location.reload();
                }
            });
    };

    return (
        <nav>
            {isLoggedIn ? (
                <ul className="loginSuccess">
                    <li>
                        <img src={profileImage} alt="Profile"></img>
                        {/*<img src={logoImage} alt="Profile" width={150}></img>*/}
                    </li>

                    <li>
                        <p><span className="userName">{nickname}</span> 님을 위한 <strong className="ezcard"> EZ:card</strong></p>
                    </li>
                    {/*<li><img src={logoImage} alt="Profile" width={150}></img></li>*/}

                    <li className="menuBar">
                        <span onClick={openMenuFun}><ion-icon name="menu-outline"></ion-icon></span>
                    </li>
                    <li className="openMenu">
                        <div className="sideMenu">
                            <p><strong>EZ:card</strong> <span onClick={closeMenuFun}><ion-icon name="close-outline"></ion-icon></span></p>
                            <ul>
                                <li><Link to="/">HOME</Link></li>
                                <li><Link to="/chat">EZ:BOT 카드상담</Link></li>
                                <li><Link to="/catalog">카드 목록</Link></li>
                                <li className="menuDetail">
                                    <p>MY PAGE <span onClick={openMenuDetailFun}><ion-icon name="chevron-down-outline"></ion-icon></span></p>
                                    <div id="detailDiv1">
                                        <ul>
                                            <li><Link to="/list">찜 목록</Link></li>
                                            <li><Link to="/mypage">회원정보 수정</Link></li>
                                        </ul>
                                    </div>
                                </li>
                                <li className="logout" onClick={handleLogout}>로그아웃</li>
                            </ul>
                        </div>
                    </li>
                </ul>
            ) : (
                <ul className="loginYet">
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
                                <li><Link to="/">HOME</Link></li>
                                <li><Link to="/chat">EZ:BOT 카드상담</Link></li>
                                <li><Link to="/catalog">카드 목록</Link></li>
                                <li><Link to="/login">로그인</Link></li>
                                <Routes>
                                    <Route path="/main" element={<Main />} />
                                    <Route path="/chat" element={<Chat />} />
                                    <Route path="/login" element={<Login />} />
                                    <Route path="/catalog" element={<Catalog />} />
                                </Routes>
                            </ul>
                        </div>
                    </li>
                </ul>
            )}
        </nav>
    );
}


export default Nav;
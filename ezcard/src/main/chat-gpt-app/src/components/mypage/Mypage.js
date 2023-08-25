import React, {useEffect, useState} from "react";

import Nav from '../../common/nav/Nav.js'; // Nav 컴포넌트를 불러옴
import Footer from '../../common/footer/Footer.js'; // Footer 컴포넌트를 불러옴

import './Mypage.css';

const Mypage = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [userData, setUserData] = useState({
        email: "",
        password: "",
        nickname: "",
        age: "",
        gender: "",
        job: "",
    });

    useEffect(() => {
        // Fetch user data from the backend when the component mounts
        fetch("/mypage", {
            method: "GET",
            credentials: "include",
        })
            .then((response) => {
                if (response.status === 200) {
                    return response.json();
                } else if (response.status === 401) {
                    setIsLoggedIn(false);
                }
            })
            .then((data) => {
                if (data) {
                    setIsLoggedIn(true);
                    // Update the state with the received user data
                    setUserData(data);
                }
            });
    }, []);

    // Render the component based on the logged-in status and user data
    return (
        <main>
            <Nav />

            <section className="sectionCL">
                <div className="mypageSec">
                    <h2>마이페이지</h2>
                    <form method="GET">
                        <div id="email">
                            <div className="category">이메일</div>
                            <div className="box">{userData.email}</div>
                        </div>
                        <div id="password">
                            <div className="category">비밀번호</div>
                            <div className="box">{userData.password}</div>
                        </div>
                        <div id="nickname">
                            <div className="category">닉네임</div>
                            <div className="box">{userData.nickname}</div>
                        </div>
                        <div id="gender">
                            <div className="category">성별</div>
                            <div className="box">{userData.gender}</div>
                        </div>
                        <div id="job">
                            <div className="category">직업</div>
                            <div className="box">{userData.job}</div>
                        </div>
                        <div id="age">
                            <div className="category">닉네임</div>
                            <div className="box">{userData.age}</div>
                        </div>
                        <button id="editMypage">회원정보 수정하기</button>
                    </form>
                </div>
            </section>

            <Footer />
        </main>
    );
};

export default Mypage;

import React from "react";
import axios from 'axios'; // axios import

import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';
import {useNavigate} from "react-router-dom";

import Nav from '../../common/nav/Nav.js'; // Nav 컴포넌트를 불러옴
import Footer from '../../common/footer/Footer.js'; // Footer 컴포넌트를 불러옴

import './Login.css';

const Login = () => {
    const [inputId, setInputId] = useState('')
    const [inputPw, setInputPw] = useState('')
    const [isEmailValid, setIsEmailValid] = useState(true); // 이메일 형식 검증 상태
    const navigate = useNavigate();

    const handleInputId = (e) => {
        const emailValue = e.target.value;
        setInputId(emailValue);

        // 정규식을 사용하여 이메일 형식 검증
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        setIsEmailValid(emailRegex.test(emailValue));
    }
 
    const handleInputPw = (e) => {
        setInputPw(e.target.value)
    }
 
    const onClickLogin = () => {
         // 이메일 형식이 아니거나 값이 하나라도 없으면 로그인 함수를 호출하지 않음
         if (!isEmailValid || inputId.trim() === '' || inputPw.trim() === '') {
            alert('이메일 또는 비밀번호를 확인해주세요');
            return;
        }else{
            console.log('click login')
            console.log('email : ', inputId)
            console.log('password : ', inputPw)
            axios.post('/users/login', {
                "email" : inputId,
                "password" : inputPw
            })
            .then(res => {
                console.log(res)
                console.log('res.status :: ', res.status)
                if(res.status === 200){
                    console.log('======================',res.status);
                    alert('로그인 성공');
                    navigate('/');
                } else if(res.status  === 204){
                    console.log('======================',res.status);
                    alert('아이디 또는 패스워드를 확인해주세요');
                    navigate('/login');
                }
                // 작업 완료 되면 페이지 이동(새로고침)
                // document.location.href = '/main'
            })
            .catch()
            }
    }
 
    //  useEffect(() => {
    //      axios.get('/user/login')
    //      .then(res => console.log(res))
    //      .catch()
    //  },[])


     return(
        <main>
            <Nav/>
            
            <section className="sectionCL">
                <div className="loginSec">
                    <h2>로그인</h2>
                    <form method="POST" action="서버의url" id="loginForm">
                        <label htmlFor="userId">이메일</label>
                        <input id="userId" type="email" name="userId" placeholder="EZcard@gamil.com" value={inputId} onChange={handleInputId}/> 
                        {!isEmailValid && <p className="error-text">올바른 이메일 형식이 아닙니다.</p>}
                        <label htmlFor="userPassword">비밀번호</label>
                        <input id="userPassword" type="password" name="userPassword" placeholder="Password" value={inputPw} onChange={handleInputPw} />
                        
                        <button type='button' onClick={onClickLogin}>로그인</button>
                    </form>
                </div>
                <div className="loginSec goRg">
                    <Link to="/register">---- 회원가입 ----</Link>
                </div>
            </section>

            <Footer/>
        </main>
     )
    
}

export default Login;
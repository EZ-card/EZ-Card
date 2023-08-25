import React from "react";
import axios from 'axios'; // axios import

import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react'; 

//import { Routes, Route, Link } from 'react-router-dom';
import Nav from '../../common/nav/Nav.js'; // Nav 컴포넌트를 불러옴
import Footer from '../../common/footer/Footer.js'; // Footer 컴포넌트를 불러옴

import './Register.css';


const Register = () => {

    const [rsId, setInputId] = useState('');
    const [rsPw, setInputPw] = useState('');
    const [rsNn, setInputNn] = useState('');
    const [selectedGender, setSelectedGender] = useState('');
    const [selectedJob, setSelectedJob] = useState('');
    const [selectedAge, setSelectedAge] = useState('');
    const [isEmailValid, setIsEmailValid] = useState(true);

    const handleRsId = (e) => {
        const emailValue = e.target.value;
        setInputId(emailValue);

        // 정규식을 사용하여 이메일 형식 검증
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        setIsEmailValid(emailRegex.test(emailValue));
    }

    const handleRsPw = (e) => {
        setInputPw(e.target.value)
    }

    const handleRsNn = (e) => {
        setInputNn(e.target.value)
    }

    const onGenderClick = (event) => {
        const selectedValue = event.target.textContent;
        setSelectedGender(selectedValue);
    }

    const onJobClick = (event) => {
        const selectedValue = event.target.textContent;
        setSelectedJob(selectedValue);
    }

    const onAgeClick = (event) => {
        const selectedValue = event.target.textContent;
        setSelectedAge(selectedValue);
    }


    const onClickJoin = () => {
        // 이메일 형식이 아니거나 값이 하나라도 없으면 로그인 함수를 호출하지 않음
        if (!isEmailValid || rsId.trim() === '' || rsPw.trim() === ''|| rsNn.trim() === '' || selectedGender.trim() === '' || selectedJob.trim() === '' || selectedAge.trim() === '') {
           alert('올바르게 모두 입력해주세요');
           return;
       }else{
            // 회원가입 요청 또는 필요한 작업을 수행
            console.log('email : ', rsId);
            console.log('password : ', rsPw);
            console.log('nickname : ', rsNn);
            console.log('gender : ', selectedGender);
            console.log('age : ', selectedAge);
            console.log('job : ', selectedJob);
            axios.post('/users/join', {
                "email": rsId,
                "password": rsPw,
                "nickname": rsNn,
                "gender": selectedGender,
                "age": selectedAge,
                "job": selectedJob
            })
           .then(res => {
               console.log(res)
               console.log('res.status :: ', res.status)
               if(res.status === 200){
                   console.log('======================',res.status);
                   alert('회원가입 성공');
                   document.location.href = '/login';
               } 
               // 작업 완료 되면 페이지 이동(새로고침)+
               // document.location.href = '/main'
           })
           .catch()
           }
   }

   return(
    <main>
        <Nav/>
        
        <section className="sectionCL">
            <div className="registerSec">
                <h2>회원가입</h2>
                <form method="POST" action="서버의url" id="registerForm">
                    <label htmlFor="rsId">이메일</label>
                    <input id="rsId" type="email" name="rsId" placeholder="EZcard@gamil.com" value={rsId} onChange={handleRsId} /> 
                    {!isEmailValid && <p className="error-text">올바른 이메일 형식이 아닙니다.</p>}

                    <label htmlFor="rsPassword">비밀번호</label>
                    <input id="rsPassword" type="password" name="rsPassword" placeholder="Password" value={rsPw} onChange={handleRsPw}/>

                    <label htmlFor="rsNickname">닉네임</label>
                    <input id="rsNickname" type="text" name="rsNickname" placeholder="김개똥" value={rsNn} onChange={handleRsNn}/>

                    <label htmlFor="rsGender">성별</label>
                    <div id="rsGender" className="rsDiv">
                        <ul>
                            <li className={selectedGender === '여성' ? 'on' : ''} onClick={onGenderClick}>여성</li>
                            <li className={selectedGender === '남성' ? 'on' : ''} onClick={onGenderClick}>남성</li>
                        </ul>
                    </div>

                    <label htmlFor="rsJob">직업</label>
                    <div id="rsJob" className="rsDiv">
                        <ul>
                            <li className={selectedJob === '청소년' ? 'on' : ''} onClick={onJobClick}>청소년</li>
                            <li className={selectedJob === '대학생' ? 'on' : ''} onClick={onJobClick}>대학생</li>
                            <li className={selectedJob === '직장인' ? 'on' : ''} onClick={onJobClick}>직장인</li>
                            <li className={selectedJob === '기타' ? 'on' : ''} onClick={onJobClick}>기타</li>
                        </ul>
                    </div>

                    <label htmlFor="rsAge">나이대</label>
                    <div id="rsAge" className="rsDiv">
                        <ul>
                            <li className={selectedAge === '10대' ? 'on' : ''} onClick={onAgeClick}>10대</li>
                            <li className={selectedAge === '20대' ? 'on' : ''} onClick={onAgeClick}>20대</li>
                            <li className={selectedAge === '30대' ? 'on' : ''} onClick={onAgeClick}>30대</li>
                            <li className={selectedAge === '40대' ? 'on' : ''} onClick={onAgeClick}>40대</li>
                            <li className={selectedAge === '50대' ? 'on' : ''} onClick={onAgeClick}>50대</li>
                            <li className={selectedAge === '60대' ? 'on' : ''} onClick={onAgeClick}>60대</li>
                        </ul>
                    </div>
                    
                    <button type='button' onClick={onClickJoin}>완료</button>
                </form>
            </div>
            <div className="loginSec goRg">
            </div>
        </section>

        <Footer />
    </main>
   )
}


export default Register;
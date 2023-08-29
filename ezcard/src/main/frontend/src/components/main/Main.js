import React, { useState, useEffect } from "react";
import { Link } from 'react-router-dom';

import Nav from '../../common/nav/Nav.js'; // Nav 컴포넌트를 불러옴
import Footer from '../../common/footer/Footer.js'; // Footer 컴포넌트를 불러옴

// img
import card_test_img from '../../assets/images/card_test_img.png';
import topCredit1 from '../../assets/images/topCredit1.png';
import topCredit2 from '../../assets/images/topCredit2.png';
import topCredit3 from '../../assets/images/topCredit3.png';
import topCheck1 from '../../assets/images/topCheck1.png';
import topCheck2 from '../../assets/images/topCheck2.png';
import topCheck3 from '../../assets/images/topCheck3.png';
import topWelfare1 from '../../assets/images/topWelfare1.png';
import topWelfare2 from '../../assets/images/topWelfare2.png';
import topWelfare3 from '../../assets/images/topWelfare3.png';
import cardRecMain from '../../assets/images/cardRecMain.png';

// Import Swiper React components
import { Swiper, SwiperSlide } from 'swiper/react';
// import required modules
import { Pagination, Navigation, Autoplay } from 'swiper/modules';

// Import Swiper styles
import 'swiper/css';
import 'swiper/css/pagination';
import 'swiper/css/navigation';
import 'swiper/css/autoplay';

import './Main.css';


// 오늘의 TOP 3
function topList(){
    const topList = document.querySelectorAll('.topList');
    topList.forEach(element => {
        element.classList.remove('topListOn');
    });
}

function topOn(event) {
    const topSortElements = document.querySelectorAll('.topSort');
    topSortElements.forEach(element => {
        element.classList.remove('on');
    });
    event.target.classList.add('on');

    if(event.target.innerText === '신용카드'){
        topList();
        document.querySelector('.topCredit').classList.add('topListOn');
    }else if(event.target.innerText === '체크카드'){
        topList();
        document.querySelector('.topCheck').classList.add('topListOn');
    }else if(event.target.innerText === '복지카드'){
        topList();
        document.querySelector('.topWelfare').classList.add('topListOn');
    }
}
// 오늘의 TOP 3 END

const Main = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [homeCardDtoList, setHomeCardDtoList] = useState([]);
    const [nickname, setNickname] = useState('이지카드'); // nickname 상태 추가

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
                    setHomeCardDtoList(data.homeCardDtoList);
                    setNickname(data.nickname); // 닉네임 설정
                }
            });
    }, []);

    return (
        // Swiper 모듈 등록
        <main>
            <Nav/>
            <section className="sectionCL">
                {/* 맞춤 카드상담 */}
                <div className="cardRecMain">
                    <strong>맞춤 카드 상담, EZ:bot</strong>
                    {/*<div className="mainText">빠르고 쉽게 맞춤 카드 상담받기</div>*/}
                    <Link to="/chat">
                        <div className="chatting">
                            <div className="gptChatMain">
                                <p><span>안녕하세요. Ez:bot이에요!</span></p>
                            </div>
                            <div className="gptChatMain">
                                <p><span>여러분의 이야기를 들려주시면 딱 맞는 카드를 추천해 드릴게요. </span></p>
                            </div>
                            <div className="userChat">
                                <p><span>여기 눌러서 카드 추천 받으러 가기 🔗</span></p>
                            </div>
                        </div>
                    </Link>
                </div>

                <div className="reco">
                    {isLoggedIn ? (<strong>{nickname} 님을 위한 추천 카드</strong>)
                        : (<strong>맞춤 카드 추천</strong>)}
                    <div className="mainText">로그인하고 맞춤 카드 추천을 받아보세요!</div>
                    <div id="mainTop">
                        {isLoggedIn ? (
                            <Swiper
                                modules={[Autoplay, Navigation]}
                                initialSlide={0}
                                autoHeight={false}
                                direction='horizontal'
                                loop={true}
                                navigation={true}
                                effect='slide'
                                spaceBetween={-30}
                                slidesPerView={2}
                                centeredSlides={true}
                                slidesOffsetBefore={0}
                                grabCursor={true}
                                autoplay={{
                                    delay: 3000,
                                    disableOnInteraction: false,
                                }}
                            >
                                {/* 카드 데이터 표시 */}
                                {homeCardDtoList.map((card, index) => (
                                    <SwiperSlide key={index}>
                                        <Link to={`/detail/${card.cardId}`}>
                                            <div className="cardImg cardImg1">
                                                <div><img src={card.cardImage} alt="CardImage" /></div>
                                            </div>
                                            <div className="cardInfo">
                                                <strong>{card.cardName}</strong>
                                                <p>{card.cardBank}</p>
                                                <ul>
                                                    <li><span>1.&nbsp;</span>{card.cardSummary1}</li>
                                                    <li><span>2.&nbsp;</span>{card.cardSummary2}</li>
                                                    <li><span>3.&nbsp;</span>{card.cardSummary3}</li>
                                                </ul>
                                            </div>
                                        </Link>
                                    </SwiperSlide>
                                ))}
                            </Swiper>
                        ) : (
                            // 로그인 상태가 아니면 로그인 메시지 표시
                            <div className="mainLoginYet">
                                <Swiper
                                    initialSlide={0}
                                    autoHeight={false}
                                    direction='horizontal'
                                    loop={true}
                                    navigation={true}
                                    modules={[Pagination, Navigation]}
                                    effect='slide'
                                    spaceBetween={-30}
                                    slidesPerView={2}
                                    centeredSlides={true}
                                    slidesOffsetBefore={0}
                                    grabCursor={true}
                                >
                                    {/* 카드 데이터 표시 */}
                                    {/*-------------------- 맞춤 카드 목록 */}
                                    {/* 카드 1 */}
                                    <SwiperSlide>
                                        <div className="cardImg cardImg1">
                                            <img src={card_test_img}></img>
                                        </div>
                                        <div className="cardInfo">
                                            <strong>KB국민 My WE:SH 카드</strong>
                                            <p>현대카드</p>
                                            <ul>
                                                <li><span>1.&nbsp;</span>아파트관리비, 공과금10%할인</li>
                                                <li><span>2.&nbsp;</span>교통, 통신, 배달앱10%할인</li>
                                                <li><span>3.&nbsp;</span>스트리밍1,500원할인</li>
                                            </ul>
                                        </div>
                                    </SwiperSlide>

                                    {/* 카드 2 */}
                                    <SwiperSlide>
                                        <div className="cardImg cardImg1">
                                            <img src={card_test_img}></img>
                                        </div>
                                        <div className="cardInfo">
                                            <strong>American Exrpess</strong>
                                            <p>현대카드</p>
                                            <ul>
                                                <li><span>1.&nbsp;</span>아파트관리비, 공과금10%할인</li>
                                                <li><span>2.&nbsp;</span>교통, 통신, 배달앱10%할인</li>
                                                <li><span>3.&nbsp;</span>스트리밍1,500원할인</li>
                                            </ul>
                                        </div>
                                    </SwiperSlide>

                                    {/* 카드 3 */}
                                    <SwiperSlide>
                                        <div className="cardImg cardImg1">
                                            <img src={card_test_img}></img>
                                        </div>
                                        <div className="cardInfo">
                                            <strong>American Exrpess</strong>
                                            <p>현대카드</p>
                                            <ul>
                                                <li><span>1.&nbsp;</span>아파트관리비, 공과금10%할인</li>
                                                <li><span>2.&nbsp;</span>교통, 통신, 배달앱10%할인</li>
                                                <li><span>3.&nbsp;</span>스트리밍1,500원할인</li>
                                            </ul>
                                        </div>
                                    </SwiperSlide>

                                    {/* 카드 4 */}
                                    <SwiperSlide>
                                        <div className="cardImg cardImg1">
                                            <img src={card_test_img}></img>
                                        </div>
                                        <div className="cardInfo">
                                            <strong>American Exrpess</strong>
                                            <p>현대카드</p>
                                            <ul>
                                                <li><span>1.&nbsp;</span>아파트관리비, 공과금10%할인</li>
                                                <li><span>2.&nbsp;</span>교통, 통신, 배달앱10%할인</li>
                                                <li><span>3.&nbsp;</span>스트리밍1,500원할인</li>
                                            </ul>
                                        </div>
                                    </SwiperSlide>
                                </Swiper>
                                <div className="nextLogin">
                                    <Link to="/login">로그인 하러가기</Link>
                                </div>
                            </div>
                        )}
                    </div>
                </div>


                {/* 오늘의 TOP 3 */}
                <div id="todayTop">
                    <strong>오늘의 TOP 3</strong>
                    <p><span className="on topSort" onClick={topOn}>신용카드</span> | <span className="topSort" onClick={topOn}>체크카드</span> | <span className="topSort" onClick={topOn}>복지카드</span></p>
                    {/* 신용카드 */}
                    <ul className="topList topListOn topCredit">
                        {/* 카드 1 */}
                        <li>
                            <div className="TDcardImg">
                                <img src={topCredit1}></img>
                            </div>
                            <div className="TDcardInfo">
                                <p className="TDcardName"><span>1.</span>KB국민 My WE:SH 카드</p>
                                <p className="TDcardName2">KB국민카드</p>
                            </div>
                            <div className="TDcardLast">
                                <a href="/detail/2"><ion-icon name="arrow-forward-outline"></ion-icon></a>
                            </div>
                        </li>
                        {/* 카드 2 */}
                        <li>
                            <div className="TDcardImg">
                                <img src={topCredit2}></img>
                            </div>
                            <div className="TDcardInfo">
                                <p className="TDcardName"><span>2.</span>LOCA 365 카드</p>
                                <p className="TDcardName2">롯데카드</p>
                            </div>
                            <div className="TDcardLast">
                                <a href="/detail/6"><ion-icon name="arrow-forward-outline"></ion-icon></a>
                            </div>
                        </li>
                        {/* 카드 3 */}
                        <li>
                            <div className="TDcardImg">
                                <img src={topCredit3}></img>
                            </div>
                            <div className="TDcardInfo">
                                <p className="TDcardName"><span>3.</span>삼성카드 taptap O</p>
                                <p className="TDcardName2">삼성카드</p>
                            </div>
                            <div className="TDcardLast">
                                <a href="/detail/38"><ion-icon name="arrow-forward-outline"></ion-icon></a>
                            </div>
                        </li>
                    </ul>
                    {/* 신용카드 end */}


                    {/* 체크카드 */}
                    <ul className="topList topCheck">
                        {/* 카드 1 */}
                        <li>
                            <div className="TDcardImg">
                                <img src={topCheck1}></img>
                            </div>
                            <div className="TDcardInfo">
                                <p className="TDcardName"><span>1.</span>NU 오하쳌(오늘하루체크)</p>
                                <p className="TDcardName2">우리카드</p>
                            </div>
                            <div className="TDcardLast">
                                <a href="/detail/42"><ion-icon name="arrow-forward-outline"></ion-icon></a>
                            </div>
                        </li>
                        {/* 카드 2 */}
                        <li>
                            <div className="TDcardImg">
                                <img src={topCheck2}></img>
                            </div>
                            <div className="TDcardInfo">
                                <p className="TDcardName"><span>2.</span>노리2 체크카드(KB Pay)</p>
                                <p className="TDcardName2">KB국민카드</p>
                            </div>
                            <div className="TDcardLast">
                                <a href="/detail/20"><ion-icon name="arrow-forward-outline"></ion-icon></a>
                            </div>
                        </li>
                        {/* 카드 3 */}
                        <li>
                            <div className="TDcardImg">
                                <img src={topCheck3}></img>
                            </div>
                            <div className="TDcardInfo">
                                <p className="TDcardName"><span>3.</span>신한카드 Deep Dream 체크</p>
                                <p className="TDcardName2">신한카드</p>
                            </div>
                            <div className="TDcardLast">
                                <a href="/detail/43"><ion-icon name="arrow-forward-outline"></ion-icon></a>
                            </div>
                        </li>
                    </ul>
                    {/* 체크카드 end */}




                    {/* 복지카드 */}
                    <ul className="topList topWelfare">
                        {/* 카드 1 */}
                        <li>
                            <div className="TDcardImg">
                                <img src={topWelfare1}></img>
                            </div>
                            <div className="TDcardInfo">
                                <p className="TDcardName"><span>1.</span>장애인 통합복지 2030</p>
                                <p className="TDcardName2">신한카드 </p>
                            </div>
                            <div className="TDcardLast">
                                <a href="/detail/36"><ion-icon name="arrow-forward-outline"></ion-icon></a>
                            </div>
                        </li>
                        {/* 카드 2 */}
                        <li>
                            <div className="TDcardImg">
                                <img src={topWelfare2}></img>
                            </div>
                            <div className="TDcardInfo">
                                <p className="TDcardName"><span>2.</span>롯데 국민행복카드</p>
                                <p className="TDcardName2">롯데카드</p>
                            </div>
                            <div className="TDcardLast">
                                <a href="/detail/14"><ion-icon name="arrow-forward-outline"></ion-icon></a>
                            </div>
                        </li>
                        {/* 카드 3 */}
                        <li>
                            <div className="TDcardImg">
                                <img src={topWelfare3}></img>
                            </div>
                            <div className="TDcardInfo">
                                <p className="TDcardName"><span>3.</span>문화누리카드</p>
                                <p className="TDcardName2">NH농협카드</p>
                            </div>
                            <div className="TDcardLast">
                                <a href="/detail/37"><ion-icon name="arrow-forward-outline"></ion-icon></a>
                            </div>
                        </li>
                    </ul>
                    {/* 신용카드 end */}
                </div>
            </section>
            <Footer/>
        </main>
    );
};



export default Main;
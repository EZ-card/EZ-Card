import React from "react";
import Nav from '../../common/nav/Nav.js';
import Footer from '../../common/footer/Footer.js';

import './Detail.css';

import detailImg from '../../assets/images/detailImg.png';

import benefit1 from '../../assets/icon/gift-box.png';
import benefit2 from '../../assets/icon/cashless-payment.png'

import food1 from '../../assets/icon/fast-food.png';
import food2 from '../../assets/icon/coffee.png';
import food3 from '../../assets/icon/breads.png';
import food4 from '../../assets/icon/shipped.png';

import online1 from '../../assets/icon/mobile-phone.png';
import online2 from '../../assets/icon/operation.png';
import online3 from '../../assets/icon/subscribe.png';
import online4 from '../../assets/icon/online-shop.png';

import transport1 from '../../assets/icon/public-transport.png';
import transport2 from '../../assets/icon/taxi.png';
import transport3 from '../../assets/icon/train.png';
import transport4 from '../../assets/icon/gas.png';
import transport5 from '../../assets/icon/car.png';
import transport6 from '../../assets/icon/tire.png';

import leisure1 from '../../assets/icon/cinema.png';
import leisure2 from '../../assets/icon/weights.png';
import leisure3 from '../../assets/icon/amusement-park.png';
import leisure4 from '../../assets/icon/golfing.png';
import leisure5 from '../../assets/icon/salon.png';

import travel1 from '../../assets/icon/lounge.png';
import travel2 from '../../assets/icon/online-shopping.png';
import travel3 from '../../assets/icon/real-estate.png';
import travel4 from '../../assets/icon/internet.png';
import travel5 from '../../assets/icon/travel-luggage.png';
import travel6 from '../../assets/icon/hotel.png';

import health1 from '../../assets/icon/healthcare.png';
import health2 from '../../assets/icon/shield.png';

import life1 from '../../assets/icon/laundry-machine.png';
import life2 from '../../assets/icon/taxes.png';
import life3 from '../../assets/icon/store.png';
import life4 from '../../assets/icon/market.png';
import life5 from '../../assets/icon/child.png';
import life6 from '../../assets/icon/notebook.png';
import life7 from '../../assets/icon/all.png';
import life8 from '../../assets/icon/choose.png';
import life9 from '../../assets/icon/money-bag.png';
import life10 from '../../assets/icon/premium.png';
import life11 from '../../assets/icon/rejected.png';
import life12 from '../../assets/icon/discount.png';
import life13 from '../../assets/icon/wealth.png';
import life14 from '../../assets/icon/voucher.png';
import life15 from '../../assets/icon/money.png';
import life16 from '../../assets/icon/welfare.png';
import life17 from '../../assets/icon/budget.png';
import life18 from '../../assets/icon/surprise.png';
import life19 from '../../assets/icon/cashback.png';
import life20 from '../../assets/icon/commission.png';

const Detail = () => (
    <main>
        <Nav/>
        <section id="sectionCL">
            <div className="detailIntro">
                <div className="detailCardImg">
                    <img src={detailImg}></img>
                </div>
                <div className="detailCardInfo">
                    <p className="detailCardName">현대카드 M BOOST</p>
                    <br></br>
                    <ul>
                        <li><img src={benefit1}></img> 월마다<span className="highlight">최대 7만P</span>추첨적립</li>
                        <li><img src={benefit1}></img> 분기마다<span className="highlight">최대 30만P</span>추첨적립</li>
                        <li><img src={benefit2}></img> 온라인 간편결제<span className="highlight">5%</span>할인</li>
                    </ul>
                </div>
            </div>

            {/* 주요혜택 */}
            <p className="mainBenefit">주요혜택</p>

            {/* 외식,푸드 혜택 */}
            <div className="detailBox">
                <img src={food1} className="detailIcon"></img>
                <p className="detailTitle">푸드</p>
                <p className="detail">서브웨이 10% 할인</p>
            </div>

            <div className="detailBox">
                <img src={food2} className="detailIcon"></img>
                <p className="detailTitle">카페</p>
                <p className="detail">스타벅스 50% 할인</p>
            </div>

            <div className="detailBox">
                <img src={food3} className="detailIcon"></img>
                <p className="detailTitle">베이커리</p>
                <p className="detail">파리바게트 10% 할인</p>
            </div>

            <div className="detailBox">
                <img src={food4} className="detailIcon"></img>
                <p className="detailTitle">배달앱</p>
                <p className="detail">배달의 민족 10% 할인</p>
            </div>

            {/* 온라인,모바일 결제 혜택 */}
            <div className="detailBox">
                <img src={online1} className="detailIcon"></img>
                <p className="detailTitle">통신</p>
                <p className="detail">이동통신요금 10% 할인</p>
            </div>

            <div className="detailBox">
                <img src={online2} className="detailIcon"></img>
                <p className="detailTitle">간편결제</p>
                <p className="detail">KB PAY 10% 할인</p>
            </div>

            <div className="detailBox">
                <img src={online3} className="detailIcon"></img>
                <p className="detailTitle">디지털구독</p>
                <p className="detail">OTT 30% 할인</p>
            </div>

            <div className="detailBox">
                <img src={online4} className="detailIcon"></img>
                <p className="detailTitle">온라인쇼핑</p>
                <p className="detail">온라인 10% 할인</p>
            </div>

            {/* 교통,차량 혜택 */}
            <div className="detailBox">
                <img src={transport1} className="detailIcon"></img>
                <p className="detailTitle">교통</p>
                <p className="detail">대중교통 10% 할인</p>
            </div>

            <div className="detailBox">
                <img src={transport2} className="detailIcon"></img>
                <p className="detailTitle">택시</p>
                <p className="detail">택시 10% 할인</p>
            </div>

            <div className="detailBox">
                <img src={transport3} className="detailIcon"></img>
                <p className="detailTitle">기차</p>
                <p className="detail">승차권 10% 할인</p>
            </div>

            <div className="detailBox">
                <img src={transport4} className="detailIcon"></img>
                <p className="detailTitle">주유소</p>
                <p className="detail">리터당 60원 할인</p>
            </div>

            <div className="detailBox">
                <img src={transport5} className="detailIcon"></img>
                <p className="detailTitle">자동차</p>
                <p className="detail">정비소, 주차장 10% 할인</p>
            </div>

            <div className="detailBox">
                <img src={transport6} className="detailIcon"></img>
                <p className="detailTitle">충전소</p>
                <p className="detail">전기차 충전 50% 할인</p>
            </div>

            <div className="detailBox">
                <img src={transport4} className="detailIcon"></img>
                <p className="detailTitle">정비</p>
                <p className="detail">차량정비 10% 할인</p>
            </div>

            {/* 문화,여가 혜택 */}
            <div className="detailBox">
                <img src={leisure1} className="detailIcon"></img>
                <p className="detailTitle">영화</p>
                <p className="detail">롯데시네마 5,000원 할인</p>
            </div>

            <div className="detailBox">
                <img src={leisure2} className="detailIcon"></img>
                <p className="detailTitle">피트니스</p>
                <p className="detail">피트니스 1.3% 할인</p>
            </div>

            <div className="detailBox">
                <img src={leisure3} className="detailIcon"></img>
                <p className="detailTitle">테마파크</p>
                <p className="detail">롯데월드 30% 할인</p>
            </div>

            <div className="detailBox">
                <img src={leisure4} className="detailIcon"></img>
                <p className="detailTitle">골프</p>
                <p className="detail">연습장 타석권 무료 제공</p>
            </div>

            <div className="detailBox">
                <img src={leisure5} className="detailIcon"></img>
                <p className="detailTitle">헤어/뷰티</p>
                <p className="detail">미용실 50% 할인</p>
            </div>

            {/* 여행,숙박 혜택 */}
            <div className="detailBox">
                <img src={travel1} className="detailIcon"></img>
                <p className="detailTitle">공항라운지</p>
                <p className="detail">무료 이용 서비스</p>
            </div>

            <div className="detailBox">
                <img src={travel2} className="detailIcon"></img>
                <p className="detailTitle">면세점</p>
                <p className="detail">이용금액 1.3% 할인</p>
            </div>

            <div className="detailBox">
                <img src={travel3} className="detailIcon"></img>
                <p className="detailTitle">렌탈</p>
                <p className="detail">이용금액 0.8% 할인</p>
            </div>

            <div className="detailBox">
                <img src={travel4} className="detailIcon"></img>
                <p className="detailTitle">해외</p>
                <p className="detail">해외가맹점 5% 할인캐시백</p>
            </div>

            <div className="detailBox">
                <img src={travel5} className="detailIcon"></img>
                <p className="detailTitle">여행/숙박</p>
                <p className="detail">국제브랜드 제공서비스</p>
            </div>

            <div className="detailBox">
                <img src={travel6} className="detailIcon"></img>
                <p className="detailTitle">호텔</p>
                <p className="detail">호텔스닷컴 20% 할인</p>
            </div>

            {/* 의료 관련 혜택 */}
            <div className="detailBox">
                <img src={health1} className="detailIcon"></img>
                <p className="detailTitle">병원</p>
                <p className="detail">병원, 약국 1.3% 할인</p>
            </div>

            <div className="detailBox">
                <img src={health2} className="detailIcon"></img>
                <p className="detailTitle">보험사</p>
                <p className="detail">생명보험 10% 할인</p>
            </div>

            {/* 생활,소비 혜택 */}
            <div className="detailBox">
                <img src={life1} className="detailIcon"></img>
                <p className="detailTitle">생활</p>
                <p className="detail">세탁소 업종 10% 할인</p>
            </div>

            <div className="detailBox">
                <img src={life2} className="detailIcon"></img>
                <p className="detailTitle">공과금</p>
                <p className="detail">아파트 관리비 10% 할인</p>
            </div>

            <div className="detailBox">
                <img src={life3} className="detailIcon"></img>
                <p className="detailTitle">편의점</p>
                <p className="detail">편의점 10% 할인</p>
            </div>

            <div className="detailBox">
                <img src={life4} className="detailIcon"></img>
                <p className="detailTitle">대형마트</p>
                <p className="detail">3대 마트 10% 할인</p>
            </div>

            <div className="detailBox">
                <img src={life5} className="detailIcon"></img>
                <p className="detailTitle">육아</p>
                <p className="detail">이용금액 0.8% 할인</p>
            </div>

            <div className="detailBox">
                <img src={life6} className="detailIcon"></img>
                <p className="detailTitle">학습지</p>
                <p className="detail">학습지 10% 할인</p>
            </div>

            <div className="detailBox">
                <img src={life7} className="detailIcon"></img>
                <p className="detailTitle">모든가맹점</p>
                <p className="detail">이용금액 0.8%~1.3% 할인</p>
            </div>

            <div className="detailBox">
                <img src={life8} className="detailIcon"></img>
                <p className="detailTitle">선택형</p>
                <p className="detail">3가지 혜택 중 택 1</p>
            </div>

            <div className="detailBox">
                <img src={life9} className="detailIcon"></img>
                <p className="detailTitle">무이자</p>
                <p className="detail">가맹점 2개월 무이자 할부</p>
            </div>

            <div className="detailBox">
                <img src={life10} className="detailIcon"></img>
                <p className="detailTitle">프리미엄</p>
                <p className="detail">PLATINUM 등급 서비스</p>
            </div>

            <div className="detailBox">
                <img src={life11} className="detailIcon"></img>
                <p className="detailTitle">무실적</p>
                <p className="detail">가맹점 1% 청구할인</p>
            </div>

            <div className="detailBox">
                <img src={life12} className="detailIcon"></img>
                <p className="detailTitle">할인</p>
                <p className="detail">매월 최대 2만원 청구할인</p>
            </div>

            <div className="detailBox">
                <img src={life13} className="detailIcon"></img>
                <p className="detailTitle">적립</p>
                <p className="detail">매월 5,000 포인트 할인</p>
            </div>

            <div className="detailBox">
                <img src={life14} className="detailIcon"></img>
                <p className="detailTitle">바우처</p>
                <p className="detail">5가지 중 매년 1가지 선택</p>
            </div>

            <div className="detailBox">
                <img src={life15} className="detailIcon"></img>
                <p className="detailTitle">연회비지원</p>
                <p className="detail">연회비 7만원 감면</p>
            </div>

            <div className="detailBox">
                <img src={life16} className="detailIcon"></img>
                <p className="detailTitle">국민행복</p>
                <p className="detail">국가바우처 이용 가능</p>
            </div>

            <div className="detailBox">
                <img src={life17} className="detailIcon"></img>
                <p className="detailTitle">금융</p>
                <p className="detail">Two-in-One 결제 서비스</p>
            </div>

            <div className="detailBox">
                <img src={life18} className="detailIcon"></img>
                <p className="detailTitle">혜택 프로모션</p>
                <p className="detail">월, 분기마다 추첨</p>
            </div>

            <div className="detailBox">
                <img src={life19} className="detailIcon"></img>
                <p className="detailTitle">캐시백</p>
                <p className="detail">전기,통신요금 5% 캐시백</p>
            </div>

            <div className="detailBox">
                <img src={life20} className="detailIcon"></img>
                <p className="detailTitle">수수료우대</p>
                <p className="detail">해외 ATM 수수료 면제</p>
            </div>
        </section>
        <Footer/>
    </main>
)

export default Detail
import React from "react";
import Nav from '../../common/nav/Nav.js';
import Footer from '../../common/footer/Footer.js';

import './Detail.css';

import detailImg from '../../assets/images/detailImg.png';

import benefit1 from '../../assets/icon/gift-box.png';
import benefit2 from '../../assets/icon/cashless-payment.png'

import 푸드 from '../../assets/icon/푸드.png';
import 카페 from '../../assets/icon/카페.png';
import 베이커리 from '../../assets/icon/베이커리.png';
import 배달앱 from '../../assets/icon/배달앱.png';

import 통신 from '../../assets/icon/통신.png';
import 간편결제 from '../../assets/icon/간편결제.png';
import 디지털구독 from '../../assets/icon/디지털구독.png';
import 온라인쇼핑 from '../../assets/icon/온라인쇼핑.png';

import 교통 from '../../assets/icon/교통.png';
import 택시 from '../../assets/icon/택시.png';
import 기차 from '../../assets/icon/기차.png';
import 주유소 from '../../assets/icon/주유소.png';
import 자동차 from '../../assets/icon/자동차.png';
import 정비 from '../../assets/icon/정비.png';
import 충전소 from '../../assets/icon/충전소.png';

import 영화 from '../../assets/icon/영화.png';
import 피트니스 from '../../assets/icon/피트니스.png';
import 테마파크 from '../../assets/icon/테마파크.png';
import 골프 from '../../assets/icon/골프.png';
import 헤어뷰티 from '../../assets/icon/헤어뷰티.png';

import 공항라운지 from '../../assets/icon/공항라운지.png';
import 면세점 from '../../assets/icon/면세점.png';
import 렌탈 from '../../assets/icon/렌탈.png';
import 해외 from '../../assets/icon/해왜.png';
import 여행숙박 from '../../assets/icon/여행숙박.png';
import 호텔 from '../../assets/icon/호텔.png';

import 병원 from '../../assets/icon/병원.png';
import 보험사 from '../../assets/icon/보험사.png';

import 생활 from '../../assets/icon/생활.png';
import 공과금 from '../../assets/icon/공과금.png';
import 편의점 from '../../assets/icon/편의점.png';
import 대형마트 from '../../assets/icon/대형마트.png';
import 육아 from '../../assets/icon/육아.png';
import 학습지 from '../../assets/icon/학습지.png';
import 모든가맹점 from '../../assets/icon/모든가맹점.png';
import 선택형 from '../../assets/icon/선택형.png';
import 무이자 from '../../assets/icon/무이자.png';
import 프리미엄 from '../../assets/icon/프리미엄.png';
import 무실적 from '../../assets/icon/무실적.png';
import 할인 from '../../assets/icon/할인.png';
import 적 from '../../assets/icon/적립.png';
import 바우처 from '../../assets/icon/바우처.png';
import 연회비지원 from '../../assets/icon/연회비지원.png';
import 국민행복 from '../../assets/icon/국민행복.png';
import 금융 from '../../assets/icon/금융.png';
import 혜택프로모션 from '../../assets/icon/혜택 프로모션.png';
import 캐시백 from '../../assets/icon/캐시백.png';
import 수수료우대 from '../../assets/icon/수수료우대.png';

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
        <img src={푸드} className="detailIcon"></img>
        <p className="detailTitle">푸드</p>
        <p className="detail">서브웨이 10% 할인</p>
      </div>

    </section>
    <Footer/>
  </main>
)

export default Detail
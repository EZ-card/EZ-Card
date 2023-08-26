import React ,{useState,useEffect } from "react";
import axios from 'axios'; // axios import

import Nav from '../../common/nav/Nav.js';
import Footer from '../../common/footer/Footer.js';
import { useParams } from "react-router-dom";

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
import 주유 from '../../assets/icon/주유.png';
import 자동차 from '../../assets/icon/자동차.png';
import 정비 from '../../assets/icon/정비.png';
import 충전소 from '../../assets/icon/충전소.png';

import 영화 from '../../assets/icon/영화.png';
import 피트니스 from '../../assets/icon/피트니스.png';
import 테마파크 from '../../assets/icon/테마파크.png';
import 골프 from '../../assets/icon/골프.png';
import 헤어뷰티 from '../../assets/icon/헤어뷰티.png';

import 항공 from '../../assets/icon/항공.png';
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
import 적포 from '../../assets/icon/적립.png';
import 바우처 from '../../assets/icon/바우처.png';
import 연회비지원 from '../../assets/icon/연회비지원.png';
import 국민행복 from '../../assets/icon/국민행복.png';
import 금융 from '../../assets/icon/금융.png';
import 혜택프로모션 from '../../assets/icon/혜택 프로모션.png';
import 캐시백 from '../../assets/icon/캐시백.png';
import 수수료우대 from '../../assets/icon/수수료우대.png';

const Detail = () => {
  const { id } = useParams();
  const [cardData, setCardData] = useState({
    cardDto: {},
    cardBenefitList: []
  });

  const [isHeartActive, setIsHeartActive] = useState(null);
  const [openDetailBox, setOpenDetailBox] = useState(null);

  useEffect(() => {
    const fetchCardData = async () => {
      try {
        const response = await fetch(`/cards/detail/${id}`);
        if (response.status === 200) {
          const data = await response.json();
          setCardData({
            cardDto: data.cardDto,
            cardBenefitList: data.cardBenefitList,
          });
          // Set the isHeartActive state based on the data.wishCardExists
          setIsHeartActive(data.wishCardExists); // 문자열 비교를 하지 않고 데이터 그대로 설정
        } else {
          console.error('Failed to fetch card data');
        }
      } catch (error) {
        console.error('Error fetching card data:', error);
      }
    };
    fetchCardData();
  }, [id]);

  const { cardDto, cardBenefitList } = cardData;


  function toggleHeart() {
    setIsHeartActive((prevIsHeartActive) => !prevIsHeartActive);
    const newWishCardValue = !isHeartActive; // 문자열이 아닌 boolean 값을 사용
    console.log(isHeartActive);
    if (!isHeartActive) { // 문자열 비교를 하지 않고 boolean 값을 사용
      axios.post(`/wish/${cardDto.cardId}`)
          .then(res => {
            console.log(res);
          })
          .catch(error => {
            console.error(error);
          });
    } else {
      axios.delete(`/wish/${cardDto.cardId}`)
          .then(res => {
            console.log(res);
          })
          .catch(error => {
            console.error(error);
          });
    }
  }

  function toggleDetail(index) {
    if (openDetailBox === index) {
      setOpenDetailBox(null);
    } else {
      setOpenDetailBox(index);
    }
  }


  return (
      <main>
        <Nav/>
        <section className="sectionCL">
          <div className="detailIntro">
            <div className="detailCardInfo">
              <div><img src={cardDto.cardImage} alt="detailCardName" /></div>
              <div>
                <strong>{cardDto.cardName}</strong>
                <p className="cardBank">{cardDto.cardBank} / {cardDto.cardType}</p>
                <ul>
                  <li>️▫ {cardDto.cardSummary1}</li>
                  <li>▫ {cardDto.cardSummary2}</li>
                  <li>▫ {cardDto.cardSummary3}</li>
                </ul>
                <p className="cardRecord">
                  <span>{cardDto.cardMembership}</span>
                </p>
              </div>
              <button
                  className={`detailHeartOn ${isHeartActive ? 'active' : ''}`}
                  onClick={toggleHeart}
              >
                <ion-icon name="heart" className="heartIcon"></ion-icon>
              </button>
            </div>
          </div>

          {/* 주요혜택 */}
          <p className="mainBenefit">주요혜택</p>
          <ul>
            {/* 카드 혜택 출력 */}
            {cardBenefitList.map((benefit, index) => (
                <li key={index} className="detailContainer">
                  <div className="detailBox" onClick={() => toggleDetail(index)}>
                    <img src={require(`../../assets/icon/${benefit.benefitCategory}.png`)} className="detailIcon" alt="Detail Icon" />
                    <p className="detailTitle">{benefit.benefitCategory}</p>
                    <p className="detail">{benefit.benefitSummary}</p>
                  </div>
                  {openDetailBox === index && (
                      <div className="additionalInfo">
                        {benefit.benefitDetail}
                        <p>{benefit.additionalInfo}</p>
                      </div>
                  )}
                </li>
            ))}

          </ul>
        </section>
        <Footer/>
      </main>
  )}

export default Detail;
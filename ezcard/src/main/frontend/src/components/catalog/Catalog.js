import React, {useState, useEffect} from 'react';
import {Link} from 'react-router-dom';

import Nav from '../../common/nav/Nav.js';
import Footer from '../../common/footer/Footer.js';
import './Catalog.css';



const Catalog = () => {
  const [cardCatalog, setCardCatalog] = useState([]);
  const [selectedCardType, setSelectedCardType] = useState('전체');
  const [selectedBtn, setSelectedBtn] = useState('전체'); // 새로운 상태 추가

  const filteredCards = cardCatalog.filter(
    (card) => selectedCardType === '전체' || card.cardType === selectedCardType
  );

    useEffect(() => {
        // Fetch card catalog data from the backend
        fetch('/cards/catalog', {
            method: "GET",
            credentials: "include",
        })
            .then(response => response.json())
            .then(data => setCardCatalog(data.cardCatalogDtoList))
            .catch(error => console.error('Error fetching card catalog:', error));
    }, []);

  // 버튼 클릭 이벤트 핸들러
  const handleBtnClick = (cardType) => {
    setSelectedCardType(cardType); // 선택된 카드 타입 업데이트
    setSelectedBtn(cardType); // 선택된 버튼 업데이트
  };

    return (
        <main>
            <Nav />
            <section className="sectionCL">
                <strong>카드 목록</strong>
                <div className="button-container">
                     <button
                        className={`sortBtn ${selectedBtn === '전체' ? 'sortBtnOn' : ''}`}
                        onClick={() => handleBtnClick('전체')}
                      >
                        전체
                      </button>
                      <button
                        className={`sortBtn ${selectedBtn === '신용' ? 'sortBtnOn' : ''}`}
                        onClick={() => handleBtnClick('신용')}
                      >
                        신용
                      </button>
                      <button
                        className={`sortBtn ${selectedBtn === '체크' ? 'sortBtnOn' : ''}`}
                        onClick={() => handleBtnClick('체크')}
                      >
                        체크
                      </button>
                      <button
                        className={`sortBtn ${selectedBtn === '복지' ? 'sortBtnOn' : ''}`}
                        onClick={() => handleBtnClick('복지')}
                      >
                        복지
                      </button>
                </div>
                <div className="catalog-container">
                    {filteredCards.map(card => (
                        <Link key={card.cardId} to={`/detail/${card.cardId}`} className="card-item-link">
                            <div className="detailIntro">
                                <div className="catalogCardInfo">
                                    <div className="catalogImage"><img src={card.cardImage} alt="catalogCardName" /></div>
                                    <div className="catalogDeatil">
                                        <strong>{card.cardName}</strong>
                                        <p className="catalogcardBank">{card.cardBank}</p>
                                        <ul>
                                            <li>️▫ {card.cardSummary1}</li>
                                            <li>▫ {card.cardSummary2}</li>
                                            <li>▫ {card.cardSummary3}</li>
                                        </ul>
                                        <p className="catalogcardRecord">
                                            <span>{card.cardMembership}</span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </Link>
                    ))}
                </div>
            </section>
            <Footer />
        </main>
    );
};

export default Catalog;

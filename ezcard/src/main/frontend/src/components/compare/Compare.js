import React, { useState, useEffect } from 'react';
import Nav from '../../common/nav/Nav.js';
import Footer from '../../common/footer/Footer.js';
import './Compare.css';
import card_test_img from '../../assets/images/card_test_img.png';

const ComparePage = () => {
    const [cardList, setCardList] = useState([]);
    const [selectedFirstCard, setSelectedFirstCard] = useState(null);
    const [selectedSecondCard, setSelectedSecondCard] = useState(null);
    const [isModalOpen, setIsModalOpen] = useState(false);

    // 가상의 카드 데이터 배열
    const dummyCardData = [
        { cardId: 1, cardName: '신한카드 Mr.Life', cardBank: '신한카드', imageUrl: card_test_img },
        { cardId: 2, cardName: 'Card B', cardBank: 'Bank B', imageUrl: card_test_img },
        { cardId: 3, cardName: 'Card C', cardBank: 'Bank C', imageUrl: card_test_img },
        // 추가 카드 정보
    ];

    useEffect(() => {
        setCardList(dummyCardData); // 가상의 카드 데이터로 초기화
    }, []);

    const handleCardSelect = (cardId) => {
        const selectedCard = dummyCardData.find(card => card.cardId === cardId);
        if (!selectedFirstCard) {
            setSelectedFirstCard(selectedCard);
        } else if (!selectedSecondCard) {
            setSelectedSecondCard(selectedCard);
        }
        setIsModalOpen(false); // 모달 닫기
    };

    const clearSelectedCards = () => {
        setSelectedFirstCard(null);
        setSelectedSecondCard(null);
    };

    return (
        <main>
            <Nav />
            <section className="sectionCL">
                <strong>카드 비교하기</strong>
                <div className="card-selection">
                    {selectedFirstCard ? (
                        <div>
                            <button className="clear-button" onClick={clearSelectedCards}>X</button>
                            <h2>{selectedFirstCard.cardName}</h2>
                            <img src={selectedFirstCard.imageUrl} alt={selectedFirstCard.cardName} />
                            <p>은행: {selectedFirstCard.cardBank}</p>
                        </div>
                    ) : (
                        <button onClick={() => setIsModalOpen(true)}>첫 번째 카드 선택</button>
                    )}

                    {selectedSecondCard ? (
                        <div>
                            <button className="clear-button" onClick={clearSelectedCards}>X</button>
                            <h2>{selectedSecondCard.cardName}</h2>
                            <img src={selectedSecondCard.imageUrl} alt={selectedSecondCard.cardName} />
                            <p>은행: {selectedSecondCard.cardBank}</p>
                        </div>
                    ) : (
                        <button onClick={() => setIsModalOpen(true)}>두 번째 카드 선택</button>
                    )}
                </div>

                {/* 선택된 카드 정보 표시 */}

                {isModalOpen && (
                    <div className="cards">
                        <p>카드 선택</p>
                        <ul>
                            {cardList.map(card => (
                                <li className="cardListModal" key={card.cardId} onClick={() => handleCardSelect(card.cardId)}>
                                    <p className="cardNames">
                                        {card.cardName}
                                    </p>
                                </li>
                            ))}
                        </ul>
                        <button onClick={() => setIsModalOpen(false)}>닫기</button>
                    </div>
                )}
            </section>
            <Footer />
        </main>
    );
};

export default ComparePage;

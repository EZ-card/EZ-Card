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
    const [selectedSide, setSelectedSide] = useState(null); // 추가: 선택한 쪽 (왼쪽 or 오른쪽)

    useEffect(() => {
        // Fetch card catalog data from the backend
        fetch('/cards/catalog', {
            method: "GET",
            credentials: "include",
        })
            .then(response => response.json())
            .then(data => setCardList(data.cardCatalogDtoList))
            .catch(error => console.error('Error fetching card catalog:', error));
    }, []);

    const handleCardSelect = (cardId) => {
        const selectedCard = cardList.find(card => card.cardId === cardId);

        if (selectedSide === 'left') {
            setSelectedFirstCard(selectedCard);
        } else if (selectedSide === 'right') {
            setSelectedSecondCard(selectedCard);
        }

        setIsModalOpen(false); // 모달 닫기
    };

    const handleSelectSide = (side) => {
        setSelectedSide(side);
        setIsModalOpen(true);
    };

    return (
        <main>
            <Nav />
            <section className="sectionCL">
                <strong>카드 비교하기</strong>
                <div className="card-selection">
                    {/* 왼쪽 카드 선택 버튼 */}
                    <button onClick={() => handleSelectSide('left')}>왼쪽 카드 선택</button>
                    {/* 오른쪽 카드 선택 버튼 */}
                    <button onClick={() => handleSelectSide('right')}>오른쪽 카드 선택</button>
                </div>

                <div className="compare-area">
                    {/* 왼쪽 선택된 카드 정보 */}
                    <div className="selected-cards">
                        {selectedFirstCard && (
                            <div>
                                <img src={selectedFirstCard.cardImage} alt={selectedFirstCard.cardName} />
                                <h2>{selectedFirstCard.cardName}</h2>
                                <p>카드사 {selectedFirstCard.cardBank}</p>
                                <p>- {selectedFirstCard.cardSummary1}</p>
                                <p>- {selectedFirstCard.cardSummary2}</p>
                                <p>- {selectedFirstCard.cardSummary3}</p>
                            </div>
                        )}
                    </div>

                    {/* 오른쪽 선택된 카드 정보 */}
                    <div className="selected-cards">
                        {selectedSecondCard && (
                            <div>
                                <img src={selectedSecondCard.cardImage} alt={selectedSecondCard.cardName} />
                                <h2>{selectedSecondCard.cardName}</h2>
                                <p>카드사: {selectedSecondCard.cardBank}</p>
                                <p>- {selectedSecondCard.cardSummary1}</p>
                                <p>- {selectedSecondCard.cardSummary2}</p>
                                <p>- {selectedSecondCard.cardSummary3}</p>
                            </div>
                        )}
                    </div>
                </div>

                {/* 모달 */}
                {isModalOpen && (
                    <div className="modal">
                        <div className="modal-content">
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
                    </div>
                )}
            </section>
            <Footer />
        </main>
    );
};

export default ComparePage;

import React, { useState, useEffect } from 'react';
import Nav from '../../common/nav/Nav.js';
import Footer from '../../common/footer/Footer.js';
import './Compare.css';
import card_test_img from '../../assets/images/card_test_img.png';
import {Link} from "react-router-dom";

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
        // 카드 선택 시 /cards/detail/{cardid} API를 호출하여 카드 정보를 받아옴
        fetch(`/cards/detail/${cardId}`, {
            method: "GET",
            credentials: "include",
        })
            .then(response => response.json())
            .then(data => {
                const selectedCard = data; // API로부터 받아온 데이터를 사용
                if (selectedSide === 'left') {
                    setSelectedFirstCard(selectedCard);
                } else if (selectedSide === 'right') {
                    setSelectedSecondCard(selectedCard);
                }
                setIsModalOpen(false); // 모달 닫기
            })
            .catch(error => console.error('Error fetching card details:', error));
    };



    const handleSelectSide = (side) => {
        setSelectedSide(side);
        setIsModalOpen(true);
    };

    // 모달 표시 함수
    const openModal = () => {
        setIsModalOpen(true);
    }

// 모달 닫기 함수
    const closeModal = () => {
        setIsModalOpen(false);
    }


    return (
        <main>
            <Nav />
            <section className="sectionCL">
                <strong>카드 비교하기</strong>
                <div> 원하는 카드를 선택해 혜택을 비교해보세요!</div>
                <div className="compare-area">
                    {/*첫번째 카드*/}
                    <div className="selected-cards">
                        {selectedFirstCard ? (
                            <div className="card-container">
                                <img src={selectedFirstCard.cardDto.cardImage} alt={selectedFirstCard.cardDto.cardName} />
                                <h3>{selectedFirstCard.cardDto.cardName}</h3>
                                <div className="comparedetailbank-value">{selectedFirstCard.cardDto.cardBank}</div>
                                <hr className="hr-line" /> {/* 가로선 */}
                                <div className="comparedetail">
                                    <div className="comparedetail-label">카드종류</div>
                                    <div className="comparedetail-value">{selectedFirstCard.cardDto.cardType}카드</div>
                                </div>
                                <hr className="hr-line" /> {/* 가로선 */}
                                <div className="comparedetail">
                                    <div className="comparedetail-label">연회비</div>
                                    <div className="comparedetailmember-value">{selectedFirstCard.cardDto.cardMembership}</div>
                                </div>
                                <hr className="hr-line" /> {/* 가로선 */}
                                <div className="comparedetail">
                                    <div className="comparedetail-label">전월실적제한</div>
                                    <div className="comparedetailRecord-value">{selectedFirstCard.cardDto.cardRecord}</div>
                                </div>
                                <hr className="hr-line" /> {/* 가로선 */}
                                <div className="comparedetail">
                                    <div className="comparedetail-label">상세혜택</div>
                                    <div className="comparedetailbenefit-value">
                                        {selectedFirstCard.cardBenefitList.map((benefit, index) => (
                                            <div key={index}>
                                                <div className="detailCategory">{benefit.benefitCategory}</div>
                                                <div className="detailContents">{benefit.benefitSummary}</div>

                                            </div>
                                        ))}
                                    </div>
                                </div>
                                <div className="fixed-button-container-first">
                                    <button>
                                        <Link to={`/detail/${selectedFirstCard.cardDto.cardId}`} className="detailLink" target="_blank">
                                            상세 페이지 바로가기
                                        </Link>
                                    </button>
                                </div>

                                <button className="circle-button x-button" onClick={() => setSelectedFirstCard(null)}>❌</button>
                            </div>
                        ) : (
                            <div>
                                <button className="circle-button plus-button" onClick={() => handleSelectSide('left')}>+</button>
                            </div>
                        )}
                    </div>

                    {/*두번째 카드*/}
                    <div className="selected-cards">
                        {selectedSecondCard ? (
                            <div className="card-container">
                                <img src={selectedSecondCard.cardDto.cardImage} alt={selectedSecondCard.cardDto.cardName} />
                                <h3>{selectedSecondCard.cardDto.cardName}</h3>
                                <div className="comparedetailbank-value">{selectedSecondCard.cardDto.cardBank}</div>
                                <hr className="hr-line" /> {/* 가로선 */}
                                <div className="comparedetail">
                                    <div className="comparedetail-label">카드종류</div>
                                    <div className="comparedetail-value">{selectedSecondCard.cardDto.cardType}카드</div>
                                </div>
                                <hr className="hr-line" /> {/* 가로선 */}
                                <div className="comparedetail">
                                    <div className="comparedetail-label">연회비</div>
                                    <div className="comparedetailmember-value">{selectedSecondCard.cardDto.cardMembership}</div>
                                </div>
                                <hr className="hr-line" /> {/* 가로선 */}
                                <div className="comparedetail">
                                    <div className="comparedetail-label">전월실적제한</div>
                                    <div className="comparedetailRecord-value">{selectedSecondCard.cardDto.cardRecord}</div>
                                </div>
                                <hr className="hr-line" /> {/* 가로선 */}
                                <div className="comparedetail">
                                    <div className="comparedetail-label">상세혜택</div>
                                    <div className="comparedetailbenefit-value">
                                        {selectedSecondCard.cardBenefitList.map((benefit, index) => (
                                            <div key={index}>
                                                <div className="detailCategory">{benefit.benefitCategory}</div>
                                                <div className="detailContents">{benefit.benefitSummary}</div>
                                            </div>
                                        ))}
                                    </div>
                                </div>
                                <div className="fixed-button-container-second">
                                    <button>
                                        <Link to={`/detail/${selectedSecondCard.cardDto.cardId}`} className="detailLink" target="_blank">
                                            상세 페이지 바로가기
                                        </Link>
                                    </button>
                                </div>

                                <button className="circle-button x-button" onClick={() => setSelectedSecondCard(null)}>❌</button>
                            </div>
                        ) : (
                            <div>
                                <button className="circle-button plus-button" onClick={() => handleSelectSide('right')}>+</button>
                            </div>
                        )}
                    </div>
                </div>


                {isModalOpen && (
                    <div className="modal">
                        <div className="modal-content">
                            <ul>
                                {cardList.map(card => (
                                    <li className="cardListModal" key={card.cardId} onClick={() => handleCardSelect(card.cardId)}>
                                        <div className="cardInfoContainer">
                                            <img src={card.cardImage} alt={card.cardName} className="cardImage" />
                                            <p className="cardNames">{card.cardName}</p>
                                        </div>
                                    </li>
                                ))}
                            </ul>
                            <button onClick={closeModal} className="closebutton">닫기</button>
                        </div>
                    </div>
                )}
            </section>
            <Footer />
        </main>
    );
};

export default ComparePage;

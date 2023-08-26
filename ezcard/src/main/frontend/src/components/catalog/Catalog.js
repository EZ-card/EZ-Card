import React, {useState, useEffect} from 'react';
import {Link} from 'react-router-dom';

import Nav from '../../common/nav/Nav.js';
import Footer from '../../common/footer/Footer.js';
import './Catalog.css';

const Catalog = () => {
    const [cardCatalog, setCardCatalog] = useState([]);
    const [selectedCardType, setSelectedCardType] = useState('');


    const filteredCards = cardCatalog.filter(
        card => selectedCardType === '' || card.cardType === selectedCardType
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

    return (
        <main>
            <Nav />
            <section className="sectionCL">
                <strong>카드 목록</strong>
                <div className="button-container">
                    <button onClick={() => setSelectedCardType('신용')}>신용</button>
                    <button onClick={() => setSelectedCardType('체크')}>체크</button>
                    <button onClick={() => setSelectedCardType('복지')}>복지</button>
                </div>
                <div className="catalog-container">
                    {filteredCards.map(card => (
                        <div key={card.cardId} className="card-item">
                            <Link to={`/detail/${card.cardId}`}>
                                <img src={card.cardImage} alt={card.cardName} />
                                <h3>{card.cardName}</h3>
                                <p>{card.cardBank}</p>
                            </Link>
                        </div>
                    ))}
                </div>
            </section>
            <Footer />
        </main>
    );
};

export default Catalog;

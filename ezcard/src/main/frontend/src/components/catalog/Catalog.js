import React, {useState, useEffect} from 'react';
import {Link} from 'react-router-dom';

import Nav from '../../common/nav/Nav.js';
import Footer from '../../common/footer/Footer.js';
import './Catalog.css';

const Catalog = () => {
    const [cardCatalog, setCardCatalog] = useState([]);

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
            <Nav/>
            <section className="sectionCL">
                <div className="catalog-container">
                    {cardCatalog.map(card => (
                        <div key={card.cardId} className="card-item">
                            <Link to={`/detail/${card.cardId}`}>
                                <img src={card.cardImage} alt={card.cardName}/>
                                <h3>{card.cardName}</h3>
                                <p>{card.cardBank}</p>
                            </Link>
                        </div>
                    ))}
                </div>
            </section>
            <Footer/>
        </main>
    );
};

export default Catalog;

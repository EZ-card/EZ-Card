import React , {useState,useEffect} from "react";
import Nav from '../../common/nav/Nav.js';
import Footer from '../../common/footer/Footer.js';

import myCard1 from '../../assets/images/topCheck1.png';


import './List.css';

const List = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [wishCardDtoList, setWishCardDtoList] = useState([]);

    useEffect(() => {
        // Fetch user wish card data from the backend when the component mounts
        fetch("/wish", {
            method: "GET",
            credentials: "include",
        })
            .then((response) => {
                if (response.status === 200) {
                    return response.json();
                } else if (response.status === 401) {
                    setIsLoggedIn(false);
                }
            })
            .then((data) => {
                if (data && data.wishCardDtoList) {
                    setIsLoggedIn(true);
                    // Update the state with the received wish card data
                    setWishCardDtoList(data.wishCardDtoList);
                }
            });
    }, []);

    const handleRemoveWishCard = (cardId) => {
        // Send a DELETE request to remove the wish card by cardId
        fetch(`/wish/${cardId}`, {
            method: "DELETE",
            credentials: "include",
        })
            .then((response) => {
                if (response.status === 200) {
                    // If the card is successfully removed, update the wishCardDtoList
                    setWishCardDtoList((prevList) =>
                        prevList.filter((card) => card.cardId !== cardId)
                    );
                }
            })
            .catch((error) => {
                console.error("Error removing wish card:", error);
            });
    };

    return (
        <main>
            <Nav />

            <section className="sectionCL">
                <div id="myCard">
                    <strong>찜 목록</strong>
                    <div className="blank"></div>
                    <ul className="myCardList">
                        {wishCardDtoList.map((wishCard, index) => (
                            <li key={index}>
                                <div className="myCardImg">
                                    <img src={myCard1} alt={wishCard.cardName} />
                                </div>
                                <div className="myCardInfo">
                                    <p className="myCardName">{wishCard.cardName}</p>
                                    <p className="myCardName2">{wishCard.cardBank}</p>
                                </div>
                                <button
                                    className="myCardHeart"
                                    onClick={() => handleRemoveWishCard(wishCard.cardId)}
                                >
                                    <ion-icon name="heart"></ion-icon>
                                </button>
                            </li>
                        ))}
                    </ul>
                </div>
            </section>

            <Footer />
        </main>
    );
};

export default List;
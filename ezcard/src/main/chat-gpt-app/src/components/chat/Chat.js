import React, { useState } from "react";
import axios from 'axios';
import Nav from '../../common/nav/Nav.js';
import Footer from '../../common/footer/Footer.js';
import cardTestImg from '../../assets/images/card_test_img.png';

import './Chat.css';

const CardInfoMessage = ({ cardDto }) => {
    return (
        <div className="gptChat gptChatCr">
            <div><img src={cardTestImg} alt="CardTestImage" /></div>
            <div>
                <strong>{cardDto.cardName}</strong>
                <p className="cardBank">{cardDto.cardBank}</p>
                <ul>
                    <li>{cardDto.cardSummary1}</li>
                    <li>{cardDto.cardSummary2}</li>
                    <li>{cardDto.cardSummary3}</li>
                </ul>
                <p className="cardRecord">
                    <span>{cardDto.cardRecord}</span>
                    <span> / </span>
                    <span>{cardDto.cardMembership}</span>
                </p>
            </div>
        </div>
    );
};

const Chat = () => {
    const [userMessage, setUserMessage] = useState('');
    const [chatMessages, setChatMessages] = useState([]);

    const handleUserMessageChange = (e) => {
        setUserMessage(e.target.value);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        const userMessageObj = { type: 'user', text: userMessage };
        setChatMessages((prevChatMessages) => [...prevChatMessages, userMessageObj]);
        setUserMessage('');

        if (!userMessage) {
            return;
        }

        try {
            const response = await axios.post('/ezbot', {
                "userinput": userMessage
            });

            const responseData = response.data;
            const cardDto = responseData.cardDto;

            const gptResponseObj = { type: 'gpt', text: responseData.gptResponse };
            setChatMessages((prevChatMessages) => [...prevChatMessages, gptResponseObj]);

            if (responseData.generalGptResponse) {
                const generalGptResponseObj = { type: 'gpt', text: responseData.generalGptResponse };
                setChatMessages((prevChatMessages) => [...prevChatMessages, generalGptResponseObj]);
            }

            if (cardDto !== null) {
                setChatMessages((prevChatMessages) => [...prevChatMessages, { type: 'card', cardDto }]);
            }
        } catch {
            // error handling
        }
    }

    return (
        <main>
            <Nav />

            <section className="sectionCL">
                <p>원하시는 정보 입력 후 마지막에 <strong className="chatSt">"카드 추천"</strong>이라고 입력해보세요 !</p>
                <div id="chat_container">
                    <div id="chat_messages" className="chatContainer">
                        {chatMessages.slice().reverse().map((message, index) => {
                            if (message.type === 'user') {
                                return (
                                    <div key={index} className="userChat">
                                        <p><span>{message.text}</span></p>
                                    </div>
                                );
                            }  else if (message.type === 'card') {
                                return (
                                    <div key={index} className="gptChat" style={message.style}>
                                        <CardInfoMessage cardDto={message.cardDto} />
                                    </div>
                                );
                            } else if (message.type === 'gpt') {
                                return (
                                    <div key={index} className="gptChat" style={message.style}>
                                        <p><span>{message.text}</span></p>
                                    </div>
                                );
                            }
                            return null;
                        })}
                    </div>

                    <div id="user_input">
                        <form onSubmit={handleSubmit}>
                            <input
                                type="text"
                                placeholder="Ex) 대학생을 위한 카드 추천해줘!"
                                value={userMessage}
                                onChange={handleUserMessageChange}
                            />
                            <button type="submit" disabled={!userMessage}>전송</button>
                        </form>
                    </div>
                </div>
            </section>

            <Footer />
        </main>
    );
}

export default Chat;

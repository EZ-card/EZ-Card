import React, { useState, useEffect } from "react";
import { Link } from 'react-router-dom';
import axios from 'axios';

import Nav from '../../common/nav/Nav.js';
import Footer from '../../common/footer/Footer.js';

import './Chat.css';

// 이 컴포넌트는 카드 정보를 표시합니다
const CardInfoMessage = ({ cardDto }) => {
    return (
        <Link to={`/detail/${cardDto.cardId}`}>
            <div className="gptChat gptChatCr">
                <div><img src={cardDto.cardImage} alt="CardImage" /></div>
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
                    <p className="cardclick">클릭해서 자세히 보기 →</p>
                </div>
            </div>
        </Link>
    );
};

// 메인 Chat
const Chat = () => {
    // 사용자 입력 메시지, 채팅 이력 및 로딩 상태를 위한 상태 관리
    const [userMessage, setUserMessage] = useState('');
    const [chatMessages, setChatMessages] = useState([]);
    const [loading, setLoading] = useState(false);

    const [showInitialMessage, setShowInitialMessage] = useState(false);

    useEffect(() => {
        const initialMessageTimeout = setTimeout(() => {
            setShowInitialMessage(true);
        }, 350); // 0.5초

        return () => {
            clearTimeout(initialMessageTimeout);
        };
    }, []);

    // 사용자 입력 메시지 변경 처리
    const handleUserMessageChange = (e) => {
        setUserMessage(e.target.value);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        setLoading(true); // 로딩 상태 시작

        // 사용자 메시지 객체 생성 및 채팅 이력 업데이트
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
            // 에러 처리
        } finally {
            setLoading(false); // 로딩 상태 종료
        }
    }

    return (
        <main>
            <Nav />

            <section className="sectionCL">
                {/*<p>원하시는 정보 입력 후 마지막에 <strong className="chatSt">"카드 추천"</strong>이라고 입력해보세요 !</p>*/}
                <div id="chat_container">
                    <div id="chat_messages" className="chatContainer">
                        {/* 로딩 상태가 true인 경우 로딩 스피너 표시 */}
                        {loading && (
                            <div className="loddingSpinner">
                                <p>EZ는 생각 중...</p>
                            </div>
                        )}
                        
                        {/* 채팅 메시지 표시 */}
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

                        {/* 초기 메시지가 보이는 경우에만 표시 */}
                        {showInitialMessage && (
                            <React.Fragment>
                                <div className="gptChat">
                                    <p><span>여러분의 이야기를 들려주시면 카드를 추천해 드릴게요. 카드 관련 정보를 말씀해주세요. 예를 들어, 연회비, 할인 혜택, 결제 수단 등을 알려주시면 더 정확한 추천을 드릴 수 있어요. 어떤 카드를 찾고 계신가요?</span></p>
                                </div>
                                <div className="gptChat">
                                    <p><span>안녕하세요! 저는 Ez:bot이에요!</span></p>
                                </div>
                            </React.Fragment>
                        )}
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
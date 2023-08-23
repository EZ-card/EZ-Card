import React, { useState, useEffect } from "react";
import axios from 'axios'; // axios import
import Nav from '../../common/nav/Nav.js'; // Nav 컴포넌트를 불러옴
import Footer from '../../common/footer/Footer.js'; // Footer 컴포넌트를 불러옴
import card_test_img from '../../assets/images/card_test_img.png';

import './Chat.css';

const Chat = () => {
    const [userMessage, setUserMessage] = useState('');
    const [chatMessages, setChatMessages] = useState([]);
    const [gptResponse, setGptResponse] = useState('');

    const handleUserMessageChange = (e) => {
        setUserMessage(e.target.value);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        // console.log(userMessage);
        if (!userMessage) {
            // 사용자 메시지가 비어있으면 아무것도 하지 않음
            return;
        }
        // 사용자 메시지를 채팅 화면에 추가
        setChatMessages([...chatMessages, { type: 'user', text: userMessage }]);
   
        try {
            // GPT에 메시지를 전송하고 응답 받아오기
            const response = await axios.post('/ezbot', {
                "userinput": userMessage
            });
            console.log(userMessage);
            console.log(chatMessages);
            console.log('hi');
            // 텍스트 형식으로 응답 파싱
            const responseData = response.data;     
            const gptResponse = responseData.gptResponse;
            const cardDto = responseData.cardDto;
     
            // GPT 응답 업데이트
            setGptResponse(gptResponse);
    
            // gptResponse와 cardDto가 있는 경우, 한 번에 모든 메시지를 추가
            const newMessages = [
                { type: 'gpt', text: gptResponse },
            ];
    
            // cardDto가 있는 경우, 카드 정보를 채팅 메시지에 추가
            if (gptResponse) {
                newMessages.push(
                    <div className="gptChat gptChatCr" key={new Date().getTime()}>
                        <div><img src={card_test_img} alt="CardTestImage" /></div>
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
            }
    
            // 채팅 메시지를 업데이트
            setChatMessages([...chatMessages, ...newMessages]);
    
            // 입력창 초기화
            setUserMessage('');
        } catch{
            // console.error(error);
        }

        setUserMessage('');
    }

    return (
        <main>
            <Nav />

            <section className="sectionCL">
                <p>원하시는 정보 입력 후 마지막에</p>
                <p><strong className="chatSt">"카드 추천"</strong>이라고 입력해보세요 !</p>
                <div id="chat_container">
                    {/* 채팅 내역 */}
                    <div id="chat_messages">
                        {chatMessages.slice().reverse().map((message, index) => (
                            <div
                                key={index}
                                className={message.type === 'user' ? 'userChat' : 'gptChat'}
                            >
                                <p>
                                    {/* <strong>{message.type === 'user' ? '나' : 'GPT'} : </strong> */}
                                    <span>{message.text}</span>
                                </p>
                            </div>
                        ))}
                        {/* GPT 응답 표시 */}
                        {gptResponse && (
                            <div className="gptChat gptChatNm" key={new Date().getTime()}>
                                <p><span>{gptResponse}</span></p>
                            </div>
                        )}
                    </div>
                    {/* 채팅 내역 END */}    

                    {/* 입력 창 */}
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
                {/* 입력 창 END */}
            </section>

            <Footer />
        </main>
    );
}

export default Chat;
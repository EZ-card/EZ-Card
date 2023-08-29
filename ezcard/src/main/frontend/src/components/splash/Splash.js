import React, { useState, useEffect } from "react";
import { Link } from 'react-router-dom';
import creditCard from '../../assets/images/creditCard.mov';
import './Splash.css';

const Splash = () => {
    const [words, setWords] = useState(['나만의 카드추천', '나를 위한 카드추천', '모든 카드 한 눈에']);
    const [colors, setColors] = useState(['#000']);
    const [letterCount, setLetterCount] = useState(1);
    const [x, setX] = useState(1);
    const [waiting, setWaiting] = useState(false);
    const [visible, setVisible] = useState(true);

    useEffect(() => {
        const interval1 = setInterval(() => {
            if (letterCount === 0 && !waiting) {
                setWaiting(true);
                setWords((prevWords) => {
                    const usedWord = prevWords.shift();
                    prevWords.push(usedWord);
                    return [...prevWords];
                });
                setColors((prevColors) => {
                    const usedColor = prevColors.shift();
                    prevColors.push(usedColor);
                    return [...prevColors];
                });
                setX(1);
                setLetterCount((prevCount) => prevCount + x);
                setWaiting(false);
            } else if (letterCount === words[0].length + 1 && !waiting) {
                setWaiting(true);
                setTimeout(() => {
                    setX(-1);
                    setLetterCount((prevCount) => prevCount + x);
                    setWaiting(false);
                }, 1000);
            } else if (!waiting) {
                setLetterCount((prevCount) => prevCount + x);
            }
        }, 120);

        const interval2 = setInterval(() => {
            setVisible((prevVisible) => !prevVisible);
        }, 400);

        return () => {
            clearInterval(interval1);
            clearInterval(interval2);
        };
    }, [letterCount, waiting, x]);

    return (
        <main className="mainSp">
            <section className="sectionSp">
                <div>
                    <video muted loop autoPlay playsInline preload="auto">
                        <source src={creditCard} type="video/mp4" />
                    </video>
                    <div className="videoFt">
                        <p><strong>EZ:card</strong></p>
                        <div className='console-container'>
                            <span style={{ color: colors[0] }}>{words[0].substring(0, letterCount)}</span>
                            <div className={`console-underscore ${visible ? "" : "hidden"}`}>&#95;</div>
                        </div>
                        <Link to="/main">서비스 이용하기</Link>
                    </div>
                </div>
            </section>
        </main>
    );
}

export default Splash;
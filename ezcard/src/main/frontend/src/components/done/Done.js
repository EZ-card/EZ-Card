import React from "react";
import './Done.css';
import done from '../../assets/icon/done.png'
import {Link} from "react-router-dom";

//회원가입 완료 페이지
const Done = () => (
    <main>

        <section className="sectionCL">
            <div className="done_box">
                <div className="done_img"><img src={done}/></div>
                <p id="text"> 완료되었습니다! </p>
                <button id="doneButton" className="done_button"><Link to="/login" className="done_link">로그인하고 맞춤 추천 받기</Link></button>
            </div>
        </section>

    </main>
)

export default Done;
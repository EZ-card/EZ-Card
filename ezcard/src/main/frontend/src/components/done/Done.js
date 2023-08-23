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
        <div className="done_button"><button id="button"><Link to="/login">로그인하고 맞춤 추천 받기</Link></button></div>
      </div>
    </section>
    
  </main>
)

export default Done;
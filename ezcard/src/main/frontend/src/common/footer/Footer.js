import React from "react";
import './Footer.css';
import 김다은 from '../../assets/images/김다은.png'
import 김민지 from '../../assets/images/김민지.png'
import 안정민 from '../../assets/images/안정민.png'
import 이건우 from '../../assets/images/이건우.png'
import 이명준 from '../../assets/images/이명준.png'

const Footer = () => (
    <div className="footer-dark">
        <footer>
            <div className="container">
                    <div className="row">
                        <div className="col-sm-6 col-md-3 item">
                            <h3>Services</h3>
                            <ul>
                                <li>Web design tool : Figma</li>
                                <li>Development language : Java & React.js</li>
                            </ul>
                        </div>
                        <div className="col-md-6 item text">
                            <h3>EZ:card</h3>
                            <p>Team : 이명준 안정민 이건우 김민지 김다은</p>
                        </div>
                        <div className="col item social">
                            <span></span>
                            <span></span>
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>
                    </div>
                    <p className="copyright">TABA 3기 5조 © 2023</p>
                </div>
        </footer>
    </div>
)

export default Footer;
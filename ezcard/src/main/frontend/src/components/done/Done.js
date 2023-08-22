import React from "react";

//import { Routes, Route, Link } from 'react-router-dom';
import Nav from '../../common/nav/Nav.js'; // Nav 컴포넌트를 불러옴
import Footer from '../../common/footer/Footer.js'; // Footer 컴포넌트를 불러옴

import './Done.css';

const Done = () => (
    <main>
        <Nav/>
        
        <section className="sectionCL">
            <div>
                Done
            </div>
        </section>

        <Footer />
    </main>
)


export default Done;
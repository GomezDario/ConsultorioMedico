import React from 'react';
import Navbar from './Navbar';
import './HomePage.css';  

const Home = () => {
  return (
    <div className="home">
      <Navbar />
      <div className="content">
        <h1>Bienvenido al Consultorio Médico</h1>
      </div>
      
    </div>
  );
};

export default Home;

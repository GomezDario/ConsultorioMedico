import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css'; 

const Navbar = () => {
  return (
    <nav className="navbar">
      <ul>
        <li><Link to="/">Inicio</Link></li>
        <li><Link to="/pacientes">Pacientes</Link></li>
        <li><Link to="/profesionales">Profesionales</Link></li>
        <li><Link to="/especialidades">Especialidades</Link></li>
        <li><Link to="/turnos">Turnos</Link></li>
      </ul>
    </nav>
  );
};

export default Navbar;

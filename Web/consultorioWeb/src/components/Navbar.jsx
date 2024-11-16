import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css'; // Estilos para la navbar

const Navbar = () => {
  return (
    <nav className="navbar">
      <ul>
        <li><Link to="/">Inicio</Link></li>
        <li><Link to="/pacientes">Pacientes</Link></li>
        <li><Link to="/profesionales">Profesionales</Link></li>
      </ul>
    </nav>
  );
};

export default Navbar;

import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import PacienteList from './components/PacienteComponent'; // Asegúrate que el nombre coincida con el componente exportado
import Home from './components/HomePage'

import './App.css';

function App() {
  return (
    <Router>
      <Routes>
      <Route path="/" element={<Home />} />
        <Route path="/pacientes" element={<PacienteList />} />
      </Routes>
    </Router>
  );
}

export default App;

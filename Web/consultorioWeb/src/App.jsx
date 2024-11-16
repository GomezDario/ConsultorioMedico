import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import PacienteList from './components/PacienteComponent'; 
import ProfesionalesList from './components/ProfesionalComponent';
import EspecialidadList from './components/EspecialidadComponent'
import Home from './components/HomePage'

import './App.css';

function App() {
  return (
    <Router>
      <Routes>
      <Route path="/" element={<Home />} />
        <Route path="/pacientes" element={<PacienteList />} />
        <Route path="/profesionales" element={<ProfesionalesList />} />
        <Route path="/especialidades" element={<EspecialidadList />} />
      </Routes>
    </Router>
  );
}

export default App;

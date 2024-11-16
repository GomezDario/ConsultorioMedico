import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import PacienteList from './components/PacienteComponent'; 
import ProfesionalesList from './components/ProfesionalComponent';
import EspecialidadList from './components/EspecialidadComponent'
import TurnoList from './components/TurnoComponent';
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
        <Route path="/turnos" element={<TurnoList />} />
      </Routes>
    </Router>
  );
}

export default App;

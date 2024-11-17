import React, { useEffect, useState } from 'react';
import ProfesionalService from '../services/api'
import '../components/ProfesionalComponent.css';  
import Navbar from './Navbar';

const ProfesionalList = () => {
    const [profesionales, setProfesionales] = useState([]);

    useEffect(() => {
        
        ProfesionalService.getAllProfesionales()
            .then(data => setProfesionales(data))
            .catch(error => console.error('Error al cargar los profesionales:', error));
    }, []);

    return (
        <div>
            <Navbar />
            <h1>Lista de Profesionales</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Especialidad</th>
                        <th>Horario de Trabajo</th>
                        <th>Número de DNI</th>
                    </tr>
                </thead>
                <tbody>
                    {profesionales.map(profesional => (
                        <tr key={profesional.id}>
                            <td>{profesional.id}</td>
                            <td>{profesional.nombre}</td>
                            <td>{profesional.especialidadId}</td>
                            <td>{`${profesional.horarioInicio} - ${profesional.horarioFin}`}</td>
                            <td>{profesional.numeroDNI}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ProfesionalList;

import React, { useEffect, useState } from 'react';
import Api from '../services/api';  
import '../components/EspecialidadComponent.css';  
import Navbar from './Navbar';  

const EspecialidadList = () => {
    const [especialidades, setEspecialidades] = useState([]);

    useEffect(() => {
        
        Api.getAllEspecialidades()
            .then(data => setEspecialidades(data))
            .catch(error => console.error('Error al cargar las especialidades:', error));
    }, []);

    return (
        <div>
            <Navbar />
            <h1>Lista de Especialidades</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                    </tr>
                </thead>
                <tbody>
                    {especialidades.map(especialidad => (
                        <tr key={especialidad.id}>
                            <td>{especialidad.id}</td>
                            <td>{especialidad.nombre}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default EspecialidadList;

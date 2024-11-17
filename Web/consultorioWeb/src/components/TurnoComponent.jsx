import React, { useEffect, useState } from 'react';
import Api from '../services/api'; 
import Navbar from './Navbar';
import '../components/TurnoComponent.css'

const TurnoList = () => {
    const [turnos, setTurnos] = useState([]);

    useEffect(() => {
        
        Api.getAllTurnos()
            .then(data => setTurnos(data))
            .catch(error => console.error('Error al cargar los turnos:', error));
    }, []);

    return (
        <div>
            <Navbar />
            <h1>Lista de Turnos</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Fecha y Hora</th>
                        <th>Profesional</th>
                        <th>Paciente</th>
                        <th>Especialidad</th>
                        <th>Consultorio</th>
                        <th>Estado</th>
                    </tr>
                </thead>
                <tbody>
                    {turnos.map(turno => (
                        <tr key={turno.id}>
                            <td>{turno.id}</td>
                            <td>{turno.fechaHora}</td>
                            <td>{turno.profesionalId}</td>
                            <td>{turno.pacienteId}</td>
                            <td>{turno.especialidadId}</td>
                            <td>{turno.consultorio}</td>
                            <td>{turno.cancelacion === 1 ? 'Cancelado' : 'Activo'}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default TurnoList;

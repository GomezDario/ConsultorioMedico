import React, { useEffect, useState } from 'react';
import Api from '../services/api';
import '../components/PacienteComponent.css'
import Navbar from './Navbar';
const PacienteList = () => {
    const [pacientes, setPacientes] = useState([]);

    useEffect(() => {
        
        Api.getAllPacientes()
            .then(data => setPacientes(data))
            .catch(error => console.error('Error al cargar los pacientes:', error));
    }, []);

    return (
        <div>
            <Navbar />
            <h1>Lista de Pacientes</h1>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Edad</th>
                        <th>Número de DNI</th>
                    </tr>
                </thead>
                <tbody>
                    {pacientes.map(paciente => (
                        <tr key={paciente.id}>
                            <td>{paciente.id}</td>
                            <td>{paciente.nombre}</td>
                            <td>{paciente.apellido}</td>
                            <td>{paciente.edad}</td>
                            <td>{paciente.numeroDni}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default PacienteList;

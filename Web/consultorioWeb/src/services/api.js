import axios from 'axios';

const api_paciente = 'http://localhost:8080/paciente';
const api_profesional = 'http://localhost:8080/profesional';
const api_especialidad = 'http://localhost:8080/especialidad';

// Obtener todos los pacientes
const getAllPacientes = () => {
    return axios.get(`${api_paciente}/all`)
        .then(response => response.data)
        .catch(error => {
            console.error('Error al obtener los pacientes:', error);
            return Promise.reject(error);
        });
};

// Obtener un paciente por ID
const getPacienteById = (id) => {
    return axios.get(`${api_paciente}/${id}`)
        .then(response => response.data)
        .catch(error => {
            console.error(`Error al obtener el paciente con ID ${id}:`, error);
            return Promise.reject(error);
        });
};

// Crear un nuevo paciente
const createPaciente = (paciente) => {
    return axios.post(api_paciente, paciente)
        .then(response => response.data)
        .catch(error => {
            console.error('Error al crear un paciente:', error);
            return Promise.reject(error);
        });
};

// Obtener todos los profesionales
const getAllProfesionales = () => {
    return axios.get(`${api_profesional}/all`)
        .then(response => response.data)
        .catch(error => {
            console.error('Error al obtener los profesionales:', error);
            return Promise.reject(error);
        });
};

// Obtener un profesional por ID
const getProfesionalById = (id) => {
    return axios.get(`${api_profesional}/${id}`)
        .then(response => response.data)
        .catch(error => {
            console.error(`Error al obtener el profesional con ID ${id}:`, error);
            return Promise.reject(error);
        });
};


// Obtener todas las especialidades
const getAllEspecialidades = () => {
    return axios.get(`${api_especialidad}/all`)
        .then(response => response.data)
        .catch(error => {
            console.error('Error al obtener las especialidades:', error);
            return Promise.reject(error);
        });
};

// Obtener una especialidad por ID
const getEspecialidadById = (id) => {
    return axios.get(`${api_especialidad}/${id}`)
        .then(response => response.data)
        .catch(error => {
            console.error(`Error al obtener la especialidad con ID ${id}:`, error);
            return Promise.reject(error);
        });
};



// Exportar los métodos
const PacienteService = {
    getAllPacientes,
    getPacienteById,
    createPaciente,
    getAllProfesionales,
    getProfesionalById,
    getAllEspecialidades,
    getEspecialidadById
};

export default PacienteService;

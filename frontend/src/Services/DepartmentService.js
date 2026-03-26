import axios from "axios";

// const DEPARTMENT_REST_API_BASE_URL = 'http://localhost:8080/api/departments';
// eslint-disable-next-line no-undef
const DEPARTMENT_REST_API_BASE_URL=`${process.env.REACT_APP_API_URL}/api/departments`;

export const listAllDepartments = () => axios.get(DEPARTMENT_REST_API_BASE_URL);

export const createDepartment = (department) => axios.post(DEPARTMENT_REST_API_BASE_URL, department);

export const getDepartment = (departmentId) => axios.get(DEPARTMENT_REST_API_BASE_URL + '/' + departmentId);

export const updateDepartment = (departmentId, department) => axios.put(DEPARTMENT_REST_API_BASE_URL + '/' + departmentId, department);

export const deleteDepartment = (departmentId) => axios.delete(DEPARTMENT_REST_API_BASE_URL + '/' + departmentId);
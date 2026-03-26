import axios from "axios";

// const REST_API_BASE_URL = 'http://localhost:8080/api/employees';
// eslint-disable-next-line no-undef
const REST_API_BASE_URL = `${process.env.REACT_APP_API_URL}/api/employees`;

export const listofemployees = () => axios.get(REST_API_BASE_URL);

export const createEmployee = (employee) => axios.post(REST_API_BASE_URL, employee);

export const getEmployee = (employeeId) => axios.get(REST_API_BASE_URL + '/' + employeeId);

export const updateEmployee = (employeeId, employee) => axios.put(REST_API_BASE_URL + '/' + employeeId, employee);

export const deleteEmployee = (employeeId) => axios.delete(REST_API_BASE_URL + '/' + employeeId);
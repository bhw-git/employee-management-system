import axios from "axios"; 
import {employeeAPI} from "./api";

const REST_API_BASE_URL = 'http://localhost:8080/api/employees';
// eslint-disable-next-line no-undef
// const REST_API_BASE_URL = "https://employee-management-system-production-03d8.up.railway.app/api/employees";

export const listofemployees = () => employeeAPI.get("");

export const createEmployee = (formData) => employeeAPI.post("",formData);

export const getEmployee = (employeeId) => employeeAPI.get(`/${employeeId}`);

export const updateEmployee = (employeeId, formData) => employeeAPI.patch(`/${employeeId}`, formData);

export const deleteEmployee = (employeeId) => employeeAPI.delete(`/${employeeId}`);


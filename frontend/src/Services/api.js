import axios from "axios";

const token =
    btoa("admin:password123");

const authHeader = {
    Authorization: `Basic ${token}`
};

export const employeeAPI = axios.create({

    baseURL:'http://localhost:8080/api/employees',

    headers: authHeader
});

export const departmentAPI = axios.create({

    baseURL:'http://localhost:8080/api/departments',

    headers: authHeader
});
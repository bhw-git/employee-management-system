import axios from "axios";
import { getAuthHeader } from "./AuthService";

export const employeeAPI = axios.create({
  baseURL: "http://localhost:8080/api/employees"
});

employeeAPI.interceptors.request.use((config) => {
  config.headers = {
    ...config.headers,
    ...getAuthHeader()
  };

  return config;
});

export const departmentAPI = axios.create({
  baseURL: "http://localhost:8080/api/departments"
});

departmentAPI.interceptors.request.use((config) => {
  config.headers = {
    ...config.headers,
    ...getAuthHeader()
  };

  return config;
});
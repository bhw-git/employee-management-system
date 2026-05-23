import axios from "axios";
import { departmentAPI } from "./api";

const DEPARTMENT_REST_API_BASE_URL = 'http://localhost:8080/api/departments';
// eslint-disable-next-line no-undef
// const DEPARTMENT_REST_API_BASE_URL="https://employee-management-system-production-03d8.up.railway.app/api/departments";

export const listAllDepartments = () => departmentAPI.get("");

export const createDepartment = (department) => departmentAPI.post("", department);

export const getDepartment = (departmentId) => departmentAPI.get(`/${departmentId}`);

export const updateDepartment = (departmentId, department) => departmentAPI.patch(`/${departmentId}`, department);

export const deleteDepartment = (departmentId) => departmentAPI.delete(`/${departmentId}`);

import axios from "axios";

export const login = (username, password) => {
  return axios.get("http://localhost:8080/api/employees", {
    headers: {
      Authorization: `Basic ${btoa(`${username}:${password}`)}`
    }
  });
};

export const logout = () => {
  localStorage.removeItem("username");
  localStorage.removeItem("password");
};

export const isAuthenticated = () => {
  return Boolean(localStorage.getItem("username") && localStorage.getItem("password"));
};

export const getAuthHeader = () => {
  const username = localStorage.getItem("username");
  const password = localStorage.getItem("password");

  if (!username || !password) {
    return {};
  }

  return {
    Authorization: `Basic ${btoa(`${username}:${password}`)}`
  };
};

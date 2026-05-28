import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../Services/AuthService";

const LoginComponent = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("");

  const navigate = useNavigate();
  const handleToggle = () => {
    setValues({ ...values, showPassword: !values.showPassword });
  };

  async function handleLogin(e) {
    e.preventDefault();

    try {
      await login(username, password);

      localStorage.setItem("username", username);
      localStorage.setItem("password", password);

      navigate("/employees");
    } catch (err) {
      setError("Invalid username or password");
    }
  }

  return (
    <div className="container mt-5" style={{ maxWidth: "400px" }}>
      <h2 className="text-center mb-4">Login</h2>

      {error && <div className="alert alert-danger">{error}</div>}

      <form onSubmit={handleLogin}>
        <div className="mb-3">
          <label className="form-label">Username</label>
          <input
            className="form-control"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>

        <div className="mb-3">
          <label className="form-label">Password</label>
          <input
            type="password"
            className="form-control"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        {/* CheckBox to show password */}
        {/* <div className="form-check mt-2">
        <input
            className="form-check-input"
            type="checkbox"
            id="showPassword"
            checked={showPassword}
            onChange={() => setShowPassword((prev) => !prev)}
        />
        <label className="form-check-label" htmlFor="showPassword">
            Show password
        </label>
        </div> */}
        <button className="btn btn-primary w-100">Login</button>
      </form>
    </div>
  );
};

export default LoginComponent;
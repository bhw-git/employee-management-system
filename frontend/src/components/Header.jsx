import React from 'react'
import { NavLink } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

const Header = () => {
  return (
    <div>
      <header>
        <nav className="navbar navbar-expand-lg navbar-light" style={{backgroundColor : '#8bcaf7'}}>
          <div className='container-fluid'>
            <a className="navbar-brand" href="https://www.youtube.com/@bhuvanbhw">Employee Management System</a>
            <button className='navbar-toggler' type='button' data-bs-toggle="collapse" data-bs-target='#navbarNav'>
              <span className='navbar-toggler-icon'></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav">
                <li className="nav-item">
                  <NavLink className='btn btn-primary me-2 mb-2 mb-lg-0' to='/employees'>Employees</NavLink>
                </li>
                <li className='nav-item'>
                  <NavLink className='btn btn-primary' to='/departments'>Department</NavLink>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </header>
    </div>
  )
}

export default Header

import './App.css'
import ListEmployeeComponent from "./components/ListEmployeeComponent"
import Header from "./components/Header"
import Footer from "./components/Footer"
import LoginComponent from './components/LoginComponent'
import { BrowserRouter, Navigate, Routes, Route } from 'react-router-dom'
import EmployeeComponent from './components/EmployeeComponent'
import ListDepartmentComponent from './components/ListDepartmentComponent'
import DepartmentComponent from './components/DepartmentComponent'
import ProtectedRoute from './components/ProtectedRoute'
import { isAuthenticated } from './Services/AuthService'

const RootRedirect = () => {
  return <Navigate to={isAuthenticated() ? "/employees" : "/login"} replace />;
}

function App() {

  return (
    <>
      <BrowserRouter>
        <div className="app-shell">
        <Header/>
        <main className="app-content">
          <Routes>
            {/* http://localhost:3000/login */}
            <Route path='/login' element={<LoginComponent />}></Route>
            {/* http://localhost:3000 */}
            <Route path='/' element = { <RootRedirect /> }></Route>
            {/* http://localhost:3000/employees */}
            <Route path='/employees' element = { <ProtectedRoute><ListEmployeeComponent/></ProtectedRoute> }></Route>
            {/* http://localhost:3000/add-employee */}
            <Route path='/add-employee' element = { <ProtectedRoute><EmployeeComponent/></ProtectedRoute> }></Route>
            {/* http://localhost:3000/edit-employee/1 */}
            <Route path='/edit-employee/:id' element = { <ProtectedRoute><EmployeeComponent/></ProtectedRoute> }></Route>
            {/* http://localhost:3000/delete-employee/1 */}
            <Route path='/delete-employee/:id' element = {<ProtectedRoute><ListEmployeeComponent/></ProtectedRoute>}></Route>
            {/* http://localhost:3000/departments */}
            <Route path='/departments' element = {<ProtectedRoute><ListDepartmentComponent/></ProtectedRoute>}></Route>
            {/* http://localhost:3000/add-department */}
            <Route path='/add-department' element = {<ProtectedRoute><DepartmentComponent/></ProtectedRoute>}></Route>
            {/* http://localhost:3000/edit-department/1 */}
            <Route path='/edit-department/:id' element = { <ProtectedRoute><DepartmentComponent/></ProtectedRoute> }></Route>
            {/* http://localhost:3000/delete-department/1 */}
            <Route path='/delete-department/:id' element = {<ProtectedRoute><ListDepartmentComponent/></ProtectedRoute>}></Route>
          </Routes>
        </main>
        <Footer/>
        </div>
      </BrowserRouter>
    </>
  )
}

export default App

import './App.css'
import ListEmployeeComponent from "./components/ListEmployeeComponent"
import Header from "./components/Header"
import Footer from "./components/Footer"
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import EmployeeComponent from './components/EmployeeComponent'
import ListDepartmentComponent from './components/ListDepartmentComponent'
import DepartmentComponent from './components/DepartmentComponent'

function App() {

  return (
    <>
      <BrowserRouter>
        <Header/>
          <Routes>
            {/* http://localhost:3000/ */}
            <Route path='/' element = { <ListEmployeeComponent/> }></Route>
            {/* http://localhost:3000/employees */}
            <Route path='/employees' element = { <ListEmployeeComponent/> }></Route>
            {/* http://localhost:3000/add-employee */}
            <Route path='/add-employee' element = { <EmployeeComponent/> }></Route>
            {/* http://localhost:3000/edit-employee/1 */}
            <Route path='/edit-employee/:id' element = { <EmployeeComponent/> }></Route>
            {/* http://localhost:3000/delete-employee/1 */}
            <Route path='/delete-employee/:id' element = {<ListEmployeeComponent/>}></Route>
            {/* http://localhost:3000/departments */}
            <Route path='/departments' element = {<ListDepartmentComponent/>}></Route>
            {/* http://localhost:3000/add-department */}
            <Route path='/add-department' element = {<DepartmentComponent/>}></Route>
            {/* http://localhost:3000/edit-department/1 */}
            <Route path='/edit-department/:id' element = { <DepartmentComponent/> }></Route>
            {/* http://localhost:3000/delete-department/1 */}
            <Route path='/delete-department/:id' element = {<ListDepartmentComponent/>}></Route>
          </Routes>
        <Footer/>
      </BrowserRouter>
    </>
  )
}

export default App

import React, {useEffect, useState} from "react"
import {deleteEmployee, listofemployees} from "../Services/EmployeeService"
import {useNavigate, useParams} from "react-router-dom"

const ListEmployeeComponent = () => {

    const[employees, setEmployees] = useState([])

    const navigate = useNavigate();

    useEffect(() =>{
        getAllEmployee()
    }, [])

    function getAllEmployee(){
        listofemployees().then((response) => {
            setEmployees(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    function addNewEmployee() {
        navigate('/add-employee')
    }

    function updateEmployee(id){
        navigate(`/edit-employee/${id}`)
    }

    function removeEmployee(id){
        console.log(id);
        deleteEmployee(id).then((response) => {
            getAllEmployee();
        }).catch(error => {
            console.error(error);
        })
    }

    return(
        <div className="container">
            <h2 className="text-center">List of Employees</h2>
            <button className="btn btn-primary mb-2" onClick={addNewEmployee} style={{background: '#64B5F6'}}>Add Employees</button>
            <table className="table table-striped table-bordered">
                <thead >
                    <tr>
                        <th>Employee-ID</th>
                        <th>Employee-FirstName</th>
                        <th>Employee-LastName</th>
                        <th>Employee-Email</th>
                        <th>Employee-Department</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        employees.map((employee) => 
                        <tr key={employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.email}</td>
                            <td>{employee.department}</td>
                            <td>
                                <button className="btn btn-info" onClick={() => updateEmployee(employee.id)}>update</button>
                                <button className="btn btn-info btn-danger" onClick={() => removeEmployee(employee.id)} style={{marginLeft: '10px'}}>Delete</button>
                            </td>
                        </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default ListEmployeeComponent
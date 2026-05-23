import React, {useEffect, useState} from "react"
import {deleteEmployee, listofemployees} from "../Services/EmployeeService"
import {useNavigate} from "react-router-dom"

const ListEmployeeComponent = () => {

    const[employees, setEmployees] = useState([])

    const navigate = useNavigate();

    function getAllEmployee(){
        listofemployees().then((response) => {
            console.log("API Response:", response.data);      // ← add this
            console.log("Type:", typeof response.data);
            setEmployees(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    useEffect(() =>{
        getAllEmployee()
    }, [])


    function addNewEmployee() {
        navigate('/add-employee')
    }

    function updateEmployee(eeid){
        navigate(`/edit-employee/${eeid}`)
    }

    function removeEmployee(eeid){
        console.log(eeid);
        deleteEmployee(eeid).then(() => {
            getAllEmployee();
        }).catch(error => {
            console.error(error);
        })
    }

    return(
        <div className="container-fluid table-responsive">
            <h2 className="text-center">List of Employees</h2>
            <button className="btn btn-primary mb-2" onClick={addNewEmployee} style={{background: '#64B5F6'}}>Add Employees</button>
            <table className="table table-striped table-bordered">
                <thead >
                    <tr>
                        <th>Employee EEID</th>
                        <th>FirstName</th>
                        <th>LastName</th>
                        <th>Official Email</th>
                        <th>Personal Email</th>
                        <th>DOB</th>
                        <th>Gender</th>
                        <th>Status</th>
                        <th>ProfilePhotoURL</th>
                        <th>Department</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                {
                    employees.map((employee) =>
                        <tr key={employee.eeid}>
                            <td>{employee.eeid}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.officialEmail}</td>
                            <td>{employee.personalEmail}</td>
                            <td>{employee.dob}</td>
                            <td>{employee.gender}</td>
                            <td>{employee.empStatus}</td>
                            <td>
                                <img
                                    src={employee.profilePhotoURL}
                                    alt="profile"
                                    width="50"
                                    height="50"
                                />
                            </td>
                            <td>{employee.department}</td>
                            <td>
                                <button
                                    className="btn btn-info me-2 mb-2 mb-lg-0"
                                    onClick={() => updateEmployee(employee.eeid)}
                                >
                                    Update
                                </button>

                                <button
                                    className="btn btn-danger me-2 mb-2 mb-lg-0"
                                    onClick={() => removeEmployee(employee.eeid)}
                                    style={{ justifyItems: "center" }}
                                >
                                    Delete
                                </button>
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
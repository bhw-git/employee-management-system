import React, { useState,useEffect } from 'react'
import { deleteDepartment, listAllDepartments } from '../Services/DepartmentService';
import { useNavigate,Link } from 'react-router-dom';

const ListDepartmentComponent = () => {

    const [departments, setDepartments] = useState([]);


    function getAllDepartments(){
        listAllDepartments().then((response) => {
            setDepartments(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    useEffect(() =>{
        getAllDepartments()
    }, [])

    const navigate = useNavigate();

    function addNewDepartment() {
        navigate('/add-department')
    }

    function updateDepartment(id){
        navigate(`/edit-department/${id}`)
    }

    function removeDepartment(id){
        console.log(id);
        deleteDepartment(id).then(() => {
            getAllDepartments();
        }).catch(error => {
            console.error(error);
        })
    }
    
    // useEffect(() =>{
    //         getAllDepartments().then((response) => {
    //             console.log(response.data);
    //             setDepartments(response.data);
    //         }).catch(error => {
    //             console.error(error);
    //         })
    //     }, []) 

  return (
    <div>
        <div className='container'>
            <h2 className='text-center'>List of Departments</h2>
            <Link to='/add-department' onClick = {addNewDepartment} className='btn btn-primary mb-2'>Add Department</Link>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Department-ID</th>
                        <th>DepartmentName</th>
                        <th>DepartmentDescription</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        departments.map((department) =>
                            <tr key={department.id}>
                                <td>{department.id}</td>
                                <td>{department.departmentName}</td>
                                <td>{department.departmentDescription}</td>
                                <td>
                                    <button className="btn btn-info" onClick={() => updateDepartment(department.id)}>update</button>
                                    <button className="btn btn-info btn-danger" onClick={() => removeDepartment(department.id)} style={{marginLeft: '10px'}}>Delete</button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    </div>
  )
}

export default ListDepartmentComponent

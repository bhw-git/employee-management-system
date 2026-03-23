import React from 'react'
import {useState, useEffect} from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { createDepartment, getDepartment, updateDepartment } from '../Services/DepartmentService';

const DepartmentComponent = () => {

    const [departmentName, setDepartmentName] = useState('');
    const [departmentDescription, setDepartmentDescription] = useState('');

    const {id} = useParams();

    const [errors, setErrors] = useState({
        departmentName: '',
        departmentDescription: ''
    });

    
    function validateForm(){
      let valid = true;
      const errorCopy = {... errors}
      
      if(!departmentName || !departmentName.trim()){
        errorCopy.departmentName = "Department Name is required";
        valid = false;
      }
      if(!departmentDescription || !departmentDescription.trim()){
        errorCopy.departmentDescription = "Description is required";
        valid = false;
      }
      setErrors(errorCopy);
      return valid;
    }
    
    function pageTitle(){
      if(id){
        return <h2 className='text-center'>Update Department</h2>
      }else{
        return <h2 className='text-center'>Add Department</h2>
      }
    }
    
    const navigate = useNavigate();

    useEffect(() => {
            if(id){
                getDepartment(id).then((response) => {
                    setDepartmentName(response.data.departmentName || '');
                    setDepartmentDescription(response.data.departmentDescription || '');
                }).catch(error => {
                    console.error(error)
                })
            }
        }, [id])

    function saveOrUpdateDepartment(e){
      e.preventDefault();
              
      if(validateForm()){
          const department = {departmentName, departmentDescription}
          console.log(department)
          if(id){
              updateDepartment(id,department).then((response) => {
                  console.log(response.data);
                  navigate('/departments');
              }).catch(error => {
                  console.error(error);
              })
          }else{
              createDepartment(department).then((response) => {
              console.log(response.data);
              navigate('/departments')
          }).catch(error => {
              console.error(error);
          })
          }
      }
    }

  return (
    <div>
      <div className='container'>
        <br/><br/>
        <div className='row'>
          <div className='card col-md-6 offset-md-3 offset-md-3'>
            {
              pageTitle()
            }
            <div className='card-body'>
              <form>
                <div className='form-group mb-2'>
                  <label className='form-label'>Department Name: </label>
                    <input
                    type='text'
                    name='departmentName'
                    placeholder='Enter Department Name'
                    value={departmentName}
                    onChange={(e) => setDepartmentName(e.target.value)}
                    className={`form-control ${errors.departmentName ? 'is-invalid': '' }`}
                    >
                    </input>
                    {errors.departmentName && <div className='invalid-feedback'>{errors.departmentName}</div>}
                  </div>
                <div className='form-group mb-2'>
                  <label className='form-label'>Department Description: </label>
                    <input
                    type='text'
                    name='departmentDescription'
                    placeholder='Enter Department Description'
                    value={departmentDescription}
                    onChange={(e) => setDepartmentDescription(e.target.value)}
                    className={`form-control ${errors.departmentDescription ? 'is-invalid': '' }`}
                    >
                    </input>
                    {errors.departmentDescription && <div className='invalid-feedback'>{errors.departmentDescription}</div>}
                </div>
                <button className='btn btn-success' onClick={(e) => saveOrUpdateDepartment(e)} onSubmit={validateForm}>Submit</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default DepartmentComponent

import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { createEmployee, getEmployee, updateEmployee } from '../Services/EmployeeService';
import {listAllDepartments} from '../Services/DepartmentService';

const EmployeeComponent = () => {

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [officialEmail, setOfficialEmail] = useState('');
    const [personalEmail, setPersonalEmail] = useState('');
    const [dob, setDob] = useState('');
    const [gender, setGender] = useState('');
    const [empStatus, setEmpStatus] = useState('');
    const [profilePhotoUrl, setProfilePhotoUrl] = useState('');
    const [departmentId, setDepartmentId] = useState('');
    const [departments, setDepartments] = useState([]);

    useEffect(() => {
        listAllDepartments().then((response) => {
            setDepartments(response.data);
        }).catch(error => {
            console.error(error);
        })
    },[])

    const {id} = useParams();

    const [errors, setErrors] = useState({
        firstName: '',
        lastName: '',
        officialEmail: '',
        personalEmail: '',
        dob:'',
        gender:'',
        empStatus:'',
        profilePhotoUrl:'',
        department: ''
    });

    function validateForm(){
        let valid = true;
        const errorCopy = {... errors}

        if(!firstName.trim()){
            errorCopy.firstName = "First Name is required";
            valid = false;
        }
        if(!lastName.trim()){
            errorCopy.lastName = "Last Name is required";
            valid = false;
        }
        if(!officialEmail.trim()){
            errorCopy.officialEmail = "Official Email is required";
            valid = false;
        }
        if(!personalEmail.trim()){
            errorCopy.personalEmail = "Personal Email is required";
            valid = false;
        }
        if(!dob.trim()){
            errorCopy.dob = "Date of Birth is required";
            valid = false;
        }
        if(!gender.trim()){
            errorCopy.gender = "Gender is required";
            valid = false;
        }
        if(!empStatus.trim()){
            errorCopy.empStatus = "Employee Status is required";
            valid = false;
        }
        if(!profilePhotoUrl.trim()){
            errorCopy.profilePhotoUrl = "ProfilePhotoURL is required";
            valid = false;
        }
        // console.log("departmentId:", departmentId, "Type:", typeof departmentId);
        if(!departmentId){
            errorCopy.departmentId= "Department is required";
            valid = false;
        }

        setErrors(errorCopy);
        return valid;
    }

    function pageTitle(){
        if(id){
            return <h2 className='text-center'>Update Employee</h2>
        }else{
            return <h2 className='text-center'>Add Employee</h2>
        }
    }

    // const handleFirstName = (e) => setFirstName(e.target.value);
    // const handleLastName = (e) => setLastName(e.target.value);
    // const handleEmail = (e) => setEmail(e.target.value);

    const navigate = useNavigate();

    useEffect(() => {
        if(id){
            getEmployee(id).then((response) => {
                setFirstName(response.data.firstName);
                setLastName(response.data.lastName);
                setOfficialEmail(response.data.officialEmail);
                setPersonalEmail(response.data.personalEmail);
                setDob(response.data.dob);
                setGender(response.data.gender);
                setEmpStatus(response.data.empStatus);
                setProfilePhotoUrl(response.data.profilePhotoUrl);
                setDepartmentId(response.data.departmentId);
            }).catch(error => {
                console.error(error)
            })
        }
    }, [id])

    function saveorUpdateEmployee(e){
        e.preventDefault();
        
        if(validateForm()){
            
            const employee = {firstName, lastName, officialEmail, personalEmail, dob, gender, empStatus, profilePhotoUrl, departmentId}
            console.log(employee)
            if(id){
                updateEmployee(id,employee).then((response) => {
                    console.log(response.data);
                    navigate('/employees');
                }).catch(error => {
                    console.error(error);
                })
            }else{
                createEmployee(employee).then((response) => {
                console.log(response.data);
                navigate('/employees')
            }).catch(error => {
                console.error(error);
            })
            }
        }
    }

  return (
    <div className='container'>
        <br /> <br />
        <div className='row'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                {
                    pageTitle()
                }
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>First Name:</label>
                            <input
                                type='text'
                                placeholder='Enter Employee First Name'
                                name='firstName'
                                value={firstName}
                                className={`form-control ${errors.firstName ? 'is-invalid': '' }`}
                                onChange={(e) => setFirstName(e.target.value)}
                            >
                            </input>
                            {errors.firstName && <div className='invalid-feedback'>{errors.firstName}</div>}
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Last Name:</label>
                            <input
                                type='text'
                                placeholder='Enter Employee Last Name'
                                name='lastName'
                                value={lastName}
                                className={`form-control ${errors.lastName ? 'is-invalid': '' }`}
                                onChange={(e) => setLastName(e.target.value)}
                            >
                            </input>
                            {errors.lastName && <div className='invalid-feedback'>{errors.lastName}</div>}
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Official Email: </label>
                            <input
                                type='email'
                                placeholder='Enter Official Email'
                                name='OfficialEmail'
                                value={officialEmail}
                                className={`form-control ${errors.officialEmail ? 'is-invalid': '' }`}
                                onChange={(e) => setOfficialEmail(e.target.value)}
                            >
                            </input>
                            {errors.officialEmail && <div className='invalid-feedback'>{errors.officialEmail}</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Personal Email: </label>
                            <input
                                type='email'
                                placeholder='Enter Personal Email'
                                name='PersonalEmail'
                                value={personalEmail}
                                className={`form-control ${errors.personalEmail ? 'is-invalid': '' }`}
                                onChange={(e) => setPersonalEmail(e.target.value)}
                            >
                            </input>
                            {errors.personalEmail && <div className='invalid-feedback'>{errors.personalEmail}</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>DOB: </label>
                            <input
                                type='date'
                                placeholder='Enter DOB'
                                name='DOB'
                                value={dob}
                                className={`form-control ${errors.dob ? 'is-invalid': '' }`}
                                onChange={(e) => setDob(e.target.value)}
                            >
                            </input>
                            {errors.dob && <div className='invalid-feedback'>{errors.dob}</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Gender: </label>
                            <select
                                name='gender'
                                value={gender}
                                className={`form-control ${errors.gender ? 'is-invalid': '' }`}
                                onChange={(e) => setGender(e.target.value)}
                            >
                                <option value=''>-- Select Status --</option>
                                <option value='MALE'>Male</option>
                                <option value='FEMALE'>Female</option>
                                <option value='BINARY'>Binary</option>
                                <option value='OTHER'>Other</option>
                            </select>
                            {errors.gender && <div className='invalid-feedback'>{errors.gender}</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Employee Status: </label>
                            <select
                                name='EmployeeStatus'
                                value={empStatus}
                                className={`form-control ${errors.empStatus ? 'is-invalid' : ''}`}
                                onChange={(e) => setEmpStatus(e.target.value)}
                            >
                                <option value=''>-- Select Status --</option>
                                <option value='ACTIVE'>Active</option>
                                <option value='INACTIVE'>Inactive</option>
                                <option value='ON_LEAVE'>On Leave</option>
                                <option value='TERMINATED'>Terminated</option>
                            </select>
                            {errors.empStatus && <div className='invalid-feedback'>{errors.empStatus}</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>ProfilePhotoURL: </label>
                            <input
                                type='text'
                                placeholder='Enter ProfilePhotoURL'
                                name='profilePhotoURL'
                                value={profilePhotoUrl}
                                className={`form-control ${errors.profilePhotoUrl ? 'is-invalid': '' }`}
                                onChange={(e) => setProfilePhotoUrl(e.target.value)}
                            >
                            </input>
                            {errors.profilePhotoUrl && <div className='invalid-feedback'>{errors.profilePhotoUrl}</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Department Name: </label>
                            <select
                                className={`form-control ${errors.department ? 'is-invalid': '' }`}
                                value={departmentId}
                                onChange={(e) => setDepartmentId(e.target.value)}
                            >
                                <option value="Select Department">Select Department</option>
                                {
                                    departments.map(department => 
                                        <option key={department.id} value={department.id}>{department.departmentName}</option>
                                    )
                                }
                            </select>
                            {errors.department && <div className='invalid-feedback'>{errors.department}</div>}
                        </div>
                        <button className='btn btn-success' onClick={saveorUpdateEmployee} onSubmit={validateForm}>Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default EmployeeComponent

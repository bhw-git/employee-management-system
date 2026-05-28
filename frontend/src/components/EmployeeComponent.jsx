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
    const [profilePhotoURL, setProfilePhotoURL] = useState(null);
    const [existingPhotoURL, setExistingPhotoURL] = useState('');
    const [role, setRole] = useState('');
    const [departmentId, setDepartmentId] = useState('');
    const [departments, setDepartments] = useState([]);

    //List all Departments
    useEffect(() => {
        listAllDepartments().then((response) => {
            setDepartments(response.data);
        }).catch(error => {
            console.error(error);
        })
    },[])

    //Record the employee ID
    const {id} = useParams();

    //Error handling
    const [errors, setErrors] = useState({
        firstName: '',
        lastName: '',
        officialEmail: '',
        personalEmail: '',
        dob:'',
        gender:'',
        empStatus:'',
        profilePhotoURL:'',
        role:'',
        departmentId: ''
    });

    //Validate Form
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
        if(!dob){
            errorCopy.dob = "Date of Birth is required";
            valid = false;
        }
        if(!gender){
            errorCopy.gender = "Gender is required";
            valid = false;
        }
        if(!empStatus){
            errorCopy.empStatus = "Employee Status is required";
            valid = false;
        }
        if(!role){
            errorCopy.role = "Role is required";
            valid = false;
        }
        // if(!profilePhotoUrl.value){
        //     errorCopy.profilePhotoUrl = "ProfilePhotoURL is required";
        //     valid = false;
        // }
        if(!departmentId){
            errorCopy.departmentId= "Department is required";
            valid = false;
        }
        setErrors(errorCopy);
        return valid;
    }

    //Title of the Page based on the button we choose
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

    //Handle employees updation data based on the employee ID
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
                setExistingPhotoURL(response.data.profilePhotoURL);
                setRole(response.data.role);
                setDepartmentId(response.data.departmentId);
            }).catch(error => {
                console.error(error)
            })
        }
    }, [id])
    
    
    //Handles Profile photo file upload
    const handleFileChange = (event) => {
        setProfilePhotoURL(event.target.files[0]);
    };
    
    function saveorUpdateEmployee(e){
        e.preventDefault();
        
        const formData = new FormData();
        if(validateForm()){
            //Handle Multipart Form Data
            formData.append("firstName", firstName);
            formData.append("lastName",lastName);
            formData.append("officialEmail", officialEmail);
            formData.append("personalEmail", personalEmail);
            formData.append("dob", dob);
            formData.append("gender", gender);
            formData.append("empStatus", empStatus);
            formData.append("departmentId", departmentId);
            formData.append("role",role);
            if(profilePhotoURL instanceof File){
                formData.append("profilePhoto",profilePhotoURL);
            }
            for(let pair of formData.entries()){
                console.log(pair[0],pair[1]);
            }

            // const employee = {firstName, lastName, officialEmail, personalEmail, dob, gender, empStatus, profilePhotoURL, departmentId}
            // console.log(employee)
            if(id){
                updateEmployee(id,formData).then((response) => {
                    console.log(response.data);
                    navigate('/employees');
                }).catch(error => {
                    console.error(error);
                })
            }else{
                createEmployee(formData).then((response) => {
                console.log(response.data);
                navigate('/employees')
            }).catch(error => {
                console.error(error);
            })
            }
        }
    }

    function returnHome(){
        navigate('/employees')
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
                            <label className='form-label'>Role: </label>
                            <select
                                name='role'
                                value={role}
                                className={`form-control ${errors.role ? 'is-invalid' : ''}`}
                                onChange={(e) => setRole(e.target.value)}
                            >
                                <option value=''>-- Select Status --</option>
                                <option value='MANAGER'>Manager</option>
                                <option value='EMPLOYEE'>Employee</option>
                                <option value='HR'>HR</option>
                                <option value='ADMIN'>Admin</option>
                            </select>
                            {errors.role && <div className='invalid-feedback'>{errors.role}</div>}
                        </div>

                        <div className='form-group mb-2 mb-3'>
                            <label className='form-label'>Profile Photo </label>
                            <input
                                type='file'
                                placeholder='Upload Profile Photo'
                                accept="image/*"
                                className={`form-control ${errors.profilePhotoURL ? 'is-invalid': '' }`}
                                onChange={handleFileChange}
                            >
                            </input>
                            {/* To preview the uploaded image in the form  */}
                            {
                                profilePhotoURL && <img 
                                src = {typeof profilePhotoURL === "string" ? `http://localhost:8080/uploads/profile/${profilePhotoURL}` : URL.createObjectURL(profilePhotoURL)}
                                width="120"
                                className='mt-3 rounded'
                                alt='preview uploaded photo' />
                            }
                            {errors.profilePhotoURL && <div className='invalid-feedback'>{errors.profilePhotoURL}</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Department Name: </label>
                            <select
                                className={`form-control ${errors.departmentId ? 'is-invalid': '' }`}
                                value={departmentId}
                                onChange={(e) => setDepartmentId(e.target.value)}
                            >
                                <option value="">Select Department</option>
                                {
                                    departments.map(department => 
                                        <option key={department.id} value={department.id}>{department.departmentName}</option>
                                    )
                                }
                            </select>
                            {errors.departmentId && <div className='invalid-feedback'>{errors.departmentId}</div>}
                        </div>
                        <button className='btn btn-success' onClick={saveorUpdateEmployee} onSubmit={validateForm}>Submit</button>
                        <button className='btn btn-danger' onClick={returnHome}>Cancel</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
  )
}

export default EmployeeComponent

import React, { useState, useEffect } from "react";

export const UserSettings = ({ onLogout, onFormSwitch, userInfo }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [company, setCompany] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');


    const token = localStorage.getItem('token'); 

    const getUserData = () => {  
        handleReset()
        const token = localStorage.getItem('token');

        fetch('/api/dashboard', {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                setEmail(data.email);
                setFirstName(data.firstName);
                setLastName(data.lastName);
                setCompany(data.company);
                setPhoneNumber(data.phoneNumber);
                console.log('Data:', data);

            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    useEffect(() => {
        handleReset()
        getUserData()
    }, []);

    const handleLogout = () => {
        onLogout();
    };

    const handleFormSwitch = (formName) => {
        onFormSwitch(formName);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(email);
    }

    const handleReset = () => {
        setEmail('');
        setPassword('');
        setFirstName('');
        setLastName('');
        setCompany('');
        setPhoneNumber('');
    };

    return (
        <><div className="auth-form-container">
            <h2>User Settings</h2>
            <form className="dashboard-form">
                <label htmlFor="email">Email:</label>
                <input type="email" id="email" name="email" value={email} />
                <label htmlFor="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" value={firstName} readOnly />
                <label htmlFor="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" value={lastName} readOnly />
                <label htmlFor="company">Company:</label>
                <input type="text" id="company" name="company" value={company} readOnly />
                <label htmlFor="phoneNumber">Phone Number:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" value={phoneNumber} readOnly />
            </form>
            <button onClick={() => handleFormSwitch('changePassword')}>Update user info</button>
            <button onClick={() => handleFormSwitch('updateUserInfo')}>Dashboard</button>
            <button onClick={() => handleFormSwitch('Stream')}>Stream</button>
        </div>
            <div className="logout-button">
                <button onClick={handleLogout}>Logout</button>
            </div></>
    )
}

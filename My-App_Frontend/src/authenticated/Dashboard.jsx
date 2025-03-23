import React, { useState } from "react";
import { UserSettings } from "./UserSettings";
import { sendLogout } from './Logout';
import { Stream } from "./Stream";

export const Dashboard = ({ onLogout, onFormSwitch, userInfo }) => {
    const [currentForm, setCurrentForm] = useState('Dashboard');
    const [email, setEmail] = useState(userInfo.email);
    const [password, setPassword] = useState(userInfo.password);
    const [firstName, setFirstName] = useState(userInfo.firstName);
    const [lastName, setLastName] = useState(userInfo.lastName);
    const [company, setCompany] = useState(userInfo.company);
    const [phoneNumber, setPhoneNumber] = useState(userInfo.phoneNumber);

    const [videoUrl, setVideoUrl] = useState(null);

    const getUserData = () => {  //
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

        fetch('/api/videoData', {
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
                setVideoUrl(data.videoUrl);

            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    const handleLogout = () => {
        sendLogout();
        onLogout();
    };

    const handleFormSwitch = (UserSettings) => {
        onFormSwitch(UserSettings);
    };

    const toggleForm = (formName) => {
        setCurrentForm(formName);
    }

    const handleUserSettings = () => {
        console.log("handleUserSettings called");
        setCurrentForm("UserSettings");
    };

    const handleReset = () => {
        setEmail('');
        setPassword('');
        setFirstName('');
        setLastName('');
        setCompany('');
        setPhoneNumber('');
    };

    return (
        <>
            {currentForm === "UserSettings" ? (
                <UserSettings onFormSwitch={toggleForm} handleUserSettings={handleUserSettings} />
            ) : currentForm === "Stream" ? (
                <Stream onFormSwitch={toggleForm} />
            ) : (
                <div className="auth-form-container">
                    <h2>Dashboard</h2>
                    <form className="dashboard-form">
                        <label htmlFor="email">Email:</label>
                        <input type="email" id="email" name="email" value={email} readOnly />
                        <label htmlFor="firstName">First Name:</label>
                        <input type="text" id="firstName" name="firstName" value={firstName} readOnly />
                        <label htmlFor="lastName">Last Name:</label>
                        <input type="text" id="lastName" name="lastName" value={lastName} readOnly />
                        <label htmlFor="company">Company:</label>
                        <input type="text" id="company" name="company" value={company} readOnly />
                        <label htmlFor="phoneNumber">Phone Number:</label>
                        <input type="text" id="phoneNumber" name="phoneNumber" value={phoneNumber} readOnly />
                        <label htmlFor="video">Video:</label>
                        <input type="text" id="video" name="video" value={phoneNumber} readOnly />
                        <video src="Streaming\demo\src\main\resources\videos" width="720" height="480" controls preload="none"></video>
                    </form>
                    <button onClick={() => toggleForm("UserSettings")}>Update User Info</button>
                    <button onClick={() => toggleForm("Stream")}>Stream</button>
                    <button onClick={() => toggleForm("changePassword")}>Change Password</button>
                </div>
            )}

            <div className="logout-button">
                <button onClick={handleLogout}>Logout</button>
            </div>
        </>
    );

}

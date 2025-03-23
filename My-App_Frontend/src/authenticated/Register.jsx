import React, { useState } from "react";


export const Register = (props) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [company, setCompany] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(email);

        fetch('/api/register', {
            method: 'POST',
            body: JSON.stringify({ email, password, firstName, lastName, company, phoneNumber }),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log('Registration successful:', data);
                props.onFormSwitch('login')
                handleReset()
            })
            .catch(error => {
                console.error('Registration failed. User already exists:', error);
                alert('Registration failed. User ' + '"' + email + '"' + ' already exists');
            });

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
        <div className="auth-form-container">
            <h2>Register</h2>
            <form className="register-form" onSubmit={handleSubmit}>
                <label htmlFor="firstName">Fist name</label>
                <input value={firstName} name="fistName" onChange={(e) => setFirstName(e.target.value)} id="fistName" placeholder="Fist name" />
                <label htmlFor="lastName">Last name</label>
                <input value={lastName} name="lastName" onChange={(e) => setLastName(e.target.value)} id="lastName" placeholder="Last name" />
                <label htmlFor="email">E-Mail</label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="youremail@gmail.com" id="email" name="email" />
                <label htmlFor="password">Password</label>
                <input value={password} onChange={(e) => setPassword(e.target.value)} type="password" placeholder="********" id="password" name="password" />
                <label htmlFor="company">Company</label>
                <input value={company} onChange={(e) => setCompany(e.target.value)} type="company" placeholder="Google" id="company" name="company" />
                <label htmlFor="phoneNumber">Phone number</label>
                <input value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} type="phoneNumber" placeholder="0761234567" id="phoneNumber" name="phoneNumber" />
                <button type="submit">Register</button>
            </form>
            <button className="link-btn" onClick={() => props.onFormSwitch('login')}>Already have an account? Login here.</button>
        </div>
    )
}

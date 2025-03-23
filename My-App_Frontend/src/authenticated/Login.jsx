import React, { useState } from "react";
import Button from 'react-bootstrap/Button';
import 'bootstrap/dist/css/bootstrap.min.css';

export const Login = (props) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');


    const handleSubmit = (e) => {  //
        e.preventDefault(); 
        console.log(email);

        fetch('/api/login', {
            method: 'POST',
            body: JSON.stringify({ email, password }),
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
                console.log('Login successful:', data);
                localStorage.clear();
                localStorage.setItem('token', data.token);

                alert("token: " + localStorage.getItem('token')); 
                props.handleLogin()

            })
            .catch(error => {
                console.error('Login failed. User or password not correct:', error);
                alert('Login failed. Please check your email and password.');
            });
    }

    return (
        <div className="auth-form-container">
            <h2>Login</h2>
            <form className="login-form" onSubmit={handleSubmit}>
                <label htmlFor="email">email</label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="youremail@gmail.com" id="email" name="email" />
                <label htmlFor="password">password</label>
                <input value={password} onChange={(e) => setPassword(e.target.value)} type="password" placeholder="********" id="password" name="password" />
                <button type="submit">Log In</button>
                <a href="/" className="btn btn-info">info Bootstrap tag</a>
            </form>
            <button className="link-btn" onClick={() => props.onFormSwitch('register')}>Don't have an account? Register here.</button>
        </div>
    )
}
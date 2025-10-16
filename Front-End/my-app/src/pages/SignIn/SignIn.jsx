import React, { useState } from 'react';
import "./SignIn.css"
import {Container, Button, Row, Col, Form} from "react-bootstrap";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";
import { login } from "../../services/auth";
import { useNavigate } from 'react-router-dom';

const SignIn = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const onChange = (e) => {
        const { name, value } = e.target;
        if (name === 'username') {
            setUsername(value);
        } else {
            setPassword(value);
        }
    };

    const onLoginClick = () => {
        if (username.length === 0 || password.length === 0) {
            setError("Fill in blanks fields");
        } else if (username.length < 3 || username.length > 30) {
            setError("User name length must not be less than 3 and greater than 30");
        } else if (password.length < 4 || password.length > 30) {
            setError("Password length must not be less than 4 and greater than 30");
        } else {
            login(username,password)
                .then( (data) => {
                    if(data && data.username) {
                        navigate("/news");
                    } else {
                        setError("User not found.");
                    }
                })
                .catch( () => {
                    setError("Login failed. Please check your credentials.");
                });
        }
    };

    return (
        <Container>

            <Header />

            <Row>
                <Col md="4">
                    <div className="body-div">
                        <h1 className="login-heading">Login</h1>
                        <Form className="form">
                            <Form.Group controlId="usernameId" className="form-group">
                                <Form.Control
                                    type="text"
                                    name="username"
                                    placeholder="Username"
                                    value={username}
                                    onChange={onChange}
                                />
                            </Form.Group>
                            <Form.Group controlId="passwordId" className="form-group">
                                <Form.Control
                                    type="password"
                                    name="password"
                                    placeholder="Password"
                                    value={password}
                                    onChange={onChange}
                                />
                            </Form.Group>
                        </Form>
                        <div className="div-button">
                            <Button color="primary" className="login-button" onClick={onLoginClick}>SIGN IN</Button>
                            <p className="login-error">{error}</p>
                        </div>
                    </div>
                </Col>
            </Row>

            <Footer />

        </Container>
    );
}
export default SignIn;
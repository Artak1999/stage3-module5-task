import React, { useState } from 'react';
import "./signin.css"
import {Container, Button, Row, Col, Form, FormControl} from "react-bootstrap";
import Header from "../header/Header";
import Footer from "../footer/Footer";

const SignIn = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const onChange = (e) => {
        const { name, value } = e.target;
        if (name === 'username') {
            setUsername(value);
        } else if (name === 'password') {
            setPassword(value);
        }
    };

    const onLoginClick = () => {
        const userData = { username, password };

        if (userData.username.length === 0 || userData.password.length === 0) {
            setError("Fill in blanks fields");
        } else if (userData.username.length < 3 || userData.username.length > 30) {
            setError("User name length must not be less than 3 and greater than 30");
        } else if (userData.password.length < 4 || userData.password.length > 30) {
            setError("Password length must not be less than 4 and greater than 30");
        } else {
            setError('');
            window.location.href='/news'
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
                            <Form.Group controlId="usernameId">
                                <Form.Control
                                    type="text"
                                    name="username"
                                    placeholder="Username"
                                    value={username}
                                    onChange={onChange}
                                />
                                <FormControl.Feedback type="invalid"></FormControl.Feedback>
                            </Form.Group>
                            <br/>
                            <Form.Group controlId="passwordId">
                                <Form.Control
                                    type="password"
                                    name="password"
                                    placeholder="Password"
                                    value={password}
                                    onChange={onChange}
                                />
                                <Form.Control.Feedback type="invalid"></Form.Control.Feedback>
                            </Form.Group>
                            <br/><br/>
                        </Form>
                        <center>
                            <Button color="primary" className="login-button" onClick={onLoginClick}>SIGN IN</Button>
                            <p className="login-error">{error}</p>
                        </center>
                    </div>
                </Col>
            </Row>

            <Footer />

        </Container>
    );
}
export default SignIn;
import React, { Component } from "react";
import "./signin.css"
import {
    Container,
    Button,
    Row,
    Col,
    Form,
    FormControl
} from "react-bootstrap";
import Header from "../header/Header";
import Footer from "../footer/Footer";

class SignIn extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: ""
        };
    }
    onChange = e => {
        this.setState({ [e.target.name]: e.target.value });
    };

    onLoginClick = () => {
        const userData = {
            username: this.state.username,
            password: this.state.password
        };
        if(userData.username.length === 0 || userData.password.length === 0){
            document.getElementById('aboutError').innerHTML = "Fill in blanks fields";
        }
        else if(userData.username.length < 3 || userData.username.length > 30){
            document.getElementById('aboutError').innerHTML = "User name length must not be less than 3 and greater than 30";
        }
        else if(userData.password.length < 4 || userData.password.length > 30){
            document.getElementById('aboutError').innerHTML = "Password length must not be less than 4 and greater than 30";
        }
        else{
            window.location.href='/newspage'
        }
    };
    componentDidMount() {
        document.body.style.backgroundColor = "#a9bffa"
    }
    render() {
        return (
            <Container>

                <Header />

                <Row className="row">
                    <Col md="4">
                        <div className="body-div">
                            <br/><br/><h1 style={{textAlign: "center"}}>Login</h1><br/><br/>
                            <Form className="form">
                                <Form.Group controlId="usernameId">
                                    <Form.Control
                                        type="text"
                                        name="username"
                                        placeholder="Username"
                                        value={this.state.username}
                                        onChange={this.onChange}
                                    />
                                    <FormControl.Feedback type="invalid"></FormControl.Feedback>
                                </Form.Group>
                                <br/>
                                <Form.Group controlId="passwordId">
                                    <Form.Control
                                        type="password"
                                        name="password"
                                        placeholder="Password"
                                        value={this.state.password}
                                        onChange={this.onChange}
                                    />
                                    <Form.Control.Feedback type="invalid"></Form.Control.Feedback>
                                </Form.Group>
                                <br/><br/>
                            </Form>
                            <center>
                                <Button color="primary" style={{width: 250, height: 50}} onClick={this.onLoginClick}>SIGN IN</Button>
                                <br/><br/>
                                <p style={{color: "red"}} id="aboutError"></p>
                            </center>
                        </div>
                    </Col>
                </Row>

                <Footer />

            </Container>
        );
    }
}
export default SignIn;
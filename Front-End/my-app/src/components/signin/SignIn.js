import React, { Component } from "react";
import "./signin.css"
import {
    Container,
    Button,
    Row,
    Col,
    Form,
    FormControl,
    Nav,
    Navbar,
} from "react-bootstrap";

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
                <header className="header">
                    <Navbar collapseOnSelect expand="lg">
                        <Container>
                            <Navbar.Brand href="#home">
                                <img
                                    src={require("../img/news-book.png")}
                                    className="d-inline-block align-top"
                                    alt="React Bootstrap logo"
                                />
                            </Navbar.Brand>
                            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                            <Navbar.Collapse id="responsive-navbar-nav">
                                <Nav className="me-auto">
                                    <Nav.Link href="/" className="navbar">HOME</Nav.Link>
                                    <Nav.Link href="/" className="navbar">NEWS</Nav.Link>
                                    <Nav.Link href="/" className="navbar">ABOUT</Nav.Link>
                                </Nav>
                                <Nav>
                                    <Nav.Link href="/" className="navbar">SIGN IN</Nav.Link>
                                    <Nav.Link href="#signup" className="navbar">SIGN UP</Nav.Link>
                                </Nav>
                            </Navbar.Collapse>
                        </Container>
                    </Navbar>
                </header>

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

                <footer className="footer">
                    <h5 style={{marginTop: "1%"}}>&#169; 2022 MJC School Student. All Rights Reserved</h5>
                </footer>

            </Container>
        );
    }
}
export default SignIn;
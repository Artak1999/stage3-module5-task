import axios from "axios";

const API_URL = "http://localhost:8080/api/v1/auth/";

export const register = (username, password, email) => {
    return axios.post(API_URL + "register", {
        username,
        password,
        email
    });
};

export const login = (username, password) => {
    return axios
        .post(API_URL + "authenticate", {
            username,
            password
        })
        .then((response) => {
            if (response.data.username) {
                localStorage.setItem("username", JSON.stringify(response.data));
            }
            return response.data;
        });
};

export const logout = () => {
    return axios
        .post(API_URL + "logout")
        .then((response) => {
            if(response.status === 200) {
                localStorage.removeItem("username");
                window.location.href = "/";
            }
            return response.data;
        })
};

export const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("username"));
};
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
                localStorage.setItem("user", JSON.stringify(response.data));
            }
            return response.data;
        });
};

export const logout = () => {
    localStorage.removeItem("user");
    return axios
        .post(API_URL + "")                      // need to implement url
        .then((response) => {
            return response.data;
        });
};

export const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"));
};
import http from "./httpService";
import config from "./config.json";

export const loginUser = (user) => {
    return http.post(`${config.localapi}/login`, user);
};
export const logoutUser = () => {
    return http.post(`${config.localapi}/logout`);
};
export const registerUser = (data) => {
    return http.post(`${config.localapi}/signup`,data);
};
export const deleteUser = (slug) => {
    return http.delete(`${config.localapi}/signup/?slug=${slug}`);
};
export const userData = () => {
    return http.get(`${config.localapi}/user/`);
};
export const allUserData = () => {
    return http.get(`${config.localapi}/user/all/`);
};
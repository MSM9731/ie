import axios from "axios";
import { toast } from "react-toastify";

axios.defaults.headers.post["Content-Type"] = "application/json";

// const token = localStorage.getItem("token");

axios.interceptors.response.use(null, error => {
    const expectedErrors =
        error.response &&
        error.response.status >= 400 &&
        error.response.status < 500;
    if (!expectedErrors) {
        toast.error("Error from server!!", {
            position: "top-right",
            closeOnClick: true
        });
    }
   
    return Promise.reject(error);
});

export default {
    get: axios.get,
    post: axios.post,
    put: axios.put,
    delete: axios.delete
};

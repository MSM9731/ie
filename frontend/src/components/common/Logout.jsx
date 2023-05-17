import React, { useEffect } from "react";
import { withRouter } from "react-router-dom";

import { errorMessage, successMessage } from "./../../utils/message";
import { logoutUser } from "../../services/userService";

const Logout = ({ history }) => {
    useEffect(() => {
        (async () => {
            await logoutUser().then((ans) => {
                successMessage(`Goodbye ${atob(localStorage.getItem("user"))}`);
                localStorage.removeItem('user');
                history.push("/login");
            }).catch((err) => {
                localStorage.removeItem('user');
                history.push("/login");
                errorMessage("somthing wrong!");
            })

        })();


    }, []);

    return null;
};

export default withRouter(Logout);
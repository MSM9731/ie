import React, { useState, useRef, Fragment } from "react";
import SimpleReactValidator from "simple-react-validator";
import { successMessage, errorMessage } from "./../../utils/message";
import { loginUser } from "../../services/userService";
import { withRouter } from "react-router";
import { Link } from "react-router-dom";

const Login = ({ history  }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const resetStates = () => {
    setPassword("");
    setUsername("");
  };

  const [, forceUpdate] = useState();
  const validator = useRef(new SimpleReactValidator({element: message => <div style={{ color: "red", marginTop: "0px" }}>{message}</div>}));

  const handleLogin = async (event) => {
    event.preventDefault();
    const user = { username, password };
    try {
      if (validator.current.allValid()) {
        const { status, data } = await loginUser(user);
        if (status === 200) {
          console.log(data);
          successMessage(`Hi ${username}:)`);
          localStorage.setItem("user", btoa(username));
          history.replace("/home");
        } else {
          errorMessage("Somthings wron...");
        }
      } else {
        validator.current.showMessages();
        forceUpdate(1);
      }
    } catch (ex) {
      if (ex.response.status == 400) {
        const register = ex.response.data;
        console.log(register);
        errorMessage(register.data);
      }
    }
  };
  return (
    <div class="container -fluid py-5 h-100 mb80">
      <div class="row d-flex align-items-center justify-content-center h-100">
        <div class="col-md-8 col-lg-7 col-xl-6">
          <img
            src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
            class="img-fluid"
            alt="Phone image"
          />
        </div>
        <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
          <form onSubmit={handleLogin}>
            <div class="form-outline mb-4">
              <input
                type="text"
                id="form1Example13"
                class="form-control form-control-lg"
                placeholder="username"
                value={username}
                onChange={(e) => {
                  setUsername(e.target.value);
                  validator.current.showMessageFor("username");
                }}
              />
              {validator.current.message("username", username, "required")}
            </div>

            <div class="form-outline mb-4">
              <input
                type="password"
                id="form1Example23"
                class="form-control form-control-lg"
                placeholder="password"
                value={password}
                onChange={(e) => {
                  setPassword(e.target.value);
                  validator.current.showMessageFor("password");
                }}
              />
              {validator.current.message("password", password, "required")}
            </div>

            <div class="d-flex justify-content-around align-items-center mb-4">
              <Link to="/signup" className="text-gary">
                Doesn't have account?
              </Link>
            </div>

            <button type="submit" class="btn btn-light-brown2 btn-lg btn-block">
              Sign in
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default withRouter(Login);

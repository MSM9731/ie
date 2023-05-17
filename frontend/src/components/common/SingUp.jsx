import React from "react";
import { Link } from "react-router-dom/cjs/react-router-dom.min";

const SingUp = () => {
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
          <form>
          <div class="form-outline mb-4">
              <input
                type="email"
                id="form1Example13"
                class="form-control form-control-lg"
                placeholder="email"
              />
              {/* <label class="form-label" for="form1Example13">Email address</label> */}
            </div>
            <div class="form-outline mb-4">
              <input
                type="text"
                id="form1Example13"
                class="form-control form-control-lg"
                placeholder="username"
              />
              {/* <label class="form-label" for="form1Example13">Email address</label> */}
            </div>

            <div class="form-outline mb-4">
              <input
                type="password"
                id="form1Example23"
                class="form-control form-control-lg"
                placeholder="password"
              />
              {/* <label class="form-label" for="form1Example23">Password</label> */}
            </div>
            <div class="form-outline mb-4">
              <input
                type="text"
                id="form1Example23"
                class="form-control form-control-lg"
                placeholder="address"
              />
              {/* <label class="form-label" for="form1Example23">Password</label> */}
            </div>
            <div class="form-outline mb-4">
              <input
                type="date"
                id="form1Example23"
                class="form-control form-control-lg"
                placeholder="birthDate"
              />
              {/* <label class="form-label" for="form1Example23">Password</label> */}
            </div>
            <div class="d-flex justify-content-around align-items-center mb-4">
              <Link to="/login" className="text-gary">
                Do you have account?
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

export default SingUp;

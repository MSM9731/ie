import React, { useEffect, useState } from "react";
import { Switch, Route, Redirect } from "react-router-dom";

import PublicLayout from "../components/layouts/PublicLayout";
import Home from "../components/common/Home";
import Product from "../components/common/Product";
import User from "../components/common/User";
import Login from "../components/common/Login";
import SingUp from "../components/common/SingUp";
import Provider from "../components/common/Provider";
import Logout from "../components/common/Logout";
import SearchContext from "../components/context/searchContext";

const Baloot = () => {
  

  return (
    <PublicLayout>
      <Switch>
        <Route
          path="/"
          exact
          render={() =>
            localStorage.getItem("user") != null ? (
              <Redirect to="/home" />
            ) : (
              <Redirect to="/login" />
            )
          }
        />
        <Route
          path="/home"
          exact
          render={() =>
            localStorage.getItem("user") != null ? (
                <SearchContext>
                    <Home />
                </SearchContext>
            ) : (
              <Redirect to="/login" />
            )
          }
        />
        <Route path="/user" exact render={() => <User />} />
        <Route
          path="/product/:id"
          exact
          render={() =>
            localStorage.getItem("user") != null ? (
              <Product />
            ) : (
              <Redirect to="/login" />
            )
          }
        />
        <Route
          path="/provider/:id"
          exact
          render={() =>
            localStorage.getItem("user") != null ? (
              <Provider />
            ) : (
              <Redirect to="/login" />
            )
          }
        />
        <Route
          path="/login"
          exact
          render={() =>
            localStorage.getItem("user") == null ? (
              <Login />
            ) : (
              <Redirect to="/" />
            )
          }
        />
        <Route
          path="/logout"
          exact
          render={() =>
            localStorage.getItem("user") != null ? (
              <Logout />
            ) : (
              <Redirect to="/login" />
            )
          }
        />
        <Route
          path="/signup"
          exact
          render={() =>
            localStorage.getItem("user") == null ? (
              <SingUp />
            ) : (
              <Redirect to="/" />
            )
          }
        />
      </Switch>
    </PublicLayout>
  );
};

export default Baloot;

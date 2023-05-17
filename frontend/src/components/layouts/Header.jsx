import React, { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom/cjs/react-router-dom.min';
// import { Link } from "react-router-dom";

const Header = () => {

    const location = useLocation();
    const [currentPath, setCurrentPath] = useState();
    useEffect(() => {
        setCurrentPath(location.pathname);
        console.log(currentPath);
    }, [location]);
    return (
        <nav class="navbar navbar-expand-lg bg-white fw-bold box-shadow rounded-0 sticky-top">
            <div class="container-fluid">
                <Link class="navbar-brand text-light-brown logo-text align-center hstack" to="/home">
                    <img src="/img/logo.png" alt="Logo" width="72" height="64"
                        class="logo-icon d-inline-block align-text-top " />
                    <span class="ms-2">Baloot</span>
                </Link>
                {currentPath == "/home" ?
                    (
                        <div class="row- h--100 justify-content-center align-items-center center mx-auto">
                            <div class="input--group">
                                <div class="input-group searchbar- border border-light-brown rounded-pill bg-very-light-brown">
                                    <div class="dropdown">
                                        <button class="btn btn-outline--secondary dropdown-toggle rounded-pill buttonsearch" type="button" data-toggle="dropdown" aria-expanded="false">
                                            Action
                                        </button>
                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" href="#">Action</a></li>
                                            <li><a class="dropdown-item" href="#">Another action</a></li>
                                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                                        </ul>
                                    </div>
                                    <input type="search" class="form-control bg-transparent border-0 border-0 shadow-none" />
                                    <button type="submit" class="input-group-text bg-transparent border-0 rounded-pill"><img src="/icon/search.svg" alt="icon" height="20" width="20" /></button>
                                </div>
                                {/* <div class="input-group-prepend searchbar">
                        <button class="btn btn-outline-secondary dropdown-toggle buttonsearch" type="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">name</button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#">Action</a>

                        </div>
                    </div>
                    <input type="text" class="form-control  my-icon" placeholder="search your product ..."/> */}
                            </div>
                        </div>
                    ) : null}
                <div class="collapse navbar--collapse d-flex fs-4" id="navbarSupportedContent">
                    <ul class="navbar-nav mx--auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <Link class="nav-link text-brown" aria-current="page" to="/user">#{atob(localStorage.getItem("user"))}</Link>
                        </li>
                        <li class="nav-item">
                            <Link class="nav-link text-bg-brown rounded-15px fw-normal px-3" aria-current="page" to="/user">
                                Cart <span class="ms-4">1</span>
                            </Link>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
}

export default Header;

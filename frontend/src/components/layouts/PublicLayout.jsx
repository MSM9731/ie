import React, { Fragment } from "react";

import { Link, withRouter } from "react-router-dom";
import Header from "./Header";
import Footer from "./Footer";



const PublicLayout = ({ children}) => {
    return (

        <React.Fragment >
            <Header />
            <main>
                {children}
            </main>
            <Footer/>
        </React.Fragment>

    );
};

export default withRouter(PublicLayout);

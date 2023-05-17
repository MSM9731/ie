import React from "react";
import { BrowserRouter } from "react-router-dom";
import { Helmet } from 'react-helmet';
import Baloot from "./Baloot";
import { ToastContainer } from "react-toastify";

const App = () => {
    return (
        <BrowserRouter>
           
            {/* <Helmet>
                <title>Baloot</title>
            </Helmet> */}
            <Baloot />
        
            <ToastContainer />
        </BrowserRouter >
    );
};

export default App;

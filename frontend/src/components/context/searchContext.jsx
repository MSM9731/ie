import React, { useState, useRef } from "react";
import { withRouter } from "react-router";
import { context } from "./contex";

const SearchContext = ({ children, history }) => {
  const [commodities, setCommodities] = useState([]);
  const [tempCommodities, setTempCommodities] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [perPage] = useState(12);
  const [countPages, setCountPages] = useState(1);

  const handlePageChange = (e, page) => {
    setCurrentPage(page);
  };

  

  return (
    <context.Provider
      value={{
        commodities,
        setCommodities,
        tempCommodities,
        setTempCommodities,
        currentPage,
        setCurrentPage,
        perPage,
        countPages,
        setCountPages,
        handlePageChange
      }}
    >
      {children}
    </context.Provider>
  );
};

export default withRouter(SearchContext);

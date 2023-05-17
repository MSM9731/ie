import { createContext } from "react";

export const context = createContext({ 
    commodities: {},
    setCommodities: () => { },
    tempCommodities: {},
    setTempCommodities: () => { },
    currentPage: {},
    setCurrentPage: () => { },
    perPage: {},
    countPages: {},
    setCountPages: () => { },
    handlePageChange: () => { },
});

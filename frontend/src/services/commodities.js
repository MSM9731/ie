import http from "./httpService";
import config from "./config.json";

export const getCommodities = () => {
    return http.get(`${config.localapi}/commodities`);
};
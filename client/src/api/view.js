import http from "./http";

const url = "http://localhost:8080/p";

export default {
    makePayment(data, handler) {
        http.post(`${url}/payment`, data, handler);
    },
    makeAdjust(data, handler) {
        http.put(`${url}/adjust`, data, handler);
    }
}

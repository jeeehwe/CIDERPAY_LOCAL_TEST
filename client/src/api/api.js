import http from "./http";

const url = "http://localhost:8080/oapi/payment";

export default {
    paymentRequestCancel(data, handler) {
        http.post(`${url}/request/cancel`, data, handler);
    },
    paymentCancel(data, handler) {
        http.post(`${url}/cancel`, data, handler);
    },
    cancelRequestAfterAdjust(data, handler) {
        http.post(`${url}/cancelRequest`, data, handler);
    }

}

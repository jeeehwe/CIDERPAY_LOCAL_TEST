import axios from 'axios';

export default {
    post(url, data, handler) {
        axios
            .post(url, data)
            .then(res => handler(res))
    },
    get(url, handler) {
        axios
            .get(url)
            .then(res => handler(res))
    },
    put(url, data, handler) {
        axios
            .put(url, data)
            .then(res => handler(res))
    },
}

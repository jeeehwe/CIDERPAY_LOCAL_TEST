import Vue from 'vue';
import Router from 'vue-router';
import Index from "@/pages";


Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        { path: '/', redirect: '/list/request' },
        { path: '/p/:payUrl', component: Index.PayUrl },
        { path: '/list/:state', component: Index.PaymentList },
    ],
});

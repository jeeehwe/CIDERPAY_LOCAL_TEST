import Vue from 'vue';
import Router from 'vue-router';
import Index from "@/pages";


Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        { path: '/', component: Index.RequestList },
        { path: '/p/:payUrl', component: Index.PayUrl },
        { path: '/payment', component: Index.PaymentList },
    ],
});

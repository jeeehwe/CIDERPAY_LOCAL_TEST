import Vue from 'vue';
import Router from 'vue-router';
import Layout from "@/pages/Layout";
import Page1 from "@/pages/Page1";

Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/test',
            component: Layout,
            children: [
                {
                    path: 'home',
                    component: Page1
                }
            ]
        },
    ],
});
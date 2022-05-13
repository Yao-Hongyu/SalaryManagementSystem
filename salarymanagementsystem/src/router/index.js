import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/home'
    }, {
        path: '/home',
        name: 'Home',
        component: Home
    }, {
        path: '/login',
        name: 'Login',
        component: Login
    }
]

const router = new VueRouter({
    routes
})

router.beforeEach((to, from, next) => {
    if (to.path === "/login")
        return next();

    //获取token
    // console.log(from.path);
    // console.log(to.path);
    const token = window.sessionStorage.getItem("token");
    if (!token || token == "undefined" || token == "") return next("/login");

    next();
});

export default router

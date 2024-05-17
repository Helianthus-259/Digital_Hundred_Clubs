import { createApp } from 'vue'
import TDesign from 'tdesign-vue-next';
import App from './App.vue'
import router from './router'
import '../mock'
import store from './store'
import './api';

// 引入组件库的少量全局样式变量
import 'tdesign-vue-next/es/style/index.css';

const app = createApp(App)

app.use(TDesign)

app.use(router)

app.use(store)

app.mount('#app')


router.beforeEach((to, from, next) => {
    if (to.meta.requireAuth) {
        if (sessionStorage.getItem('token')) {
            next() //如果用户已经登录，则直接放行
        } else {
            next({ path: '/login' }) //如果用户未登录，则跳转到登录页面
        }
    } else {
        next() //如果不需要权限校验，直接放行
    }
})
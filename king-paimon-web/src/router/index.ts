import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
    scrollBehavior() {
        return { top: 0 }
    },
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView
        },
        {
            path: '/about',
            name: 'about',
            component: () => import('../views/AboutView.vue')
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('../views/LoginView.vue')
        },
        {
            path: '/posts',
            name: 'posts',
            component: () => import('../views/PostsView.vue')
        },
        {
            path: '/post/:id',
            name: 'post',
            component: () => import('../views/PostView.vue')
        },
        {
            path: '/weather',
            name: 'weather',
            component: () => import('../views/WeatherView.vue')
        },
        {
            path: '/admin',
            name: 'admin',
            component: () => import('../views/AdminPage.vue')
        },
        {
            path: '/:pathMatch(.*)*',
            name: 'notfound',
            component: () => import('../views/NotFound.vue')
        }
    ]
})

router.beforeEach(() => {
    window.scrollTo(0, 0)
})

export default router

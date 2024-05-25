import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')

import { useUserStore } from './stores/user'

// get data from localStorage
const userStore = useUserStore()

const logged = localStorage.getItem('logged') || false

if (logged) {
    userStore.login(
        localStorage.getItem('username') || '',
        localStorage.getItem('token') || '',
        localStorage.getItem('role') || ''
    )
} else {
    userStore.logout()
}

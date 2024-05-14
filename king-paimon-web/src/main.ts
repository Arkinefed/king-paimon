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

userStore.setUsername(localStorage.getItem('username') || '')
userStore.setToken(localStorage.getItem('token') || '')
userStore.setRole(localStorage.getItem('role') || '')

import axios from 'axios'

import { useUserStore } from '@/stores/user'

const userStore = useUserStore()

const axiosRest = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${userStore.token}`
    }
})

axiosRest.interceptors.request.use(
    (config) => {
        const userStore = useUserStore()
        const token = userStore.token

        if (token) {
            config.headers.Authorization = `Bearer ${token}`
        }
        return config
    },
    (error) => {
        return Promise.reject(error)
    }
)

export default axiosRest

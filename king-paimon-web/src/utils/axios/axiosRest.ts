import axios from 'axios'

import { useUserStore } from '@/stores/user'

const userStore = useUserStore()

const axiosRest = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + userStore.token
    }
})

export default axiosRest

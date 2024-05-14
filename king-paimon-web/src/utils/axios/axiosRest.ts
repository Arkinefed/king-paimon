import axios from 'axios'

const axiosRest = axios.create({
    baseURL: 'localhost:8080',
    headers: {
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + ''
    }
})

export default axiosRest

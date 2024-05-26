import axios from 'axios'

const axiosWeather = axios.create({
    baseURL: 'http://localhost:8081',
    headers: {
        'Content-Type': 'application/json'
    }
})

export default axiosWeather

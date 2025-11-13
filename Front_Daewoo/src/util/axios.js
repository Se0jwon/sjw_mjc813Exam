import axios from 'axios'

console.log('baseUrl : ' + process.env.VUE_APP_API_URL);

export const bTeamApi = axios.create({
    baseURL: process.env.VUE_APP_API_URL
});

bTeamApi.interceptors.request.use(
    config => {
        const token = localStorage.getItem("jwtToken")
        if (token) {
            config.headers.Authorization = `Bearer ${token}`
        }
        return config
    },
    error => Promise.reject(error)
)

export default bTeamApi
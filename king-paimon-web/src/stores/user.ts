import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', () => {
    const logged = ref(false)

    const username = ref('')
    const token = ref('')

    const role = ref('')

    const login = (newUsername: string, newToken: string, newRole: string) => {
        setLogged(true)
        setUsername(newUsername)
        setToken(newToken)
        setRole(newRole)
    }

    const logout = () => {
        setLogged(false)
        setUsername('')
        setToken('')
        setRole('')

        localStorage.clear()
    }

    const isAdmin = computed(() => role.value === 'admin')

    const setLogged = (l: boolean) => {
        logged.value = l

        localStorage.setItem('logged', l.toString())
    }

    const setUsername = (name: string) => {
        username.value = name

        localStorage.setItem('username', name)
    }

    const setToken = (t: string) => {
        token.value = t

        localStorage.setItem('token', t)
    }

    const setRole = (r: string) => {
        role.value = r

        localStorage.setItem('role', r)
    }

    return { logged, username, token, login, logout, isAdmin }
})

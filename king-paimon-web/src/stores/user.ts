import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', () => {
    const logged = ref(false)

    const username = ref('')
    const token = ref('')

    const role = ref('')

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

        localStorage.setItem('toke', t)
    }

    const setRole = (r: string) => {
        role.value = r

        localStorage.setItem('role', r)
    }

    const isAdmin = computed(() => role.value === 'admin')

    return { logged, username, token, role, setLogged, setUsername, setToken, setRole, isAdmin }
})

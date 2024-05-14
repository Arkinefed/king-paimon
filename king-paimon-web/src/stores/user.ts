import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', () => {
    const username = ref('')
    const token = ref('')

    const role = ref('')

    const setUsername = (name: string) => {
        username.value = name

        localStorage.setItem('username', name)
    }

    const setToken = (t: string) => {
        token.value = t

        localStorage.setItem('username', t)
    }

    const setRole = (r: string) => {
        role.value = r

        localStorage.setItem('username', r)
    }

    const isAdmin = computed(() => role.value === 'admin')

    return { username, token, role, setUsername, setToken, setRole, isAdmin }
})

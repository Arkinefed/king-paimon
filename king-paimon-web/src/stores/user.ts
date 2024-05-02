import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', () => {
    const username = ref('')
    const token = ref('')

    const role = ref('')

    const setUsername = (name: string) => {
        username.value = name
    }

    const setToken = (t: string) => {
        token.value = t
    }

    const setRole = (r: string) => {
        role.value = r
    }

    const isAdmin = computed(() => role.value === 'admin')

    return { username, token, role, setUsername, setToken, setRole, isAdmin }
})

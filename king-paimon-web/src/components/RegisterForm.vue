<template>
    <div class="registerForm">
        <form @submit.prevent="onSubmit">
            <div class="form-group">
                <label for="username">username</label>
                <input type="text" id="username" v-model="username" required />
            </div>
            <div class="form-group">
                <label for="email">email</label>
                <input type="email" id="email" v-model="email" required />
            </div>
            <div class="form-group">
                <label for="firstName">first name</label>
                <input type="text" id="firstName" v-model="firstName" required />
            </div>
            <div class="form-group">
                <label for="lastName">last name</label>
                <input type="text" id="lastName" v-model="lastName" required />
            </div>
            <div class="form-group">
                <label for="password">password</label>
                <input type="password" id="password" v-model="password" required />
            </div>
            <div class="form-group">
                <label for="confirmPassword">confirm password</label>
                <input type="password" id="confirmPassword" v-model="confirmPassword" required />
            </div>
            <button type="submit">register</button>
        </form>
    </div>
</template>

<script setup lang="ts">
import router from '@/router'
import { useUserStore } from '@/stores/user'
import axiosRest from '@/utils/axios/axiosRest'
import { ref } from 'vue'

const username = ref('')
const email = ref('')
const firstName = ref('')
const lastName = ref('')
const password = ref('')
const confirmPassword = ref('')

const validateInputs = () => {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
    const usernameRegex = /^[a-zA-Z0-9_]*$/
    const nameRegex = /^[a-zA-Z-]*$/

    if (!username.value || !password.value || !email.value || !firstName.value || !lastName.value) {
        return false
    }

    if (
        username.value.length > 128 ||
        password.value.length > 128 ||
        email.value.length > 128 ||
        firstName.value.length > 128 ||
        lastName.value.length > 128
    ) {
        return false
    }

    if (
        !usernameRegex.test(username.value) ||
        !emailRegex.test(email.value) ||
        !nameRegex.test(firstName.value) ||
        !nameRegex.test(lastName.value) ||
        password.value.includes(' ')
    ) {
        return false
    }

    if (password.value !== confirmPassword.value) {
        return false
    }

    return true
}

const userStore = useUserStore()

const onSubmit = () => {
    if (!validateInputs()) {
        return
    }

    const user = {
        username: username.value,
        password: password.value,
        email: email.value,
        firstName: firstName.value,
        lastName: lastName.value
    }

    axiosRest
        .post('/api/v1/auth/register', user)
        .then((result) => {
            const parts = result.data.split('.')
            const payload = JSON.parse(window.atob(parts[1]))

            userStore.login(payload.sub, result.data, payload.role)

            router.push('/')
        })
        .catch((error) => {
            console.error(error)
        })
}
</script>

<style scoped>
.registerForm {
    width: 100%;
    font-size: 1.1rem;
    margin-top: 1rem;
    margin-bottom: 1rem;
}

form * {
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}

.form-group {
    margin-bottom: 1rem;
}

label {
    display: block;
    margin-bottom: 0.5rem;
}

input {
    width: 100%;
    padding: 0.5rem;
    font-size: 1rem;
    margin-bottom: 0.5rem;
}

button {
    padding: 0.5rem 1rem;
    font-size: 1rem;
    cursor: pointer;
}
</style>

<template>
    <div class="loginForm">
        <form @submit.prevent="onSubmit">
            <div class="form-group">
                <label for="username">username</label>
                <input type="text" id="username" v-model="username" required />
            </div>
            <div class="form-group">
                <label for="password">password</label>
                <input type="password" id="password" v-model="password" required />
            </div>
            <button type="submit">login</button>
        </form>
    </div>
</template>

<script setup lang="ts">
import axiosRest from '@/utils/axios/axiosRest'
import { ref } from 'vue'

const username = ref('')
const password = ref('')

const onSubmit = () => {
    if (username.value && password.value) {
        axiosRest
            .post('/api/v1/auth/login', {
                username: username.value,
                password: password.value
            })
            .then((result) => {
                console.log(result)
            })
            .catch((error) => {
                console.log(error)
            })
    }
}
</script>

<style scoped>
.loginForm {
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

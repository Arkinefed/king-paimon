<script setup lang="ts">
import { useUserStore } from '@/stores/user'

import LoginForm from '../components/LoginForm.vue'
import RegisterForm from '../components/RegisterForm.vue'
</script>

<template>
    <div class="login">
        <template v-if="login">
            <LoginForm />

            <a class="change" @click="changeToRegister">
                <h3 class="red">zarejestruj się</h3>
            </a>
        </template>
        <template v-else>
            <RegisterForm />

            <a class="change" @click="changeToLogin">
                <h3 change="red">zaloguj się</h3>
            </a>
        </template>
    </div>
</template>

<script lang="ts">
import router from '@/router'
import { ref } from 'vue'

const userStore = useUserStore()

const login = ref(true)

export default {
    mounted() {
        login.value = true

        if (userStore.logged) {
            router.replace('/')
        }
    },
    methods: {
        changeToRegister: () => {
            login.value = false
        },
        changeToLogin: () => {
            login.value = true
        }
    }
}
</script>

<style scoped>
.change {
    cursor: pointer;
}

@media (min-width: 1024px) {
    .login {
        width: 1000px;
    }
}
</style>

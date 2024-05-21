<script setup lang="ts">
import { useUserStore } from '@/stores/user'

import LoginForm from '../components/LoginForm.vue'
</script>

<template>
    <div class="login">
        <template v-if="login">
            <LoginForm />
        </template>
        <template v-else>register form</template>
    </div>
</template>

<script lang="ts">
import router from '@/router'

const userStore = useUserStore()

export default {
    data() {
        return {
            login: true
        }
    },
    mounted() {
        if (userStore.logged) {
            router.replace('/')
        }
    }
}
</script>

<style scoped>
@media (min-width: 1024px) {
    .login {
        width: 1000px;
    }
}
</style>

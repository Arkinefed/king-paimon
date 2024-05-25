<script setup lang="ts">
import { RouterLink } from 'vue-router'
import { useUserStore } from '@/stores/user'
import router from '@/router'

const userStore = useUserStore()

const logout = () => {
    userStore.logout()
    router.push('/')
}
</script>

<template>
    <div class="account">
        <template v-if="userStore.logged">
            <h3>&lpar;{{ userStore.username }}&rpar;</h3>
            <a class="logout" @click="logout">
                <h3 class="red">wyloguj się</h3>
            </a>
        </template>
        <template v-else>
            <h3>&lpar;nie jesteś zalogowany&rpar;</h3>
            <RouterLink to="/login">
                <h3>zaloguj się</h3>
            </RouterLink>
        </template>
    </div>
</template>

<style scoped>
.account {
    display: flex;
    flex-direction: row;
    column-gap: 0.5rem;
    justify-content: end;
    padding: 1rem;
}

.logout {
    cursor: pointer;
}
</style>

<script setup lang="ts">
import { RouterLink } from 'vue-router'
</script>

<template>
    <div class="account">
        <template v-if="userStore.logged">
            <h3>&lpar;{{ userStore.username }}&rpar;</h3>
        </template>
        <template v-else>
            <h3>&lpar;nie jesteś zalogowany&rpar;</h3>
        </template>

        <RouterLink to="/login">
            <h3>zaloguj się</h3>
        </RouterLink>
    </div>
</template>

<script lang="ts">
import { useUserStore } from '@/stores/user'

export default {
    data() {
        return {
            userStore: useUserStore()
        }
    }
}
</script>

<style scoped>
.account {
    display: flex;
    flex-direction: row;
    column-gap: 1rem;
    justify-content: end;
    padding: 1rem;
}
</style>

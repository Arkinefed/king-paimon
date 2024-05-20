<script setup lang="ts">
import { useUserStore } from '@/stores/user'

const userStore = useUserStore()
</script>

<template>
    <div class="login">
        <template v-if="userStore.logged"> </template>
        <template v-else> </template>
    </div>
</template>

<style scoped>
@media (min-width: 1024px) {
    .login {
        width: 1000px;
    }
}
</style>

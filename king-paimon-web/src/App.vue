<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
    <header>
        <RouterLink to="/">
            <img alt="King Paimon logo" class="logo" src="@/assets/Paimon.jpg" />

            <h1>King Paimon</h1>
        </RouterLink>
    </header>

    <RouterView />

    <footer>
        <h3 class="red">
            &copy; 2024
            <a href="https://open.spotify.com/artist/6nHoVYudIbrQ8Kz1oMudtQ" target="_blank">
                Arkinefed
            </a>
        </h3>
    </footer>
</template>

<style scoped>
header {
    line-height: 1.5;
    max-height: 100vh;
}

header h1 {
    font-weight: 500;
    font-size: 2.6rem;
}

.logo {
    width: auto;
    height: 250px;
}

a {
    padding: 2rem 0 0 0;
}

h3 {
    font-size: 1.5rem;
    font-weight: 500;
    text-align: center;
    padding: 1rem;
}

@media (min-width: 1024px) {
    header {
        display: flex;
        flex-direction: column;
        place-items: center;
    }

    header h1 {
        font-size: 2.7rem;
    }
}
</style>

<script setup lang="ts">
import { RouterLink } from 'vue-router'
import HomeItem from './HomeItem.vue'
</script>

<template>
    <RouterLink to="/">
        <HomeItem>
            <template #image>
                <img src="@/assets/Paimon.jpg" alt="King Paimon" />
            </template>
            <template #heading>Home</template>
            <template #description>
                Main page. Welcome to the King Paimon website. Music, art, and more.
            </template>
        </HomeItem>
    </RouterLink>

    <RouterLink to="/">
        <HomeItem>
            <template #image>
                <img src="@/assets/Paimon.jpg" alt="King Paimon" />
            </template>
            <template #heading>Posts</template>
            <template #description>
                Posts about music. King Paimon gave me this music. I am grateful.
            </template>
        </HomeItem>
    </RouterLink>

    <RouterLink to="/about">
        <HomeItem>
            <template #image>
                <img src="@/assets/Paimon.jpg" alt="King Paimon" />
            </template>
            <template #heading>About</template>
            <template #description>
                King Paimon is a demon described in the Lesser Key of Solomon and the Ars Goetia.
            </template>
        </HomeItem>
    </RouterLink>
</template>

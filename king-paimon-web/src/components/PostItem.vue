<template>
    <RouterLink :to="`/post/${id}`">
        <div class="post-item">
            <h1>{{ title }}</h1>
            <h2>{{ author }}</h2>
            <p class="date">{{ `${whenAdded[0]}/${whenAdded[1]}/${whenAdded[2]}` }}</p>
        </div>
    </RouterLink>
</template>

<script setup lang="ts">
interface PostProps {
    id: number
    author: string
    title: string
    content: string
    whenAdded: Array<number>
}

defineProps<PostProps>()
</script>

<style scoped>
.post-item {
    display: flex;
    flex-direction: column;
    padding: 1rem;
    border: 1px solid var(--color-text);
    margin-top: 1rem;
}

h2 {
    font-size: 1.5rem;
    font-weight: 500;
    margin: 0.5rem 0;
}

p {
    font-size: 1rem;
    color: var(--color-text);
    margin: 0.5rem 0;
}

.date {
    width: 100%;
    text-align: right;
}

@media (min-width: 1024px) {
    .post-item {
        flex-direction: column;
        width: 1000px;
        align-items: flex-start;
    }

    h2,
    p {
        margin: 0 1rem 0 0;
    }
}
</style>

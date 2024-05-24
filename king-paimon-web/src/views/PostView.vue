<template>
    <div class="post">
        <template v-if="post != null">
            <div class="post-content">
                <h1>{{ post.title }}</h1>
                <h2>{{ post.author }}</h2>
                <p>{{ post.content }}</p>
                <p class="date">
                    {{ `${post.whenAdded[0]}/${post.whenAdded[1]}/${post.whenAdded[2]}` }}
                </p>
            </div>
        </template>
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import axiosRest from '@/utils/axios/axiosRest'
import { useRoute } from 'vue-router'

interface Post {
    id: number
    author: string
    title: string
    content: string
    whenAdded: Array<number>
}

const post = ref<Post | null>(null)
const route = useRoute()

const getPost = () => {
    axiosRest
        .get(`/api/v1/public/post/${route.params.id}`)
        .then((result) => {
            post.value = result.data
        })
        .catch((error) => {
            console.log(error)
        })
}

onMounted(() => {
    getPost()
})
</script>

<style scoped>
.post {
    display: flex;
    flex-direction: column;
    padding: 1rem;
    margin-top: 1rem;
}

.post-content {
    display: flex;
    flex-direction: column;
    padding: 1rem;
    margin-top: 1rem;
}

h1 {
    font-size: 2rem;
    font-weight: bold;
    margin: 0.5rem 0;
}

h2 {
    font-size: 1.5rem;
    font-weight: 500;
    margin: 0.5rem 0;
}

p {
    font-size: 1.2rem;
    color: var(--color-text);
    margin: 0.5rem 0;
}

.date {
    width: 100%;
    text-align: right;
    font-style: italic;
    color: gray;
}

@media (min-width: 1024px) {
    .post {
        width: 1000px;
    }

    .post-content {
        width: 1000px;
        align-items: flex-start;
    }

    h1,
    h2,
    p {
        margin: 0 1rem 0 0;
    }
}
</style>

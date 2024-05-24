<template>
    <div class="posts">
        <template v-if="posts.length != 0">
            <PostItem
                v-for="post in posts"
                :key="post.id"
                :id="post.id"
                :author="post.author"
                :title="post.title"
                :content="post.content"
                :whenAdded="post.whenAdded"
            />
        </template>
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import PostItem from '../components/PostItem.vue'
import axiosRest from '@/utils/axios/axiosRest'

interface Post {
    id: number
    author: string
    title: string
    content: string
    whenAdded: Array<number>
}

const posts = ref<Post[]>([])

onMounted(() => {
    getPosts()
})

const getPosts = () => {
    axiosRest
        .get('/api/v1/public/post/all')
        .then((result) => {
            posts.value = result.data
        })
        .catch((error) => {
            console.log(error)
        })
}
</script>

<style scoped>
.change {
    cursor: pointer;
}

@media (min-width: 1024px) {
    .posts {
        width: 1000px;
    }
}
</style>

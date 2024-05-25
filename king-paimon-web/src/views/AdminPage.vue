<template>
    <div class="admin">
        <h1 class="red">admin page</h1>

        <div class="post">
            <h1 class="red">post management</h1>

            <form @submit.prevent="onSubmit">
                <div class="form-group">
                    <label for="title">title</label>
                    <input type="text" id="title" v-model="title" required />
                </div>
                <div class="form-group">
                    <label for="content">content</label>
                    <input type="text" id="content" v-model="content" required />
                </div>
                <button type="submit">add post</button>
            </form>
        </div>
    </div>
</template>

<script setup lang="ts">
import axiosRest from '@/utils/axios/axiosRest'
import { ref } from 'vue'

const title = ref('')
const content = ref('')

const onSubmit = () => {
    if (title.value && content.value) {
        axiosRest
            .put('/api/v1/admin/post/add', {
                title: title.value,
                content: content.value
            })
            .then((result) => {
                title.value = ''
                content.value = ''

                console.log(result)
            })
            .catch((error) => {
                console.log(error)
            })
    }
}
</script>

<style scoped>
.post {
    width: 100%;
    font-size: 1.1rem;
    margin-top: 1rem;
    margin-bottom: 1rem;
}

form * {
    font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}

.form-group {
    margin-bottom: 1rem;
}

label {
    display: block;
    margin-bottom: 0.5rem;
}

input {
    width: 100%;
    padding: 0.5rem;
    font-size: 1rem;
    margin-bottom: 0.5rem;
}

button {
    padding: 0.5rem 1rem;
    font-size: 1rem;
    cursor: pointer;
}

@media (min-width: 1024px) {
    .admin {
        text-align: justify;
        width: 1000px;
    }
}
</style>

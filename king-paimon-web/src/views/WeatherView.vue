<template>
    <div class="weather">
        <h1 class="red">jest pogoda: {{ weather }}</h1>
    </div>
</template>

<script setup lang="ts">
import axiosWeather from '@/utils/axios/axiosWeather'
import { onMounted, ref } from 'vue'

const weather = ref('none')

onMounted(() => {
    getWeather()
})

const getWeather = () => {
    axiosWeather
        .get('/api/v1/weather/lodz')
        .then((result) => {
            weather.value = result.data
        })
        .catch((error) => {
            console.log(error)
        })
}
</script>

<style scoped>
@media (min-width: 1024px) {
    .weather {
        text-align: justify;
        width: 1000px;
    }
}
</style>

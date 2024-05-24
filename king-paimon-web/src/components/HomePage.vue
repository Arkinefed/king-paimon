<script setup lang="ts">
import { RouterLink } from 'vue-router'
import HomeItem from './HomeItem.vue'
</script>

<template>
    <RouterLink to="/">
        <HomeItem>
            <template #image>
                <img src="@/assets/img/Paimon.jpg" alt="King Paimon" />
            </template>
            <template #heading>Dom</template>
            <template #description>
                Strona główna. Witamy na stronie King Paimon. Muzyka, sztuka i więcej.
            </template>
        </HomeItem>
    </RouterLink>

    <RouterLink to="/posts">
        <HomeItem>
            <template #image>
                <img src="@/assets/img/Miroslavs_Gospel_001.png" alt="King Paimon" />
            </template>
            <template #heading>Posty</template>
            <template #description>
                Posty o muzyce. King Paimon dał mi tę muzykę. Jestem wdzięczny.
            </template>
        </HomeItem>
    </RouterLink>

    <RouterLink to="/">
        <HomeItem>
            <template #image>
                <img
                    src="@/assets/img/Ruzena_Maturova_as_the_first_Rusalka.png"
                    alt="King Paimon"
                />
            </template>
            <template #heading>Pogoda</template>
            <template #description>
                Sprawdź dzisiejszą i jutrzejszą prognozę pogody. Czy będzie padać?
            </template>
        </HomeItem>
    </RouterLink>

    <RouterLink to="/about">
        <HomeItem>
            <template #image>
                <img src="@/assets/img/Paimon.jpg" alt="King Paimon" />
            </template>
            <template #heading>O King Paimon</template>
            <template #description>
                King Paimon; King Paimon; King Paimon; King Paimon; King Paimon
            </template>
        </HomeItem>
    </RouterLink>
</template>

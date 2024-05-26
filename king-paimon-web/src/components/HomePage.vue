<script setup lang="ts">
import { RouterLink } from 'vue-router'
import HomeItem from './HomeItem.vue'
import { useUserStore } from '@/stores/user'

const userStore = useUserStore()
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
                <img src="@/assets/img/Miroslavs_Gospel_001.png" alt="Miroslavs Gospel" />
            </template>
            <template #heading>Posty</template>
            <template #description>
                Posty o muzyce. King Paimon dał mi tę muzykę. Jestem wdzięczny.
            </template>
        </HomeItem>
    </RouterLink>

    <RouterLink to="/weather">
        <HomeItem>
            <template #image>
                <img
                    src="@/assets/img/Ruzena_Maturova_as_the_first_Rusalka.png"
                    alt="Ruzena Maturova"
                />
            </template>
            <template #heading>Pogoda</template>
            <template #description>
                Sprawdź dzisiejszą i jutrzejszą prognozę pogody. Czy będzie padać?
            </template>
        </HomeItem>
    </RouterLink>

    <template v-if="userStore.isAdmin">
        <RouterLink to="/admin">
            <HomeItem>
                <template #image>
                    <img src="@/assets/img/Dollarnote_siegel_hq.png" alt="Dollarnote" />
                </template>
                <template #heading>Admin</template>
                <template #description> Σ Σ Σ Σ Σ </template>
            </HomeItem>
        </RouterLink>
    </template>

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

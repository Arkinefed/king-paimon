<template>
    <div class="item">
        <slot name="image"></slot>
        <div class="details">
            <h3 class="red">
                <slot name="heading"></slot>
            </h3>
            <p>
                <slot name="description"></slot>
            </p>
        </div>
    </div>
</template>

<style scoped>
.item {
    display: flex;
    align-items: center;
    flex-direction: column;
    padding: 1rem;
    border: 1px solid var(--color-text);
    margin-top: 1rem;
}

.item::v-deep(img) {
    max-width: 50%;
    width: auto;
    height: auto;
}

.details {
    margin: 0rem;
}

h3 {
    font-size: 2rem;
    font-weight: 500;
}

p {
    font-size: 1.2rem;
    color: var(--color-text);
    width: 100%;
}

@media (min-width: 1024px) {
    .item {
        flex-direction: row;
        width: 1000px;
    }

    .details {
        margin: 0rem 1rem;
        display: flex;
        height: 100%;
        flex-direction: column;
        justify-content: flex-start;
    }
}
</style>

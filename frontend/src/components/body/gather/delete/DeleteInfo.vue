<template>
    <article class="gather-info">
        <div class="gather-logo">
            <b-img :src="gatherImg" alt="모임 이미지" width="100"></b-img>
        </div>
        <div class="gather-name">
            <h3 class="gather-name">모임 이름</h3>
            <h4>{{ gatherName }}</h4>
        </div>
    </article>
</template>

<script setup>
    defineProps({
        gatherImg: 
        {
            type: String,
            default: ''
        },
        gatherName: 
        {
            type: String,
            required: true
        }
});
</script>

<style scoped>
    .gather-info {
        display: flex;
        align-items: center;
        gap: 2rem;
        margin-bottom: 2rem;
    }
    .gather-logo {
        flex-shrink: 0;
    }
    .gather-name {
        flex-grow: 1;
    }
    .gather-name h3 {
        font-size: 1.2rem;
        color: #666;
        margin-bottom: 1rem;
    }
    .gather-name h4 {
        font-size: 1.5rem;
        padding: 0.5rem;
        background-color: #FFFED3;
        display: inline-block;
    }
</style>
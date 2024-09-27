<template>
    <article class="gather-info">
        <div class="gather-logo">
            <b-img :src="gatherImg" alt="모임 이미지" width="100"></b-img>
        </div>
        <div class="gather-name">
            <h3 class="gather-name">모임 이름</h3>
            <b-form-textarea
            id="contentsInput"
            v-model="name"
            placeholder="모임 명을 작성하세요."
            rows="3"
            max-rows="6"
            class="contents"
        ></b-form-textarea>
        </div>
    </article>
</template>

<script setup>
    import { ref, watch } from 'vue';

    const { gatherImg, gatherName } = defineProps({
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

    const name = ref(gatherName);

    watch(
        () => gatherName,
        (newValue) => {
            name.value = newValue;
        }
    );
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
</style>
<template>
    <div class="btn">
        <b-button @click="createGather" type="submit" variant="primary">모임 생성</b-button>
    </div>
</template>

<script setup>
    import { useRouter } from 'vue-router';

    const router = useRouter();

    const createGather = () => {
        router.push('/gatherlist')
    }

</script>

<style scoped>
    .btn {
        display: flex;
        justify-content: right;
        margin-top: 1rem;
    }
</style>
<template>
    <div class="gatherbtn">
        <b-button size="lg" variant="outline-primary">나의 모임 조회</b-button>
        <b-button @click="gatherCreated" size="lg" variant="outline-primary">모임 생성</b-button>
    </div>
</template>

<script setup>
    import { useRouter } from 'vue-router';

    const router = useRouter();

    const gatherCreated = () => {
        router.push('/gathercreate');
    }

</script>

<style scoped>
    .gatherbtn{
        display: flex;
        justify-content: end;
        margin-right: 3rem;
        padding-bottom: 1rem;
        gap: 1rem;
    }
</style>
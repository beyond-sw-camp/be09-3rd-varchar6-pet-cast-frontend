<template>
    <div class="gatherbtn">
        <b-button v-if="select" @click="wSelect" size="lg" variant="outline-primary">나의 모임 조회</b-button>
        <b-button v-if="!select" @click="wSelect" size="lg" variant="outline-primary">전체 모임 조회</b-button>
        <b-button @click="gatherCreated" size="lg" variant="outline-primary">모임 생성</b-button>
    </div>
</template>

<script setup>
    import { useRouter } from 'vue-router';
    import { ref } from 'vue';

    const router = useRouter();
    const select = ref(true);

    const emit = defineEmits(['update-select']);

    const gatherCreated = () => {
        router.push('/gathercreate');
    }
    
    const wSelect = () => {
        select.value = !select.value;
        emit('update-select', select.value);
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
<template>
    <div class="btn">
        <b-button @click="modifyGather" type="submit" variant="primary">수정 완료</b-button>
    </div>
</template>

<script setup>
    import { useRouter } from 'vue-router';

    const router = useRouter();

    const props = defineProps({
        gatherId:
        {
            type: Number,
            required: true
        }
    });

    const modifyGather = () => {
        router.push(`/gatherdetail/${props.gatherId}`)
    }
</script>

<style scoped>
    .btn {
        display: flex;
        justify-content: right;
        margin-top: 1rem;
    }
</style>
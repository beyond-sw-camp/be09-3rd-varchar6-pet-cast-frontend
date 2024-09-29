<template>
    <div class="btn">
        <b-button @click="deleteGather" type="submit" variant="primary">추방 완료</b-button>    
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

    const deleteGather = () => {
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
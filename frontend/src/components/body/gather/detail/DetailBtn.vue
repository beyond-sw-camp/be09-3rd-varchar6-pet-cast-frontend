<template>
    <div class="btn">
        <b-button v-if="props.show" @click="deleteGather" type="submit" variant="primary">추방 하기</b-button>
        <b-button v-if="props.show" @click="modifyGather" type="submit" variant="primary">수정 하기</b-button>
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
        },
        show:
        {
            type:Boolean,
            required: true
        }
    });


    const modifyGather = () => {
        router.push(`/gathermodify/${props.gatherId}`)
    }
    const deleteGather = () => {
        router.push('/gatherdelete')
    }
</script>

<style scoped>
    .btn {
        display: flex;
        justify-content: right;
        margin-top: 1rem;
        gap: 2rem;
    }
</style>
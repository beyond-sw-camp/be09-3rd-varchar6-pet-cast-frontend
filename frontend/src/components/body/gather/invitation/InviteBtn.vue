<template>
    <div class="btn">
        <b-button @click="invitation" type="submit" variant="primary">초대장 전송</b-button>
    </div>
</template>

<script setup>
    import { useRouter } from 'vue-router';

    const router = useRouter();
    const invitation = () => {    
        window.close();
    }
</script>

<style scoped>
    .btn {
        display: flex;
        justify-content: right;
        margin-top: 1rem;
    }
</style>
<template>
    <div class="dated">
        <b-form-group label="생성 일자" label-for="createdAtInput">
            <p id="createdAtInput">{{ formattedTime }}</p>
        </b-form-group>
    </div>
</template>

<script setup>
    import { ref, onMounted, onUnmounted } from 'vue';

    const currentTime = ref(new Date());

    const formatTime = (date) => {
        const year = date.getFullYear();
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const day = date.getDate().toString().padStart(2, '0');
        const hours = date.getHours().toString().padStart(2, '0');
        const minutes = date.getMinutes().toString().padStart(2, '0');
        const seconds = date.getSeconds().toString().padStart(2, '0');
        return `${year}년 ${month}월 ${day}일 ${hours}:${minutes}:${seconds}`;
    };

    const formattedTime = ref(formatTime(currentTime.value));

    let timer;


    onMounted(() => {
        timer = setInterval(() => {
            currentTime.value = new Date();
            formattedTime.value = formatTime(currentTime.value);
        }, 1000);
    });

    onUnmounted(() => {
        clearInterval(timer);
    });
</script>

<style scoped>
    .dated {
        display: flex;
        justify-content: flex-end;
        margin: 10px 0;
    }

    .dated h6 {
        margin: 0;
        font-size: 0.9rem;
        color: #333;
    }
</style>